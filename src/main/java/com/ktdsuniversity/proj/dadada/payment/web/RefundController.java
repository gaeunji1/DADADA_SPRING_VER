package com.ktdsuniversity.proj.dadada.payment.web;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.proj.dadada.payment.dao.PaymentDAO;
import com.ktdsuniversity.proj.dadada.payment.vo.PaymentVO;
import com.ktdsuniversity.proj.dadada.shipping.service.ShippingService;

@Controller
public class RefundController {

    private final String API_KEY = "8776708412781406";
    private final String API_SECRET = "6p87b0uJmqyiX41AiVwXqRMDHIJciOIcf50uGCsHPv638Ry95hBGF7DjoQkg1TQ1CqiI9BfFEJtKspuM";

    @GetMapping("/refund")
    public String showRefundPage() {
        return "payment/refund";
    }
    @Autowired
    private PaymentDAO paymentDao;
    
    @Autowired
    private ShippingService shippingService;


    @PostMapping("/refund/process")
    public String processRefund(@RequestParam("imp_uid") String impUid, Model model) {
        try {
            // 1. access token 요청
            String token = getAccessToken();
            if (token == null) {
                model.addAttribute("result", false);
                model.addAttribute("message", "토큰 발급 실패");
                return "payment/refund-result";
            }

            // 2. 환불 요청
            boolean isSuccess = requestCancel(impUid, token);

            // ✅ 3. 환불 성공 시, DB 상태도 'refund'로 변경

            if (isSuccess) {
                PaymentVO pmt = paymentDao.selectPaymentByImpUid(impUid);
                int ordId = Integer.parseInt(pmt.getMerchantUid());

                shippingService.updateShippingStatus(ordId, "취소 완료");
            }

            model.addAttribute("result", isSuccess);
            model.addAttribute("impUid", impUid);
            model.addAttribute("message", isSuccess ? "환불 성공" : "환불 실패");
            return "payment/refund-result";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("result", false);
            model.addAttribute("message", "예외 발생: " + e.getMessage());
            return "payment/refund-result";
        }
    }


    private String getAccessToken() throws IOException {
        URL url = new URL("https://api.iamport.kr/users/getToken");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonBody = "{ \"imp_key\": \"" + API_KEY + "\", \"imp_secret\": \"" + API_SECRET + "\" }";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonBody.getBytes("utf-8"));
        }

        if (conn.getResponseCode() != 200) return null;

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        br.close();

     // ✅ 문자열에서 access_token 직접 파싱
        String token = null;
        String target = "\"access_token\":\"";
        int start = response.indexOf(target);
        if (start != -1) {
            int from = start + target.length();
            int to = response.indexOf("\"", from);
            token = response.substring(from, to);
        }

        return token;
    }


    private boolean requestCancel(String impUid, String token) throws IOException {
        URL url = new URL("https://api.iamport.kr/payments/cancel");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", token);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonBody = "{ \"imp_uid\": \"" + impUid + "\" }";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonBody.getBytes("utf-8"));
        }

        return conn.getResponseCode() == 200;
    }
}
