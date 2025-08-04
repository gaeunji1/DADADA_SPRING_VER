<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageTitle" value="문의하기" scope="request"/>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<div class="inquiry-form-page">
    
    <div class="inquiry-form-box">
        <h2>
            <c:choose>
                <c:when test="${empty inquiry}">문의글 작성</c:when>
                <c:otherwise>문의글 수정</c:otherwise>
            </c:choose>
        </h2>

        <!-- ① 주문정보가 넘어왔으면 상단에 출력 -->
        <c:if test="${not empty order}">
            <div class="order-info" style="margin-bottom:1em; padding:0.5em; border:1px solid #ddd;">
                <p><strong>주문번호:</strong> ${order.ordId}</p>
                <p>
                    <strong>상품명:</strong>
                    <c:choose>
                        <c:when test="${order.ordDvsn == 'package'}">
                            ${order.pckgNm}
                        </c:when>
                        <c:otherwise>
                            ${order.prdNm}
                        </c:otherwise>
                    </c:choose>
                </p>
                <!-- ② 경쟁 룸 구매면 환불 불가 안내 -->
                <c:if test="${order.ordDvsn == 'competition'}">
                    <div style="color:#d9534f;">
                        ⚠️ 경쟁 룸 구매 상품은 환불이 불가합니다.
                    </div>
                </c:if>
            </div>
        </c:if>

        <form action="${empty inquiry ? '/cs/inquiry/write' 
                                       : '/cs/inquiry/'.concat(inquiry.inqryId).concat('/edit')}"
              method="post" enctype="multipart/form-data">

            <!-- ③ ordId 히든으로 전달 -->
            <c:if test="${not empty order}">
                <input type="hidden" name="ordId" value="${order.ordId}" />
            </c:if>

            <c:if test="${not empty inquiry}">
                <input type="hidden" name="inqryId" value="${inquiry.inqryId}" />
            </c:if>
            
            <div class="form-group">
                <label for="inqryCtgry">문의 유형</label>
                <select id="inqryCtgry" name="inqryCtgry" required>
                    <option value="0" <c:if test="${inquiry.inqryCtgry == 0}">selected</c:if>>
                        일반 문의
                    </option>
                    <option value="1" <c:if test="${inquiry.inqryCtgry == 1}">selected</c:if>>
                        교환 문의
                    </option>
                    <!-- canRefund 이 true 일 때만 환불 옵션 노출 -->
                    <c:if test="${canRefund}">
                        <option value="2" <c:if test="${inquiry.inqryCtgry == 2}">selected</c:if>>
                            환불 문의
                        </option>
                    </c:if>
                </select>
            </div>

            <div class="form-group">
                <label for="inqryTtl">제목</label>
                <input type="text" id="inqryTtl" name="inqryTtl"
                       value="<c:out value='${inquiry.inqryTtl}'/>" required>
            </div>

            <div class="form-group">
                <label for="inqryCntnt">내용</label>
                <textarea id="inqryCntnt" name="inqryCntnt" rows="10" required>
                    <c:out value='${inquiry.inqryCntnt}'/>
                </textarea>
            </div>

            <div class="form-group">
                <label>공개 설정</label>
                <div class="radio-group">
                    <label>
                        <input type="radio" name="inqryPrvt" value="0"
                               <c:if test="${empty inquiry || inquiry.inqryPrvt == 0}">checked</c:if>>
                        공개
                    </label>
                    <label>
                        <input type="radio" name="inqryPrvt" value="1"
                               <c:if test="${inquiry.inqryPrvt == 1}">checked</c:if>>
                        비공개
                    </label>
                </div>
            </div>

            <div class="form-group">
                <label for="imageFile">이미지 첨부</label>
                <input type="file" id="imageFile" name="imageFile"
                       accept=".jpg, .jpeg, .png, image/jpeg, image/png">
                <p class="small">* 최대 30MB, <strong>JPG, JPEG, PNG</strong> 파일만 업로드 가능합니다.</p>
                <p id="fileTypeError" class="error-message" style="display:none; color:#FF0000;">
                    JPG, JPEG, PNG 형식의 이미지만 업로드 가능합니다.
                </p>
                <p id="fileSizeError" class="error-message" style="display:none; color:#FF0000;">
                    이미지 파일은 30MB를 초과할 수 없습니다.
                </p>
                <div id="imagePreviewContainer" style="display:none; margin-top:10px;">
                    <p>미리보기:</p>
                    <img id="imagePreview" src="#" alt="이미지 미리보기"
                         style="max-width:200px; max-height:100px;">
                </div>

                <c:if test="${not empty inquiry.inqryImgPath}">
                    <%
                        // 기존 이미지 파일명 분리 로직
                        com.ktdsuniversity.proj.dadada.cs.vo.InquiryVO vo =
                            (com.ktdsuniversity.proj.dadada.cs.vo.InquiryVO) request.getAttribute("inquiry");
                        String imgPath = vo.getInqryImgPath();
                        String fileName = "";
                        if (imgPath != null) {
                            int lastSlash = Math.max(imgPath.lastIndexOf('/'), imgPath.lastIndexOf('\\'));
                            fileName = imgPath.substring(lastSlash + 1);
                            request.setAttribute("fileName", fileName);
                        }
                    %>
                    <div class="current-image mt-2">
                        <p>현재 첨부된 이미지:</p>
                        <img src="/cs/inquiry/image/${fileName}"
                             alt="첨부 이미지" class="thumbnail"
                             style="max-width:200px; max-height:100px;">
                        <p class="small">* 새 이미지를 업로드하면 기존 이미지는 대체됩니다.</p>
                    </div>
                </c:if>
            </div>

            <div class="btn-wrap">
                <button type="submit" id="submitButton" class="btn-submit">
                    <c:choose>
                        <c:when test="${empty inquiry}">등록하기</c:when>
                        <c:otherwise>수정하기</c:otherwise>
                    </c:choose>
                </button>
                <a href="${empty inquiry 
                            ? '/cs/inquiry' 
                            : '/cs/inquiry/'.concat(inquiry.inqryId)}"
                   class="btn-cancel">취소</a>
            </div>
        </form>
    </div> 

    <div id="exchangeRefundModal" class="modal-container" style="display: none;">
            <div class="modal-content">
         <div class="modal-header">
           <h3 id="modalTitle">교환/환불 안내</h3>
           <span class="close-btn">&times;</span>
         </div>
         <div class="modal-body">
           <div id="exchangeGuide" style="display: none;">
             <h4>교환 신청 전 확인사항</h4>
             <ul>
               <li>상품 수령 후 7일 이내에 교환 신청이 가능합니다.</li>
               <li>고객 변심으로 인한 교환의 경우 배송비는 고객 부담입니다.</li>
               <li>상품 하자로 인한 교환은 배송비를 부담하지 않습니다.</li>
               <li>교환 접수 시 상품 사진을 첨부해 주시면 빠른 처리가 가능합니다.</li>
               <li>교환 처리는 재고 상황에 따라 지연될 수 있습니다.</li>
             </ul>
           </div>
           <div id="refundGuide" style="display: none;">
             <h4>환불 신청 전 확인사항</h4>
             <ul>
               <li>상품 수령 후 7일 이내에 환불 신청이 가능합니다.</li>
               <li>고객 변심으로 인한 환불의 경우 배송비는 고객 부담입니다.</li>
               <li>상품 하자로 인한 환불은 배송비를 부담하지 않습니다.</li>
               <li>환불 접수 시 상품 사진을 첨부해 주시면 빠른 처리가 가능합니다.</li>
               <li>환불은 상품 회수 및 검수 후 처리되며, 결제 수단에 따라 3-7일 소요됩니다.</li>
             </ul>
           </div>
         </div>
         <div class="modal-footer">
           <button id="modalConfirmBtn" class="btn-primary">확인했습니다</button>
         </div>
       </div>
    </div>

</div> 

<script>
    $(document).ready(function() {
        <c:if test="${not empty errorMessage}">
            alert("<c:out value='${errorMessage}' />");
        </c:if>
    });
</script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>