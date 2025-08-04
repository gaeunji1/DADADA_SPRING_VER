package com.ktdsuniversity.proj.dadada.chat.vo;

public class ParticipantVO {
    private int evntRmPtptId;
    private int mbrId;
    private int evntRmId;
    private int ptcptCnt;
    private int ptcptRnk;
    private String ptcptJnDt;
    private String confirmDt;

    public ParticipantVO() { }

	public int getEvntRmPtptId() {
		return evntRmPtptId;
	}

	public void setEvntRmPtptId(int evntRmPtptId) {
		this.evntRmPtptId = evntRmPtptId;
	}

	public int getMbrId() {
		return mbrId;
	}

	public void setMbrId(int mbrId) {
		this.mbrId = mbrId;
	}

	public int getEvntRmId() {
		return evntRmId;
	}

	public void setEvntRmId(int evntRmId) {
		this.evntRmId = evntRmId;
	}

	public int getPtcptCnt() {
		return ptcptCnt;
	}

	public void setPtcptCnt(int ptcptCnt) {
		this.ptcptCnt = ptcptCnt;
	}

	public int getPtcptRnk() {
		return ptcptRnk;
	}

	public void setPtcptRnk(int ptcptRnk) {
		this.ptcptRnk = ptcptRnk;
	}

	public String getPtcptJnDt() {
		return ptcptJnDt;
	}

	public void setPtcptJnDt(String ptcptJnDt) {
		this.ptcptJnDt = ptcptJnDt;
	}

	public String getConfirmDt() {
		return confirmDt;
	}

	public void setConfirmDt(String confirmDt) {
		this.confirmDt = confirmDt;
	}


}