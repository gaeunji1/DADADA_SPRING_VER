package com.ktdsuniversity.proj.dadada.payment.dao;

import org.apache.ibatis.annotations.Param;

import com.ktdsuniversity.proj.dadada.payment.vo.PaymentVO;

public interface PaymentDAO {
	void insertPayment(PaymentVO paymentVO);

	PaymentVO selectPaymentByImpUid(String impUid);
	
	int updatePaymentStatusToRefund(String impUid);
	
	PaymentVO selectPaymentByMerchantUid(@Param("merchantUid") String merchantUid);
	
	PaymentVO selectPaymentByOrderId(int ordId);
	
}
