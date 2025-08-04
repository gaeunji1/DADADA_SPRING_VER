package com.ktdsuniversity.proj.dadada.product.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;
import com.ktdsuniversity.proj.dadada.product.service.ProductService;
import com.ktdsuniversity.proj.dadada.product.vo.ProductSearchRequestVO;
import com.ktdsuniversity.proj.dadada.product.vo.ProductVO;
import com.ktdsuniversity.proj.dadada.review.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
    private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;

    // 상품 등록 페이지 이동
    @GetMapping("/register")
    public String viewProductRegisterPage(@SessionAttribute("loginUser") MemberVO loginUser) {
        // 관리자 권한 체크
        if (loginUser.getAdminYn() != 1) {
            throw new IllegalArgumentException("관리자만 상품 등록 페이지에 접근할 수 있습니다.");
        }
        return "product/productRegister";
    }

    // 상품 등록 처리
    @PostMapping("/create")
    public String doRegisterProduct(@SessionAttribute("loginUser") MemberVO loginUser,
    								@ModelAttribute ProductVO productVO) {
        // 관리자 권한 체크
        if (loginUser.getAdminYn() != 1) {
            throw new IllegalArgumentException("관리자만 상품 등록이 가능합니다.");
        }
    	
        productService.createNewProduct(productVO);
        return "redirect:/product/list";
    }

    // 상품 리스트 (페이지네이션 적용)
    @GetMapping("/list")
    public String viewProductListPage(@ModelAttribute ProductSearchRequestVO productSearchRequestVO, Model model) {

        if (productSearchRequestVO.getPageNo() <= 0) {
            productSearchRequestVO.setPageNo(1);
        }

        int totalCount = productService.getProductTotalCount(productSearchRequestVO);

        productSearchRequestVO.setPageCount(totalCount); // 여기서 pageCount + startRowNum, endRowNum 다 계산됨

        List<ProductVO> productList = productService.getPagedProductList(productSearchRequestVO);

        model.addAttribute("productList", productList);
        model.addAttribute("pagination", productSearchRequestVO);

        return "product/productList";
    }
    
 // 상품 상세 페이지
    @GetMapping("/view/{prdId}")
    public String viewProductDetail(
        @PathVariable int prdId,
        HttpSession session,
        Model model
    ) {
        ProductVO product = productService.getProductById(prdId);
        model.addAttribute("product", product);
        
     // 리뷰 평균 별점 가져오기 추가
        Double avgRating = reviewService.getAverageRatingByProductId(prdId);
        if (avgRating == null) {
            avgRating = 0.0;
        }
        model.addAttribute("avgRating", avgRating);

        @SuppressWarnings("unchecked")
        List<ProductVO> recent =
            (List<ProductVO>) session.getAttribute("recentProducts");
        if (recent == null) {
            recent = new ArrayList<>();
        }

        recent.removeIf(p -> p == null || p.getPrdId() == product.getPrdId());

        recent.add(0, product);

        if (recent.size() > 5) {
            recent = recent.subList(0, 5);
        }

        session.setAttribute("recentProducts", recent);

        return "product/productDetail";
    }
    
    //상품 삭제
    @PostMapping("/delete/{prdId}")
    @ResponseBody
    public boolean doDeleteProduct(
            @PathVariable int prdId,
            @SessionAttribute("loginUser") MemberVO loginUser) {

        //관리자 권한 체크
        if (loginUser.getAdminYn() != 1) {
            throw new IllegalArgumentException("관리자만 상품을 삭제할 수 있습니다.");
        }

        return productService.deleteProductById(prdId);
    }
    
}
