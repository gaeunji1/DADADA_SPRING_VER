<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="admin-page">
  <div class="admin-container">
    <h2 class="admin-title">주문 및 배송 관리</h2>

    <table class="admin-table">
      <thead>
        <tr>
          <th>주문번호</th>
          <th>회원명</th>
          <th>주문일시</th>
          <th>총금액</th>
          <th>배송상태</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="order" items="${orderList}">
          <tr>
            <td>${order.ordId}</td>
            <td>${order.mbrNcknm}</td>
            <td>${order.ordDt}</td>
            <td><fmt:formatNumber value="${order.prdPrcFinal}" type="number" groupingUsed="true" /> 원</td>
            <td>
              <span class="delivery-status ${order.dlvStat}">
                ${order.dlvStatLabel}
              </span>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
