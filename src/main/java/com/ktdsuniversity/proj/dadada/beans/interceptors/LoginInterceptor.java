package com.ktdsuniversity.proj.dadada.beans.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        // 로그인 여부 확인
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null) {
            return true; // 로그인 되어 있음 → 통과
        }

        // 로그인 안 된 경우 → 원래 URL 저장 후 로그인 페이지로 리다이렉트
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        String targetUrl = (query == null) ? uri : uri + "?" + query;

        String encodedUrl = URLEncoder.encode(targetUrl, StandardCharsets.UTF_8);

        response.sendRedirect(request.getContextPath() + "/member/login?redirect=" + encodedUrl);
        return false;
    }
}