package com.ktdsuniversity.proj.dadada.shipping.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.shipping.dao.ShippingDao;
import com.ktdsuniversity.proj.dadada.shipping.vo.ShippingVO;

@Repository
public class ShippingDaoImpl implements ShippingDao {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.ktdsuniversity.proj.dadada.shipping.dao.ShippingDaoImpl.";

    @Override
    public int insertShipping(ShippingVO shippingVO) {
        return sqlSession.insert(NAMESPACE + "insertShipping", shippingVO);
    }
    

    @Override
    public void updateShippingStatus(ShippingVO vo) {
        sqlSession.update(NAMESPACE + "updateShippingStatus", vo);
    }
}

