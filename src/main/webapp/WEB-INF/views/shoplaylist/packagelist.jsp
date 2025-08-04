<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageTitle" value="패키지 리스트" scope="request" />

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<!-- ===== 패키지 리스트 ===== -->
<div class="package-list-page">
  <div class="package-container">
    <h2>#플리</h2>

    <!-- 탭 버튼 -->
    <div class="tab-buttons">
      <button onclick="showTab('userTab')">User 패키지</button>
      <button onclick="showTab('adminTab')">Admin 패키지</button>
    </div>

    <c:set var="loginUser" value="${sessionScope.loginUser}" />

    <!-- ========== User 패키지 탭 ========== -->
    <div id="userTab" class="tab-content">
      <h3>User 패키지</h3>

      <!-- 검색 -->
      <div class="user-package-search">
        <form action="${ctx}/shoplaylist/list" method="get"
              style="display: flex; align-items: center; width: 100%">
          <input type="text"
                 name="searchKeyword"
                 class="user-package-search-input"
                 placeholder="패키지 검색…"
                 value="${fn:escapeXml(searchKeyword)}" />
          <input type="hidden" name="activeTab" value="userTab" />
          <button type="submit" class="user-package-search-btn">
            <img src="https://cdn-icons-png.flaticon.com/512/622/622669.png"
                 alt="검색"
                 style="width:22px;height:22px;border:0;background:transparent;" />
          </button>
        </form>
      </div>

      <c:choose>
        <c:when test="${not empty userPackages}">
          <form id="cartForm">
            <table width="100%">
              <thead>
                <tr>
                  <th><input type="checkbox" id="selectAll" onclick="toggleAll(this)" /></th>
                  <th>추천</th>
                  <th>추천 수</th>
                  <th>패키지명</th>
                  <th>생성일</th>
                  <th>삭제</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="pkg" items="${userPackages}">
                  <tr>
                    <td>
                      <input type="checkbox"
                             class="packageCheckbox"
                             name="packageIds"
                             value="${pkg.packageId}" />
                    </td>
                    <td>
                           <c:choose>
					     <c:when test="${loginUser != null and loginUser.mbrId == pkg.memId}">
					      <span style="color:gray; font-weight:bold;">내 패키지</span>
					    </c:when>
					        <c:when test="${loginUser != null 
					                       and loginUser.mbrId != pkg.memId 
					                       and not recommendedMap[pkg.packageId]}">
					          <button type="button"
					                  class="recommend-btn"
					                  data-id="${pkg.packageId}">
					            추천
					          </button>
					        </c:when>
					        <c:otherwise>
					          <button type="button" disabled>추천됨</button>
					        </c:otherwise>
                    </c:choose>
                    </td>
                    <td id="rcount-${pkg.packageId}">${pkg.recommendCount}</td>
                    <td>
                      <a href="/shoplaylist/view/${pkg.packageId}">
                        ${pkg.packageName}
                      </a>
                    </td>
                    <td>${pkg.createdAt}</td>
					  <td>
					   <c:if test="${loginUser != null 
               and (loginUser.mbrId == pkg.memId 
                    or loginUser.adminYn == 1)}">
                        <a href="/shoplaylist/delete/${pkg.packageId}"
                           onclick="return confirm('정말 삭제하시겠습니까?')"
                           class="btn btn-danger">삭제</a>
                           </c:if>
                      </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>

            <div style="margin-top:10px; text-align:right">
<button type="button" class="add-to-cart-btn" onclick="submitCart()">
  선택한 패키지 장바구니 담기
