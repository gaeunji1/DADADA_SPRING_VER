package com.ktdsuniversity.proj.dadada.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ktdsuniversity.proj.dadada.exceptions.base.BaseException;

/**
 * 뷰 컨트롤러에서 발생하는 모든 예외를 처리하는 글로벌 핸들러
 */
@Order(1)
@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * 기본 커스텀 예외 처리
     */
    @ExceptionHandler(BaseException.class)
    public String handleBaseException(BaseException ex, Model model) {
        logger.error("[{}] {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("errorCode", ex.getErrorCode());
        model.addAttribute("timestamp", java.time.LocalDateTime.now().toString());
        
        return "error/custom-error";
    }
    
    /**
     * 페이지를 찾을 수 없는 경우 404 처리
     */
    @ExceptionHandler({PageNotFoundException.class, NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePageNotFoundException(Exception ex, Model model) {
        String message = ex instanceof PageNotFoundException 
                        ? ex.getMessage() 
                        : "요청하신 페이지를 찾을 수 없습니다.";
        
        logger.error("404 에러 발생: {}", message, ex);
        
        model.addAttribute("errorMessage", message);
        model.addAttribute("timestamp", java.time.LocalDateTime.now().toString());
        
        return "error/404";
    }
    
    /**
     * 기타 모든 예외 처리 (500 에러)
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, Model model) {
        logger.error("서버 오류 발생: {}", ex.getMessage(), ex);
        
        model.addAttribute("errorMessage", "서비스 처리 중 오류가 발생했습니다.");
        model.addAttribute("timestamp", java.time.LocalDateTime.now().toString());
        
        return "error/500";
    }
}
