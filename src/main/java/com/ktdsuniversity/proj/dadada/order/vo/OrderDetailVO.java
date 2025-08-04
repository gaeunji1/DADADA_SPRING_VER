package com.ktdsuniversity.proj.dadada.order.vo;

public class OrderDetailVO {
	private int ordDetlId;

	private int ordId;
	private int prdId;
	private int prdQty;
	private double prdPrc;
	private double dscntRt;
	private double prdDscntPrc;
	private double prdPrcFinal;
	
	public int getOrdId() {
		return ordId;
	}
	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}
	public int getPrdId() {
		return prdId;
	}
	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}
	public int getPrdQty() {
		return prdQty;
	}
	public void setPrdQty(int prdQty) {
		this.prdQty = prdQty;
	}
	public int getOrdDetlId() {
		return ordDetlId;
	}
	public void setOrdDetlId(int ordDetlId) {
		this.ordDetlId = ordDetlId;
	}
	public double getPrdPrc() {
		return prdPrc;
	}
	public void setPrdPrc(double prdPrc) {
		this.prdPrc = prdPrc;
	}
	public double getDscntRt() {
		return dscntRt;
	}
	public void setDscntRt(double dscntRt) {
		this.dscntRt = dscntRt;
	}
	public double getPrdDscntPrc() {
		return prdDscntPrc;
	}
	public void setPrdDscntPrc(double prdDscntPrc) {
		this.prdDscntPrc = prdDscntPrc;
	}
	public double getPrdPrcFinal() {
		return prdPrcFinal;
	}
	public void setPrdPrcFinal(double prdPrcFinal) {
		this.prdPrcFinal = prdPrcFinal;
	}
	
}