<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 환불 처리 결과</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/refund-admin.css">
</head>
<body>
<div class="refund-admin-container">
  <div class="refund-admin-result">
    <h2 class="refund-admin-result-title">환불 요청 처리 완료!</h2>
    
    <div class="refund-admin-result-content">
      <p class="refund-admin-detail">imp_uid:</p> 
      <span class="refund-admin-uid">${impUid}</span>
    </div>
    
    <p class="refund-admin-message">${message}</p>
  </div>
</div>
</body>
</html>
