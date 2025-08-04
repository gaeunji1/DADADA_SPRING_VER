<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="회원정보 수정" scope="request"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<div class="modify-container">
    <h2>회원정보 수정</h2>

    <c:if test="${not empty error}">
        
        <p class="error-msg">${error}</p>
    </c:if>

    <form action="/member/update" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="mbrId" value="${member.mbrId}" />

        
        <div class="form-group">
            <label>아이디:</label>
            <input type="text" name="mbrInsId" value="${member.mbrInsId}" readonly>
        </div>

        <div class="form-group">
            <label>비밀번호:</label>
            <input type="password" name="mbrPwd" placeholder="새 비밀번호 입력(변경하지 않을 시 기존 비밀번호입력)">
        </div>

        <div class="form-group">
            <label>이메일:</label>
            
            <div class="input-group">
                <input type="text" name="mbrEmail" value="${member.mbrEmail}" required>
                
                <button type="button" onclick="sendAuthCode()" class="btn-common">인증코드 전송</button>
            </div>
        </div>

        <div class="form-group">
            <label>인증코드 입력:</label>
            <div class="input-group">
                <input type="text" name="emailCode" placeholder="인증코드" required>
                <button type="button" id="verifyButton" onclick="verifyCode()" class="btn-common" disabled>인증 확인</button>
            </div>
        </div>

        <div class="form-group">
            <label>휴대전화번호:</label>
            <input type="text" name="mbrPhNo" id="mbrPhNo" value="${member.mbrPhNo}" placeholder="010-0000-0000" required oninput="validatePhoneNumber()">
            <span id="phoneError" class="error-msg"></span>
        </div>

        <div class="form-group">
            <label>닉네임:</label>
            <input type="text" name="mbrNcknm" value="${member.mbrNcknm}" required>
        </div>

        <div class="form-group">
            <label>우편번호:</label>
            <div class="input-group"> 
                
                <input type="text" id="sample6_postcode" name="mbrPstlCd" value="${member.mbrPstlCd}" readonly>
                 
                <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="btn-common">
            </div>
        </div>

        <div class="form-group">
            <label>주소:</label>
            <input type="text" id="sample6_address" name="mbrAddr1" value="${member.mbrAddr1}" readonly>
        </div>

        <div class="form-group">
            <label>상세주소:</label>
            <input type="text" id="sample6_detailAddress" name="mbrAddr2" value="${member.mbrAddr2}">
        </div>

        <input type="hidden" id="sample6_extraAddress">
        <input type="hidden" name="adminYn" value="${member.adminYn}" />
        <input type="hidden" name="bnftCnt" value="${member.bnftCnt}" />
        <input type="hidden" name="stackDscntRt" value="${member.stackDscntRt}" />

        
        <div class="btn-row">
            <button type="submit" id="submitBtn" class="btn-common" disabled>수정하기</button>
        </div>
    </form>

    
    <form action="/member/delete" method="post" onsubmit="return confirm('정말 탈퇴하시겠습니까?');">
        <input type="hidden" name="mbrId" value="${member.mbrId}" />
        <div class="btn-row">
            
            <button type="submit" class="withdraw-btn">회원 탈퇴</button>
        </div>
    </form>

    <p><a href="/main">메인으로</a></p>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />