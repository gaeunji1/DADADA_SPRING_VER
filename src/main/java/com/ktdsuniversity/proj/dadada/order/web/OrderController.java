package com.ktdsuniversity.proj.dadada.order.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.proj.dadada.cart.service.CartService;
import com.ktdsuniversity.proj.dadada.cart.vo.CartVO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	private CartService cartService;

	@GetMapping("/order")
	public String showOrderPage(@RequestParam(value = "totalPrice", required = false) Integer totalPrice,
	                             HttpSession session,
	                             Model model) {

	    // 총 금액 처리
	    if (totalPrice != null) {
	        model.addAttribute("totalPrice", totalPrice);
	    } else {
	        model.addAttribute("totalPrice", 0);
	    }

	    // 주문자 정보 처리
	    MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	    if (loginUser != null) {
	        model.addAttribute("loginUser", loginUser);
	    }
	    // 장바구니 정보 처리
	    int mbrId = loginUser.getMbrId();
	    int cartId = cartService.findCartIdByMbrId(mbrId);
	    List<CartVO> cartList = cartService.selectCartProductsByCartId(cartId);
	    model.addAttribute("cartList", cartList);

	    return "order/order";
	}

}
