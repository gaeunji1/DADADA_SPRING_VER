package com.ktdsuniversity.proj.dadada.review.vo;

public class ReviewVO {
    private int rvwId;       // 리뷰 ID (PK)
    private int mbrId;       // 회원 ID (FK)
    private int prdId;       // 상품 ID (FK)
    private double rtng;     // 평점
    private String rvwTtl;   // 리뷰 제목
    private String rvwCntnt; // 리뷰 내용
    private String rvwDt;      // 작성일
    
    
    private int startRowNum;
    private int endRowNum;
    
    
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	public int getEndRowNum() {
		return endRowNum;
	}
	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}
	public int getRvwId() {
		return rvwId;
	}
	public void setRvwId(int rvwId) {
		this.rvwId = rvwId;
	}
	public int getMbrId() {
		return mbrId;
	}
	public void setMbrId(int mbrId) {
		this.mbrId = mbrId;
	}
	public int getPrdId() {
		return prdId;
	}
	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}
	public double getRtng() {
		return rtng;
	}
	public void setRtng(double rtng) {
		this.rtng = rtng;
	}
	public String getRvwTtl() {
		return rvwTtl;
	}
	public void setRvwTtl(String rvwTtl) {
		this.rvwTtl = rvwTtl;
	}
	public String getRvwCntnt() {
		return rvwCntnt;
	}
	public void setRvwCntnt(String rvwCntnt) {
		this.rvwCntnt = rvwCntnt;
	}
	public String getRvwDt() {
		return rvwDt;
	}
	public void setRvwDt(String rvwDt) {
		this.rvwDt = rvwDt;
	}
    
    
    
}