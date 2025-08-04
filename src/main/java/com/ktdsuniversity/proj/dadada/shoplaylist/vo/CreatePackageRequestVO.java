package com.ktdsuniversity.proj.dadada.shoplaylist.vo;

import java.util.List;

public class CreatePackageRequestVO {
	
	private ShoplaylistVO shopplaylist;
	private List<Integer> productId;
	private String packageName;
	
	public ShoplaylistVO getShopplaylist() {
		return this.shopplaylist;
	}
	public List<Integer> getProductId() {
		return this.productId;
	}

	public void setShopplaylist(ShoplaylistVO shopplaylist) {
		this.shopplaylist = shopplaylist;
	}
	public void setProductIds(List<Integer> productId) {
		this.productId = productId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
