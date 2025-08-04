package com.ktdsuniversity.proj.dadada.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String mainPage() {
        return "main";  // ⇒ /WEB-INF/views/main.jsp
    }
    @GetMapping("/company")
    public String companyPage() {
        return "detail/company";  // ⇒ /WEB-INF/views/main.jsp
    }
    @GetMapping("/terms")
    public String termsPage() {
        return "detail/terms";  // ⇒ /WEB-INF/views/main.jsp
    }
    @GetMapping("/privacy")
    public String privacyPage() {
        return "detail/privacy";  // ⇒ /WEB-INF/views/main.jsp
    }
}