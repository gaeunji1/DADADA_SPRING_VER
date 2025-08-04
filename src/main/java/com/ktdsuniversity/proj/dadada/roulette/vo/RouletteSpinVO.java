package com.ktdsuniversity.proj.dadada.roulette.vo;

public class RouletteSpinVO {

    private int spinId;
    private int memId;
    private int benefitId;
    private String spinDatetime;
    private Integer isClaimed;
    private String couponCode;

    
    private String benefitName;
    private int benefitType;
    private int benefitValue;

    
    private RouletteBenefitVO benefit;

    public int getSpinId() {
        return spinId;
    }

    public void setSpinId(int spinId) {
        this.spinId = spinId;
    }

    public int getMemId() {
        return memId;
    }

    public void setMemId(int memId) {
        this.memId = memId;
    }

    public int getBenefitId() {
        return benefitId;
    }

    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }

    public String getSpinDatetime() {
        return spinDatetime;
    }

    public void setSpinDatetime(String spinDatetime) {
        this.spinDatetime = spinDatetime;
    }

    public Integer getIsClaimed() {
        return isClaimed;
    }

    public void setIsClaimed(Integer isClaimed) {
        this.isClaimed = isClaimed;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }

    public int getBenefitType() {
        return benefitType;
    }

    public void setBenefitType(int benefitType) {
        this.benefitType = benefitType;
    }

    public int getBenefitValue() {
        return benefitValue;
    }

    public void setBenefitValue(int benefitValue) {
        this.benefitValue = benefitValue;
    }

    public RouletteBenefitVO getBenefit() {
        return benefit;
    }

    public void setBenefit(RouletteBenefitVO benefit) {
        this.benefit = benefit;
    }
}

