package com.ktdsuniversity.proj.dadada.common;

public abstract class Search {

	private int pageNo;
	private int listSize;
	private int pageCount;
	private int pageCountInGroup;
	private int groupCount;
	private int groupNo;
	private int groupStartPageNo;
	private int groupEndPageNo;
	private boolean hasNextGroup;
	private boolean hasPrevGroup;
	private int nextGroupStartPageNo;
	private int prevGroupStartPageNo;
	private boolean hasPrevPage;
	private boolean hasNextPage;
	private int prevPageNo;
	private int nextPageNo;
	
	public Search() {
		this.listSize = 10;
		this.pageCountInGroup = 10;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public int getListSize() {
		return listSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public int getPageCountInGroup() {
		return pageCountInGroup;
	}
	public int getGroupCount() {
		return groupCount;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public int getGroupStartPageNo() {
		return groupStartPageNo;
	}

	public int getGroupEndPageNo() {
		return groupEndPageNo;
	}

	public boolean isHasNextGroup() {
		return hasNextGroup;
	}

	public boolean isHasPrevGroup() {
		return hasPrevGroup;
	}

	public int getNextGroupStartPageNo() {
		return nextGroupStartPageNo;
	}

	public int getPrevGroupStartPageNo() {
		return prevGroupStartPageNo;
	}

	public boolean isHasPrevPage() {
		return hasPrevPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public int getPrevPageNo() {
		return prevPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public void setPageCount(int searchCount) {
		// 총 페이지 수 구하기
		this.pageCount = (int) Math.ceil( (double) searchCount / this.listSize);
		
		// 총 그룹 수 구하기
		this.groupCount = (int) Math.ceil( (double) this.pageCount / this.pageCountInGroup);
		
		// 현재 페이지 그룹 번호 구하기
		this.groupNo = this.pageNo / this.pageCountInGroup;
		
		// 현재 페이지 그룹의 시작 페이지 번호 구하기
		this.groupStartPageNo = this.groupNo * this.pageCountInGroup;
		
		// 현재 페이지 그룹의 마지막 페이지 번호 구하기
		this.groupEndPageNo = (this.groupNo + 1) * this.pageCountInGroup - 1;
		
		// 총 페이지 수가 현재 페이지 그룹의 마지막 페이지보다 작을 때
		// 현재 페이지 그룹의 마지막 페이지를 수정
		if (this.groupEndPageNo > this.pageCount) {
			this.groupEndPageNo = this.pageCount - 1;
		}
		
		if (this.groupEndPageNo < 0) {
			this.groupEndPageNo = 0;
		}
		
		// 다음 그룹이 있는지 확인하기
		this.hasNextGroup = this.groupNo + 1 < this.groupCount;
		
		// 이전 그룹이 있는지 확인하기
		this.hasPrevGroup = this.groupNo > 0;
		
		// 다음 페이지 그룹의 시작 페이지 번호 구하기
		this.nextGroupStartPageNo = this.groupEndPageNo + 1;
		this.prevGroupStartPageNo = this.groupStartPageNo - this.pageCountInGroup;
		
		// 이전 페이지가 존재하는지 여부 (현재 페이지 번호가 0보다 크면 존재)
		this.hasPrevPage = this.pageNo > 0;

		// 다음 페이지가 존재하는지 여부 (현재 페이지 번호가 전체 페이지 수 - 1보다 작으면 존재)
		this.hasNextPage = this.pageNo < this.pageCount - 1;

		// 이전 페이지 번호 (현재 페이지 번호 - 1)
		this.prevPageNo = this.pageNo - 1;

		// 다음 페이지 번호 (현재 페이지 번호 + 1)
		this.nextPageNo = this.pageNo + 1;
	}
}
