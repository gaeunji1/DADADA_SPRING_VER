package com.ktdsuniversity.proj.dadada.payment.service;

import java.util.Map;

import com.ktdsuniversity.proj.dadada.payment.vo.PaymentVO;

public interface PaymentService {

    String              getAccessToken();
    Map<String,Object>  verifyPayment(String impUid);

    void        insertPayment(PaymentVO vo);
    PaymentVO   getPaymentByImpUid(String impUid);
    boolean     refundPayment(String impUid);
    PaymentVO getPaymentByMerchantUid(String merchantUid);
	PaymentVO getPaymentByOrderId(int ordId);
}