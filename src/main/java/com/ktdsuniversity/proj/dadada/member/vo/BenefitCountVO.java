package com.ktdsuniversity.proj.dadada.member.vo;

public class BenefitCountVO {

	private int mbrId;
	private int count;
	
	public BenefitCountVO() {}
	
	public BenefitCountVO(int mbrId, int count) {
		this.mbrId = mbrId;
		this.count = count;
	}
	
	public int getMbrId() {
		return mbrId;
	}
	public int getCount() {
		return count;
	}
	public void setMbrId(int mbrId) {
		this.mbrId = mbrId;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
