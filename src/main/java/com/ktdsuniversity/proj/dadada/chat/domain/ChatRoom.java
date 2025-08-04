package com.ktdsuniversity.proj.dadada.chat.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.ktdsuniversity.proj.dadada.chat.vo.RoomResultVO;

public class ChatRoom {
    public static final int MIN_START    = 6;
    public static final int MAX_PEOPLE   = 6;
    public static final long LIMIT_MILLIS = 5 * 60 * 1000;

    private final int roomId;
    private final int productId;
    private final Map<Integer, Participant> people = new ConcurrentHashMap<>();
    private volatile boolean active = false;
    private volatile long endAt = 0;

    public ChatRoom(int roomId, int productId) {
        this.roomId    = roomId;
        this.productId = productId;
    }

    public synchronized boolean join(Participant p) {
        if (people.size() >= MAX_PEOPLE || active) return false;
        people.put(p.getUserId(), p);
        if (people.size() >= MIN_START && !active) startCountDown();
        return true;
    }

    public void submitNumber(int userId, int number) {
        Participant p = people.get(userId);
        if (p != null && !p.isSubmitted()) {
            p.setNumber(number);
            p.setSubmitted(true);
			if (allSubmitted()) finish();
        }
    }

    private void startCountDown() {
        active = true;
        endAt = System.currentTimeMillis() + LIMIT_MILLIS;
    }

    private boolean allSubmitted() {
        return people.values().stream().allMatch(Participant::isSubmitted);
    }

    public RoomResultVO finish() {
        active = false;
        Map<String, Integer> subsMap = people.values().stream()
            .filter(Participant::isSubmitted)
            .collect(Collectors.toMap(
                Participant::getNick,
                Participant::getNumber
            ));
        Optional<Participant> topOpt = people.values().stream()
            .filter(Participant::isSubmitted)
            .max(Comparator.comparingInt(Participant::getNumber));
        int winnerId = 0;
        String winnerNick = null;
        int winningNumber = 0;
        if (topOpt.isPresent()) {
            Participant top = topOpt.get();
            winnerId       = top.getUserId();
            winnerNick     = top.getNick();
            winningNumber  = top.getNumber();
        }
        String payUrl = "/order/checkout?productId=" + productId;
        return new RoomResultVO(
            roomId,
            winnerId,
            winnerNick,
            winningNumber,
            payUrl,
            subsMap
        );
    }

    public Map<Integer, Participant> getPeople() {
        return Collections.unmodifiableMap(people);
    }
    public int getCurrentCount() { return people.size(); }
    public int getMaxPeople() {
    	return MAX_PEOPLE;
    }
    public List<String> getParticipantNicknames() {
        return people.values().stream()
                     .map(Participant::getNick)
                     .collect(Collectors.toList());
        
        
    }
    public int getRoomId() { 
    	return roomId;
    }
    public int getProductId() {
    	return productId;
    }
    public boolean isActive() {
    	return System.currentTimeMillis() < this.getEndAt(); 
    }
    public long getEndAt() {
    	return endAt;    
    }
}
