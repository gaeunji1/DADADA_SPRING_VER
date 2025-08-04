package com.ktdsuniversity.proj.dadada.beans.interceptors;

import java.net.URLEncoder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class CheckSessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        
        // 로그인하지 않은 경우 처리
        if (loginUser == null) {
            // 현재 요청된 URL(쿼리스트링 포함)를 구함
            String currentUrl = request.getRequestURI();
            String queryString = request.getQueryString();
            if (queryString != null) {
                currentUrl += "?" + queryString;
            }
            // URL 인코딩: redirect 파라미터로 사용하기 위함
            String encodedUrl = URLEncoder.encode(currentUrl, "UTF-8");
            
            // AJAX 요청이 아니라면 로그인 페이지로 forward 또는 redirect
            // 여기는 redirect를 사용하여 URL 파라미터로 전달하는 예시입니다.
            response.sendRedirect(request.getContextPath() + "/member/login?redirect=" + encodedUrl);
            return false;
        }
        
        // 로그인된 경우 컨트롤러 실행 계속
        return true;
    }
}