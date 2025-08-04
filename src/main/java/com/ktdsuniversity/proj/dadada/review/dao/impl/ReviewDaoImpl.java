package com.ktdsuniversity.proj.dadada.review.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.review.dao.ReviewDao;
import com.ktdsuniversity.proj.dadada.review.vo.ReviewVO;

@Repository
public class ReviewDaoImpl implements ReviewDao{

    @Autowired
    private SqlSessionTemplate sqlSession;

    private final String NAME_SPACE = "com.ktdsuniversity.proj.dadada.review.dao.ReviewDao";
	
    @Override
    public int createReview(ReviewVO reviewVO) {
        return sqlSession.insert(NAME_SPACE + ".insertReview", reviewVO);
    }

    @Override
    public int selectReviewCountByProductId(int prdId) {
        return sqlSession.selectOne(NAME_SPACE + ".selectReviewCountByProductId", prdId);
    }

    @Override
    public List<ReviewVO> selectPagedReviewsByProductId(ReviewVO reviewVO) {
        return sqlSession.selectList(NAME_SPACE + ".selectPagedReviewsByProductId", reviewVO);
    }

    @Override
    public int updateProductReviewCount(int prdId) {
        return sqlSession.update(NAME_SPACE + ".updateProductReviewCount", prdId);
    }

	@Override
	public Double selectAverageRatingByProductId(int prdId) {
		return sqlSession.selectOne(NAME_SPACE + ".selectAverageRatingByProductId", prdId);
	}

}