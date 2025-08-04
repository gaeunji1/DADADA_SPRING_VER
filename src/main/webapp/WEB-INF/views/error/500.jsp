<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>서버 오류</title>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .error-container {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            padding: 40px;
            max-width: 500px;
            width: 100%;
            text-align: center;
        }
        .error-code {
            font-size: 72px;
            font-weight: bold;
            color: #dc3545;
            margin-bottom: 10px;
        }
        .error-title {
            font-size: 24px;
            font-weight: 500;
            margin-bottom: 20px;
            color: #343a40;
        }
        .error-message {
            font-size: 16px;
            color: #6c757d;
            margin-bottom: 30px;
            line-height: 1.5;
        }
        .error-info {
            font-size: 14px;
            color: #adb5bd;
            margin-bottom: 30px;
            padding: 15px;
            background-color: #f8f9fa;
            border-radius: 4px;
            text-align: left;
        }
        .error-actions {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        .btn {
            padding: 10px 20px;
            border-radius: 4px;
            font-size: 14px;
            font-weight: 500;
            text-decoration: none;
            cursor: pointer;
            transition: all 0.2s;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0069d9;
        }
        .btn-secondary {
            background-color: #6c757d;
            color: white;
            border: none;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-code">500</div>
        <div class="error-title">서버 오류가 발생했습니다</div>
        <div class="error-message">
            ${errorMessage != null ? errorMessage : '서비스 처리 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.'}
        </div>
        <div class="error-info">
            <p>요청 시간: ${timestamp}</p>
        </div>
        <div class="error-actions">
            <a href="/" class="btn btn-primary">홈으로 돌아가기</a>
            <button onclick="history.back()" class="btn btn-secondary">이전 페이지로</button>
        </div>
    </div>
</body>
</html>
