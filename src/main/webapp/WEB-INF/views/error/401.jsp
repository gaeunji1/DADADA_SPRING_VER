<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>접근 권한 없음</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css" />
</head>
<body>
    
    <div class="unauthorized-page">
        
        <div class="permission-box">
            
            <h2>접근 권한이 없습니다</h2>
            <p>이 페이지는 관리자만 접근할 수 있습니다.</p>
            <a href="${pageContext.request.contextPath}/main">메인으로 돌아가기</a>
        </div>
    </div>
</body>
</html>