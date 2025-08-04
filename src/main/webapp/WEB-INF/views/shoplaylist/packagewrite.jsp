<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %> 
<c:set var="pageTitle" value="패키지 등록" scope="request"/> 

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<div class="package-write-page">
  
  <div class="package-write-container">
    <h2>패키지 등록</h2>
    
    <form action="/shoplaylist/write" method="post">
      <div class="back-button-wrap">
        <a href="/shoplaylist/list">
          
          <button type="button">&leftarrow; 뒤로가기</button> 
        </a>
      </div>

      
      <div> 
          <label>패키지명</label>
          <input type="text" name="packageName" required />
      </div>

      <div> 
          <label>설명</label>
          <textarea name="packageDescription"></textarea>
      </div>

      <div> 
          <label>상품 선택</label>
          <div class="product-list">
            <c:if test="${empty productList}">
              <p>선택할 수 있는 상품이 없습니다.</p> 
            </c:if>
            <c:forEach var="product" items="${productList}">
              <label class="product-checkbox-label">
                <input
                  type="checkbox"
                  name="productIds"
                  value="${product.productId}"
                />
                ${product.productName} (<fmt:formatNumber value="${product.basePrice}" type="number" pattern="#,##0" />원) 
              </label>
            </c:forEach>
          </div>
      </div>

       
      <button type="submit">등록</button>
    </form>
  </div> 
</div> 

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />