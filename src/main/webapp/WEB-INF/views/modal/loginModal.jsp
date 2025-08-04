<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="loginModal" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.5); z-index:1000;">
  <div style="background:#fff; width:400px; margin:100px auto; padding:20px; border-radius:8px; position:relative;">
    <h2>로그인</h2>
    
     <div id="login-error" style="color:red; margin-bottom:10px; display:none;"></div>

    <form id="loginForm" action="${pageContext.request.contextPath}/member/login" method="post">
      <div class="form-group" style="padding-right: 20px;">
        <input type="text" name="mbrInsId" placeholder="아이디" required style="width:100%; padding:8px; margin-bottom:10px;" />
      </div>
      <div class="form-group" style="padding-right: 20px;">
        <input type="password" name="mbrPwd" placeholder="비밀번호" required style="width:100%; padding:8px; margin-bottom:10px;" />
      </div>

      <button type="submit" style="width:100%; padding:10px; background:#ef5350; color:#fff; border:none; border-radius:4px;">로그인</button>
    </form>

    <button id="closeLoginModal" type="button" style="position:absolute; top:10px; right:10px; background:none; border:none; font-size:18px;">❌</button>
  </div>
</div>
