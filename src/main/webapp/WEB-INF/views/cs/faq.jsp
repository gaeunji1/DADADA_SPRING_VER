<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="/WEB-INF/views/layout/header.jsp"/>




<div class="faq-page">
    
    <section class="faq-header">
        <h2>자주 묻는 질문</h2>
        <p class="desc">궁금하신 점을 빠르게 확인해보세요.</p>
        <div class="direct-link">
            <p>원하는 답변을 찾지 못하셨나요? <a href="/cs/inquiry" class="link-to-inquiry">문의하기</a></p>
        </div>
    </section>

    <div class="category-tabs">
        <a href="#" data-category="all" class="active">전체</a>
        <a href="#" data-category="0">일반</a>
        <a href="#" data-category="1">교환</a>
        <a href="#" data-category="2">환불</a>
    </div>

    <div class="faq-container">
        <div class="faq-item" data-category="0">
            <div class="faq-question">회원가입은 어떻게 하나요?</div>
            <div class="faq-answer">
                홈페이지 우측 상단의 '회원가입' 버튼을 클릭하여 필요한 정보를 입력하시면 회원가입이 완료됩니다. 소셜 계정(카카오, 네이버, 구글)을 통한 간편 가입도 가능합니다.
            </div>
        </div>

        <div class="faq-item" data-category="0">
            <div class="faq-question">아이디/비밀번호를 잊어버렸어요.</div>
            <div class="faq-answer">
                로그인 페이지 하단의 '아이디 찾기' 또는 '비밀번호 찾기' 버튼을 통해 본인인증 후 정보를 확인하실 수 있습니다. 비밀번호는 보안상의 이유로 재설정만 가능합니다.
            </div>
        </div>

        <div class="faq-item" data-category="0">
            <div class="faq-question">포인트는 어떻게 적립되나요?</div>
            <div class="faq-answer">
                상품 구매 시 결제 금액의 1%가 기본 적립됩니다. 리뷰 작성 시 500포인트, 포토리뷰 작성 시 1,000포인트가 추가 적립됩니다. 이벤트 참여나 첫 회원가입 시에도 특별 포인트가 지급됩니다.
            </div>
        </div>

        <div class="faq-item" data-category="0">
            <div class="faq-question">배송은 얼마나 걸리나요?</div>
            <div class="faq-answer">
                결제 완료 후 1-2일 내에 출고되며, 출고 후 배송은 보통 1-3일 정도 소요됩니다. 주문량이 많은 시즌이나 도서/산간지역은 배송이 지연될 수 있습니다. 주문 상태는 '마이페이지 > 주문 내역'에서 확인 가능합니다.
            </div>
        </div>

        <div class="faq-item" data-category="0">
            <div class="faq-question">해외 배송도 가능한가요?</div>
            <div class="faq-answer">
                현재 해외 배송은 지원하지 않습니다. 추후 해외 배송 서비스가 오픈되면 공지사항을 통해 안내해 드리겠습니다.
            </div>
        </div>

        <div class="faq-item" data-category="0">
            <div class="faq-question">주문 취소는 어떻게 하나요?</div>
            <div class="faq-answer">
                '마이페이지 > 주문 내역'에서 취소하려는 주문의 '주문 취소' 버튼을 클릭하시면 됩니다. 단, 상품 준비 중 단계까지만 직접 취소가 가능하며, 이후에는 고객센터로 문의해 주세요.
            </div>
        </div>

        <div class="faq-item" data-category="1">
            <div class="faq-question">상품 교환은 어떻게 신청하나요?</div>
            <div class="faq-answer">
                '마이페이지 > 주문 내역'에서 해당 상품의 '교환 신청' 버튼을 클릭하여 신청하실 수 있습니다. 교환 사유와 함께 상품 상태를 사진으로 첨부해 주시면 빠른 처리에 도움이 됩니다.
            </div>
        </div>

        <div class="faq-item" data-category="1">
            <div class="faq-question">교환 시 배송비는 누가 부담하나요?</div>
            <div class="faq-answer">
                상품 불량이나 오배송의 경우 당사에서 배송비를 부담합니다. 고객 변심으로 인한 교환의 경우, 왕복 배송비 5,000원을 고객님께서 부담하셔야 합니다. 제주도 및 도서산간 지역은 추가 배송비가 발생할 수 있습니다.
            </div>
        </div>

        <div class="faq-item" data-category="1">
            <div class="faq-question">교환 가능 기간은 얼마인가요?</div>
            <div class="faq-answer">
                상품 수령 후 7일 이내에 교환 신청이 가능합니다. 단, 상품이 사용되었거나 훼손된 경우, 그리고 고객의 책임 있는 사유로 상품이 멸실되거나 훼손된 경우에는 교환이 불가능할 수 있습니다.
            </div>
        </div>

        <div class="faq-item" data-category="1">
            <div class="faq-question">상품이 파손되거나 변질되어 도착했어요. 교환 가능한가요?</div>
            <div class="faq-answer">
                네, 상품 파손이나 변질의 경우 즉시 교환 가능합니다. '마이페이지 > 주문 내역'에서 해당 상품의 '교환 신청' 버튼을 클릭한 후, 상품 상태 사진을 첨부해 주세요. 이런 경우 왕복 배송비는 당사에서 부담합니다. 신선식품의 경우 배송 당일 연락 주시면 더욱 신속한 처리가 가능합니다.
            </div>
        </div>

        <div class="faq-item" data-category="1">
            <div class="faq-question">교환 신청 후 처리 과정이 궁금해요.</div>
            <div class="faq-answer">
                교환 신청 > 판매자 승인 > 반품 상품 회수 > 상품 상태 확인 > 교환 상품 발송의 순서로 진행됩니다. 각 단계별 진행 상황은 마이페이지에서 확인 가능하며, 처리까지 약 3-7일 정도 소요됩니다.
            </div>
        </div>

        <div class="faq-item" data-category="2">
            <div class="faq-question">환불은 언제 완료되나요?</div>
            <div class="faq-answer">
                반품 상품이 판매자에게 도착하여 검수 완료 후 1-3영업일 내에 환불이 진행됩니다. 결제 수단에 따라 환불 처리 시간이 다를 수 있으며, 카드사 사정에 따라 카드 환불의 경우 최대 7일까지 소요될 수 있습니다.
            </div>
        </div>

        <div class="faq-item" data-category="2">
            <div class="faq-question">묶음 상품 중 일부만 환불할 수 있나요?</div>
            <div class="faq-answer">
                세트 구성이 아닌 묶음 배송 상품의 경우, 특정 상품만 선택하여 부분 환불이 가능합니다. 단, 세트 할인이 적용된 상품은 세트 전체를 반품해야 합니다. 부분 환불 시에도 무료배송 기준 금액이 변경될 경우 초기 배송비가 추가로 청구될 수 있습니다. 자세한 사항은 고객센터로 문의해 주세요.
            </div>
        </div>

        <div class="faq-item" data-category="2">
            <div class="faq-question">프로모션 상품을 환불할 경우 사은품도 반납해야 하나요?</div>
            <div class="faq-answer">
                네, 프로모션으로 증정된 사은품은 상품 환불 시 함께 반납하셔야 합니다. 사은품 누락 또는 사용/개봉된 경우 사은품 가액만큼 환불 금액에서 차감될 수 있습니다. 일부 소모성 샘플이나 소액 사은품의 경우 반납이 면제될 수 있으니, 환불 전 고객센터에 문의해 주세요.
            </div>
        </div>

        <div class="faq-item" data-category="2">
            <div class="faq-question">현금으로 결제했는데 환불은 어떻게 받나요?</div>
            <div class="faq-answer">
                계좌이체나 무통장입금으로 결제하신 경우, 환불 신청 시 입력하신 환불계좌로 입금됩니다. 환불계좌는 주문자 본인 명의의 계좌만 가능하며, 타인 명의 계좌로는 환불이 불가능합니다.
            </div>
        </div>

        <div class="faq-item" data-category="2">
            <div class="faq-question">환불 신청 후 취소할 수 있나요?</div>
            <div class="faq-answer">
                환불 처리가 시작되기 전이라면 '마이페이지 > 주문내역'에서 해당 주문의 '환불 취소' 버튼을 통해 취소 가능합니다. 이미 처리가 진행된 경우에는 고객센터로 문의해 주세요.
            </div>
        </div>

        <div class="faq-item" data-category="2">
            <div class="faq-question">상품에 문제가 있어 환불받고 싶어요.</div>
            <div class="faq-answer">
                상품 불량이나 오배송으로 인한 환불은 '마이페이지 > 주문내역'에서 신청 가능합니다. 상품 상태를 확인할 수 있는 사진을 첨부해 주시면 더 신속한 처리가 가능합니다. 이 경우 반품 배송비는 당사에서 부담합니다.
            </div>
        </div>
    </div> <div class="faq-footer">
        <p>원하는 답변을 찾지 못하셨나요?</p>
        <a href="/cs/inquiry/write" class="btn-inquiry">1:1 문의하기</a>
    </div>

    <div class="faq-guide">
        <h3><i class="fas fa-info-circle"></i> FAQ 이용 안내</h3>
        <ul>
            <li>자주 묻는 질문은 카테고리별로 확인하실 수 있습니다.</li>
            <li>찾으시는 답변이 없는 경우 1:1 문의를 이용해 주세요.</li>
            <li>상품별 상세 문의는 해당 상품 페이지에서 문의하시면 더 빠른 답변을 받으실 수 있습니다.</li>
        </ul>
    </div>
</div> 

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>