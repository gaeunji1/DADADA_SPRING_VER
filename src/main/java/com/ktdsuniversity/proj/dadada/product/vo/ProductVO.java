package com.ktdsuniversity.proj.dadada.product.vo;

import com.ktdsuniversity.proj.dadada.supplier.vo.SupplierVO;

public class ProductVO {

    private int prdId;             // PRD_ID (상품 ID)
    private int splrId;            // SPLR_ID (공급업체 ID)
    private int dscntPlcyId;       // DSCNT_PLCY_ID (할인 정책 ID)
    private int prdCtgryId;        // PRD_CTGRY_ID (카테고리 ID)

    private String prdNm;          // PRD_NM (상품명)
    private String prdDesc;        // PRD_DESC (상품 상세 설명)
    private int basePrice;         // BASE_PRICE (기본 가격)
    private double prdDscntPrc;    // PRD_DSCNT_PRC (할인 금액)

    private int prdRvwCnt;         // PRD_RVW_CNT (리뷰 수)
    private int prdQty;            // PRD_QTY (재고 수량)

    private String prdImg;         // PRD_IMG (이미지 파일명)
    private String prdSmplDesc;    // PRD_SMPL_DESC (한 줄 설명)

    
    
    private SupplierVO supplierVO;

    public SupplierVO getSupplierVO() {
        return supplierVO;
    }
    public void setSupplierVO(SupplierVO supplierVO) {
        this.supplierVO = supplierVO;
    }
    

	
	public int getPrdId() {
		return prdId;
	}
	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}
	public int getSplrId() {
		return splrId;
	}
	public void setSplrId(int splrId) {
		this.splrId = splrId;
	}
	public int getDscntPlcyId() {
		return dscntPlcyId;
	}
	public void setDscntPlcyId(int dscntPlcyId) {
		this.dscntPlcyId = dscntPlcyId;
	}
	public int getPrdCtgryId() {
		return prdCtgryId;
	}
	public void setPrdCtgryId(int prdCtgryId) {
		this.prdCtgryId = prdCtgryId;
	}
	public String getPrdNm() {
		return prdNm;
	}
	public void setPrdNm(String prdNm) {
		this.prdNm = prdNm;
	}
	public String getPrdDesc() {
		return prdDesc;
	}
	public void setPrdDesc(String prdDesc) {
		this.prdDesc = prdDesc;
	}
	public int getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}
	public double getPrdDscntPrc() {
		return prdDscntPrc;
	}
	public void setPrdDscntPrc(double prdDscntPrc) {
		this.prdDscntPrc = prdDscntPrc;
	}
	public int getPrdRvwCnt() {
		return prdRvwCnt;
	}
	public void setPrdRvwCnt(int prdRvwCnt) {
		this.prdRvwCnt = prdRvwCnt;
	}
	public int getPrdQty() {
		return prdQty;
	}
	public void setPrdQty(int prdQty) {
		this.prdQty = prdQty;
	}
	public String getPrdImg() {
		return prdImg;
	}
	public void setPrdImg(String prdImg) {
		this.prdImg = prdImg;
	}
	public String getPrdSmplDesc() {
		return prdSmplDesc;
	}
	public void setPrdSmplDesc(String prdSmplDesc) {
		this.prdSmplDesc = prdSmplDesc;
	}
    
    
    
}
