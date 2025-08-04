<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>다다다 - 경쟁 구매방 #${roomId}</title>
  
  <!-- 기본 CSS 및 스크립트 -->
  <link rel="stylesheet" href="/css/common.css" type="text/css" />
  
  <!-- jQuery 및 필요한 라이브러리 -->
  <script src="/js/jquery-3.7.1.min.js"></script>
  <script src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
  <script src="${pageContext.request.contextPath}/js/payment.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sockjs-client/dist/sockjs.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js"></script>
  
  <script>
    // context path 설정 (원래 헤더에서 제공하던 부분)
    window.contextPath = '${pageContext.request.contextPath}';
    window.ctx = window.contextPath;
    
    // 기존 스크립트 변수들
    var ctx         = '${pageContext.request.contextPath}';
    var roomId      = '${roomId}';
    var userId      = '${userId}';
    var nick        = '${nick}';

    // 결제 정보 전역 변수
    window.productName  = '${product.prdNm}';
    window.productId = ${product.prdId};
    window.productPrice = ${product.basePrice};
    window.buyerEmail   = '${sessionScope.loginUser.mbrEmail}';
    window.buyerName    = '${sessionScope.loginUser.mbrNcknm}';
    window.buyerAddr    = '${sessionScope.loginUser.mbrAddr1} ${sessionScope.loginUser.mbrAddr2}';
    window.buyerPostcode= '${sessionScope.loginUser.mbrPstlCd}';
    window.orderDvsn = 'competition';

    var client;

    // 남은 시간(ms)을 MM:SS로 포맷팅
    function formatTime(ms) {
      var totalSec = Math.ceil(ms / 1000);
      var m = Math.floor(totalSec / 60);
      var s = totalSec % 60;
      function pad(n){ return n < 10 ? '0' + n : n; }
      return pad(m) + ':' + pad(s);
    }

    document.addEventListener('DOMContentLoaded', function() {
      // 모달 열기
      function showCompetitionNotice() {
        document.getElementById('competitionNoticeModal').style.display = 'flex';
      }

      // 모달 닫기
      document.getElementById('closeCompetitionModal').onclick =
      document.getElementById('confirmCompetitionModal').onclick = function() {
        document.getElementById('competitionNoticeModal').style.display = 'none';
      };
      showCompetitionNotice();
      
      var sock = new SockJS(ctx + '/ws/chat');
      client = Stomp.over(sock);

      client.connect({}, function() {
        // 룸 상태 및 결과 구독
        client.subscribe('/topic/room/' + roomId, function(message) {
          var data = JSON.parse(message.body);

          if (data.status) {
            var ul = document.getElementById('participants');
            ul.innerHTML = '';
            (data.participants || []).forEach(function(n) {
              var li = document.createElement('li');
              li.textContent = n;
              ul.appendChild(li);
            });
            document.getElementById('status').textContent = data.status;
            document.getElementById('timer').textContent = formatTime(data.remainMillis);
            document.getElementById('submitBtn').disabled = data.status !== 'ACTIVE' || window.hasSubmitted;
          } else if (data.winnerId) {
                var allSubs = data.submissions || {};
                var sorted = Object.entries(allSubs).sort((a, b) => b[1] - a[1]);
                var myRank = sorted.findIndex(e => e[0] === nick) + 1;
                var myQty = allSubs[nick] || 0;

                window.selectedQty = myQty;

                requestPay(function(rsp) {
                  if (data.winnerId === parseInt(userId, 10)) {
                    // 🎉 1등
                    alert('🎉 축하합니다! 1등으로 룰렛 쿠폰 10장이 지급되었습니다!');

                    var msg =
                      '모든 유저 구매수량:\n' +
                      sorted.map(e => e[0] + ': ' + e[1] + '개').join('\n') +
                      '\n\n당신의 등수는 ' + myRank + '등입니다.';
                    alert(msg);
                    window.location.href = ctx
                    + '/payment/result?imp_uid=' + rsp.imp_uid
                    + '&askPackage=false';
                    

                  } else {
                    // 😢 1등 아님
                    var msg =
                      '모든 유저 구매수량:\n' +
                      sorted.map(e => e[0] + ': ' + e[1] + '개').join('\n') +
                      '\n\n당신의 등수는 ' + myRank + '등입니다.\n\n아쉽게도 1등은 아닙니다. 다음에 다시 도전해 주세요!';
                    alert(msg);
                    window.location.href = ctx
                    + '/payment/result?imp_uid=' + rsp.imp_uid
                    + '&askPackage=false';
                  }
                });
              }
        });

        // 입장 메시지 전송
        client.send('/app/room/enter', {}, JSON.stringify({
          roomId:   roomId,
          userId:   userId,
          nickname: nick
        }));
      });
    });

    // 숫자 제출 및 중복 방지
    function submitNum() {
      if (!client || !client.connected) {
        alert('서버 연결이 아직 준비되지 않았습니다.');
        return;
      }
      var inputEl  = document.getElementById('num');
      var num      = parseInt(inputEl.value, 10);
      var maxStock = parseInt(inputEl.getAttribute('max'), 10);
      if (num < 1) {
    	  alert('1 이상을 입력해야 합니다!');
    	  return;
      }
      if (num > maxStock) {
        alert('입력 가능한 개수를 넘었습니다.');
        return;
      }
      // 중복 제출 방지
      if (window.hasSubmitted) {
        alert('이미 제출하셨습니다.');
        return;
      }

      client.send('/app/room/submit', {}, JSON.stringify({
        roomId: roomId,
        userId: parseInt(userId, 10),
        number: num
      }));

      // 제출 후 버튼·입력 비활성화
      window.hasSubmitted = true;
      inputEl.disabled = true;
      document.getElementById('submitBtn').disabled = true;
    }
  </script>
  
