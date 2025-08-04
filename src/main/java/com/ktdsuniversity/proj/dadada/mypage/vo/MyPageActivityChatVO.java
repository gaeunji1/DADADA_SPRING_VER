package com.ktdsuniversity.proj.dadada.mypage.vo;

import com.ktdsuniversity.proj.dadada.common.Search;

public class MyPageActivityChatVO extends Search {
	
	// EVNT_RM 테이블
	private int evntRmId; // 경쟁 구매 채팅방 ID
	
	// EVNT_RM_PTCPT 테이블
	private int evntRmPtcptId; // 경쟁 구매 채팅방 참여자 ID
	
	private int evntRmPtcptCnt; // 구매 수량
	
	private int evntRmPtcptRnk; // 순위
	
	private String evntRmPtcptJnDt; // 참여 일시
	
	// MBR 테이블
	private int mbrId; // 회원 식별 번호
	
	// PRD 테이블
	private int prdId; // 상품 ID
	
	private String prdNm; // 상품명
	
	private String prdImg; // 상품 이미지


	public int getEvntRmId() {
		return this.evntRmId;
	}

	public void setEvntRmId(int evntRmId) {
		this.evntRmId = evntRmId;
	}

	public int getEvntRmPtcptId() {
		return this.evntRmPtcptId;
	}

	public void setEvntRmPtcptId(int evntRmPtcptId) {
		this.evntRmPtcptId = evntRmPtcptId;
	}

	public int getEvntRmPtcptCnt() {
		return this.evntRmPtcptCnt;
	}

	public void setEvntRmPtcptCnt(int evntRmPtcptCnt) {
		this.evntRmPtcptCnt = evntRmPtcptCnt;
	}

	public int getEvntRmPtcptRnk() {
		return this.evntRmPtcptRnk;
	}

	public void setEvntRmPtcptRnk(int evntRmPtcptRnk) {
		this.evntRmPtcptRnk = evntRmPtcptRnk;
	}

	public String getEvntRmPtcptJnDt() {
		return this.evntRmPtcptJnDt;
	}

	public void setEvntRmPtcptJnDt(String evntRmPtcptJnDt) {
		this.evntRmPtcptJnDt = evntRmPtcptJnDt;
	}

	public int getMbrId() {
		return this.mbrId;
	}

	public void setMbrId(int mbrId) {
		this.mbrId = mbrId;
	}

	public int getPrdId() {
		return this.prdId;
	}

	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}

	public String getPrdNm() {
		return this.prdNm;
	}

	public void setPrdNm(String prdNm) {
		this.prdNm = prdNm;
	}

	public String getPrdImg() {
		return this.prdImg;
	}

	public void setPrdImg(String prdImg) {
		this.prdImg = prdImg;
	}

	public int getStartRow() {
		return (this.getPageNo() * this.getListSize()) + 1;
	}

	public int getEndRow() {
		return (this.getPageNo() + 1) * this.getListSize();
	}
}
