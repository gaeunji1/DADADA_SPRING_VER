package com.ktdsuniversity.proj.dadada.chat.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.proj.dadada.chat.dao.ChatRoomDAO;
import com.ktdsuniversity.proj.dadada.chat.dao.ParticipantDAO;
import com.ktdsuniversity.proj.dadada.chat.domain.ChatRoom;
import com.ktdsuniversity.proj.dadada.chat.domain.Participant;
import com.ktdsuniversity.proj.dadada.chat.service.RoomService;
import com.ktdsuniversity.proj.dadada.chat.vo.ChatRoomVO;
import com.ktdsuniversity.proj.dadada.chat.vo.MessageVO;
import com.ktdsuniversity.proj.dadada.chat.vo.ParticipantRankVO;
import com.ktdsuniversity.proj.dadada.chat.vo.ParticipantVO;
import com.ktdsuniversity.proj.dadada.chat.vo.RoomResultVO;
import com.ktdsuniversity.proj.dadada.chat.vo.RoomStatusVO;
import com.ktdsuniversity.proj.dadada.member.service.MemberService;
import com.ktdsuniversity.proj.dadada.member.vo.BenefitCountVO;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    private final SimpMessagingTemplate broker;
    private final ChatRoomDAO chatRoomDAO;
    private final ParticipantDAO participantDAO;
    private final ScheduledExecutorService ses = Executors.newScheduledThreadPool(4);
    private final ConcurrentMap<Integer, ChatRoom> rooms = new ConcurrentHashMap<>();
    private final MemberService memberService;
    
    public RoomServiceImpl(
        SimpMessagingTemplate broker,
        ChatRoomDAO chatRoomDAO,
        ParticipantDAO participantDAO,
        MemberService memberService
    ) {
        this.broker = broker;
        this.chatRoomDAO = chatRoomDAO;
        this.participantDAO = participantDAO;
        this.memberService = memberService;
    }

    @Override
    public ChatRoomVO createRoom(int productId) {
        ChatRoomVO vo = new ChatRoomVO();
        vo.setPrdId(productId);
        vo.setEvntRmStat("active");
        vo.setEvntRmMx(ChatRoom.MAX_PEOPLE);
        LocalDateTime now = LocalDateTime.now();
        vo.setEvntRmDt(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        vo.setEvntRmEndt(
            now.plusNanos(ChatRoom.LIMIT_MILLIS * 1_000_000)
               .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        chatRoomDAO.insertRoom(vo);

        int roomId = vo.getEvntRmId();
        ChatRoom room = new ChatRoom(roomId, productId);
        rooms.put(roomId, room);

        broker.convertAndSend(
            "/topic/room/" + roomId,
            new RoomStatusVO(
                String.valueOf(roomId),
                "WAITING",
                room.getCurrentCount(),
                ChatRoom.LIMIT_MILLIS,
                List.of()
            )
        );

        return vo;
    }

    @Override
    public boolean enter(int roomId, Participant p) {
        ChatRoom room = rooms.get(roomId);
        if (room == null) {
            logger.warn("enter(): room {} not found", roomId);
            return false;
        }
        boolean joined = room.join(p);
        if (joined) {
//            ChatRoomParticipantVO participantVO =
//                new ChatRoomParticipantVO(
//                    Integer.valueOf(roomId),
//                    p.getUserId(),
//                    p.getNick()
//                );
//            chatRoomDAO.insertParticipant(participantVO);

            pushStatus(room);
            if (room.getCurrentCount() == ChatRoom.MIN_START) {
                startTicker(roomId);

                long delay = room.getEndAt() - System.currentTimeMillis();
                ses.schedule(() -> forceFinish(roomId),
                             delay > 0 ? delay : 0,
                             TimeUnit.MILLISECONDS);
        }
        }
        return joined;
    }

    @Override
    public void submit(MessageVO msg) {
    	int roomId = Integer.parseInt(msg.getRoomId());
        ChatRoom room = rooms.get(roomId);
        if (room != null) {
        	room.submitNumber(msg.getUserId(), msg.getNumber());

            if (room.getPeople().values().stream().allMatch(Participant::isSubmitted)) {
                RoomResultVO result = room.finish();      // ✅ 강제 종료
                announceResult(result);                   // ✅ 브로드캐스트
            }
        }
        ParticipantVO vo = new ParticipantVO();
        vo.setMbrId(msg.getUserId());
        vo.setEvntRmId(roomId);
        vo.setPtcptCnt(msg.getNumber());
        vo.setPtcptRnk(0);
        participantDAO.insertParticipant(vo);

    }

    @Override
    public void updateParticipantRank(int eventRoomId, int memberId, int rank) {
        ParticipantRankVO rankVo = new ParticipantRankVO(eventRoomId, memberId, rank);
        participantDAO.updateParticipantRank(rankVo);
    }

    @Override
    public ChatRoom getChatRoom(int roomId) {
        return rooms.get(roomId);
    }

    private void pushStatus(ChatRoom room) {
        String status = room.isActive()
            ? "ACTIVE"
            : (room.getCurrentCount() >= ChatRoom.MIN_START ? "CLOSING" : "WAITING");
        long remain = room.isActive()
            ? Math.max(0, room.getEndAt() - System.currentTimeMillis())
            : ChatRoom.LIMIT_MILLIS;
        broker.convertAndSend(
            "/topic/room/" + room.getRoomId(),
            new RoomStatusVO(
                String.valueOf(room.getRoomId()),
                status,
                room.getCurrentCount(),
                remain,
                room.getParticipantNicknames()
            )
        );
    }

    private void startTicker(int roomId) {
        ses.scheduleAtFixedRate(() -> {
            ChatRoom r = rooms.get(roomId);
            if (r != null && r.isActive()) {
                pushStatus(r);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void forceFinish(int roomId) {
        ChatRoom room = rooms.get(roomId);
        if (room != null && room.isActive()) {
            announceResult(room);
            pushStatus(room);
        }
    }
    
    private void announceResult(RoomResultVO result) {
        broker.convertAndSend("/topic/room/" + result.getRoomId(), result);

        if (result.getWinnerId() > 0) {
            BenefitCountVO benefitCountVO = new BenefitCountVO(result.getWinnerId(), 10);
            memberService.addBenefitCount(benefitCountVO);
        }
        rooms.remove(result.getRoomId());
    }
    
    private void announceResult(ChatRoom room) {
        // 1) 참가자 제출 목록을 점수 내림차순으로 정렬
        List<Participant> submitted = room.getPeople().values().stream()
            .filter(Participant::isSubmitted)
            .sorted((a, b) -> Integer.compare(b.getNumber(), a.getNumber()))
            .collect(Collectors.toList());

        // 2) 정렬된 순서대로 DB에 순위 업데이트
        for (int i = 0; i < submitted.size(); i++) {
            Participant p = submitted.get(i);
            int rank = i + 1;
            participantDAO.updateParticipantRank(
                new ParticipantRankVO(room.getRoomId(), p.getUserId(), rank)
            );
        }

        // 3) 페이로드 생성에 필요한 데이터 준비
        int winnerId = submitted.isEmpty() ? 0 : submitted.get(0).getUserId();
        String winnerNick = submitted.isEmpty() ? null : submitted.get(0).getNick();
        int winningNumber = submitted.isEmpty() ? 0 : submitted.get(0).getNumber();

        Map<String, Integer> subsByNick = room.getPeople().values().stream()
            .filter(Participant::isSubmitted)
            .collect(Collectors.toMap(
                Participant::getNick,
                Participant::getNumber
            ));

        String payUrl = "/pay?prdId=" + room.getProductId()
                      + "&qty="   + winningNumber;

        RoomResultVO result = new RoomResultVO(
            room.getRoomId(),
            winnerId,
            winnerNick,
            winningNumber,
            payUrl,
            subsByNick
        );

        // 4) 결과 브로드캐스트 및 혜택 추가
        broker.convertAndSend("/topic/room/" + room.getRoomId(), result);
        if (winnerId > 0) {
            BenefitCountVO benefitCountVO = new BenefitCountVO(winnerId, 10);
            memberService.addBenefitCount(benefitCountVO);
        }
        rooms.remove(room.getRoomId());
    }


	@Override
	public ChatRoom findAvailableRoom(int productId) {
		return rooms.values()
				     .stream()
				    .filter(r -> r.getProductId() == productId)
				    .filter(r -> r.getCurrentCount() < r.getMaxPeople())
				    .findFirst()
				    .orElse(null);
	}
}
