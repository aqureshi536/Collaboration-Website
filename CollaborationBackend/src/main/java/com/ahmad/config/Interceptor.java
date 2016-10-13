package com.ahmad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@Configuration
//@EnableWebMvc
@ComponentScan("com.ahmad")
public class Interceptor extends WebMvcConfigurerAdapter{

	@Bean
	public WebContentInterceptor webContentInterceptor(){
		WebContentInterceptor interceptor  = new WebContentInterceptor();
		interceptor.setCacheSeconds(0);
		interceptor.setUseExpiresHeader(true);
		interceptor.setUseCacheControlHeader(true);
		interceptor.setUseCacheControlNoStore(true);
		return interceptor;
	}
	
	
	 @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(webContentInterceptor());
	  }
}
