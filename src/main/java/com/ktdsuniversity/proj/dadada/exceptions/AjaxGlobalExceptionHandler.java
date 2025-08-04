package com.ktdsuniversity.proj.dadada.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ktdsuniversity.proj.dadada.exceptions.base.BaseException;
import com.ktdsuniversity.proj.dadada.exceptions.base.ErrorResponse;

/**
 * API 컨트롤러에서 발생하는 모든 예외를 처리하는 글로벌 핸들러 
 */
@Order(2)
@RestControllerAdvice
public class AjaxGlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(AjaxGlobalExceptionHandler.class);
    
    /**
     * 기본 커스텀 예외 처리
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        logger.error("[{}] {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            ex.getErrorCode(),
            ex.getStatus().value(),
            null  // path는 null로 설정
        );
        
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
    
    /**
     * 페이지를 찾을 수 없는 경우 404 처리
     */
    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePageNotFoundException(PageNotFoundException ex) {
        logger.error("404 에러 발생: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            ex.getErrorCode(),
            HttpStatus.NOT_FOUND.value(),
            null
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    /**
     * 기타 모든 예외 처리 (500 에러)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        logger.error("서버 오류 발생: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = new ErrorResponse(
            "서비스 처리 중 오류가 발생했습니다.",
            "INTERNAL_SERVER_ERROR",
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            null
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
