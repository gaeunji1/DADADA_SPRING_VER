<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="pageTitle" value="패키지 관리" scope="request"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="mypage-page">
    <div class="mypage-layout-container">

        <jsp:include page="/WEB-INF/views/mypage/mypage_aside_menu.jsp" />

        <div class="mypage-content-wrapper">
            <h2 class="page-title">패키지 관리</h2>
            <h5 class="page-subtitle">내가 만든 패키지를 조회/삭제할 수 있습니다.</h5>

            <c:choose>
                <c:when test="${empty myPackages}">
                    <div class="no-data">생성한 패키지가 없습니다.</div>
                </c:when>
                <c:otherwise>
                    <table class="my-package-table">
                        <thead>
                            <tr>
                                <th>패키지명</th>
                                <th>생성일</th>
                                <th>추천 수</th>
                                <th>삭제</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pkg" items="${myPackages}">
                                <tr>
                                    <td><a href="${pageContext.request.contextPath}/shoplaylist/view/${pkg.packageId}"
                                           class="mypage-package-name">${pkg.packageName}</a></td>
                                    <td>${pkg.createdAt}</td>
                                    <td>${pkg.recommendCount}</td>
                                    <td>
                                        <a href="/shoplaylist/delete/${pkg.packageId}"
                                           onclick="return confirm('정말 삭제하시겠습니까?')"
                                           class="btn btn-danger">삭제</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>

             <%-- 페이지네이션 등 필요시 여기에 추가 --%>

        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
