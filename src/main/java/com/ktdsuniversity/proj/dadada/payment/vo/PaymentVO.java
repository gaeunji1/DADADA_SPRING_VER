package com.ktdsuniversity.proj.dadada.payment.vo;

import java.util.List;

public class PaymentVO {
	private String impUid;
	private int    ordId;
	private String merchantUid;
	private String payMethod;
	private String status;
	private int paidAmount;
	private String pgTid;
	private String success;
	private String buyerEmail;
    private String deliveryMsg;
    private String ordDvsn;
    private List<Item> items;
	
	
	public String getImpUid() {
		return impUid;
	}
	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}
	public int getOrdId() {
		return ordId;
	}
	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}
	public String getMerchantUid() {
		return merchantUid;
	}
	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getPgTid() {
		return pgTid;
	}
	public void setPgTid(String pgTid) {
		this.pgTid = pgTid;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getBuyerEmail() {
		return buyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	public String getDeliveryMsg() {
		return deliveryMsg;
	}
	public void setDeliveryMsg(String deliveryMsg) {
		this.deliveryMsg = deliveryMsg;
	}
	public List<Item> getItems() {
		return items;
	}
	 public void setOrdDvsn(String ordDvsn) {
		this.ordDvsn = ordDvsn;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getOrdDvsn() {
		return ordDvsn;
	}

	public static class Item {
	        private int prdId;
	        private int qty;

	        public int getPrdId() { return prdId; }
	        public void setPrdId(int prdId) { this.prdId = prdId; }

	        public int getQty() { return qty; }
	        public void setQty(int qty) { this.qty = qty; }
	    }
}
