<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="pageTitle" value="보유 혜택" scope="request"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="mypage-page">
  <div class="mypage-layout-container">
    
    <jsp:include page="/WEB-INF/views/mypage/mypage_aside_menu.jsp" />

    <div class="mypage-content-wrapper">
      <h2 class="page-title">보유 혜택</h2>

      <!-- 콘텐츠 2열 분할 -->
      <div class="reward-content-grid">
        
        <!-- 좌측: 혜택 목록 -->
        <div class="mypage-page reward-left">
          <div class="filter-select">
            <label for="filter-type">경품 유형 선택: </label>
            <select id="filter-type">
              <option value="all">전체</option>
              <option value="2">무료배송</option>
              <option value="3">추가 룰렛 찬스</option>
              <option value="4">사은품 증정</option>
              <option value="5">할인</option>
            </select>
          </div>
          
          

          <div class="reward-container" id="reward-container">
            <c:forEach var="spin" items="${rewardList}" varStatus="status">
              <div class="reward-card"
                   data-type="${spin.benefitType}"
                   <c:if test="${status.index >= 5}">style="display: none;"</c:if>>
                <div class="reward-name">${spin.benefitName}</div>
                <div class="reward-date"><strong>당첨 일시:</strong> ${spin.spinDatetime}</div>
                <div class="reward-claim"><strong>수령 여부:</strong>
                  <c:choose>
                    <c:when test="${spin.isClaimed == 1}">자동 수령 완료</c:when>
                    <c:otherwise>미수령</c:otherwise>
                  </c:choose>
                </div>
              </div>
            </c:forEach>

            <c:if test="${fn:length(rewardList) > 5}">
                <div class="show-more-container">
                   <button id="toggle-view-btn" class="btn-toggle-view">더 보기</button>
                </div>

            </c:if>
          </div>
        </div>

        <!-- 우측: 포인트 내역 --> 
        <div class="mypage-page reward-right">
          <div class="point-summary">
            <h3>사용 가능한 총 포인트</h3>
            <div class="point-value" id="totalPoint">${totalPoint}</div>
          </div>
          <div class="point-history">
            <h4>포인트 획득 내역</h4>
            <c:forEach var="spin" items="${rewardList}">
              <c:if test="${spin.benefitType == 1}">
                <div class="point-entry">${spin.spinDatetime} - ${spin.benefitValue} P</div>
              </c:if>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- 스크립트: 필터 + 더 보기 -->
<script>
  // 경품 유형 필터 기능 (기존 그대로 유지)
  document.querySelector("#filter-type").addEventListener("change", function () {
    const selected = this.value;
    const allCards = document.querySelectorAll(".reward-card");

    allCards.forEach((card, index) => {
      const type = card.getAttribute("data-type");
      const shouldShow = (selected === "all" || selected === type);

      // 펼쳐보기 상태에서는 전부 보여주고 접은 상태에서는 5개만
      if (shouldShow) {
        if (!isExpanded && index >= 5) {
          card.style.display = "none";
        } else {
          card.style.display = "";
        }
      } else {
        card.style.display = "none";
      }
    });
  });

  // 더 보기 / 접기 버튼 토글 기능
  const toggleBtn = document.querySelector("#toggle-view-btn");
  const allCards = document.querySelectorAll(".reward-card");
  let isExpanded = false;

  if (toggleBtn) {
    toggleBtn.addEventListener("click", function () {
      const selected = document.querySelector("#filter-type").value;

      allCards.forEach((card, index) => {
        const type = card.getAttribute("data-type");
        const shouldShow = (selected === "all" || selected === type);

        if (!shouldShow) {
          card.style.display = "none";
        } else if (index >= 5) {
          card.style.display = isExpanded ? "none" : "block";
        }
      });

      toggleBtn.textContent = isExpanded ? "더 보기" : "접기";
      isExpanded = !isExpanded;
    });
  }
  
  document.addEventListener("DOMContentLoaded", function () {
      const pointEl = document.getElementById("totalPoint");
      if (pointEl) {
        const raw = parseInt(pointEl.textContent.replace(/[^\d]/g, ""));
        pointEl.textContent = raw.toLocaleString() + " P";
      }

      // 포인트 내역들도 포맷팅
      document.querySelectorAll(".point-entry").forEach(entry => {
        entry.textContent = entry.textContent.replace(/(\d{4,}) P/, function (match, p1) {
          return parseInt(p1).toLocaleString() + " P";
        });
      });
    });
</script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
