<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<footer class="footer-container">
    <div class="footer-top-menu">
        <a href="${pageContext.request.contextPath}/company" class="footer-link">회사소개</a>
        <a href="${pageContext.request.contextPath}/terms" class="footer-link">이용약관 및 규칙</a>
        <a href="${pageContext.request.contextPath}/privacy" class="footer-link">개인정보 처리방침 및 청소년보호정책</a>
    </div>

    <hr class="footer-divider" />

    <div class="footer-content">

        <!-- 좌: 로고 -->
        <div class="footer-logo">
            <a href="${pageContext.request.contextPath}/main">
                <img src="${pageContext.request.contextPath}/resources/img/dadada_logo.png" alt="로고">
            </a>
        </div>

        <!-- 중앙: 회사 정보 -->
        <div class="footer-info">
            <p>
                (주)다다다 (대표자: 오동석) | 주소: 서울특별시 서초구 강남대로 50층 (서초동, K트원)
                <br>사업자등록번호: 000-00-00000 | 통신판매업신고번호: 제2023-어쩌구-000호
                <br>이메일: dadada@gsretail.com | 고객센터: 0000-0000 (평일 09:00~18:00)
                <br>Copyright © Dadada Co.,Ltd. All Rights Reserved.
            </p>
        </div>

        <!-- 우: 취급자금증 안내 -->
        <div class="footer-notice">
            <p>취급자금증 안내
                <br>당사는 고객님의 현금 결제를 관계법령에 따라 보호받고 있습니다.
                <br>서비스 사용 중 궁금하신 사항은 언제든 문의해주시기 바랍니다.
            </p>
        </div>
    </div>
</footer>
