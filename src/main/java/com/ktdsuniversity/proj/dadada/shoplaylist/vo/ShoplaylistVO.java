package com.ktdsuniversity.proj.dadada.shoplaylist.vo;

import java.util.List;

/**
 * 패키지 정보
 */
public class ShoplaylistVO {

	private int packageId;
	private int memId;
	private String packageName;
	private String packageDescription;
	private String packageImage;
	private String createdAt;
	private String updatedAt;
	private String creatorType;
	private int recommendCount;
	private List<ShoplaylistProductVO> productList;
	private String memberName;
	
	
	public List<ShoplaylistProductVO> getProductList() {
		return productList;
	}
	public void setProductList(List<ShoplaylistProductVO> productList) {
		this.productList = productList;
	}
	public int getPackageId() {
		return this.packageId;
	}
	public int getMemId() {
		return this.memId;
	}
	public String getPackageName() {
		return this.packageName;
	}
	public String getPackageDescription() {
		return this.packageDescription;
	}
	public String getPackageImage() {
		return this.packageImage;
	}
	public String getCreatedAt() {
		return this.createdAt;
	}
	public String getUpdatedAt() {
		return this.updatedAt;
	}
	public String getCreatorType() {
		return this.creatorType;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}
	public void setPackageImage(String packageImage) {
		this.packageImage = packageImage;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setCreatorType(String creatorType) {
		this.creatorType = creatorType;
	}
	public int getRecommendCount() {
		return recommendCount;
	}
	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
}
