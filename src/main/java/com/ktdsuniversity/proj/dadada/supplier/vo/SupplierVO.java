package com.ktdsuniversity.proj.dadada.supplier.vo;

public class SupplierVO {
	
	private int splrId;          // 공급업체 고유 ID
    private String splrNm;       // 공급업체 명
    private String cntctNf;      // 공급업체 연락처 및 추가 정보(JSON)
    private String splrLgo;      // 공급업체 로고 이미지 URL
    private String splrDesc;     // 공급업체에 대한 상세 설명
    private double spllrRt;      // 공급업체 신뢰도 평가
    
	public int getSplrId() {
		return splrId;
	}
	public void setSplrId(int splrId) {
		this.splrId = splrId;
	}
	public String getSplrNm() {
		return splrNm;
	}
	public void setSplrNm(String splrNm) {
		this.splrNm = splrNm;
	}
	public String getCntctNf() {
		return cntctNf;
	}
	public void setCntctNf(String cntctNf) {
		this.cntctNf = cntctNf;
	}
	public String getSplrLgo() {
		return splrLgo;
	}
	public void setSplrLgo(String splrLgo) {
		this.splrLgo = splrLgo;
	}
	public String getSplrDesc() {
		return splrDesc;
	}
	public void setSplrDesc(String splrDesc) {
		this.splrDesc = splrDesc;
	}
	public double getSpllrRt() {
		return spllrRt;
	}
	public void setSpllrRt(double spllrRt) {
		this.spllrRt = spllrRt;
	}
    
    
}
