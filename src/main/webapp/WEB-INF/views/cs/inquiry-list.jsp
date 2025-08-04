<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>


<c:if test="${not empty errorMessage}">
    <div id="error-message" data-message="${errorMessage}" style="display:none;"></div>
</c:if>

<div class="inquiry-list-page">
    
    <section class="inquiry-header">
        <h2>고객센터 문의목록</h2>
        <p class="desc">궁금하신 점이나 불편 사항을 남겨주시면 빠르게 답변해 드리겠습니다.</p>
        
        <div class="faq-link">
            <p>문의 전에 <a href="/cs/faq" class="link-to-faq">자주 묻는 질문</a>을 확인해보세요!</p>
        </div>
    </section>

    
    <div class="control-panel">
        <div class="sort-control">
            <label for="sortSelect">정렬:</label>
            <select id="sortSelect" onchange="changeSort(this.value)">
                <option value="newest" ${inquiryList.sort eq 'newest' ? 'selected' : ''}>최신순</option>
                <option value="oldest" ${inquiryList.sort eq 'oldest' ? 'selected' : ''}>오래된순</option>
            </select>
        </div>

        <div class="search-bar">
            <form action="/cs/inquiry" method="get">
                <input type="hidden" name="page" value="1">
                <input type="hidden" name="category" value="${inquiryList.category}">
                <input type="hidden" name="sort" value="${inquiryList.sort}">
                <input type="text" name="keyword" placeholder="제목으로 검색" value="${param.keyword}">
                <button type="submit" class="btn-search">검색</button>
            </form>
        </div>
    </div>

    
    <div class="category-bar">
        <div class="category-tabs">
            <a href="?page=1&sort=${inquiryList.sort}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}"
               class="${empty inquiryList.category ? 'active' : ''}">전체</a>
            <a href="?category=0&page=1&sort=${inquiryList.sort}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}"
               class="${inquiryList.category eq 0 ? 'active' : ''}">일반</a>
            <a href="?category=1&page=1&sort=${inquiryList.sort}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}"
               class="${inquiryList.category eq 1 ? 'active' : ''}">교환</a>
            <a href="?category=2&page=1&sort=${inquiryList.sort}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}"
               class="${inquiryList.category eq 2 ? 'active' : ''}">환불</a>
        </div>
        <c:choose>
            <c:when test="${not empty loginUser}">
                <a href="?page=1&myInquiries=true&sort=${inquiryList.sort}"
                   class="my-inquiry-btn${param.myInquiries eq 'true' ? ' active' : ''}">
                   <i class="fas fa-user"></i> 내 문의글 조회
                </a>
            </c:when>
            <c:otherwise>
                <a class="my-inquiry-btn login-required">
                   <i class="fas fa-sign-in-alt"></i> 로그인 후 내 문의글 보기
                </a>
            </c:otherwise>
        </c:choose>
    </div>

    
    <div class="table-container">
        <table class="inquiry-table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>카테고리</th>
                    <th>작성자</th>
                    <th>제목</th>
                    <th>작성일</th>
                    <th>상태</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${inquiryList.inquiries}" var="inquiry">
                    <tr>
                        
                        <td class="text-center">${inquiry.inqryId}</td>
                        <td class="text-center">
                            
                            <span class="category-badge category-${inquiry.inqryCtgry}">
                                <c:choose>
                                    <c:when test="${inquiry.inqryCtgry == 0}">일반</c:when>
                                    <c:when test="${inquiry.inqryCtgry == 1}">교환</c:when>
                                    <c:when test="${inquiry.inqryCtgry == 2}">환불</c:when>
                                </c:choose>
                            </span>
                        </td>
                        <td class="text-center">${inquiry.mbrNcknm}</td>
                        
                        <td class="title-cell">
                            <c:choose>
                                <c:when test="${inquiry.inqryPrvt == 1 && (empty loginUser || (loginUser.adminYn != 1 && loginUser.mbrId != inquiry.mbrId))}">
                                    <a href="/cs/inquiry/${inquiry.inqryId}">
                                        <span class="private-icon"><i class="fas fa-lock">비밀글입니다.</i></span>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/cs/inquiry/${inquiry.inqryId}">
                                        <c:if test="${inquiry.inqryPrvt == 1}">
                                            <span class="private-icon"><i class="fas fa-lock"></i></span>
                                        </c:if>
                                        ${inquiry.inqryTtl}
                                    </a>
                                </c:otherwise>
                            </c:choose>

                            <c:if test="${not empty inquiry.inqryRply}">
                                <span class="reply-badge">답변 완료</span>
                            </c:if>
                        </td>

                        <td class="text-center">
                            ${fn:substring(inquiry.inqryCrtDt, 0, 10)}
                        </td>
                        <td class="text-center">
                             
                            <span class="status-badge status-${inquiry.inqryStat}">
                                <c:choose>
                                    <c:when test="${inquiry.inqryStat == 0}">처리 전</c:when>
                                    <c:when test="${inquiry.inqryStat == 1}">처리 중</c:when>
                                    <c:when test="${inquiry.inqryStat == 2}">완료</c:when>
                                </c:choose>
                            </span>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty inquiryList.inquiries}">
                     
                    <tr class="no-data">
                        <td colspan="6">
                            <p>등록된 문의가 없습니다.</p>
                            <c:if test="${not empty param.keyword}">
                                <p class="search-tip">'${param.keyword}'에 대한 검색 결과가 없습니다. 다른 검색어를 시도해보세요.</p>
                            </c:if>
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>

    
    <div class="pagination-container">
        <div class="pagination">
            <c:if test="${inquiryList.currentPage > 3}">
                <a href="?page=1&size=${inquiryList.size}&category=${inquiryList.category}&sort=${inquiryList.sort}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}${param.myInquiries eq 'true' ? '&myInquiries=true' : ''}" class="pagination-arrow">
                    &laquo;
                </a>
            </c:if>

            <c:if test="${inquiryList.currentPage > 1}">
                <a href="?page=${inquiryList.currentPage-1}&size=${inquiryList.size}&category=${inquiryList.category}&sort=${inquiryList.sort}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}${param.myInquiries eq 'true' ? '&myInquiries=true' : ''}" class="pagination-arrow">
                    &lsaquo;
                </a>
            </c:if>

            <c:forEach begin="${inquiryList.startPage}" end="${inquiryList.endPage}" var="i">
                <a href="?page=${i}&size=${inquiryList.size}&category=${inquiryList.category}&sort=${inquiryList.sort}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}${param.myInquiries eq 'true' ? '&myInquiries=true' : ''}"
                class="pagination-number ${i eq inquiryList.currentPage ? 'active' : ''}">${i}</a>
            </c:forEach>

            <c:if test="${inquiryList.currentPage < inquiryList.totalPages}">
                <a href="?page=${inquiryList.currentPage+1}&size=${inquiryList.size}&category=${inquiryList.category}&sort=${inquiryList.sort}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}${param.myInquiries eq 'true' ? '&myInquiries=true' : ''}" class="pagination-arrow">
                    &rsaquo;
                </a>
            </c:if>

            <c:if test="${inquiryList.currentPage < inquiryList.totalPages - 2}">
                <a href="?page=${inquiryList.totalPages}&size=${inquiryList.size}&category=${inquiryList.category}&sort=${inquiryList.sort}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}${param.myInquiries eq 'true' ? '&myInquiries=true' : ''}" class="pagination-arrow">
                    &raquo;
                </a>
            </c:if>
        </div>
    </div>

    
    <div class="btn-container">
        <c:choose>
            <c:when test="${not empty loginUser}">
                <a href="/cs/inquiry/write" class="btn-write">
                    <i class="fas fa-pen"></i> 문의 작성
                </a>
            </c:when>
            <c:otherwise>
                <a class="my-inquiry-btn login-required">
                   <i class="fas fa-sign-in-alt"></i> 로그인 후 문의 작성하기
                </a>
            </c:otherwise>
        </c:choose>
    </div>

    
    <div class="inquiry-guide">
        <h3><i class="fas fa-info-circle"></i> 문의 안내</h3>
        <ul>
            <li>상품 관련 문의는 해당 상품 페이지에서도 문의하실 수 있습니다.</li>
            <li>답변 완료된 문의글은 수정 및 삭제가 불가능합니다.</li>
            <li>비공개 문의글은 작성자와 관리자만 확인할 수 있습니다.</li>
            <li>욕설, 비방, 광고성 내용이 포함된 문의는 통보 없이 삭제될 수 있습니다.</li>
        </ul>
    </div>
</div> 

</body>
<jsp:include page="/WEB-INF/views/modal/loginModal.jsp" />
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>

