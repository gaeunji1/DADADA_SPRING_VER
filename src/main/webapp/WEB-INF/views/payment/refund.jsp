<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 환불 요청</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/refund-admin.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
</head>
<body>
<div class="refund-admin-container">
  <h1 class="refund-admin-title">환불하기</h1>
  
  <form class="refund-admin-form" action="/refund/process" method="post">
    <div class="refund-admin-input-wrap">
      <label class="refund-admin-label" for="imp_uid">imp_uid 입력:</label>
      <input type="text" id="imp_uid" name="imp_uid" class="refund-admin-input" required placeholder="결제 고유번호를 입력하세요" />
    </div>
    
    <button type="submit" class="refund-admin-btn">환불 요청</button>
  </form>
</div>
</body>
</html>
