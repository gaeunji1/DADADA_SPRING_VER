package com.ktdsuniversity.proj.dadada.mypage.vo;

import com.ktdsuniversity.proj.dadada.common.Search;

public class MyPageActivityRouletteVO extends Search {
	
	// MBR 테이블
	private int mbrId; // 회원 식별 번호
	
	// RLT_BNFT 테이블
	private int bnftId; // 혜택 ID
	
	private String bnftNm; // 혜택 이름
	
	private int bnftTyp; // 혜택 종류
	
	private int bnftVal; // 혜택 종류의 값
	
	// RLT_SPN 테이블
	private int spnId; // 룰렛 스핀 ID
	
	private String spnDt; // 룰렛 스핀 실행 일시


	public int getMbrId() {
		return this.mbrId;
	}

	public void setMbrId(int mbrId) {
		this.mbrId = mbrId;
	}

	public int getBnftId() {
		return this.bnftId;
	}

	public void setBnftId(int bnftId) {
		this.bnftId = bnftId;
	}

	public String getBnftNm() {
		return this.bnftNm;
	}

	public void setBnftNm(String bnftNm) {
		this.bnftNm = bnftNm;
	}

	public int getBnftTyp() {
		return this.bnftTyp;
	}

	public void setBnftTyp(int bnftTyp) {
		this.bnftTyp = bnftTyp;
	}

	public int getBnftVal() {
		return this.bnftVal;
	}

	public void setBnftVal(int bnftVal) {
		this.bnftVal = bnftVal;
	}

	public int getSpnId() {
		return this.spnId;
	}

	public void setSpnId(int spnId) {
		this.spnId = spnId;
	}

	public String getSpnDt() {
		return this.spnDt;
	}

	public void setSpnDt(String spnDt) {
		this.spnDt = spnDt;
	}

	public int getStartRow() {
		return (this.getPageNo() * this.getListSize()) + 1;
	}

	public int getEndRow() {
		return (this.getPageNo() + 1) * this.getListSize();
	}
}
