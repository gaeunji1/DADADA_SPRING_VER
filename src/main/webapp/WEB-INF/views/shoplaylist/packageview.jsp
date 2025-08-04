<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="pageTitle" value="패키지 상세 보기" scope="request"/> 
 
<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<div class="package-view-page">
  
  <div class="package-view-container">
    <h2>패키지 상세 보기</h2>

    
    <table>
        <thead> 
          <tr>
            <th>패키지명</th>
            <td>${shoplaylist.packageName}</td>
          </tr>
        </thead>
       <tbody> 
          <tr>
            <th>설명</th>
            <td>${shoplaylist.packageDescription}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${shoplaylist.memberName}</td>
          </tr>
          <tr>
            <th>생성일</th>
            <td>${shoplaylist.createdAt}</td>
          </tr>
       </tbody>
    </table>

	<c:if test="${shoplaylist.creatorType != 'user' and not empty shoplaylist.packageImage}">
    <div>
	    <img src="${shoplaylist.packageImage}" style="width: 1100px; height: 380px;" />
	  </div>
	</c:if>
	

    <br />
    <c:set var="currentUser" value="${sessionScope.loginUser}" />
    <c:set var="isAdmin" value="${currentUser.adminYn == 1}" />
    <c:set var="isAuthor" value="${currentUser.mbrId == shoplaylist.memId}" />
    <c:set var="canDelete" value="${isAdmin or isAuthor}" />

    <h3>포함된 상품</h3>

    <c:choose>
      <c:when test="${not empty shoplaylist.productList}">
        
        <table class="product-table">
          <thead>
            <tr>
              <th>상품명</th>
              <th>가격</th>

              <th>이미지</th>
              <c:if test="${canDelete}">
                <th>삭제</th>
              </c:if>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="product" items="${shoplaylist.productList}">
              <tr>
                <td>
	                <a href="${pageContext.request.contextPath}/product/view/${product.productId}">
	                ${product.productName}
	                </a>
                </td>
                <td><fmt:formatNumber value="${product.basePrice}" type="number" pattern="#,##0" />원</td> 
                <td>
                  <c:if test="${not empty product.productImage}">
                    <a href="${pageContext.request.contextPath}/product/view/${product.productId}">
                    <img
                      src="${product.productImage}"
                      alt="상품 이미지"
                    />
                    </a>
                  </c:if>
                </td>
                <c:if test="${canDelete}">
                  <td>
                    <form
                      action="/shoplaylist/delete/${shoplaylist.packageId}/product/${product.productId}"
                      method="get"
                      onsubmit="return confirm('정말 이 상품을 삭제하시겠습니까?');"
                    >
                      <button type="submit">삭제</button> 
                    </form>
                  </td>
                </c:if>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:when>
      <c:otherwise>
        <p>포함된 상품이 없습니다.</p> 
      </c:otherwise>
    </c:choose>

    <br />
    
    <div class="action-buttons">
	  <c:choose>
	    <c:when test="${shoplaylist.creatorType == 'admin'}">
	      <button onclick="location.href='/shoplaylist/list?activeTab=adminTab'">
	        &leftarrow; 목록으로 돌아가기
	      </button>
	    </c:when>
	    <c:otherwise>
	      <button onclick="location.href='/shoplaylist/list?activeTab=userTab'">
	        &leftarrow; 목록으로 돌아가기
	      </button>
	    </c:otherwise>
    </c:choose>
    </div>
  </div> 
</div> 


<jsp:include page="/WEB-INF/views/layout/footer.jsp" />