package com.ktdsuniversity.proj.dadada.payment.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;
import com.ktdsuniversity.proj.dadada.order.service.OrderService;
import com.ktdsuniversity.proj.dadada.payment.service.PaymentService;
import com.ktdsuniversity.proj.dadada.payment.vo.PaymentVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired private OrderService   orderService;
    @Autowired private PaymentService paymentService;

    @PostMapping("/verify")
    @ResponseBody
    public ResponseEntity<String> verify(@RequestBody Map<String,String> req) {

        String impUid = req.get("imp_uid");
        Map<String,Object> verified = paymentService.verifyPayment(impUid);

        if (!"paid".equals(verified.get("status"))) {
            return ResponseEntity.badRequest().body("상태가 paid 아님");
        }
        return ResponseEntity.ok("검증 완료");
    }

    @PostMapping("/success")
    @ResponseBody
    public ResponseEntity<String> saveData(@RequestBody PaymentVO vo,
                                           HttpSession session) {

        MemberVO user = (MemberVO) session.getAttribute("loginUser");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("로그인이 필요합니다.");
        }

        orderService.saveOrder(user.getMbrId(), vo);
        return ResponseEntity.ok("DB 저장 완료");
    }

    @GetMapping("/result")
    public String showPaymentResult(@RequestParam("imp_uid") String impUid,
                                    Model model) {

        PaymentVO info = paymentService.getPaymentByImpUid(impUid);
        model.addAttribute("info", info);
        return "payment/payment-result";
    }
}