package com.ktdsuniversity.proj.dadada.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktdsuniversity.proj.dadada.beans.interceptors.AdminAccessInterceptor;
import com.ktdsuniversity.proj.dadada.beans.interceptors.AutoLoginInterceptor;
import com.ktdsuniversity.proj.dadada.beans.interceptors.CheckSessionInterceptor;
import com.ktdsuniversity.proj.dadada.beans.interceptors.LoginInterceptor;

@Configurable // --> Bean 을 매번 생성하는 역할 (Prototype scope)
@Configuration // --> Bean 을 한 번만 생성하는 역할 (Singleton scope)
@EnableWebMvc // Spring Validator 를 사용할 준비를 한다. 
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/resources/");
		
	}
	
	  @Autowired
	    private AutoLoginInterceptor autoLoginInterceptor; // 자동 로그인

	
	/*
	 * interceptor들을 추가한다
	 * */
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new CheckSessionInterceptor())
	        .addPathPatterns("/roulette/**", "/mypage/**", "/room/**",  "/shoplaylist/**", "/cs/**") // 로그인 필요 페이지 지정
	        .excludePathPatterns("/member/login", "/member/regist", "/css/**", "/js/**", "/cs/inquiry", "/cs/inquiry/image/**", "/cs/faq");
	    
	    registry.addInterceptor(new AdminAccessInterceptor())
        	.addPathPatterns("/admin/**"); // 관리자 영역 보호
	    
	    registry.addInterceptor(autoLoginInterceptor)
        .addPathPatterns("/**") // 모든 경로에 적용
        .excludePathPatterns("/member/login", "/member/regist", "/css/**", "/js/**", "/images/**"); // 제외 경로
	    
	    registry.addInterceptor(new LoginInterceptor())
        .addPathPatterns("/buy/**", "/member/modify", "/cart/**", "/mypage/**")
        .excludePathPatterns("/member/login", "/member/regist", "/css/**", "/js/**", "/images/**");

	}
	
	
	/**
	 * [Favicon.ico 404 에러 해결을 위한 컨트롤러]
	 * 
	 * 원인: 브라우저는 웹사이트 방문 시 자동으로 /favicon.ico를 요청하는데,
	 * 이 요청을 처리할 핸들러가 없어서 404 에러 발생 및 로그 오염
	 * 
	 * 해결책: 이 컨트롤러가 /favicon.ico 요청을 가로채서 빈 응답(204 No Content)을 반환함
	 * 다른 방법으로는 /static 폴더에 favicon.ico 파일을 추가하는 것도 가능
	 */
	@Controller
	public class FaviconController {
	    @GetMapping("favicon.ico")
	    @ResponseBody
	    public void returnNoFavicon() {
	        // 빈 응답 반환
	    }
	}

}
