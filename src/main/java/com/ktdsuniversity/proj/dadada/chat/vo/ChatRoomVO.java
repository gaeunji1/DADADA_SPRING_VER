package com.ktdsuniversity.proj.dadada.chat.vo;

public class ChatRoomVO {

    private int evntRmId;
    private int prdId;
    private String evntRmStat;
    private int evntRmMx;
    private String   evntRmDt;
    private String   evntRmEndt;

    public ChatRoomVO() { }

    public ChatRoomVO(int evntRmId, int prdId, String evntRmStat,
                      int evntRmMx, String evntRmDt, String evntRmEndt) {
        this.evntRmId   = evntRmId;
        this.prdId      = prdId;
        this.evntRmStat = evntRmStat;
        this.evntRmMx   = evntRmMx;
        this.evntRmDt   = evntRmDt;
        this.evntRmEndt = evntRmEndt;
    }

	public int getEvntRmId() {
		return evntRmId;
	}

	public void setEvntRmId(int evntRmId) {
		this.evntRmId = evntRmId;
	}

	public int getPrdId() {
		return prdId;
	}

	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}

	public String getEvntRmStat() {
		return evntRmStat;
	}

	public void setEvntRmStat(String evntRmStat) {
		this.evntRmStat = evntRmStat;
	}

	public int getEvntRmMx() {
		return evntRmMx;
	}

	public void setEvntRmMx(int evntRmMx) {
		this.evntRmMx = evntRmMx;
	}

	public String getEvntRmDt() {
		return evntRmDt;
	}

	public void setEvntRmDt(String evntRmDt) {
		this.evntRmDt = evntRmDt;
	}

	public String getEvntRmEndt() {
		return evntRmEndt;
	}

	public void setEvntRmEndt(String evntRmEndt) {
		this.evntRmEndt = evntRmEndt;
	}

}