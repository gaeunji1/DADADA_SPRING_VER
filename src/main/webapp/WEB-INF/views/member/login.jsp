<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="로그인" scope="request"/>

<c:set var="redirect" value="${param.redirect}" scope="request"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<div class="login-container">
    <h2>로그인</h2>

    <c:if test="${not empty error}">
        
        <p class="error-msg">${error}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/member/login" method="post" onsubmit="return validateLoginForm()">
        
        <div class="form-group">
            <label>아이디:</label>
            <input type="text" name="mbrInsId" required>
            <span id="idError" class="error-msg"></span>
        </div>

        <div class="form-group">
            <label>비밀번호:</label>
            <input type="password" name="mbrPwd" required>
            <span id="pwError" class="error-msg"></span>
        </div>

      <input type="hidden" id="redirect" name="redirect" value="${redirect}" />


        
        <div class="btn-container">
            <button type="submit" class="btn-common">로그인</button>
        </div>

        <div class="form-group">
            <label>
                <input type="checkbox" name="autoLogin">
                이 기기에서 자동 로그인
            </label>
        </div>
    </form>

    <p><a href="${pageContext.request.contextPath}/member/regist">회원가입 하러가기</a></p>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />