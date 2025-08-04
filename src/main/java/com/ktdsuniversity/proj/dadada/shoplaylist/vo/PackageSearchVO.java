package com.ktdsuniversity.proj.dadada.shoplaylist.vo;

import com.ktdsuniversity.proj.dadada.common.Search;

public class PackageSearchVO extends Search{

	private String packageName;
	private String createdBy;
	private String searchKeyword;
	
	public String getSearchKeyword() {
		return searchKeyword;
	}
	
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	public String getPackageName() {
		return packageName;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}	
	
	public int getStartRow() {
		return this.getPageNo() * this.getListSize();
	}
	public int getEndRow() {
		return (this.getPageNo() + 1) * this.getListSize();
	}
	
}
