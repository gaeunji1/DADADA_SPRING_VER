package com.ktdsuniversity.proj.dadada.shipping.service;

import com.ktdsuniversity.proj.dadada.shipping.vo.ShippingVO;

public interface ShippingService {
	int insertShipping(ShippingVO shippingVO);
	
	void updateShippingStatus(int ordId, String shpStat);
}
