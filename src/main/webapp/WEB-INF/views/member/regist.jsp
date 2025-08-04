<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="회원가입" scope="request"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="regist-container">
    <h2>회원가입</h2>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <form action="/member/regist" method="post" onsubmit="return validateForm()">
        <label>아이디:</label>
        
        <div class="input-group">
            <input type="text" name="mbrInsId" placeholder="아이디" required>
            <button type="button" onclick="checkIdDuplication()">중복확인</button>
        </div>
        
        <span id="idError" class="error-msg"></span>

    <label>비밀번호:</label><br>
    <input type="password" name="mbrPwd" placeholder="비밀번호" required><br>
    <span id="pwdError" class="error-msg"></span><br><br>

    <label>비밀번호 확인:</label><br>
    <input type="password" name="mbrPwdCheck" placeholder="비밀번호 확인" required oninput="validatePasswordMatch()">
    <span id="pwdCheckError"></span>

        <label>이메일:</label>
        <div class="input-group">
            <input type="text" name="mbrEmail" placeholder="이메일" required>
            <button type="button" onclick="sendAuthCode()">인증코드 전송</button>
        </div>

    <label>인증코드 입력:</label>
    <div class="input-group">
        <input type="text" name="emailCode" placeholder="인증코드" required>
        <button type="button" id="verifyButton" onclick="verifyCode()" disabled>인증 확인</button>
    </div>

    <label>휴대전화번호:</label><br>
    <div class="input-group">
        <input type="text" name="mbrPhNo" id="mbrPhNo" placeholder="010-1234-5678" required>
    </div><br><br>
    <span id="phoneError" class="error-msg"></span>

        <label>닉네임:</label><br>
        <input type="text" name="mbrNcknm" placeholder="닉네임" required oninput="validateNickname()"><br>
        <span id="ncknmError" class="error-msg"></span><br><br>

        <label>우편번호:</label>
        
        <div class="address-group">
            <input type="text" id="sample6_postcode" name="mbrPstlCd" placeholder="우편번호" readonly>
            <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
        </div>

        <label>주소:</label><br>
        <input type="text" id="sample6_address" name="mbrAddr1" placeholder="기본 주소" readonly><br><br>

        <label>상세주소:</label><br>
        <input type="text" id="sample6_detailAddress" name="mbrAddr2" placeholder="상세 주소"><br><br>

        <input type="hidden" id="sample6_extraAddress">
        <input type="hidden" name="adminYn" value="0" />
        <input type="hidden" name="bnftCnt" value="0" />
        <input type="hidden" name="stackDscntRt" value="0.0" />

        <button type="submit" id="submitBtn" disabled>가입하기</button>
    </form>
    <p><a href="/member/login">로그인하러 가기</a></p>
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />