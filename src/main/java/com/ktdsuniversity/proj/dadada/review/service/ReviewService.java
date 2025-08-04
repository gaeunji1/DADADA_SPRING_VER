package com.ktdsuniversity.proj.dadada.review.service;

import java.util.List;

import com.ktdsuniversity.proj.dadada.review.vo.ReviewVO;

public interface ReviewService {
	
    public boolean createReview(ReviewVO reviewVO);
    
    public List<ReviewVO> getPagedReviewsByProductId(ReviewVO reviewVO);
    
    public int getReviewCountByProductId(int prdId);

    public Double getAverageRatingByProductId(int prdId);
}