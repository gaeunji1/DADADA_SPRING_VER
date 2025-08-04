<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
  <title>${empty pageTitle ? 'DADADA' : pageTitle}</title>
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/img/top_logo.png">
  <script>
    window.contextPath = '<c:out value="${pageContext.request.contextPath}" />';
    window.ctx         = window.contextPath;
  </script>
  
  <link rel="stylesheet" href="/css/common.css" type="text/css" />

  <c:if test="${not empty pageCss}">
    <link rel="stylesheet" href="${pageCss}" type="text/css" />
  </c:if>
  <script src="/js/jquery-3.7.1.min.js"></script>
    <script src="/js/common.js" type="text/javascript"></script>
  
  
  <c:if test="${not empty pageJs}">
    <script src="${pageJs}"></script>
  </c:if>

  
</head>
<body>
  <div class="header-wrapper">
    <header class="header-container">
      <div class="header-logo">
        <a href="${ctx}/main">
          <img src="${ctx}/resources/img/dadada_logo.png" alt="DADADA Logo" />
        </a>
        
      </div>
      <div class="header-search">
        <form action="${ctx}/product/list" method="get" style="display:flex;align-items:center;width:100%;">
          
          <input type="text" id="searchInput" name="searchKeyword" class="header-search-input" placeholder="상품 검색..." />
          
          <button type="submit" id="searchIcon" class="header-search-btn">
            <img src="https://cdn-icons-png.flaticon.com/512/622/622669.png" alt="검색" style="width:22px;height:22px;border:0;background:transparent;" />
          </button>
        </form>
      </div>
      <div class="header-icons">
        
        <a href="${ctx}/mypage" class="header-icon-link">
          
          <img src="https://cdn-icons-png.flaticon.com/512/149/149071.png" alt="마이페이지" class="header-icon-img" />
          
          <div class="header-icon-label">마이페이지</div>
        </a>
        
        <a href="${ctx}/cart" class="header-icon-link">
          
          <img src="https://cdn-icons-png.flaticon.com/512/263/263142.png" alt="장바구니" class="header-icon-img" />
          
          <div class="header-icon-label">장바구니</div>
        </a>
      </div>
    </header>
    <nav class="nav-container">
      <ul class="nav-ul">
        
        
        <li class="nav-li nav-category-menu">
          
          <img src="https://cdn-icons-png.flaticon.com/512/1828/1828859.png" alt="카테고리" class="nav-bar-icon" />
          
          
        </li>

        
        <li class="nav-li nav-right">
          <c:choose>
            <c:when test="${not empty sessionScope.loginUser}">
              <span><strong>${sessionScope.loginUser.mbrNcknm}</strong>님</span>
              <a href="${ctx}/member/logout">로그아웃</a>
            </c:when>
            <c:otherwise>
              <a href="${ctx}/member/login">로그인</a> |
              <a href="${ctx}/member/regist">회원가입</a> |
            </c:otherwise>
          </c:choose>
              <a href="${ctx}/cs/inquiry">고객센터</a>
        </li>
      </ul>
    </nav>
  </div>

  
  <div class="sidebar-overlay"></div>
<div class="sidebar">
  <ul>
    <li><a href="${ctx}/roulette/spin">룰렛이벤트</a></li>
    <li><a href="${ctx}/shoplaylist/list">패키지</a></li>
    <li><a href="${ctx}/product/list">상품목록</a></li>
  </ul>
</div>