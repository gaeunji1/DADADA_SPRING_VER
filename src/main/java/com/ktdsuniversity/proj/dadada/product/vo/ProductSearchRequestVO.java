package com.ktdsuniversity.proj.dadada.product.vo;

import com.ktdsuniversity.proj.dadada.common.Search;

public class ProductSearchRequestVO extends Search{

    private String searchKeyword; // 검색어

    public ProductSearchRequestVO() {
    	super();
    	this.setListSize(12);
    }
    
    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}