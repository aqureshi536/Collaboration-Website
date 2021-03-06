package com.ahmad.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter{

	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filtering is on");
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		//HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletResponse.getHeader("Access-Control-Request-Headers"));
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		//httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
		
		chain.doFilter(request,response);
	}
	
	@Override
	public void init(FilterConfig filterConfig)  {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
