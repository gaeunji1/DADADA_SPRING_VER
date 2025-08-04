<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="pageTitle" value="주문 상세" scope="request"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="mypage-page">
    <div class="mypage-layout-container">

        <jsp:include page="/WEB-INF/views/mypage/mypage_aside_menu.jsp" />

        <div class="mypage-content-wrapper">
            <h2 class="page-title">주문 상세</h2>

            <c:if test="${not empty orderDetails}">
                <!-- 주문 헤더 -->
                <section class="order-detail-section">
                    <div class="order-header">
                        <span class="order-date">
                            ${orderDetails[0].ordDt} 주문 · 주문 번호 ${orderDetails[0].ordId}
                        </span>
                        <div class="order-buttons">
                            <a href="#" class="ol-btn ol-btn-secondary">구매 영수증</a>
                            <a href="#" class="ol-btn ol-btn-secondary">거래 명세표</a>
                        </div>
                    </div>
                </section>

                <!-- 상품 리스트 -->
              <c:forEach var="detail" items="${orderDetails}">
                    <section class="order-detail-section">
                        <div class="order-content">
                            <div class="order-content-left">
                                <div class="shipping-status-text"
                                     data-shipping-start="${detail.shpDt}"
                                     data-shipping-arrival="${detail.shpArrvDt}">
                                    <c:choose>
                                        <c:when test="${detail.shpStat eq '결제 완료'}">결제 완료</c:when>
                                        <c:when test="${detail.shpStat eq '상품 준비 중'}">상품 준비 중</c:when>
                                        <c:when test="${detail.shpStat eq '배송 중'}">
                                            배송 중 · <c:out value="${fn:substring(detail.shpDt, 0, 10)}"/> 출발
                                        </c:when>
                                        <c:when test="${detail.shpStat eq '배송 완료'}">
                                            배송 완료 · <c:out value="${fn:substring(detail.shpArrvDt, 0, 10)}"/> 도착
                                        </c:when>
                                        <c:otherwise>${detail.shpStat}</c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="ol-product-info">
                                    <a href="${pageContext.request.contextPath}/product/view/${detail.prdId}">
                                        <c:choose>
                                            <c:when test="${detail.pckgId > 0}">
                                                <img src="${pageContext.request.contextPath}${detail.pckgImg}"
                                                     class="ol-product-img" alt="패키지 이미지" />
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${pageContext.request.contextPath}${detail.prdImg}"
                                                     class="ol-product-img" alt="상품 이미지" />
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                    <div class="ol-product-text-info">
                                        <a href="${pageContext.request.contextPath}/product/view/${detail.prdId}"
                                           class="ol-product-name">
                                            ${detail.prdNm}
                                        </a>
                                        <div class="order-meta">
                                            <fmt:formatNumber value="${detail.prdPrc}" pattern="#,##0" />원
                                            · ${detail.ordCnt}개
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="order-content-right">
                            <c:choose>
                                <c:when test="${detail.shpStat eq '결제 완료'}">
                                    <a href="${ctx}/cs/inquiry/write?ordId=${detail.ordId}" 
                                       class="ol-btn ol-btn-secondary">주문 취소</a>
                                </c:when>
                                <c:when test="${detail.shpStat eq '상품 준비 중'}">
                                    <a href="${ctx}/cs/inquiry/write" class="ol-btn ol-btn-secondary">취소 문의</a>
                                </c:when>
                                <c:when test="${detail.shpStat eq '배송 중'}">
                                    <!-- <a href="#" class="ol-btn ol-btn-primary">배송 조회</a> -->
                                    <a href="${ctx}/cs/inquiry/write" class="ol-btn ol-btn-secondary">취소 문의</a>
                                </c:when>
                                <c:when test="${detail.shpStat eq '배송 완료'}">
                                    <!-- <a href="#" class="ol-btn ol-btn-primary">배송 조회</a> -->
                                    <a href="${ctx}/cs/inquiry/write" class="ol-btn ol-btn-secondary">교환/환불 신청</a>
                                    <c:if test="${detail.ordDvsn ne 'package'}">
                                        <c:choose>
                                            <c:when test="${detail.reviewWritten eq 1}">
                                                <a href="${ctx}/product/view/${detail.prdId}#review-section" 
                                                   class="ol-btn ol-btn-secondary">작성한 리뷰 보기</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${ctx}/product/view/${detail.prdId}#review-section" 
                                                   class="ol-btn ol-btn-secondary">리뷰 작성하기</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </c:when>
                            </c:choose>
                        </div>
                            
                        </div>
                    </section>
                </c:forEach>
             

                <!-- 배송 정보 & 결제 정보 -->
                <section class="order-detail-section">
                    <div class="order-detail-box">
                        <!-- 배송 정보 -->
                        <div class="order-detail-info-wrapper">
                            <div class="order-detail-info-title"><span>배송 정보</span></div>
                            <div class="order-detail-info-box">
                                <div class="order-detail-info-content">
                                    <div class="order-detail-row">
                                        <div class="order-detail-label">받는 사람</div>
                                        <div class="order-detail-value">${orderDetails[0].shpNm}</div>
                                    </div>
                                    <div class="order-detail-row">
                                        <div class="order-detail-label">연락처</div>
                                        <div class="order-detail-value">${orderDetails[0].shpPhNo}</div>
                                    </div>
                                    <div class="order-detail-row">
                                        <div class="order-detail-label">주소</div>
                                        <div class="order-detail-value">${orderDetails[0].shpAddr}</div>
                                    </div>
                                    <div class="order-detail-row d-none">
                                    <div class="order-detail-label">
                                        배송 업체
                                    </div>
                                    <div class="order-detail-value">
                                        ${orderDetails[0].shpCmpny}
                                    </div>
                                </div>
                                <div class="order-detail-row d-none">
                                    <div class="order-detail-label">
                                        운송장번호
                                    </div>
                                    <div class="order-detail-value">
                                        ${orderDetails[0].shpTrkNum}
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div>

                        <!-- 결제 정보 -->
                        <div class="order-detail-info-wrapper">
                            <div class="order-detail-info-title"><span>결제 정보</span></div>
                            <div class="order-detail-info-box">
                                <div class="order-detail-info-content">
                                    <div class="order-detail-payment-method">
                                        <div class="order-detail-payment-title">결제 방식</div>
                                        <div class="order-detail-payment-content">
                                            <div class="order-payment-method-name">${paymentMethod}</div>
                                            <div class="final-price-up">
                                                <fmt:formatNumber value="${paidAmount}" pattern="#,##0" />원
                                            </div>
                                        </div>
                                    </div>
                                    <div class="order-detail-subdivider"></div>
                                    <ul class="order-payment-list">
                                        <li>
                                            <span>총 상품 금액</span>
                                            <span><fmt:formatNumber value="${totalProductAmount}" pattern="#,##0" />원</span>
                                        </li>
                                        <li>
                                            <span>총 할인 금액</span>
                                            <span><fmt:formatNumber value="${totalDiscountAmount}" pattern="#,##0" />원</span>
                                        </li>
                                        <li>
                                            <span>배송비</span>
                                            <span><fmt:formatNumber value="${shippingFee}" pattern="#,##0" />원</span>
                                        </li>
                                        <li class="final-price-down">
                                            <span>총 결제 금액</span>
                                            <span><fmt:formatNumber value="${grandTotal}" pattern="#,##0" />원</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <!-- 뒤로 가기 -->
                <div class="order-go-back-wrapper">
                    <a href="${pageContext.request.contextPath}/mypage/order-list" class="ol-btn-go-back">&laquo; 주문 내역 돌아가기</a>
                </div>

             <!-- 주문 상태 안내 -->
                <section class="order-progress-wrapper">
                    <h3 class="order-progress-title">배송 상품 주문 상태 안내</h3>
                    <div class="order-progress-steps">
                        <c:set var="shpStat" value="${orderDetails[0].shpStat}" />
                        <div class="order-progress-step ${shpStat eq '결제 완료' ? 'active' : ''}">
                            <div class="order-progress-subject">결제 완료</div>
                            <div class="order-progress-text">주문, 결제 확인이 완료되었습니다.</div>
                        </div>
                        <div class="order-progress-arrow">⇒</div>
                        <div class="order-progress-step ${shpStat eq '상품 준비 중' ? 'active' : ''}">
                            <div class="order-progress-subject">상품 준비 중</div>
                            <div class="order-progress-text">판매자가 발송할 상품을 준비 중입니다.</div>
                        </div>
                        <div class="order-progress-arrow">⇒</div>
                        <div class="order-progress-step ${shpStat eq '배송 중' ? 'active' : ''}">
                            <div class="order-progress-subject">배송 중</div>
                            <div class="order-progress-text">상품이 고객님께 배송 중입니다.</div>
                        </div>
                        <div class="order-progress-arrow">⇒</div>
                        <div class="order-progress-step ${shpStat eq '배송 완료' ? 'active' : ''}">
                            <div class="order-progress-subject">배송 완료</div>
                            <div class="order-progress-text">상품이 고객님께 전달 완료되었습니다.</div>
                        </div>
                    </div>
                </section>

            </c:if>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