</head>
<body>
  <!-- 미니 헤더 - 로고와 홈 버튼 -->
  <div class="competition-mini-header">
    <div class="competition-mini-logo">
      <img src="${pageContext.request.contextPath}/resources/img/dadada_logo.png" alt="다다다 로고" />
      <span>경쟁 구매방</span>
    </div>
    
    <div class="mini-user-info">
      <span><strong>${nick}</strong>님 참여 중</span>
    </div>
    
    <div class="competition-mini-actions">
      <a href="${pageContext.request.contextPath}/main" class="mini-action-btn home-btn">
        <img src="https://cdn-icons-png.flaticon.com/512/1946/1946488.png" alt="홈" style="width:16px;height:16px;" />
        홈으로
      </a>
    </div>
  </div>

  <!-- 경쟁방 컨텐츠 -->
  <div class="competition-room-page">
    <div class="page-section">
      <!-- 방 정보 섹션 -->
      <div id="room-info">
        <h2>경쟁 구매방 #${roomId}</h2>
        
        <!-- 상품 정보 카드 -->
        <div class="product-card">
          <div class="product-image">
            <img src="${product.prdImg}" alt="${product.prdNm}" />
          </div>
          <div class="product-details">
            <h3>${product.prdNm}</h3>
            <p class="product-id">상품 ID: ${product.prdId}</p>
            <p class="product-price">상품가격: <fmt:formatNumber value="${product.basePrice}" type="number" pattern="#,##0" />원</p>
            <p class="product-stock">남은 재고: ${product.prdQty}개</p>
          </div>
        </div>
        
        <!-- 경쟁 상태 섹션 -->
        <div class="competition-status">
          <div class="status-item">
            <span class="status-label">방 상태:</span>
            <span id="status" class="status-value">연결 중...</span>
          </div>
          <div class="status-item timer">
            <span class="status-label">남은 시간:</span>
            <span id="timer" class="status-value">--:--</span>
          </div>
        </div>
      </div>
      
      <!-- 참가자 목록 섹션 -->
      <div id="participants-section">
        <h3>참가자 목록</h3>
        <ul id="participants">
          <li class="connecting">연결 중...</li>
        </ul>
      </div>
      
      <!-- 숫자 제출 섹션 -->
      <div id="submission-section">
        <h3>구매 수량 제출</h3>
        <p class="submission-guide">최대 구매 수량: ${product.prdQty}개</p>
        
        <div class="submission-form">
          <div class="quantity-selector">
            <input id="num" type="number" min="1" max="${product.prdQty}" value="1" placeholder="구매 수량 입력" />
          </div>
          <button id="submitBtn" onclick="submitNum()" disabled>제출하기</button>
        </div>
        
        <div class="submission-note">
          <p>* 한 번 제출하면 수정할 수 없습니다</p>
          <p>* 남은 시간이 종료되면 결제가 자동으로 진행됩니다</p>
          <p>* 가장 많은 수량을 구매한 1등에게 룰렛 쿠폰이 제공됩니다</p>
        </div>
      </div>
    </div>
  </div>

  <!-- 모달 include - 원래 있던 것 활용 -->
  <jsp:include page="/WEB-INF/views/modal/roomModal.jsp" />

</body>
</html>
