package com.ktdsuniversity.proj.dadada.shipping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * DADADA 기본 배송비 정책
 */
@Component
public class ShippingPolicy {
	
	@Value("${shipping.base-fee}")
	private int baseFee;
	
	@Value("${shipping.free-min-price}")
	private int freeMinPrice;
	
	public int getBaseFee() {
		return baseFee;
	}
	
	public int calcShippingFee(double totalPrice) {
		if (totalPrice >= freeMinPrice) {
			return 0;
		}
		return baseFee;
	}
}
