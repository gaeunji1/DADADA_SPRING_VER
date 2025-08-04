<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="pageTitle" value="주문 내역" scope="request"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="mypage-page">
    <div class="mypage-layout-container">

        <jsp:include page="/WEB-INF/views/mypage/mypage_aside_menu.jsp" />

        <div class="mypage-content-wrapper">
            <h2 class="page-title">주문 내역</h2>

            <c:if test="${empty orderList}">
                <div class="no-data">주문 내역이 없습니다.</div>
            </c:if>

            <c:forEach var="order" items="${orderList}">
                <section class="order-box">
                    <!-- 주문 헤더 -->
                    <div class="order-header">
                        <span class="order-date">${order.pmtDt} 주문</span>
                        <a href="${ctx}/mypage/order-detail/${order.ordId}" class="order-detail-link">주문 상세 보기 &raquo;</a>
                    </div>

                    <div class="order-content">
                        <div class="order-content-left">

                            <div class="shipping-status-text"
                                 data-shipping-start="${order.shpDt}"
                                 data-shipping-arrival="${order.shpArrvDt}">
                                <c:choose>
                                    <c:when test="${order.shpStat eq '결제 완료'}">결제 완료</c:when>
                                    <c:when test="${order.shpStat eq '상품 준비 중'}">상품 준비 중</c:when>
                                    <c:when test="${order.shpStat eq '배송 중'}">
                                        배송 중 · <c:out value="${fn:substring(order.shpDt, 0, 10)}"/> 출발
                                    </c:when>
                                    <c:when test="${order.shpStat eq '배송 완료'}">
                                        배송 완료 · <c:out value="${fn:substring(order.shpArrvDt, 0, 10)}"/> 도착
                                    </c:when>
                                    <c:otherwise>${order.shpStat}</c:otherwise>
                                </c:choose>
                            </div>

                            <div class="ol-product-info">
                                <a href="${ctx}/product/view/${order.samplePrdId}">
                                    <img src="${ctx}${order.samplePrdImg}" 
                                         class="ol-product-img" 
                                         alt="${order.samplePrdNm}" />
                                </a>
                                <div class="ol-product-text-info">
                                    <div class="ol-product-name-wrapper">
                                        <a href="${ctx}/product/view/${order.samplePrdId}" class="ol-product-name">
                                            <span class="product-main-name">${order.samplePrdNm}</span>
                                        </a>
                                        <c:if test="${order.additionalProductCount > 0}">
                                            <span class="additional-product-count">
                                                + ${order.additionalProductCount}개 상품
                                            </span>
                                        </c:if>
                                    </div>
                                    <div class="order-meta">
                                        <fmt:formatNumber value="${order.prdPrcFinal}" pattern="#,##0"/>원 
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="order-content-right">
                            <c:choose>
                                <c:when test="${order.shpStat eq '취소 완료'}">
							      <button class="ol-btn ol-btn-secondary" disabled>취소 완료</button>
							    </c:when>
                                <c:when test="${order.shpStat eq '결제 완료'}">
                                    <a href="${ctx}/cs/inquiry/write?ordId=${order.ordId}" 
                                       class="ol-btn ol-btn-secondary">주문 취소</a>
                                </c:when>
                                <c:when test="${order.shpStat eq '상품 준비 중'}">
                                    <a href="${ctx}/cs/inquiry/write" class="ol-btn ol-btn-secondary">취소 문의</a>
                                </c:when>
                                <c:when test="${order.shpStat eq '배송 중'}">
                                    <a href="#" class="ol-btn ol-btn-primary">배송 조회</a>
                                    <a href="${ctx}/cs/inquiry/write" class="ol-btn ol-btn-secondary">취소 문의</a>
                                </c:when>
                                <c:when test="${order.shpStat eq '배송 완료'}">
                                    <a href="#" class="ol-btn ol-btn-primary">배송 조회</a>
                                    <a href="${ctx}/cs/inquiry/write" class="ol-btn ol-btn-secondary">교환/환불 신청</a>
                                    <c:if test="${order.ordDvsn ne 'package'}">
                                        <c:choose>
                                            <c:when test="${order.reviewWritten}">
                                                <a href="${ctx}/product/view/${order.samplePrdId}#review-section" 
                                                   class="ol-btn ol-btn-secondary">작성한 리뷰 보기</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${ctx}/product/view/${order.samplePrdId}#review-section" 
                                                   class="ol-btn ol-btn-secondary">리뷰 작성하기</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <a href="${ctx}/mypage/order-detail/${order.ordId}" 
                                       class="ol-btn ol-btn-secondary">주문 확인</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </section>
            </c:forEach>

            <div class="order-pagination">
                <c:if test="${pagination.hasPrevPage}">
                    <a href="?pageNo=${pagination.prevPageNo}&listSize=${pagination.listSize}" 
                       class="page-link">이전</a>
                </c:if>
                <c:if test="${pagination.hasNextPage}">
                    <a href="?pageNo=${pagination.nextPageNo}&listSize=${pagination.listSize}" 
                       class="page-link">다음</a>
                </c:if>
            </div>
        </div>
    </div>
</div>

<div id="shippingModal" class="modal-wrapper" style="display: none;">
    <div class="modal-content">
        <div id="shippingModalContent"></div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
