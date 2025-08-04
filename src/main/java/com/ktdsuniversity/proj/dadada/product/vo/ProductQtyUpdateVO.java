package com.ktdsuniversity.proj.dadada.product.vo;

public class ProductQtyUpdateVO {

    private int prdId;  // 상품 ID
    private int qty;    // 차감할 수량(+) 또는 추가할 수량(-)

    public ProductQtyUpdateVO() {}

    public ProductQtyUpdateVO(int prdId, int qty) {
        this.prdId = prdId;
        this.qty   = qty;
    }

    public int getPrdId() { return prdId; }
    public void setPrdId(int prdId) { this.prdId = prdId; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
}