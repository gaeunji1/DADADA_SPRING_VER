package com.ktdsuniversity.proj.dadada.exceptions.base;

import org.springframework.http.HttpStatus;

/**
 * 모든 커스텀 예외의 기본 클래스
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1124571366672315969L;
	
	private final String errorCode;
    private final HttpStatus status;
    
    public BaseException(String message, String errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
}
