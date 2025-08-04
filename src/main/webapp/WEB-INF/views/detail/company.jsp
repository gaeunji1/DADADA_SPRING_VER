<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>DADADA | 회사소개</title>
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/top_logo.png" type="image/png">
      <link rel="stylesheet" href="/css/common.css" type="text/css" />
    <style>
        html {
            scroll-behavior: smooth;
        }
    </style>
    
</head>
<body class='company-page'>
<a href="${pageContext.request.contextPath}/main">
  <img src="${pageContext.request.contextPath}/resources/img/dadada_logo.png" alt="로고" style="position: absolute; top: 20px; left: 20px; z-index: 100; height: 60px;">
</a>

<nav class="company-nav">
  <ul>
    <li><a href="#about-company">회사 소개</a></li>
    <li><a href="#brand-info">브랜드</a></li>
    <li><a href="#service-roulette">서비스</a></li>
  </ul>
</nav>

<div class="company-hero" style="background-image: url('${pageContext.request.contextPath}/resources/img/company.png');">
    <div class="company-hero-text">
        <h1>DADADA</h1>
        <h2>多모아 多사면 多할인</h2>
        <p>쇼핑을 게임처럼, 다다다에서 시작하세요</p>
    </div>
</div>

<div class="company-section" id="about-company">
    <h2>No.1 Participation Commerce Platform</h2>
    <p>
        Dadada는 대량 구매 기반의 이커머스 플랫폼으로, 소비자에게 단순한 제품 이상의 재미를 제공합니다.<br>
        룰렛 이벤트, 랭킹 경쟁, 묶음 할인 등 다양한 요소로 쇼핑의 본질을 새롭게 정의합니다.
    </p>

    <div class="company-features">
        <div class="company-feature">
            <img src="${pageContext.request.contextPath}/resources/img/tech.jpg" alt="tech">
            <h3>Technology</h3>
            <p>"쇼핑도 기술이다, 다다다스럽게."<br>Dadada가 직접 A-Z 쇼핑몰 제작으로 최적의 쇼핑을 실현합니다.</p>
        </div>
        <div class="company-feature">
            <img src="${pageContext.request.contextPath}/resources/img/trust.jpg" alt="trust">
            <h3>Trust</h3>
            <p>"신뢰는 다져진다, 다다다스럽게."<br>회원 혜택, 이벤트, CS까지 다다다는 투명하고 일관된 운영으로 고객 신뢰를 쌓아갑니다.</p>
        </div>
        <div class="company-feature">
            <img src="${pageContext.request.contextPath}/resources/img/hope.jpg" alt="hope">
            <h3>Hope</h3>
            <p>"포장도 모아, 지구도 생각한다, 다다다스럽게."<br>대량 패키지일수록 불필요한 낱개 포장을 줄이고, 에코박스 하나로 묶음 배송합니다.</p>
        </div>
    </div>
</div>

<div class="company-section" id="brand-info">
  <div class="company-brand-container">
    <div class="company-brand-text">
      <h2>쇼핑에 재미를 더하다,<br><strong>다다다</strong></h2>
      <p>
        다다다는 단순히 가격만 낮추는 쇼핑몰이 아닙니다.<br>
        룰렛, 랭킹, 게임화된 UX를 통해 고객에게 즐거움을 제공하는<br>
        새로운 방식의 참여형 커머스 플랫폼입니다.<br><br>
        공동구매를 넘어, 사용자가 직접 혜택을 만들어가는<br>
        신개념 커머스 생태계를 구축합니다.
      </p>
    </div>
  </div>
</div>

<div class="company-section" id="service-roulette">
  <div class="company-service-box">
	  <div class="company-service-img">
	  		<a href="${pageContext.request.contextPath}/roulette/spin">
	      		<img src="${pageContext.request.contextPath}/resources/img/roulette.png" alt="룰렛">
	      </a>
	    </div>
	   <div class="company-service-text">
	      <h3>룰렛 이벤트</h3>
	      <p>
	        다다다에서는 구매 시마다 룰렛에 참여할 수 있어요.<br>
	        포인트, 쿠폰, 추가 상품 등 랜덤 보상을 통해<br>
	        쇼핑 그 자체가 하나의 게임이 됩니다.
	      </p>
	   </div>
  </div>
</div>

<div class="company-section" id="service-shopply">
  <div class="company-service-box">
    <div class="company-service-img">
    	<a href="${pageContext.request.contextPath}/shoplaylist/list?activeTab=adminTab">
      <img src="${pageContext.request.contextPath}/resources/img/shopply.png" alt="#플리">
      </a>
    </div>
    <div class="company-service-text">
      <h3>#플리</h3>
      <p>
        저번에 구매한 걸 1초만에 재구매<br>
        누구보다 빠르게 재구매 가능한 서비스를 제공합니다.
      </p>
    </div>
  </div>
</div>

<div class="company-section" id="service-competition">
  <div class="company-service-box">
    <div class="company-service-text">
      <h3>경쟁 룸</h3>
      <p>
        다다다에는 경쟁 룸이 있어요.<br>
        다른 구매자들과의 구매 수량 경쟁을 통해<br>
        순위에 따라 보상받고, 경쟁의 즐거움을 누려보세요.
      </p>
    </div>
    <div class="company-service-img">
    	<a href="${pageContext.request.contextPath}/product/list">
      		<img src="${pageContext.request.contextPath}/resources/img/competition.png" alt="경쟁 룸">
      </a>
    </div>
  </div>
</div>


</body>
</html>
