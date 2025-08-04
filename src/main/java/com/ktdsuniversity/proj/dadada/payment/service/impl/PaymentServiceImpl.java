package com.ktdsuniversity.proj.dadada.payment.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ktdsuniversity.proj.dadada.payment.dao.PaymentDAO;
import com.ktdsuniversity.proj.dadada.payment.service.PaymentService;
import com.ktdsuniversity.proj.dadada.payment.vo.PaymentVO;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final String IMP_KEY    = "8776708412781406";
    private static final String IMP_SECRET = "6p87b0uJmqyiX41AiVwXqRMDHIJciOIcf50uGCsHPv638Ry95hBGF7DjoQkg1TQ1CqiI9BfFEJtKspuM";
    private static final String TOKEN_URL  = "https://api.iamport.kr/users/getToken";

    private final RestTemplate rest = new RestTemplate();

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public String getAccessToken() {
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        String body = String.format("{\"imp_key\":\"%s\",\"imp_secret\":\"%s\"}", IMP_KEY, IMP_SECRET);

        ResponseEntity<Map> res = rest.postForEntity(TOKEN_URL, new HttpEntity<>(body, h), Map.class);
        if (res.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException("토큰 발급 실패: " + res.getStatusCode());

        return (String)((Map)res.getBody().get("response")).get("access_token");
    }

    @Override
    public Map<String,Object> verifyPayment(String impUid) {
        String token = getAccessToken();
        HttpHeaders h = new HttpHeaders();
        h.set("Authorization", token);

        String url = "https://api.iamport.kr/payments/" + impUid;
        ResponseEntity<Map> res = rest.exchange(url, HttpMethod.GET, new HttpEntity<>(h), Map.class);
        if (res.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException("결제 검증 실패: " + res.getStatusCode());

        return (Map<String,Object>) res.getBody().get("response");
    }

    @Override
    @Transactional
    public void insertPayment(PaymentVO vo) {
        paymentDAO.insertPayment(vo);
    }

    @Override
    public PaymentVO getPaymentByImpUid(String impUid) {
        return paymentDAO.selectPaymentByImpUid(impUid);
    }

    @Override
    @Transactional
    public boolean refundPayment(String impUid) {
        int rows = paymentDAO.updatePaymentStatusToRefund(impUid);
        return rows > 0;
    }
    
    @Override
    public PaymentVO getPaymentByMerchantUid(String merchantUid) {
        return paymentDAO.selectPaymentByMerchantUid(merchantUid);
    }
    @Override
    public PaymentVO getPaymentByOrderId(int ordId) {
    	return paymentDAO.selectPaymentByOrderId(ordId);
    }
}