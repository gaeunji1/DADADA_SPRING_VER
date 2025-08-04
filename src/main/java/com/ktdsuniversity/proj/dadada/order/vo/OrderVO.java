package com.ktdsuniversity.proj.dadada.order.vo;

public class OrderVO {
    private int ordId;
    private int mbrId;
    private int prdId;
    private Integer pckgId;
    private Integer evntRmId;
    private int ordCnt;
    private double prdPrc;
    private double dscntRt;
    private double prdDscntPrc;
    private double prdPrcFinal;
    private String ordDvsn;
    private String dlvStat;
    private String crtPckgYn;

    public int getOrdId() { return ordId; }
    public void setOrdId(int ordId) { this.ordId = ordId; }

    public int getMbrId() { return mbrId; }
    public void setMbrId(int mbrId) { this.mbrId = mbrId; }

    public int getPrdId() { return prdId; }
    public void setPrdId(int prdId) { this.prdId = prdId; }

    public Integer getPckgId() { return pckgId; }
    public void setPckgId(Integer pckgId) { this.pckgId = pckgId; }

    public Integer getEvntRmId() { return evntRmId; }
    public void setEvntRmId(Integer evntRmId) { this.evntRmId = evntRmId; }

    public int getOrdCnt() { return ordCnt; }
    public void setOrdCnt(int ordCnt) { this.ordCnt = ordCnt; }

    public double getPrdPrc() { return prdPrc; }
    public void setPrdPrc(double prdPrc) { this.prdPrc = prdPrc; }

    public double getDscntRt() { return dscntRt; }
    public void setDscntRt(double dscntRt) { this.dscntRt = dscntRt; }

    public double getPrdDscntPrc() { return prdDscntPrc; }
    public void setPrdDscntPrc(double prdDscntPrc) { this.prdDscntPrc = prdDscntPrc; }

    public double getPrdPrcFinal() { return prdPrcFinal; }
    public void setPrdPrcFinal(double prdPrcFinal) { this.prdPrcFinal = prdPrcFinal; }

    public String getOrdDvsn() { return ordDvsn; }
    public void setOrdDvsn(String ordDvsn) { this.ordDvsn = ordDvsn; }

    public String getDlvStat() { return dlvStat; }
    public void setDlvStat(String dlvStat) { this.dlvStat = dlvStat; }

    public String getCrtPckgYn() { return crtPckgYn; }
    public void setCrtPckgYn(String crtPckgYn) { this.crtPckgYn = crtPckgYn; }
}