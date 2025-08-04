var IMP = window.IMP;
IMP.init('imp23223382');

var today = new Date();
var hours = today.getHours();
var minutes = today.getMinutes();
var seconds = today.getSeconds();
var milliseconds = today.getMilliseconds();
var makeMerchantUid = hours + minutes + seconds + milliseconds;

function requestPay() {
  IMP.request_pay(
    {
      pg: 'html5_inicis',
      pay_method: 'card', // 카드
      merchant_uid: 'IMP' + makeMerchantUid,
      name: '당근 10kg',
      amount: 100,
      buyer_email: 'Iamport@chai.finance',
      buyer_name: '아임포트 기술지원팀',
      buyer_tel: '010-1234-5678',
      buyer_addr: '서울특별시 강남구 삼성동',
      buyer_postcode: '123-456',
    },

    function (rsp) {
      if (rsp.success) {
        console.log('결제 성공', rsp);
        alert('결제 성공');
		fetch('/save-payment', {
		      method: 'POST',
		      headers: {
		        'Content-Type': 'application/json',
		      },
		      body: JSON.stringify(rsp),
		    });
      } else {
        console.log('결제 실패', rsp);
        alert('결제에 실패하였습니다: ' + rsp.error_msg);
      }
    }
  );
}

function requestKakaoPay() {
  IMP.request_pay(
    {
      pg: 'kakaopay',
      pay_method: 'card', // 카드
      merchant_uid: 'IMP' + makeMerchantUid,
      name: '당근 10kg',
      amount: 100,
      buyer_email: 'Iamport@chai.finance',
      buyer_name: '아임포트 기술지원팀',
      buyer_tel: '010-1234-5678',
      buyer_addr: '서울특별시 강남구 삼성동',
      buyer_postcode: '123-456',
    },

    function (rsp) {
      if (rsp.success) {
        console.log('결제 성공', rsp);
        alert('결제 성공');
      } else {
        console.log('결제 실패', rsp);
        alert('결제에 실패하였습니다: ' + rsp.error_msg);
      }
    }
  );
}

function refundPay(){
	
}

$(".refundStatusYes").on("click", function() {
             
             console.log("#payRefundModal");   
              var payMethod = $("input[name='payMethod']").val();
              var refundStatus = $("input[name='refundStatus']").val();
              jQuery.ajax({
                  url: "/reservation/json/payRefund/${reservation.reservationNo}/${reservation.payMethod}/${reservation.refundStatus}/", 
                  type: "GET",
                  dataType: "json",
                  headers: {
                      "Accept": "application/json",
                      "Content-Type": "application/json"
                  },
                  success: function (JSONData, status) {
                     console.log(JSONData);
                     
                     if (JSONData == 1) {
                     $('#refundStatus').modal("hide");
                     alert("결제 취소가 완료되었습니다.");
                     history.go(0);
                  }
                  },
                  error : function(e) {
                  alert(e.responseText);
               }
            }); 
         });
