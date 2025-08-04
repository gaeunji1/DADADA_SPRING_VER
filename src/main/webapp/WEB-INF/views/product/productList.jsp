<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="pageTitle" value="상품 리스트" scope="request"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<div class="product-list-page">
    <h2>상품 리스트</h2>

    <c:if test="${empty productList}">
        
        <div class="product-nosearch">검색 결과가 없습니다.</div>
    </c:if>

    
    <div style="text-align: center; margin-bottom: 20px;"> 
        <c:if test="${sessionScope.loginUser.adminYn == 1}">
            <a href="${pageContext.request.contextPath}/product/register"
                class="admin-product-register-link"> 
                상품 등록 하기
            </a>
        </c:if>
    </div>

    
    <div class="product-container">
        <c:forEach var="product" items="${productList}">
            <div class="product-card">
                
                <a href="/product/view/${product.prdId}" class="product-card-link">
                    
                    <div class="product-image">
                        <img src="${product.prdImg}" alt="상품 이미지" />
                    </div>
                    
                    <div class="product-info">
                        <div class="product-name">${product.prdNm}</div>
                        <div class="product-price"><fmt:formatNumber value="${product.basePrice}" type="number" pattern="#,##0" />원</div>
                        <div class="product-review">리뷰 수: ${product.prdRvwCnt}개</div>
                    </div>
                </a>

				<%-- 상품 삭제 버튼 (관리자 전용) --%>
				<c:if test="${sessionScope.loginUser != null && sessionScope.loginUser.adminYn == 1}">
				    <%-- product-buttons 클래스 유지 --%>
					<div class="product-buttons">
					    <%-- delete-btn 클래스 유지 (CSS에서 button 스타일 적용) --%>
						<button type="button" class="delete-btn"
							data-prdid="${product.prdId}">상품 삭제</button>
					</div>
				</c:if>
			</div> <%-- End of product-card --%>
		</c:forEach>
	</div>

	<div class="pagination">
	        <a href="/product/list?pageNo=${pagination.prevGroupStartPageNo}&searchKeyword=${pagination.searchKeyword}">
	            이전
	        </a>
		
		<c:forEach var="i" begin="1" end="${pagination.pageCount}">
			<a
				href="/product/list?pageNo=${i}&searchKeyword=${pagination.searchKeyword}"
				class="${i == pagination.pageNo ? 'active' : ''}"> ${i} </a>
		</c:forEach>
	        <a href="/product/list?pageNo=${pagination.nextGroupStartPageNo}&searchKeyword=${pagination.searchKeyword}">
	            다음
	        </a>
    
	</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />