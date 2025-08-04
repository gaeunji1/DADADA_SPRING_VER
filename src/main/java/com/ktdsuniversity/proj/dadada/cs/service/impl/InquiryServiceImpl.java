package com.ktdsuniversity.proj.dadada.cs.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.proj.dadada.beans.FileHandler;
import com.ktdsuniversity.proj.dadada.cs.dao.InquiryDao;
import com.ktdsuniversity.proj.dadada.cs.service.InquiryService;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryListVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryPaginationVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryUpdateRequestVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryWriteRequestVO;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryDao inquiryDao;
    
    @Autowired
    private FileHandler fileHandler;

    private static final Logger LOGGER = LoggerFactory.getLogger(InquiryServiceImpl.class);

    /**
     * 이미지 파일 검증 및 업로드 처리
     * @param file 업로드할 이미지 파일
     * @return 저장된 파일명, 유효하지 않은 파일이면 null 반환
     * @throws IllegalArgumentException 유효하지 않은 파일일 경우 예외 발생
     */
    private String validateAndStoreImageFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        
        // 1. 파일명에서 확장자 추출
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("파일명이 없습니다.");
        }
        
        String extension = "";
        int lastDotIndex = originalFilename.lastIndexOf(".");
        if (lastDotIndex > 0) {
            extension = originalFilename.substring(lastDotIndex + 1).toLowerCase();
        }
        
        // 2. 허용된 확장자인지 검증
        List<String> allowedExtensions = List.of("jpg", "jpeg", "png");
        if (!allowedExtensions.contains(extension)) {
            LOGGER.warn("허용되지 않은 파일 형식: {}", originalFilename);
            throw new IllegalArgumentException("JPG, JPEG, PNG 형식의 이미지만 업로드 가능합니다.");
        }
        
        // 3. MIME 타입 검증
        String contentType = file.getContentType();
        List<String> allowedContentTypes = List.of("image/jpeg", "image/jpg", "image/png");
        if (!allowedContentTypes.contains(contentType)) {
            LOGGER.warn("허용되지 않은 컨텐츠 타입: {}", contentType);
            throw new IllegalArgumentException("JPG, JPEG, PNG 형식의 이미지만 업로드 가능합니다.");
        }
        
        // 4. 파일 크기 검증 추가 ✨✨✨
        if (!fileHandler.isValidFileSize(file)) {
            LOGGER.warn("파일 크기 초과: {} ({}바이트)", originalFilename, file.getSize());
            throw new IllegalArgumentException("이미지 파일은 30MB를 초과할 수 없습니다.");
        }
        
        // 5. 유효성 검증 통과 후 파일 저장
        try {
            FileHandler.StoredFile storedFile = fileHandler.store(file);
            return storedFile != null ? storedFile.getRealFileName() : null;
        } catch (Exception e) {
            LOGGER.error("파일 저장 중 오류 발생: {}", e.getMessage(), e);
            throw new IllegalArgumentException("파일 저장 중 오류가 발생했습니다.");
        }
    }


    @Transactional(readOnly = true)
    @Override
    public InquiryListVO getInquiryList(int page, int size, Map<String, Object> params) {
        // Map에서 VO로 변환
        InquiryPaginationVO paginationVO = new InquiryPaginationVO();
        paginationVO.setCurrentPage(page);
        paginationVO.setPageSize(size);
        
        // 검색/필터링 조건 설정
        if (params.get("category") != null) {
            paginationVO.setCategory((Integer) params.get("category"));
        }
        
        if (params.get("sort") == null) {
            paginationVO.setSort("newest");
        } else {
            paginationVO.setSort((String) params.get("sort"));
        }
        
        if (params.get("keyword") != null) {
            paginationVO.setKeyword((String) params.get("keyword"));
            paginationVO.setSearchType((String) params.get("searchType"));
        }
        
        if (params.get("currentUserId") != null) {
            paginationVO.setCurrentUserId(Integer.valueOf(params.get("currentUserId").toString()));
        }
        
        if (params.get("onlyPublic") != null) {
            paginationVO.setOnlyPublic((Boolean) params.get("onlyPublic"));
        }
        
        // 내 문의글만 보기 필터 설정
        if (params.get("filterByUserId") != null) {
            paginationVO.setFilterByUserId(Integer.valueOf(params.get("filterByUserId").toString()));
            LOGGER.info("Filtering inquiries for user ID: {}", paginationVO.getFilterByUserId());
        }
        
        // 페이징 계산
        int totalCount = this.inquiryDao.selectInquiryCount(paginationVO);
        paginationVO.calculatePagination(totalCount);
        
        // 문의 목록 조회
        List<InquiryVO> inquiries = this.inquiryDao.selectInquiryList(paginationVO);
        
        // 결과 객체 생성
        InquiryListVO result = new InquiryListVO();
        result.setInquiries(inquiries);
        result.setCurrentPage(paginationVO.getCurrentPage());
        result.setTotalPages(paginationVO.getTotalPages());
        result.setSize(paginationVO.getPageSize());
        result.setCategory(paginationVO.getCategory());
        result.setSort(paginationVO.getSort());
        result.setStartPage(paginationVO.getStartPage());
        result.setEndPage(paginationVO.getEndPage());
        result.setTotalCount(totalCount);
        
        // 내 문의글 필터링 상태 유지
        if (paginationVO.getFilterByUserId() != null) {
            result.setMyInquiries(true);
        }
        
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public InquiryVO selectOneInquiry(int inquiryId) {
        return this.inquiryDao.selectOneInquiry(inquiryId);
    }

    @Transactional
    @Override
    public boolean insertNewInquiry(InquiryWriteRequestVO inquiryWriteRequestVO) {
        try {
            // 이미지 파일 처리 - 공통 메서드 사용
            if (inquiryWriteRequestVO.getImageFile() != null && !inquiryWriteRequestVO.getImageFile().isEmpty()) {
                String storedFileName = validateAndStoreImageFile(inquiryWriteRequestVO.getImageFile());
                inquiryWriteRequestVO.setInqryImgPath(storedFileName);
            }
            
            // 데이터베이스 저장
            int insertedCount = this.inquiryDao.insertNewInquiry(inquiryWriteRequestVO);
            return insertedCount > 0;
        } catch (IllegalArgumentException e) {
            // 파일 형식 오류는 상위로 전달하여 컨트롤러에서 처리
            LOGGER.warn("파일 검증 오류: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("문의글 등록 중 오류: {}", e.getMessage(), e);
            return false;
        }
    }

    @Transactional
    @Override
    public boolean updateInquiryReply(InquiryUpdateRequestVO inquiryUpdateRequestVO) {
        // 업데이트 실행
        int updatedCount = this.inquiryDao.updateInquiryReply(inquiryUpdateRequestVO);
        
        // 답변 등록 시 자동으로 상태를 '완료'로 변경
        if (updatedCount > 0) {
            this.inquiryDao.updateInquiryStatus(inquiryUpdateRequestVO.getInqryId(), 2); // 2는 '완료' 상태
        }
        
        return updatedCount > 0;
    }

    @Transactional
    @Override
    public boolean updateInquiryStatus(int inquiryId, int status) {
        // 상태 업데이트
        int updatedCount = this.inquiryDao.updateInquiryStatus(inquiryId, status);
        return updatedCount > 0;
    }
    
    @Transactional
    @Override
    public boolean updateOneInquiry(InquiryUpdateRequestVO inquiryUpdateRequestVO) {
        try {
            // 이미지 파일 처리 - 공통 메서드 사용
            if (inquiryUpdateRequestVO.getImageFile() != null && !inquiryUpdateRequestVO.getImageFile().isEmpty()) {
                String storedFileName = validateAndStoreImageFile(inquiryUpdateRequestVO.getImageFile());
                inquiryUpdateRequestVO.setInqryImgPath(storedFileName);
            }
            
            // 업데이트 실행
            int updatedCount = this.inquiryDao.updateOneInquiry(inquiryUpdateRequestVO);
            return updatedCount > 0;
        } catch (IllegalArgumentException e) {
            // 파일 형식 오류는 상위로 전달하여 컨트롤러에서 처리
            LOGGER.warn("파일 검증 오류: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("문의글 수정 중 오류: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Transactional
    @Override
    public boolean deleteOneInquiry(int inquiryId) {
        // 삭제 실행
        int deletedCount = this.inquiryDao.deleteOneInquiry(inquiryId);
        return deletedCount > 0;
    }
}
