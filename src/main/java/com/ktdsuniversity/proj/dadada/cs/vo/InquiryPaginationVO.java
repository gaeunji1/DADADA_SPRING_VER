package com.ktdsuniversity.proj.dadada.cs.vo;

public class InquiryPaginationVO {
    // 기존 필드
    private int currentPage; // 현재 페이지 번호
    private int totalPages;  // 전체 페이지 수
    private int totalItems;  // 전체 항목 수
    private int pageSize;    // 페이지당 글 개수
    
    // 추가 필드 - 검색/필터링/정렬 조건
    private Integer category;
    private String sort;
    private String keyword;
    private String searchType;
    private Integer currentUserId;
    private Boolean onlyPublic;
    private Integer filterByUserId; // 내 문의글 필터링을 위한 필드 추가
    
    // 페이징 처리용 필드
    private int startRow;
    private int endRow;
    private int startPage;
    private int endPage;

    public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Boolean getOnlyPublic() {
        return onlyPublic;
    }

    public void setOnlyPublic(Boolean onlyPublic) {
        this.onlyPublic = onlyPublic;
    }
    
    public Integer getFilterByUserId() {
        return filterByUserId;
    }

    public void setFilterByUserId(Integer filterByUserId) {
        this.filterByUserId = filterByUserId;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    
    // 페이징 계산 헬퍼 메서드
    public void calculatePagination(int totalCount) {
        this.totalItems = totalCount;
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
        this.startRow = (this.currentPage - 1) * this.pageSize + 1;
        this.endRow = this.currentPage * this.pageSize;
        
        int visiblePages = 9;
        this.startPage = Math.max(1, this.currentPage - (visiblePages / 2));
        this.endPage = Math.min(this.totalPages, this.startPage + visiblePages - 1);
    }
}