</button>

            </div>
          </form>

          <c:set var="totalPages" value="${userTotalPages}" />
    
    <ul class="paginator">
      <li>
        <a href="?userPageNo=0&userListSize=${userPagination.listSize}&searchKeyword=${fn:escapeXml(searchKeyword)}">
          처음으로
        </a>
      </li>
    
      <li>
        <a href="?userPageNo=${userPagination.pageNo > 0 ? userPagination.pageNo - 1 : 0}&userListSize=${userPagination.listSize}&searchKeyword=${fn:escapeXml(searchKeyword)}">
          이전
        </a>
      </li>
    
      <c:forEach var="p" begin="1" end="${totalPages}">
        <li class="${userPagination.pageNo + 1 == p ? 'active' : ''}">
          <a href="?userPageNo=${p - 1}&userListSize=${userPagination.listSize}&searchKeyword=${fn:escapeXml(searchKeyword)}">
            ${p}
          </a>
        </li>
      </c:forEach>
    
      <li>
        <a href="?userPageNo=${userPagination.pageNo + 1 < totalPages ? userPagination.pageNo + 1 : totalPages - 1}&userListSize=${userPagination.listSize}&searchKeyword=${fn:escapeXml(searchKeyword)}">
          다음
        </a>
      </li>
    
      <li>
        <a href="?userPageNo=${totalPages - 1}&userListSize=${userPagination.listSize}&searchKeyword=${fn:escapeXml(searchKeyword)}">
          마지막으로
        </a>
      </li>
    </ul>

        </c:when>
        <c:otherwise>
          <p>등록된 user 패키지가 없습니다.</p>
        </c:otherwise>
      </c:choose>
    </div>

    <div id="adminTab" class="tab-content" style="display: none">
      <h3>Admin 패키지</h3>
      <div class="admin-package-search">
        <form action="${ctx}/shoplaylist/list" method="get"
              style="display: flex; align-items: center; width: 100%">
          <input type="text"
                 name="searchKeyword"
                 class="admin-package-search-input"
                 placeholder="패키지 검색..."
                 value="${fn:escapeXml(searchKeyword)}" />
          <input type="hidden" name="activeTab" value="adminTab" />
          <button type="submit" class="admin-package-search-btn">
            <img src="https://cdn-icons-png.flaticon.com/512/622/622669.png"
                 alt="검색"
                 style="width:22px;height:22px;border:0;background:transparent;" />
          </button>
        </form>
      </div>

      <c:choose>
        <c:when test="${not empty adminPackages}">
          <form id="cartForm">
            <table width="100%">
              <thead>
                <tr>
                  <th><input type="checkbox" id="selectAll" onclick="toggleAll(this)" /></th>
                  <th>패키지명</th><th>설명</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="pkg" items="${adminPackages}">
                  <tr>
                    <td>
                      <input type="checkbox"
                             class="packageCheckbox"
                             name="packageIds"
                             value="${pkg.packageId}" />
                    </td>
                    <td>
                      <a href="/shoplaylist/view/${pkg.packageId}">
                        ${pkg.packageName}
                      </a>
                    </td>
                    <td>${pkg.packageDescription}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
            <div style="margin-top:10px; text-align:right">
<button type="button" class="add-to-cart-btn" onclick="submitCart()">
  선택한 패키지 장바구니 담기
</button>

            </div>
          </form>

     <ul class="paginator">
      <li>
        <a href="?adminPageNo=${adminPagination.pageNo}
                  &adminListSize=${adminPagination.listSize}
                  &searchKeyword=${fn:escapeXml(searchKeyword)}
                  &activeTab=adminTab">
          처음으로
        </a>
      </li>
    
      <li>
        <a href="?adminPageNo=${adminPagination.pageNo}
                  &adminListSize=${adminPagination.listSize}
                  &searchKeyword=${fn:escapeXml(searchKeyword)}
                  &activeTab=adminTab">
          이전
        </a>
      </li>
    
      <c:forEach var="p" begin="1" end="${adminPagination.pageCount}">
        <li class="${adminPagination.pageNo + 1 == p ? 'active' : ''}">
          <a href="?adminPageNo=${adminPagination.pageNo}
                    &adminListSize=${adminPagination.listSize}
                    &searchKeyword=${fn:escapeXml(searchKeyword)}
                    &activeTab=adminTab">
            ${p}
          </a>
        </li>
      </c:forEach>
    
      <li>
        <a href="?adminPageNo=${adminPagination.pageNo}
                  &adminListSize=${adminPagination.listSize}
                  &searchKeyword=${fn:escapeXml(searchKeyword)}
                  &activeTab=adminTab">
          다음
        </a>
      </li>
    
      <li>
        <a href="?adminPageNo=${adminPagination.pageNo}
                  &adminListSize=${adminPagination.listSize}
                  &searchKeyword=${fn:escapeXml(searchKeyword)}
                  &activeTab=adminTab">
          마지막으로
        </a>
      </li>
    </ul>
        </c:when>
        <c:otherwise>
          <p>등록된 admin 패키지가 없습니다.</p>
        </c:otherwise>
      </c:choose>
    </div>

  </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />