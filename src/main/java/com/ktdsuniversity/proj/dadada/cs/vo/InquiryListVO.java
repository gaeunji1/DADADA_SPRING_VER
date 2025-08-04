package com.ktdsuniversity.proj.dadada.cs.vo;

import java.util.List;

public class InquiryListVO {
    private List<InquiryVO> inquiries;

    // 페이지네이션 정보
    private int currentPage;
    private int totalPages;
    private int size;
    private Integer category; // null이면 전체
    private String sort; // newest/oldest

    // 페이지네이션 범위(페이지 버튼)
    private int startPage;
    private int endPage;

    // 전체 문의글 개수
    private int totalCount;

    // 내 문의글 필터링 여부
    private boolean myInquiries;

    // --- Getter/Setter ---
    public List<InquiryVO> getInquiries() {
        return inquiries;
    }
    public void setInquiries(List<InquiryVO> inquiries) {
        this.inquiries = inquiries;
    }
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
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
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
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public boolean isMyInquiries() {
        return myInquiries;
    }
    public void setMyInquiries(boolean myInquiries) {
        this.myInquiries = myInquiries;
    }
}
