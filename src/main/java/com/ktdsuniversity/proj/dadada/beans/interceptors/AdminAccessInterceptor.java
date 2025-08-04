package com.ktdsuniversity.proj.dadada.beans.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;

public class AdminAccessInterceptor implements HandlerInterceptor {

   
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    HttpSession session = request.getSession(false);

	    // 1. 로그인 안 한 경우
	    if (session == null || session.getAttribute("loginUser") == null) {
	        response.sendRedirect(request.getContextPath() + "/member/login");
	        return false;
	    }

	    // 2. 로그인 했지만 관리자 아님 (adminYn != 1)
	    MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	    if (loginUser.getAdminYn() != 1) {
	        response.sendRedirect(request.getContextPath() + "/error/unauthorized");
	        return false;
	    }

	    // 3. 관리자 접근 허용
	    return true;
	}
}