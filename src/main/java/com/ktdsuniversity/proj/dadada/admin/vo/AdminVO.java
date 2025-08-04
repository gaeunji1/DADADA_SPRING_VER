package com.ktdsuniversity.proj.dadada.admin.vo;

public class AdminVO {

    private String ordId;         // 주문 ID
    private int mbrId;            // 회원 ID
    private int prdId;            // 상품 ID
    private int pckgId;           // 패키지 ID
    private int evntRmId;         // 이벤트/경매 ID
    private int ordCnt;           // 수량
    private int prdPrc;           // 상품 가격
    private int dscntRt;          // 할인율
    private int prdDscntPrc;      // 할인 적용 금액
    private int prdPrcFinal;      // 최종 결제 금액
    private String ordDt;         // 주문 일자 
    private String ordDvsn;       // 주문 구분 
    private String dlvStat;       // 배송 상태
    private String crtPckgYn;     // 패키지 생성 여부
    private String mbrNcknm;
    private String dlvStatLabel;



	public String getOrdId() {
        return ordId;
    }
    public void setOrdId(String ordId) {
        this.ordId = ordId;
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
    public int getPckgId() {
        return pckgId;
    }
    public void setPckgId(int pckgId) {
        this.pckgId = pckgId;
    }
    public int getEvntRmId() {
        return evntRmId;
    }
    public void setEvntRmId(int evntRmId) {
        this.evntRmId = evntRmId;
    }
    public int getOrdCnt() {
        return ordCnt;
    }
    public void setOrdCnt(int ordCnt) {
        this.ordCnt = ordCnt;
    }
    public int getPrdPrc() {
        return prdPrc;
    }
    public void setPrdPrc(int prdPrc) {
        this.prdPrc = prdPrc;
    }
    public int getDscntRt() {
        return dscntRt;
    }
    public void setDscntRt(int dscntRt) {
        this.dscntRt = dscntRt;
    }
    public int getPrdDscntPrc() {
        return prdDscntPrc;
    }
    public void setPrdDscntPrc(int prdDscntPrc) {
        this.prdDscntPrc = prdDscntPrc;
    }
    public int getPrdPrcFinal() {
        return prdPrcFinal;
    }
    public void setPrdPrcFinal(int prdPrcFinal) {
        this.prdPrcFinal = prdPrcFinal;
    }
    public String getOrdDt() {
        return ordDt;
    }
    public void setOrdDt(String ordDt) {
        this.ordDt = ordDt;
    }
    public String getOrdDvsn() {
        return ordDvsn;
    }
    public void setOrdDvsn(String ordDvsn) {
        this.ordDvsn = ordDvsn;
    }
    public String getDlvStat() {
        return dlvStat;
    }
    public void setDlvStat(String dlvStat) {
        this.dlvStat = dlvStat;
    }
    public String getCrtPckgYn() {
        return crtPckgYn;
    }
    public void setCrtPckgYn(String crtPckgYn) {
        this.crtPckgYn = crtPckgYn;
    }
    
    public String getMbrNcknm() {
		return mbrNcknm;
	}
	public void setMbrNcknm(String mbrNcknm) {
		this.mbrNcknm = mbrNcknm;
	}
	
	public String getDlvStatLabel() {
		return dlvStatLabel;
	}
	public void setDlvStatLabel(String dlvStatLabel) {
		this.dlvStatLabel = dlvStatLabel;
	}
}
