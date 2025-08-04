var IMP;
document.addEventListener('DOMContentLoaded', function() {
  if (window.IMP && typeof window.IMP.init === 'function') {
    IMP = window.IMP;
    IMP.init("imp23223382");
  } else {
    console.error("Iamport 라이브러리가 로드되지 않았습니다.");
  }
});

function requestPay(onSuccess) {
  var contextPath  = window.ctx;
  var quantity     = parseInt(window.selectedQty, 10) || 0;
  var merchantUid  = "imp" + Date.now();
  var isCompetition = window.orderDvsn === "competition";
  
  const productTotal = window.productPrice * quantity;
  const shippingFee = productTotal >= 50000 ? 0 : 2500;
  const totalAmount = productTotal + shippingFee;
  
  IMP.request_pay(
    {
      pg: "html5_inicis.INIpayTest",
      pay_method: "card",
      merchant_uid: merchantUid,
      name: window.productName,
      amount: totalAmount,
      buyer_email: window.buyerEmail,
      buyer_name: window.buyerName,
      buyer_tel: window.buyerPhone,
      buyer_addr: window.buyerAddr,
      buyer_postcode: window.buyerPostcode,
    },
    function (rsp) {
      if (!rsp.success) {
        return alert("결제 실패: " + rsp.error_msg);
      }

      alert("결제 성공!");

      // 패키지 대상 저장
      sessionStorage.setItem(
        'packageItems',
        JSON.stringify([{ prdId: window.productId }])
      );

      // 결제 정보 payload
      const paymentPayload = {
        impUid:      rsp.imp_uid,
        merchantUid: rsp.merchant_uid,
        payMethod:   rsp.pay_method,
        status:      rsp.status,
      /*  paidAmount:  rsp.paid_amount,*/
        pgTid:       rsp.pg_tid,
        paidAmount: totalAmount,
        shippingFee: shippingFee, 
        items:       [{ prdId: window.productId, qty: quantity }],
        buyerEmail:  rsp.buyer_email,
        deliveryMsg: (document.querySelector("textarea[name='deliveryMsg']")?.value || ""),
		ordDvsn:     window.orderDvsn || 'regular',
        success:     true
      };

      // ① 결제 정보 저장 후 콜백에서 리다이렉트
      $.ajax({
        url: contextPath + "/payment/success",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(paymentPayload),
        success: function () {
          console.log("결제 정보 저장 완료");
          let redirectUrl = contextPath + "/payment/result?imp_uid=" + rsp.imp_uid;
           if (!isCompetition) {
             redirectUrl += "&askPackage=true";
           }

           window.location.href = redirectUrl;  
      },
        error: function () {
          alert("결제 정보 저장 실패");
        }
      });

      // ② (선택) 배송 정보도 백그라운드에서 저장
      const shippingPayload = {
        buyerName: (document.getElementById("receiverName")?.value || ""),
        buyerPhone:(document.getElementById("receiverPhone")?.value || ""),
        buyerAddr:
          (document.getElementById("receiverAddr1")?.value || "") + " " +
          (document.getElementById("receiverAddr2")?.value || ""),
        deliveryMsg: (document.querySelector("textarea[name='deliveryMsg']")?.value || ""),
        shpStat:    "결제 완료",
        merchantUid:rsp.merchant_uid
      };
      $.ajax({
        url: contextPath + "/shipping/save",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(shippingPayload),
        success: function () { console.log("배송 정보 저장 완료"); },
        error:   function () { console.warn("배송 정보 저장 실패"); }
      });

      // 콜백
      if (typeof onSuccess === "function") {
        onSuccess(rsp);
      }
    }
  );
}