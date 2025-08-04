package com.ktdsuniversity.proj.dadada.review.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;
import com.ktdsuniversity.proj.dadada.review.service.ReviewService;
import com.ktdsuniversity.proj.dadada.review.vo.ReviewVO;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public boolean createReview(
        @RequestBody ReviewVO reviewVO,
        @SessionAttribute("loginUser") MemberVO memberVO
    ) {
        reviewVO.setMbrId(memberVO.getMbrId()); // 세션에서 로그인한 회원ID
        return reviewService.createReview(reviewVO);
    }

    @GetMapping("/product/{prdId}")
    public List<ReviewVO> getReviewsByProduct(
            @PathVariable int prdId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        
        int startRow = (page - 1) * size + 1;
        int endRow = page * size;

        ReviewVO reviewVO = new ReviewVO();
        reviewVO.setPrdId(prdId);
        reviewVO.setStartRowNum(startRow);
        reviewVO.setEndRowNum(endRow);

        return reviewService.getPagedReviewsByProductId(reviewVO);
    }


    // 총 리뷰 개수 가져오기 (페이지네이션 계산용)
    @GetMapping("/product/{prdId}/count")
    public int getReviewCount(@PathVariable int prdId) {
        return reviewService.getReviewCountByProductId(prdId);
    }

    
}