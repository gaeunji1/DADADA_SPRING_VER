$(function(){
   const IMP = window.IMP;
   IMP.init('imp23223382');
 
   const STEP_MULT     = 50,
         STEP_BASE_DIV = 200,
         STEP_EXP      = -0.6,
         RATE_MULT     = 0.04,
         RATE_BASE_DIV = 200,
         RATE_EXP      = -0.5,
         MAX_MULT      = 0.223,
         MAX_BASE_DIV  = 235,
         MAX_EXP       = -0.3;
 
   function calcDiscountRate(P, Q){
     const deltaQ    = STEP_MULT * Math.pow(P/STEP_BASE_DIV, STEP_EXP);
     const rateRaw   = RATE_MULT * Math.pow(P/RATE_BASE_DIV, RATE_EXP);
     const Dmax      = MAX_MULT  * Math.pow(P/MAX_BASE_DIV,  MAX_EXP);
     const N         = Math.floor(Q / deltaQ);
     return { deltaQ, rate: Math.min(N * rateRaw, Dmax), Dmax };
   }
 
   function updateRow($row){
     const id    = +$row.data('prdid');
     const P     = +$row.data('baseprice');
     const Q     = +$row.find('.cart-item-qty-input').val();
     const name  = $row.data('prdname');
 
     const { deltaQ, rate, Dmax } = calcDiscountRate(P, Q);
     const unitDisc   = Math.round(P * rate);
     const totalDisc  = unitDisc * Q;
     const unitPrice  = P - unitDisc;
     const totalPrice = unitPrice * Q;
     const baseTotal  = P * Q;
 
     $row.find('.cart-step-qty').text(Math.ceil(deltaQ) + '개');
     $row.find('.cart-discount-rate').text((rate*100).toFixed(2) + '%');
     $row.find('.cart-total-discount-amount').text(totalDisc.toLocaleString() + '원');
     $row.find('.cart-unit-discounted-price').text(unitPrice.toLocaleString() + '원');
     $row.find('.cart-item-total').text(totalPrice.toLocaleString() + '원');
     $row.find('.cart-max-discount-rate').text((Dmax*100).toFixed(2) + '%');
 
     $row.find('.cart-item-base-total').text(baseTotal.toLocaleString() + '원');
     $row.find('.cart-original-total-price').text((P * Q).toLocaleString() + '원');
     
     return {
       prdId: id,
       name: name,
       qty: Q,
       unitPrice: unitPrice,
       totalDisc: totalDisc,
       totalPrice: totalPrice
     };
   }
 
   function collectCartData(){
     let productTotal = 0;
     const items = [];
 
     $('.cart-item').each(function(){
       const data = updateRow($(this));
       items.push(data);
       productTotal += data.totalPrice;
     });
 
     const FREE_SHIPPING_THRESHOLD = 50000;
     const SHIPPING_FEE = 2500;
     const shippingFee = productTotal >= FREE_SHIPPING_THRESHOLD ? 0 : SHIPPING_FEE;
     const total = productTotal + shippingFee;
 
     $("#productTotalPrice").text(productTotal.toLocaleString());
     $("#shippingFee").text(shippingFee.toLocaleString());
     $("#totalPrice").text(total.toLocaleString());
 
     window.productTotal = productTotal;
     window.shippingFee = shippingFee;
     window.finalTotalPrice = total;
 
     return {
       amount: total,
       items
     };
   }
 
   $('#btnPay').click(function(){
     const cart = collectCartData();
     const merchantUid = 'imp'+Date.now();
     const name = cart.items.map(i=>i.name).join(', ');
 
     IMP.request_pay({
       pg: 'html5_inicis.INIpayTest',
       pay_method: 'card',
       merchant_uid: merchantUid,
       name: name,
       amount: cart.amount,
       buyer_email: buyerEmail,
       buyer_name:  buyerName,
       buyer_tel: '010-0000-0000'
     }, function(rsp){
       if(rsp.success){
         const cart = collectCartData();
         sessionStorage.setItem('packageItems', JSON.stringify(cart.items));
         const payload = {
           impUid: rsp.imp_uid,
           merchantUid: rsp.merchant_uid,
           payMethod: rsp.pay_method,
           status: rsp.status,
           paidAmount: rsp.paid_amount,
           pgTid: rsp.pg_tid,
           items: cart.items,
           ordDvsn: window.orderDvsn || 'regular',
           success: true
         };
         $.ajax({
           url: contextPath+'/payment/success',
           method: 'POST',
           contentType: 'application/json',
           data: JSON.stringify(payload),
           success: () => { window.location.href = contextPath + '/payment/result?imp_uid=' + rsp.imp_uid + '&askPackage=true'; },
           error: () => { alert('결제 데이터 전송 실패'); }
         });
       } else {
         alert('결제 실패: ' + rsp.error_msg);
       }
     });
   });
 
   $(document).on('change','.cart-item-qty-input',function(){
     const $i = $(this);
     $.ajax({
       url: contextPath+'/cart/update',
       method: 'POST',
       contentType: 'application/json',
       data: JSON.stringify({ cartId: $i.data('cartid'), prdId: $i.data('prdid'), qty: +$i.val() }),
       success: collectCartData,
       error: () => alert('수량 업데이트 실패')
     });
   });
 
   $(document)
     .on('click','.cart-delete-btn',function(){
       const $b = $(this);
       $.ajax({
         url: contextPath+'/cart/delete',
         method: 'POST',
         contentType: 'application/json',
         data: JSON.stringify({ cartId: $b.data('cartid'), prdId: $b.data('prdid') }),
         success: () => location.reload(),
         error: () => alert('삭제 실패')
       });
     }).on('click','.cart-continue-shopping-btn', () => window.location.href = contextPath + '/product/list');
 
   $(document).on('click', '.cart-qty-increase', function(){
     const $input = $(this).siblings('.cart-item-qty-input');
     $input.val(parseInt($input.val()) + 1).trigger('change');
   });
 
   $(document).on('click', '.cart-qty-decrease', function(){
     const $input = $(this).siblings('.cart-item-qty-input');
     const currentVal = parseInt($input.val());
     if (currentVal > 1) {
       $input.val(currentVal - 1).trigger('change');
     }
   });
 
   $('#btnClearCart').on('click', function(){
     if (!confirm('정말 장바구니를 모두 비우시겠습니까?')) return;
 
     $.ajax({
       url: contextPath + '/cart/clearAll',
       method: 'POST',
       success: function(){
         alert('장바구니가 모두 비워졌습니다.');
         location.reload();
       },
       error: function(xhr){
         alert('전체 삭제에 실패했습니다: ' + xhr.responseText);
       }
     });
   });
 
   collectCartData();
 });