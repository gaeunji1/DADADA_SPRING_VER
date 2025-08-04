package com.ktdsuniversity.proj.dadada.review.dao;

import java.util.List;

import com.ktdsuniversity.proj.dadada.review.vo.ReviewVO;

public interface ReviewDao {
	
	public int createReview(ReviewVO reviewVO);

    // 특정 상품의 전체 리뷰 개수 가져오기
    public int selectReviewCountByProductId(int prdId);

    // 특정 상품의 리뷰 페이징 조회
    public List<ReviewVO> selectPagedReviewsByProductId(ReviewVO reviewVO);

    //리뷰 갯수 업데이트
    public int updateProductReviewCount(int prdId);

	public Double selectAverageRatingByProductId(int prdId);
    
}