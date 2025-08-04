package com.ktdsuniversity.proj.dadada.payment.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.payment.dao.PaymentDAO;
import com.ktdsuniversity.proj.dadada.payment.vo.PaymentVO;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.ktdsuniversity.proj.dadada.payment.dao.impl.PaymentDAOImpl.";

    @Override
    public void insertPayment(PaymentVO paymentVO) {
        int result = sqlSession.insert(NAMESPACE + "insertPayment", paymentVO);
        System.out.println("insert result: " + result);
    }

    @Override
    public PaymentVO selectPaymentByImpUid(String impUid) {
        return sqlSession.selectOne(NAMESPACE + "selectPaymentByImpUid", impUid);
    }

    @Override
    public int updatePaymentStatusToRefund(String impUid) {
        return sqlSession.update(NAMESPACE + "updatePaymentStatusToRefund", impUid);
    }
    
    @Override
    public PaymentVO selectPaymentByMerchantUid(String merchantUid) {
        return sqlSession.selectOne(
            NAMESPACE + "selectPaymentByMerchantUid", 
            merchantUid
        );
    }
    @Override
    public PaymentVO selectPaymentByOrderId(int ordId) {
        return sqlSession.selectOne(
            NAMESPACE + "selectPaymentByOrderId",
            ordId
        );
    }
}