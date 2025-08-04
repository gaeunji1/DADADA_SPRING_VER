<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="룰렛 돌리기" scope="request"/>

<script>
  var segments = [
    <c:forEach items="${benefitList}" var="b" varStatus="st">
      { id: ${b.benefitId}, name: "${b.benefitName}" }<c:if test="${!st.last}">,</c:if>
    </c:forEach>
  ];
  console.log("Segments:", segments);
</script>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="roulette-spin-page">
  <div class="roulette-wrapper">
    <h1>룰렛 돌리기</h1>

    <c:if test="${not empty sessionScope.loginUser}">
      <p class="username">
        닉네임:
        <span class="highlight-username">${sessionScope.loginUser.mbrNcknm}</span>
      </p>

      <p class="bonus-label">응모권 보유량</p>
      <p class="bonus-count">
        <span id="bonusCountDisplay">${sessionScope.loginUser.bnftCnt}</span>개
      </p>

      <div class="roulette-container">
        <div id="rouletteWheel" class="roulette-wheel">
        </div>
        <div class="pointer"></div>
      </div>
      <button type="button" class="spin-button" <c:if test="${sessionScope.loginUser.bnftCnt <= 0}">disabled</c:if>> 
          룰렛 돌리기
      </button>
    </c:if>

    <c:if test="${empty sessionScope.loginUser}">
      <p>로그인 후 이용해 주세요.
        <a href="${pageContext.request.contextPath}/member/login">로그인</a>
      </p>
    </c:if>

    <div id="spinModal" class="modal-overlay">
      <div class="modal-content">
        <p id="spinMessage"></p>
        <button id="closeSpinModal">닫기</button>
      </div>
    </div>
  </div> 
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />