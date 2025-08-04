package com.ktdsuniversity.proj.dadada.beans.interceptors;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.proj.dadada.member.service.MemberService;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;

@Component
public class AutoLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        // 이미 로그인된 사용자면 패스
        if (session.getAttribute("loginUser") != null) {
            return true;
        }

        // 쿠키 검사
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("autoLoginId".equals(cookie.getName())) {
                    String mbrInsId = cookie.getValue();
                    MemberVO member = memberService.getMemberByInsId(mbrInsId);
                    if (member != null) {
                        session.setAttribute("loginUser", member);
                        System.out.println("자동 로그인 적용됨: " + mbrInsId);
                    }
                    break;
                }
            }
        }

        return true;
    }
}