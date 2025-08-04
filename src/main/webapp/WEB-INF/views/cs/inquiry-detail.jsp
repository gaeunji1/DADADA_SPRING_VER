<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<script type="text/javascript">
  var contextPath = '${pageContext.request.contextPath}';
</script>





<div class="inquiry-detail-page">
    
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>

    
    <div class="page-header">
        <h2>문의 상세</h2>
    </div>

    <c:if test="${accessDenied}">
         
        <div class="alert alert-warning">
            <h4><i class="fas fa-exclamation-triangle"></i> <c:out value="${errorMessage}"/></h4>
            <p>비공개 문의글입니다. 작성자와 관리자만 내용을 볼 수 있습니다.</p>
            <a href="/cs/inquiry" class="btn btn-secondary">목록으로 돌아가기</a>
        </div>
    </c:if>

    
     <c:if test="${not empty inquiry}">
        
        <div class="inquiry-header">
            <div class="inquiry-title">
                <h3><c:out value="${inquiry.inqryTtl}"/></h3>
                <span class="inquiry-category">
                    <c:choose>
                        <c:when test="${inquiry.inqryCtgry == 0}">일반 문의</c:when>
                        <c:when test="${inquiry.inqryCtgry == 1}">교환 문의</c:when>
                        <c:when test="${inquiry.inqryCtgry == 2}">환불 문의</c:when>
                    </c:choose>
                </span>
                <c:if test="${inquiry.inqryPrvt == 1}">
                    <span class="inquiry-private"><i class="fas fa-lock"></i> 비공개</span>
                </c:if>
            </div>
            <div class="inquiry-info">
                <span>작성자: <c:out value="${inquiry.mbrNcknm}"/></span>
                <span>작성일: ${fn:substring(inquiry.inqryCrtDt, 0, 16)}</span>
                <span class="inquiry-status">
                    상태:
                    <c:choose>
                        <c:when test="${inquiry.inqryStat == 0}"><span class="status-badge waiting">처리 전</span></c:when>
                        <c:when test="${inquiry.inqryStat == 1}"><span class="status-badge processing">처리 중</span></c:when>
                        <c:when test="${inquiry.inqryStat == 2}"><span class="status-badge completed">처리 완료</span></c:when>
                    </c:choose>
                </span>
            </div>
        </div>

        <div class="inquiry-content">
            <c:out value="${inquiry.inqryCntnt}" escapeXml="false"/>
        </div>

        <c:if test="${not empty inquiry.inqryImgPath}">
            <div class="inquiry-image-container">
                
                <%
                    String imgPath = "";
                    try {
                        com.ktdsuniversity.proj.dadada.cs.vo.InquiryVO vo =
                            (com.ktdsuniversity.proj.dadada.cs.vo.InquiryVO)request.getAttribute("inquiry");
                        imgPath = vo.getInqryImgPath();
                        String fileName = "";
                        if (imgPath != null) {
                            int lastSlash = Math.max(imgPath.lastIndexOf('/'), imgPath.lastIndexOf('\\'));
                            fileName = lastSlash >= 0 ? imgPath.substring(lastSlash + 1) : imgPath;
                            request.setAttribute("fileName", fileName);
                        }
                    } catch (Exception e) {
                        // 오류 무시
                    }
                %>
                <img src="/cs/inquiry/image/${fileName}"
                     alt="첨부 이미지" class="inquiry-image"
                     onerror="this.style.display='none'">
                <div class="image-caption">첨부 이미지: <c:out value="${fileName}"/></div>
            </div>
        </c:if>

        <hr>

        <div class="reply-section">
            <h4><i class="fas fa-comment-dots"></i> 답변</h4>
            <c:choose>
                <c:when test="${not empty inquiry.inqryRply}">
                    <div class="reply-content">
                        <p><c:out value="${inquiry.inqryRply}" escapeXml="false"/></p>
                        <div class="reply-date">
                            답변일: <c:if test="${not empty inquiry.inqryRplyDt}">${fn:substring(inquiry.inqryRplyDt, 0, 16)}</c:if>
                        </div>
                    </div>
                    <c:if test="${loginUser.adminYn == 1}">
                        <button id="showEditForm" class="btn-edit-reply">답변 수정</button>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <div class="no-reply">아직 답변이 등록되지 않았습니다.</div>
                    <c:if test="${loginUser.adminYn == 1}">
                        
                        <div class="reply-form">
                            <textarea id="replyContent" name="inqryRply" rows="5" placeholder="답변을 입력하세요"></textarea>
                            <button type="button" id="btnSubmitReply" data-inquiry-id="${inquiry.inqryId}" class="btn-submit-reply">답변 등록</button>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>

        <c:if test="${loginUser.adminYn == 1}">
            <div class="admin-controls">
                <h4><i class="fas fa-cog"></i> 관리자 기능</h4>
                <div class="status-control">
                    <label for="statusSelect">문의 상태 변경:</label>
                    <select id="statusSelect" data-inquiry-id="${inquiry.inqryId}">
                        <option value="0" ${inquiry.inqryStat == 0 ? 'selected' : ''}>처리 전</option>
                        <option value="1" ${inquiry.inqryStat == 1 ? 'selected' : ''}>처리 중</option>
                        <option value="2" ${inquiry.inqryStat == 2 ? 'selected' : ''}>처리 완료</option>
                    </select>
                    <button id="btnStatusUpdate" class="btn-status-update">상태 변경</button>
                </div>
            </div>
        </c:if>
        
<c:if test="${loginUser.adminYn == 1 && inquiry.inqryCtgry == 2}">
  <hr/>
  <div class="refund-admin-section">
    <h4>💸 환불 처리</h4>
    <form id="refundForm" method="post">
      <!-- 조회된 imp_uid 를 읽기 전용으로 보여줌 -->
      <div class="form-group">
        <label>조회된 IMP UID</label>
        <input type="text"
               class="form-control"
               value="${paymentInfo.impUid}"
                />
      </div>
      <!-- 실제 환불 요청 payload -->
      <input type="hidden"
             id="imp_uid"
             name="imp_uid"
             value="${paymentInfo.impUid}" />
      <button type="button" id="btnRefund" class="btn btn-danger">
        환불 실행
      </button>
    </form>
  </div>
</c:if>

        <div class="btn-wrap">
            <a href="/cs/inquiry" class="btn-list">목록으로</a>


            <c:if test="${loginUser.mbrId == inquiry.mbrId || loginUser.adminYn == 1}">
                <div class="action-btns">
                    <c:if test="${empty inquiry.inqryRply && loginUser.mbrId == inquiry.mbrId}">
                        <a href="/cs/inquiry/${inquiry.inqryId}/edit" class="btn-edit">수정</a>
                    </c:if>
                    <button
                        id="btnDelete"
                        class="btn-delete"
                        data-inquiry-id="${inquiry.inqryId}"
                        type="button">
                        삭제
                    </button>
                </div>
            </c:if>
        </div>

        <div id="replyEditForm" class="reply-edit-form" style="display:none;">
            <textarea id="editReplyContent" name="inqryRply" rows="5"></textarea>
            <div class="edit-buttons">
                <button id="saveReplyEdit" data-inquiry-id="${inquiry.inqryId}" class="btn-submit">저장</button>
                <button id="cancelReplyEdit" class="btn-cancel">취소</button>
            </div>
        </div>
    </c:if> 
</div> 

<form id="deleteForm" action="/cs/inquiry/${inquiry.inqryId}/delete" method="post" style="display:none;">
</form>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>