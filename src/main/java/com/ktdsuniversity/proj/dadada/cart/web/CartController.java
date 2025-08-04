package com.ktdsuniversity.proj.dadada.cart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.proj.dadada.cart.service.CartService;
import com.ktdsuniversity.proj.dadada.cart.vo.CartVO;
import com.ktdsuniversity.proj.dadada.cart.vo.PackageIdsRequestVO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.service.ShoplaylistService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ShoplaylistService shoplaylistService;

    @GetMapping("/cart")
    public String goCartPage(HttpSession session, Model model) {

        // 로그인 확인
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/member/login";
        }

        int mbrId = loginUser.getMbrId();
        int cartId = cartService.findCartIdByMbrId(mbrId);

        // 상품 목록 조회
        List<CartVO> cartProductList = cartService.selectCartProductsByCartId(cartId);
        model.addAttribute("cartProductList", cartProductList);

        int productTotal = cartProductList.stream()
        								  .mapToInt(vo -> vo.getPrdDscntPrc() * vo.getQty())
        								  .sum();
        
        int shippingFee = productTotal >= 50000 ? 0 : 2500;
        int totalPayment = productTotal + shippingFee;
        
        List<CartVO> cartList = cartService.getCartProductListByMbrId(mbrId);
        model.addAttribute("cartList", cartList);
        model.addAttribute("productTotal", productTotal);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("totalPayment", totalPayment);

        return "cart/cart"; 
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public ResponseEntity<String> addToCart(@RequestBody CartVO cartVO, HttpSession session) {
        MemberVO user = (MemberVO) session.getAttribute("loginUser");
        if (user == null) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("로그인이 필요합니다.");
        }
        cartVO.setMbrId(user.getMbrId());
        cartService.addOrUpdateCart(cartVO);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public ResponseEntity<String> updateCartItem(@RequestBody CartVO cartVO, HttpSession session) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("로그인이 필요합니다.");
        }
        cartVO.setMbrId(loginUser.getMbrId());
        cartService.setCartProductQty(cartVO);
        return ResponseEntity.ok("success");
    }
    
    @PostMapping("/cart/delete")
    @ResponseBody
    public String deleteCartItem(@RequestBody CartVO cartVO) {
        cartService.deleteCartProduct(cartVO);
        return "success";
    }
    
    @PostMapping("/cart/addPackages")
    @ResponseBody
    public String addPackageToCart(@RequestBody PackageIdsRequestVO packageIdsRequestVO, HttpSession session) {
    	
    	MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
    	if (loginUser == null) {
    		return "redirect:/member/login";
    	}
    	
    	int mbrId = loginUser.getMbrId();
    	
    	List<Integer> allProductIds = shoplaylistService.getProductIdsByPackageIds(packageIdsRequestVO.getPackageIds());
    	
    	for (int productId : allProductIds) {
    		CartVO cartVO = new CartVO();
    		cartVO.setMbrId(mbrId);
    		cartVO.setPrdId(productId);
    		cartVO.setQty(1);
    		cartService.addOrUpdateCart(cartVO);
    	}
    	return "success";
    }
    
    @PostMapping("/cart/clearAll")
    @ResponseBody
    public ResponseEntity<String> clearAllCart(HttpSession session) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("로그인이 필요합니다.");
        }
        int mbrId = loginUser.getMbrId();
        cartService.clearCartByMemberId(mbrId);
        return ResponseEntity.ok("cleared");
    }
    
}
