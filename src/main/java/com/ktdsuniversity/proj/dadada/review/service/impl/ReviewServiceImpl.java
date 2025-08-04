package com.ktdsuniversity.proj.dadada.review.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.proj.dadada.review.dao.ReviewDao;
import com.ktdsuniversity.proj.dadada.review.service.ReviewService;
import com.ktdsuniversity.proj.dadada.review.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public boolean createReview(ReviewVO reviewVO) {
        int inserted = reviewDao.createReview(reviewVO);

        if (inserted > 0) {
            // 리뷰가 등록 성공했을 때만 PRD 테이블 리뷰수 업데이트
            reviewDao.updateProductReviewCount(reviewVO.getPrdId());
        }

        return inserted > 0;
    }

    @Override
    public List<ReviewVO> getPagedReviewsByProductId(ReviewVO reviewVO) {
        return reviewDao.selectPagedReviewsByProductId(reviewVO);
    }

    @Override
    public int getReviewCountByProductId(int prdId) {
        return reviewDao.selectReviewCountByProductId(prdId);
    }

	@Override
	public Double getAverageRatingByProductId(int prdId) {
		Double avg = reviewDao.selectAverageRatingByProductId(prdId);
	    if (avg == null) {
	        return 0.0;
	    }
	    return avg;
	}

}