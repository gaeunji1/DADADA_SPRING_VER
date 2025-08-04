<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<h2 style="text-align: center;">다다다 쇼핑 메인페이지</h2>

<c:if test="${not empty sessionScope.loginUser}">
    <p style="text-align: center;">
        [관리자] <strong>${sessionScope.loginUser.mbrNcknm}</strong> 님, 환영합니다!
    </p>
</c:if>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />