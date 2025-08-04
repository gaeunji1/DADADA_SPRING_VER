package com.ktdsuniversity.proj.dadada.beans.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CartLoginSessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();

        // 이미 USER_ID가 세션에 없고
        if (session.getAttribute("USER_ID") == null) {
            // LOGIN_ID가 있다면 MemberService로부터 MBR_ID를 조회해서 세션에 넣는다
            String loginId = (String) session.getAttribute("LOGIN_ID");
            if (loginId != null) {
                // 이 부분은 Login 안 건드리는 조건에 맞춰,
                // MemberService 쓸 수 없으므로 (막혀있으니까)
                // 강제로 mbrId를 특정값으로 세팅하거나,
                // 추후 확장 가능하도록 로그만 찍어둔다
                System.out.println("⚠ USER_ID가 세션에 없음. LOGIN_ID: " + loginId);
                // session.setAttribute("USER_ID", 5); // 테스트용
            }
        }

        return true;
    }
}
