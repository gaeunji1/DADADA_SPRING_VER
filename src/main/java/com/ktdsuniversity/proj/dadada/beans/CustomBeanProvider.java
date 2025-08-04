package com.ktdsuniversity.proj.dadada.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Hello-Spring project에 필요한 bean들을 생성하는 역할.
 * 생성하는 Bean들의 종류.
 * 	- @Controller, @Service, @Repository, @Component를 제외한 모든 빈.
 */

@Configuration // Spring Boot Application의 설정을 담당 & Bean 생성.
public class CustomBeanProvider {

	// application.yml 파일의 설정을 얻어올 수 있음.
	// -> @Controller, @Service, @Repository, @Component에서 얻어올 수 있음.
	@Value("${app.multipart.base-dir.windows:C:\\upload-files}")
	private String baseDirWindows;
	@Value("${app.multipart.base-dir.linux:/var/local/src/upload-files}")
	private String baseDirLinux;
	@Value("${app.multipart.base-dir.macos:/document/upload-files}")
	private String baseDirMacos;

	@Value("${app.multipart.obfuscation.enable:false}")
	private boolean obfuscationEnable;
	@Value("${app.multipart.obfuscation.hide-ext.enable:false}")
	private boolean obfuscationHideExtEnable;
	
	
	// FileHandler Bean 생성 (빈의 이름을 명시하지 않으면 메소드의 이름이 빈의 이름이 된다.)
	@Bean
	FileHandler fileHandler() {
		FileHandler fileHandler = new FileHandler();
		fileHandler.setBaseDirLinux(baseDirLinux);
		fileHandler.setBaseDirWindows(baseDirWindows);
		fileHandler.setBaseDirMacos(baseDirMacos);
		fileHandler.setObfuscationEnable(obfuscationEnable);
		fileHandler.setObfuscationHideExtEnable(obfuscationHideExtEnable);
		
		String osname = System.getProperty("os.name");
		fileHandler.setOsname(osname.toLowerCase());
		
		return fileHandler;
	}
	
	@Bean
	 Sha sha() {
		return new Sha();
	}
	
}













