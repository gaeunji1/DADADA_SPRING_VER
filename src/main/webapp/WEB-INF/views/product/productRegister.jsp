<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="상품 등록" scope="request"/>
 
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="product-register-page">
    
    <div class="product-form-container">
        
        <form action="/product/create" method="post">

            <div class="form-row">
                <label for="prdNm">상품명:</label>
                <input type="text" id="prdNm" name="prdNm" required />
            </div>

            <div class="form-row">
                <label for="prdCtgryId">카테고리:</label>
                <select id="prdCtgryId" name="prdCtgryId" required>
                    <option value="">카테고리 선택</option>
                    <option value="1">전자기기</option>
                    <option value="2">패션</option>
                    <option value="3">식품</option>
                    <option value="4">가구</option>
                    <option value="5">운동용품</option>
                    <option value="6">자동차용품</option>
                    <option value="7">도서</option>
                    <option value="8">반려동물용품</option>
                </select>
            </div>

            <div class="form-row">
                <label for="prdDesc">상품 상세 설명:</label> 
                <input type="text" id="prdDesc" name="prdDesc" required />
                
            </div>

            <div class="form-row">
                <label for="basePrice">기본 가격:</label>
                <input type="number" id="basePrice" name="basePrice" required />
            </div>

            <div class="form-row">
                <label for="prdQty">재고 수량:</label>
                <input type="number" id="prdQty" name="prdQty" required/>
            </div>

            <div class="form-row">
                <label for="prdImg">이미지(URL):</label>
                <input type="text" id="prdImg" name="prdImg" required/>
            </div>

            <div class="form-row">
                <label for="prdSmplDesc">한 줄 설명:</label>
                <input type="text" id="prdSmplDesc" name="prdSmplDesc" />
            </div>

            
            <div class="form-row"> 
                <button type="submit">상품 등록</button>
            </div>
        </form>
    </div> 
</div> 
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />