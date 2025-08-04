package com.ktdsuniversity.proj.dadada.mypage.vo;

import com.ktdsuniversity.proj.dadada.common.Search;

public class MyPageOrderSearchVO extends Search {
	
	/**
	 * @ColumnName MBR_ID
	 * @ColumnComment 회원 식별 번호
	 */
	private int mbrId;


	public int getMbrId() {
		return this.mbrId;
	}

	public void setMbrId(int mbrId) {
		this.mbrId = mbrId;
	}

	/**
	 * ROWNUM 페이징을 위한 시작 행 번호
	 */
	public int getStartRow() {
		return (this.getPageNo() * this.getListSize()) + 1;
	}

	/**
	 * ROWNUM 페이징을 위한 끝 행 번호
	 */
	public int getEndRow() {
		return (this.getPageNo() + 1) * this.getListSize();
	}
}
