package com.ktdsuniversity.proj.dadada.cs.dao;

import java.util.List;

import com.ktdsuniversity.proj.dadada.cs.vo.InquiryPaginationVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryUpdateRequestVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryWriteRequestVO;

public interface InquiryDao {

    /**
     * 페이징, 검색, 필터링 조건에 맞는 문의글 목록 조회
     */
    public List<InquiryVO> selectInquiryList(InquiryPaginationVO paginationVO);
    
    /**
     * 조건에 맞는 문의글 총 개수 조회
     */
    public int selectInquiryCount(InquiryPaginationVO paginationVO);
    
    /**
     * 문의글 단건 조회
     */
    public InquiryVO selectOneInquiry(int inquiryId);
    
    /**
     * 새 문의글 등록
     */
    public int insertNewInquiry(InquiryWriteRequestVO inquiryWriteRequestVO);
    
    /**
     * 문의글 답변 등록/수정
     */
    public int updateInquiryReply(InquiryUpdateRequestVO inquiryUpdateRequestVO);
    
    /**
     * 문의글 상태 변경
     */
    public int updateInquiryStatus(int inquiryId, int status);
    
    /**
     * 문의글 내용 수정
     */
    public int updateOneInquiry(InquiryUpdateRequestVO inquiryUpdateRequestVO);
    
    /**
     * 문의글 삭제
     */
    public int deleteOneInquiry(int inquiryId);
    
    
    /* 내 문의글 조회 */
    public int countUserInquiries(int userId);
    
}
