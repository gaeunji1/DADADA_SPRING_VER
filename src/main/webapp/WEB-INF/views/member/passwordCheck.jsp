<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>비밀번호 확인</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>

<div class="pwd-check-container">
    <h2 class="pwd-check-title">비밀번호 확인</h2>
    
    <div class="pwd-check-form">
        <form action="${pageContext.request.contextPath}/member/password-check" method="post">
            <div class="pwd-check-input-wrap">
                <label for="password" class="pwd-check-label">비밀번호 입력:</label>
                <input type="password" id="password" name="mbrPwd" class="pwd-check-input" required />
            </div>
            
            <button type="submit" class="pwd-check-btn">확인</button>
            
            <c:if test="${not empty errorMsg}">
                <p class="pwd-check-error">${errorMsg}</p>
            </c:if>
        </form>
    </div>
</div>

</body>
</html>
