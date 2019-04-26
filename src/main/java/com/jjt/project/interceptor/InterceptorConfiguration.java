package com.jjt.project.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfiguration implements WebMvcConfigurer {
	@Autowired
	LoginInterceptor loginInterceptor;
	
	@Autowired
	HostInterceptor hostInterceptor;
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(hostInterceptor).addPathPatterns("/**");
				registry.addInterceptor(loginInterceptor).addPathPatterns("/bookAPI/books/**", "/bookAPI/addBook**");
			}
		};
		
	}
}
