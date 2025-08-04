package com.ktdsuniversity.proj.dadada.cs.service;

import java.util.Map;

import com.ktdsuniversity.proj.dadada.cs.vo.InquiryListVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryUpdateRequestVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryWriteRequestVO;

public interface InquiryService {

    /**
     * 페이징, 검색, 필터링 조건에 맞는 문의 목록 조회
     */
    public InquiryListVO getInquiryList(int page, int size, Map<String, Object> params);

    /**
     * 문의글 단건 조회
     */
    public InquiryVO selectOneInquiry(int inquiryId);
    
    /**
     * 새 문의글 등록
     */
    public boolean insertNewInquiry(InquiryWriteRequestVO inquiryWriteRequestVO);
    
    /**
     * 문의글 답변 등록/수정
     */
    public boolean updateInquiryReply(InquiryUpdateRequestVO inquiryUpdateRequestVO);
    
    /**
     * 문의글 상태 변경
     */
    public boolean updateInquiryStatus(int inquiryId, int status);
    
    /**
     * 문의글 내용 수정
     */
    public boolean updateOneInquiry(InquiryUpdateRequestVO inquiryUpdateRequestVO);
    
    /**
     * 문의글 삭제
     */
    public boolean deleteOneInquiry(int inquiryId);
}
