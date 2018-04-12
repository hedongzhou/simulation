package com.simulation.ken.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.simulation.ken.interceptor.RequestInterceptor;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 请求拦截器
		HandlerInterceptor requestInterceptor = new RequestInterceptor();
		registry.addInterceptor(requestInterceptor).addPathPatterns("/**");
	}

}
