<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 모달용 환불 결과 콘텐츠 -->
<div class="refund-admin-container">
  <div class="refund-admin-result">
    <h2 class="refund-admin-result-title">환불 요청 처리 완료!</h2>
    
    <div class="refund-admin-result-content">
      <p class="refund-admin-detail">imp_uid:</p> 
      <span class="refund-admin-uid">${impUid}</span>
    </div>
    
    <p class="refund-admin-message">${message}</p>
    
    <button type="button" class="refund-admin-btn" onclick="closeRefundModal()">닫기</button>
  </div>
</div>

<script>
  function closeRefundModal() {
    document.getElementById("refundModal").style.display = "none";
  }
</script>
