package com.ktdsuniversity.proj.dadada.order.dao;

import com.ktdsuniversity.proj.dadada.order.vo.OrderDetailVO;
import com.ktdsuniversity.proj.dadada.order.vo.OrderVO;

public interface OrderDao {
	
	public void insertOrder(OrderVO order);

	public void insertOrderDetail(OrderDetailVO detail);

	

}
