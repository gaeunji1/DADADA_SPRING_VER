package com.ktdsuniversity.proj.dadada.roulette.vo;

import java.math.BigDecimal;

public class RouletteBenefitVO {
    private int benefitId;
    private String benefitName;
    private int benefitType;       // 예: 0=할인, 1=포인트, 2=무료배송, 등
    private BigDecimal benefitValue;   // 할인율(%) 또는 포인트수 등
    private BigDecimal benefitProbability; // 20.00 -> 20%
    
    

    
    
    
    
    public RouletteBenefitVO() { }

    public int getBenefitId() {
        return benefitId;
    }
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
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
    public BigDecimal getBenefitValue() {
        return benefitValue;
    }
    public void setBenefitValue(BigDecimal benefitValue) {
        this.benefitValue = benefitValue;
    }
    public BigDecimal getBenefitProbability() {
        return benefitProbability;
    }
    public void setBenefitProbability(BigDecimal benefitProbability) {
        this.benefitProbability = benefitProbability;
    }
}