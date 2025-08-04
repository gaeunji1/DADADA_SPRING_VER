package com.ktdsuniversity.proj.dadada.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 권한 에러, 일반 에러 등의 전용 컨트롤러
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    // 접근 권한 없을 때 보여줄 페이지
    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "error/unauthorized"; // → /WEB-INF/views/error/unauthorized.jsp
    }

    // 필요한 경우 다른 에러 페이지도 여기에 추가 가능
}