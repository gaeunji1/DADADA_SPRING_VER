<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<div class="main-wrapper">

<c:if test="${not empty sessionScope.loginUser}">
  <p class="welcome-message">
    <strong>${sessionScope.loginUser.mbrNcknm}</strong> 님, 환영합니다!
  </p>
</c:if>

  <h3 class="main-section-title"># 플리</h3>

  <div class="main-banner-container">
    <div class="main-banner-slide" id="bannerSlide">
      <a href="${ctx}/shoplaylist/view/143">
        <img src="${ctx}/resources/img/pack3.png" alt="패키지 143" />
      </a>
      <a href="${ctx}/shoplaylist/view/65">
        <img src="${ctx}/resources/img/pack1.png" alt="패키지 65" />
      </a>
      <a href="${ctx}/shoplaylist/view/142">
        <img src="${ctx}/resources/img/pack2.png" alt="패키지 142" />
      </a>
      <a href="${ctx}/shoplaylist/view/143">
        <img src="${ctx}/resources/img/pack3.png" alt="패키지 143" />
      </a>
       <a href="${ctx}/shoplaylist/view/65">
        <img src="${ctx}/resources/img/pack1.png" alt="패키지 65" />
      </a>
    </div>
    <button class="main-banner-button main-banner-prev" onclick="prevSlide()">‹</button>
    <button class="main-banner-button main-banner-next" onclick="nextSlide()">›</button>
  </div>

  <div class="recent-panel">
    <h3 class="main-section-title">최근 본 상품</h3>

    <c:if test="${not empty sessionScope.recentProducts}">
      <div class="main-recent-grid">
        <c:forEach var="prod" items="${sessionScope.recentProducts}">
          <div class="main-recent-card">
            <a href="${pageContext.request.contextPath}/product/view/${prod.prdId}">
              <img src="${prod.prdImg}" alt="${prod.prdNm}" />
            </a>
          </div>
        </c:forEach>
      </div>
    </c:if>
    <c:if test="${empty sessionScope.recentProducts}">
      <p class="no-recent">아직 본 상품이 없습니다.</p>
    </c:if>
  </div>

  <a href="/roulette/spin" class="roulette-btn">
    <span class="spin-icon">🎰</span> 룰렛 돌리러 가기
  </a>

  <div class="left-ad-container">
    <a href="${ctx}/shoplaylist/list?activeTab=adminTab" class="left-ad">
      <img src="${pageContext.request.contextPath}/resources/img/admin-shoplay.png" alt="admin 패키지" />
    </a>
    <a href="${ctx}/shoplaylist/list?activeTab=userTab" class="left-ad">
      <img src="${pageContext.request.contextPath}/resources/img/user-shoplay.png" alt="user 패키지" />
    </a>
  </div>

  <h3 class="main-section-title">오늘의 추천 상품</h3>
  <div class="main-product-grid">
      <div class="main-product-card">
          <img src="https://sitem.ssgcdn.com/18/37/68/item/1000012683718_i1_750.jpg" alt="">
          <div class="main-product-hover"><a href="/product/view/13" class="main-buy-btn">구매하기</a></div>
      </div>
      <div class="main-product-card">
          <img src="https://asset.m-gs.kr/prod/65639578/1/550" alt="">
          <div class="main-product-hover"><a href="/product/view/21" class="main-buy-btn">구매하기</a></div>
      </div>
      <div class="main-product-card">
          <img src="https://roomnhtr8103.cdn-nhncommerce.com/data/goods/22/04/15/186798871/186798871_detail_062.jpg" alt="">
          <div class="main-product-hover"><a href="/product/view/22" class="main-buy-btn">구매하기</a></div>
      </div>
      <div class="main-product-card">
          <img src="https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/0c74/80f5ef7ece9b589933f168346d40d79ba3db7d3ae7c13df8cbe7f0242bc7.png" alt="">
          <div class="main-product-hover"><a href="/product/view/23" class="main-buy-btn">구매하기</a></div>
      </div>
      <div class="main-product-card">
          <img src="https://8717.co.kr/product_images/3491/3491113_500.jpg" alt="">
          <div class="main-product-hover"><a href="/product/view/24" class="main-buy-btn">구매하기</a></div>
      </div>
      <div class="main-product-card">
          <img src="https://image2.lotteimall.com/goods/65/69/84/1196846965_12.jpg/dims/resizemc/550x550/optimize" alt="">
          <div class="main-product-hover"><a href="/product/view/25" class="main-buy-btn">구매하기</a></div>
      </div>
  </div>

</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />