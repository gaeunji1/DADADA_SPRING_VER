<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="pageTitle" value="${product.prdNm} 상세 정보" scope="request"/> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<script>
  const contextPath = '${pageContext.request.contextPath}';
</script>


<div class="product-detail-page">
  
  <div class="page-section">
    
    <c:if test="${not empty error}">
        <script>alert("${error}");</script>
    </c:if>
    
    <div id="product-detail"
         data-prdid="${product.prdId}"
         data-mbrid="${sessionScope.loginUser.mbrId}"
         data-context-path="${pageContext.request.contextPath}">
        <h2>상품 상세정보</h2>
        
        <div><img src="${product.prdImg}" alt="상품 이미지" /></div>
        
        <div class="product-info-text">
            <p><strong>상품명:</strong> ${product.prdNm}</p>
            <p><strong>공급업체:</strong> ${product.supplierVO.splrNm}</p>
            <p><strong>가격:</strong> <fmt:formatNumber value="${product.basePrice}" type="number" pattern="#,##0" />원</p>
            <c:if test="${product.prdDscntPrc > 0}"> 
                <p><strong>할인:</strong> <fmt:formatNumber value="${product.prdDscntPrc}" type="number" pattern="#,##0" />원</p>
                 <p><strong>최종 가격:</strong> <fmt:formatNumber value="${product.basePrice - product.prdDscntPrc}" type="number" pattern="#,##0" />원</p>
            </c:if>
        </div>
        
        <div id="product-qty" style="margin-top: 20px;">
            <button type="button" id="decreaseQty">-</button>
            <input type="text" id="quantity" value="1" readonly style="width: 40px; text-align: center; margin: 0 5px;" />
            <button type="button" id="increaseQty">+</button>
        </div>

        <div class="action-buttons">
            <a> 
                <button type="button" id="addToCartBtn" class="btn-cart">장바구니</button>
            </a>
            <a>
                <button type="button" id="buyroomBtn" class="btn-competition">경쟁구매하기</button>
            </a>
        </div>
    </div> 
<div id="review-section">
        <h3>리뷰</h3>
        <div class="review-average">
        <span class="rating-text">${avgRating}점</span> / 5점
        <span class="rating-stars">
            <c:forEach var="i" begin="1" end="5">
                <c:choose>
                    <c:when test="${i <= avgRating}">
                        ⭐
                    </c:when>
                    <c:otherwise>
                        ☆
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </span>
    </div>
        
        
        <div id="review-list">
        </div>
        <div class="review-form">
            <input type="text" id="reviewTitle" placeholder="리뷰 제목" required />
            <textarea id="reviewContent" placeholder="리뷰 내용" required></textarea>
            <select id="rating" required>
                <option value="">별점을 선택하세요</option>
                <option value="5">★★★★★ (5점)</option>
                <option value="4">★★★★☆ (4점)</option>
                <option value="3">★★★☆☆ (3점)</option>
                <option value="2">★★☆☆☆ (2점)</option>
                <option value="1">★☆☆☆☆ (1점)</option>
            </select>
            <button type="button" id="reviewSubmitBtn" >리뷰 작성</button>
        </div>
    </div> 
    <div id="pagination"></div>

  </div> 
</div> 
<jsp:include page="/WEB-INF/views/modal/loginModal.jsp" />
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />