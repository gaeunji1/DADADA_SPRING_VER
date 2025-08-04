<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 모달용 환불 폼 콘텐츠 -->
<div class="refund-admin-container">
  <h1 class="refund-admin-title">환불하기</h1>
  
  <form class="refund-admin-form" id="refundForm">
    <div class="refund-admin-input-wrap">
      <label class="refund-admin-label" for="imp_uid">imp_uid 입력:</label>
      <input type="text" id="imp_uid" name="imp_uid" class="refund-admin-input" required placeholder="결제 고유번호를 입력하세요" />
    </div>
    
    <button type="submit" class="refund-admin-btn">환불 요청</button>
  </form>
</div>

<script>
  $(document).ready(function() {
    $("#refundForm").submit(function(e) {
      e.preventDefault();
      
      $.ajax({
        url: "/refund/process",
        type: "POST",
        data: {
          imp_uid: $("#imp_uid").val()
        },
        success: function(response) {
          // 성공 시 결과 보여주기
          $("#modalBody").html(response);
        },
        error: function() {
          alert("환불 처리 중 오류가 발생했습니다.");
        }
      });
    });
  });
</script>
