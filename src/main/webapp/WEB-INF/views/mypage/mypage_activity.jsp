<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="활동 내역" scope="request"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="mypage-page">
    
    <div class="mypage-layout-container">
    
        <jsp:include page="/WEB-INF/views/mypage/mypage_aside_menu.jsp" />
        
        <div class="mypage-content-wrapper">
            <h2 class="page-title">활동 내역</h2>
            
            <!-- 경쟁 구매 참여 내역 영역 -->
            <section class="activity-section">
                <h3 class="activity-subtitle1">경쟁 구매 참여 내역</h3>
                
                <c:choose>
                    <c:when test="${empty chatList}">
                        <p class="activity-empty-message">경쟁 구매 참여 내역이 없습니다.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="activity-table1">
                            <thead>
                                <tr>
                                    <th>상품명</th>
                                    <th>구매 수량</th>
                                    <th>참여 일시</th>
                                    <!-- <th>순위</th> -->
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${chatList}" var="chat">
                                    <tr>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/product/view/${chat.prdId}"
                                               class="activity-product-name-link">
                                                ${chat.prdNm}
                                                <span class="activity-product-tooltip">
                                                    <img src="${chat.prdImg}" alt="상품 이미지" />
                                                </span>
                                            </a>
                                        </td>
                                        <td>${chat.evntRmPtcptCnt}</td>
                                        <td>${chat.evntRmPtcptJnDt}</td>
                                        <!-- <td>
                                            <c:choose>
                                                <c:when test="${chat.evntRmPtcptRnk == 1}">
                                                    <span class="activity-rank-first">${chat.evntRmPtcptRnk}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    ${chat.evntRmPtcptRnk}
                                                </c:otherwise>
                                            </c:choose>
                                        </td> -->
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
                
                <!-- 경쟁 참여 페이지네이션 -->
                <div class="activity-pagination-wrapper">
                    <!-- ◀ 이전 그룹 이동 -->
                    <c:if test="${chatSearch.hasPrevGroup}">
                        <a href="${pageContext.request.contextPath}/mypage/activity?chatPageNo=${chatSearch.prevGroupStartPageNo}&roulettePageNo=${param.roulettePageNo}"
                           class="activity-page-link">◀ 이전</a>
                    </c:if>
                    
                    <!-- 페이지 번호 출력 -->
                    <c:forEach var="i" begin="${chatSearch.groupStartPageNo}" end="${chatSearch.groupEndPageNo}">
                        <a href="${pageContext.request.contextPath}/mypage/activity?chatPageNo=${i}&roulettePageNo=${param.roulettePageNo}"
                           class="activity-page-link <c:if test='${i == chatSearch.pageNo}'>active</c:if>">
                            ${i + 1}
                        </a>
                    </c:forEach>
                    
                    <!-- 다음 그룹 이동 ▶ -->
                    <c:if test="${chatSearch.hasNextGroup}">
                        <a href="${pageContext.request.contextPath}/mypage/activity?chatPageNo=${chatSearch.nextGroupStartPageNo}&roulettePageNo=${param.roulettePageNo}"
                           class="activity-page-link">다음 ▶</a>
                    </c:if>
                </div>
            </section>
            
            <!-- 룰렛 이용 내역 영역 -->
            <section class="activity-section">
                <h3 class="activity-subtitle2">룰렛 이용 내역</h3>
                
                <c:choose>
                    <c:when test="${empty rouletteList}">
                        <p class="activity-empty-message">룰렛 이용 내역이 없습니다.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="activity-table2">
                            <thead>
                                <tr>
                                    <th>당첨 혜택</th>
                                    <th>이용 일시</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${rouletteList}" var="roulette">
                                    <tr>
                                        <td>${roulette.bnftNm}</td>
                                        <td>${roulette.spnDt}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
                
                <!-- 룰렛 페이지네이션 -->
                <div class="activity-pagination-wrapper">
                    <!-- ◀ 이전 그룹 이동 -->
                    <c:if test="${rouletteSearch.hasPrevGroup}">
                        <a href="${pageContext.request.contextPath}/mypage/activity?roulettePageNo=${rouletteSearch.prevGroupStartPageNo}&chatPageNo=${param.chatPageNo}"
                           class="activity-page-link">◀ 이전</a>
                    </c:if>
                    
                    <!-- 페이지 번호 출력 -->
                    <c:forEach var="i" begin="${rouletteSearch.groupStartPageNo}" end="${rouletteSearch.groupEndPageNo}">
                        <a href="${pageContext.request.contextPath}/mypage/activity?roulettePageNo=${i}&chatPageNo=${param.chatPageNo}"
                           class="activity-page-link <c:if test='${i == rouletteSearch.pageNo}'>active</c:if>">
                            ${i + 1}
                        </a>
                    </c:forEach>
                    
                    <!-- 다음 그룹 이동 ▶ -->
                    <c:if test="${rouletteSearch.hasNextGroup}">
                        <a href="${pageContext.request.contextPath}/mypage/activity?roulettePageNo=${rouletteSearch.nextGroupStartPageNo}&chatPageNo=${param.chatPageNo}"
                           class="activity-page-link">다음 ▶</a>
                    </c:if>
                </div>
            </section>
        </div>
    </div> 
</div> 

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
