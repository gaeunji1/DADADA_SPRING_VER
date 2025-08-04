package com.ktdsuniversity.proj.dadada.chat.web;

import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ktdsuniversity.proj.dadada.chat.domain.ChatRoom;
import com.ktdsuniversity.proj.dadada.chat.domain.Participant;
import com.ktdsuniversity.proj.dadada.chat.service.RoomService;
import com.ktdsuniversity.proj.dadada.chat.vo.ChatRoomVO;
import com.ktdsuniversity.proj.dadada.chat.vo.EnterMessageVO;
import com.ktdsuniversity.proj.dadada.chat.vo.MessageVO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;
import com.ktdsuniversity.proj.dadada.product.service.ProductService;
import com.ktdsuniversity.proj.dadada.product.vo.ProductVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/room")
public class RoomController {

    private final RoomService    roomService;
    private final ProductService productService;

    public RoomController(RoomService roomService,
                          ProductService productService,
                          SimpMessagingTemplate broker) {
        this.roomService    = roomService;
        this.productService = productService;
    }

    @GetMapping("/new")
    public String createAndGo(@RequestParam int productId,
                              RedirectAttributes ra) {

        ChatRoom available = roomService.findAvailableRoom(productId);
        int roomId;

        if (available != null) {
            if (available.isActive()) {
                ra.addFlashAttribute("error", "이미 해당 상품에 대한 경쟁이 진행중입니다. 잠시만 기다려주세요.");
                return "redirect:/product/view/" + productId;
            }
            roomId = available.getRoomId();
        } else {
            ChatRoomVO vo = roomService.createRoom(productId);
            if (vo.getEvntRmId() <= 0) {
                ra.addFlashAttribute("error", "방 생성 실패");
                return "redirect:/product/list";
            }
            roomId = vo.getEvntRmId();
        }

        return "redirect:/room/" + roomId;
    }

    @GetMapping("/enter")
    @ResponseBody
    public Map<String,Object> enterRoom(@RequestParam int roomId,
                                        @RequestParam int userId,
                                        @RequestParam String nick){
        boolean ok = roomService.enter(roomId, new Participant(userId, nick));
        return Map.of("joined", ok);
    }

    @GetMapping("/{roomId}")
    public String chatRoom(@PathVariable int roomId,
                           HttpSession session,
                           Model model){

        MemberVO user = (MemberVO) session.getAttribute("loginUser");
        if (user == null) return "redirect:/member/login";

        int    userId = user.getMbrId();
        String nick   = user.getMbrNcknm();

        roomService.enter(roomId, new Participant(userId, nick));

        model.addAttribute("roomId", roomId);
        model.addAttribute("userId", userId);
        model.addAttribute("nick",   nick);

        ChatRoom chatRoom   = roomService.getChatRoom(roomId);
        ProductVO product   = productService.getProductById(chatRoom.getProductId());
        model.addAttribute("product", product);

        return "room/chatRoom";
    }

    @MessageMapping("/room/submit")
    public void wsSubmit(MessageVO msg){
        roomService.submit(msg);
    }
    
    @MessageMapping("/submit")
    public void wsSubmitShortcut(MessageVO msg){
        roomService.submit(msg);
    }

    /** 실시간 입장 */
    @MessageMapping("/room/enter")
    public void wsEnter(EnterMessageVO msg){
        Participant p = new Participant(msg.getUserId(), msg.getNickname());
        roomService.enter(msg.getRoomId(), p);
    }
}