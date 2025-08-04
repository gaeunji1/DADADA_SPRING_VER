package com.ktdsuniversity.proj.dadada.shipping.dao;

import com.ktdsuniversity.proj.dadada.shipping.vo.ShippingVO;

public interface ShippingDao {
	int insertShipping(ShippingVO shippingVO);
	
	void updateShippingStatus(ShippingVO vo);
}
