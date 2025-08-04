package com.ktdsuniversity.proj.dadada.shoplaylist.vo;

/*
 * 패키지에 포함된 개별 상품 1개의 정보
 */
public class ShoplaylistProductVO {
	private int packageId;
	private int productId;
	private int sortOrder;
	private String addedAt;
	private String productName;
	private int basePrice;
	private String productImage;
	private String productSummary;
	
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public void setProductSummary(String productSummary) {
		this.productSummary = productSummary;
	}
	public String getProductName() {
		return productName;
	}
	public int getBasePrice() {
		return basePrice;
	}
	public String getProductImage() {
		return productImage;
	}
	public String getProductSummary() {
		return productSummary;
	}
	public int getPackageId() {
		return this.packageId;
	}
	public int getProductId() {
		return this.productId;
	}
	public int getSortOrder() {
		return this.sortOrder;
	}
	public String getAddedAt() {
		return this.addedAt;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public void setAddedAt(String addedAt) {
		this.addedAt = addedAt;
	}
}
