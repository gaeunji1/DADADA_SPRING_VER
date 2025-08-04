package com.ktdsuniversity.proj.dadada.order.service;

import com.ktdsuniversity.proj.dadada.payment.vo.PaymentVO;

public interface OrderService {
	
	 void saveOrder(int mbrId, PaymentVO vo);

}
