package com.ktdsuniversity.proj.dadada.exceptions.base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * API 오류 응답을 위한 표준 응답 객체 / 지금 사용 안함 임시.
 */
public class ErrorResponse {
    
    private final String timestamp;
    private final String message;
    private final String errorCode;
    private final int status;
    private final String path;
    
    public ErrorResponse(String message, String errorCode, int status, String path) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.path = path;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public int getStatus() {
        return status;
    }
    
    public String getPath() {
        return path;
    }
}
