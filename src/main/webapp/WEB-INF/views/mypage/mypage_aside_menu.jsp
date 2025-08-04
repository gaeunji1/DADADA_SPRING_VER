<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <nav id="my-menu" class="my-page-left-menu">
        <div class="my-menu-title">MY MENU</div>
        <ul class="aside-menu">
            <li>
                <a href="/mypage/order-list" title="주문 내역/배송 조회" target="_self" class="my-menu-link ${pageMenu eq 'order-list' ? 'active' : ''}">
                    주문 내역/배송 조회
                </a>
            </li>
            <li>
                <a href="/mypage/reward" title="보유 혜택" target="_self" class="my-menu-link ${pageMenu eq 'reward' ? 'active' : ''}">
                    보유 혜택
                </a>
            </li>
            <li>
                <a href="/mypage/activity" title="활동 내역 (경쟁/룰렛)" target="_self" class="my-menu-link ${pageMenu eq 'activity' ? 'active' : ''}">
                    활동 내역 (경쟁/룰렛)
                </a>
            </li>
            <li>
                <a href="/mypage/shoplaylist" title="내 패키지 관리" target="_self" class="my-menu-link ${pageMenu eq 'shoplaylist' ? 'active' : ''}">
                    패키지 관리
                </a>
            </li>
            <li>
                <a href="/member/password-check" title="회원 정보 확인/수정" target="_self" class="my-menu-link">
                    회원 정보 확인/수정
                </a>
            </li>
        </ul>
    </nav>