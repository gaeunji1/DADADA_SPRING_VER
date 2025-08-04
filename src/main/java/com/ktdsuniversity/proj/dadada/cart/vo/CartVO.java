package com.ktdsuniversity.proj.dadada.cart.vo;

public class CartVO {

	private int prdId;
	private int cartId;
	private int qty;
	private int mbrId;
	private String prdImg;
	private String prdNm;
	public String getPrdImg() {
		return prdImg;
	}
	public void setPrdImg(String prdImg) {
		this.prdImg = prdImg;
	}
	public String getPrdNm() {
		return prdNm;
	}
	public void setPrdNm(String prdNm) {
		this.prdNm = prdNm;
	}
	private int basePrice;
	private int prdDscntPrc;
	public int getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}
	public int getPrdDscntPrc() {
		return prdDscntPrc;
	}
	public void setPrdDscntPrc(int prdDscntPrc) {
		this.prdDscntPrc = prdDscntPrc;
	}
	public int getPrdId() {
		return prdId;
	}
	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getMbrId() {
		return mbrId;
	}
	public void setMbrId(int mbrId) {
		this.mbrId = mbrId;
	}
	
}
