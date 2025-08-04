<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="pageJs" value="/js/cart.js" scope="request"/>
<c:set var="pageTitle" value="장바구니" scope="request"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<script>
  window.contextPath = '<c:out value="${pageContext.request.contextPath}" />';
  window.buyerEmail  = '<c:out value="${sessionScope.loginUser.mbrEmail}" />';
  window.buyerName   = '<c:out value="${sessionScope.loginUser.mbrNcknm}" />';

  <%-- 전화번호 컬럼이 생겼을 때만 주입 --%>
  <%--
  <c:if test="${not empty sessionScope.loginUser.mbrTel}">
    window.buyerTel = '<c:out value="${sessionScope.loginUser.mbrTel}" />';
  </c:if>
  --%>
</script>

<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<div class="cart-page">
  <div class="cart-page-section">
    <div class="cart-header">
      <h2>🛒 장바구니</h2>
      <p class="cart-subtitle">상품을 얼마나 많이 구매하는지에 따라 할인율이 달라집니다!</p>
    </div>

    <c:choose>
      <c:when test="${empty cartList}">
        <div class="cart-empty">
          <div class="cart-empty-icon">🛒</div>
          <p>장바구니에 담긴 상품이 없습니다.</p>
          <a href="${pageContext.request.contextPath}/product/list" class="cart-btn-common cart-continue-shopping-btn">쇼핑하러 가기</a>
        </div>
      </c:when>
      <c:otherwise>
        <div class="cart-container">
          <div class="cart-actions-top">
            <button type="button" id="btnClearCart" class="cart-btn-common cart-clear-btn">전체 삭제</button>
          </div>

          <div class="cart-items-table">
            <div class="cart-table-header">
              <div class="cart-col cart-col-select">선택</div>
              <div class="cart-col cart-col-info" style="justify-content: center;">상품 정보</div>
              <div class="cart-col cart-col-qty">수량</div>
              <div class="cart-col cart-col-price">가격 정보</div>
              <div class="cart-col cart-col-discount">할인 정보</div>
              <div class="cart-col cart-col-total">총액</div>
            </div>

            <c:forEach var="item" items="${cartList}">
              <div class="cart-item"
                   data-prdid="${item.prdId}"
                   data-cartid="${item.cartId}"
                   data-baseprice="${item.basePrice}"
                   data-prdname="${item.prdNm}">

                <div class="cart-col cart-col-select">
                  <button class="cart-delete-btn" data-cartid="${item.cartId}" data-prdid="${item.prdId}">
                    <img src="https://cdn-icons-png.flaticon.com/512/1214/1214428.png" alt="삭제" class="cart-delete-icon" />
                  </button>
                </div>

                <div class="cart-col cart-col-info">
                  <div class="cart-product-info">
                    <div class="cart-product-image">
                      <img src="${item.prdImg}" alt="${item.prdNm}" />
                    </div>
                    <div class="cart-product-details">
                      <div class="cart-product-name">${item.prdNm}</div>
                    </div>
                  </div>
                </div>

                <div class="cart-col cart-col-qty">
                  <div class="cart-quantity-control">
                    <button class="cart-qty-btn cart-qty-decrease" data-cartid="${item.cartId}" data-prdid="${item.prdId}">-</button>
                    <input type="number" class="cart-item-qty-input" min="1"
                           value="${item.qty}"
                           data-cartid="${item.cartId}"
                           data-prdid="${item.prdId}" />
                    <button class="cart-qty-btn cart-qty-increase" data-cartid="${item.cartId}" data-prdid="${item.prdId}">+</button>
                  </div>
                </div>

                <div class="cart-col cart-col-price">
                  <div class="cart-price-detail">
                    <div class="cart-base-price-label">정가</div>
                    <div class="cart-base-price-value"><fmt:formatNumber value="${item.basePrice}" pattern="#,###" />원</div>
                  </div>
                </div>

                <div class="cart-col cart-col-discount">
                  <div class="cart-discount-details">
                    <div class="cart-discount-row">
                      <span>단계별 수량:</span>
                      <span class="cart-step-qty"></span>
                    </div>
                    <div class="cart-discount-row">
                      <span>할인율:</span>
                      <span class="cart-discount-rate"></span>
                    </div>
                    <div class="cart-discount-row">
                      <span>최대 할인율:</span>
                      <span class="cart-max-discount-rate"></span>
                    </div>
                    <div class="cart-discount-row">
                      <span>할인금액:</span>
                      <span class="cart-total-discount-amount"></span>
                    </div>
                  </div>
                </div>

                <div class="cart-col cart-col-total">
                    <span class="cart-original-total-price" style="text-decoration: line-through; color: gray;"></span>
                    <div class="cart-item-total"></div>
                </div>
              </div>
            </c:forEach>
          </div>

          <div class="cart-summary">
            <div>상품 총액: <span id="productTotalPrice">${productTotal}</span>원</div>
            <div>배송비: <span id="shippingFee">${shippingFee}</span>원</div>
            <div class="cart-total-price-container">
              <span class="cart-total-price-label">총 결제 금액:</span>
              <span id="totalPrice" class="cart-total-price-value">${totalPayment}</span>
            </div>

            <div class="cart-actions-bottom">
              <button id="btnPay" class="cart-btn-primary cart-pay-btn">결제하기</button>
              <a href="${pageContext.request.contextPath}/product/list" class="cart-btn-secondary cart-continue-shopping-btn">계속 쇼핑하기</a>
            </div>
          </div>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />