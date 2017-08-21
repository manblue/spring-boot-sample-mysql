package com.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName="TestFilter",urlPatterns="/*")
public class TestFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(TestFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("init {}", "TestFilter.init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("doFilter {}", "TestFilter.doFilter");
		
		HttpServletResponse res = (HttpServletResponse)response;
		res.setHeader("Access-Control-Allow-Headers", "Origin,LYC_SESSION_ID, sysId, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,token");
		res.setHeader("Access-Control-Expose-Headers", "Origin,LYC_SESSION_ID, sysId, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,token");
		
		res.setContentType("application/json; application/x-www-form-urlencoded; charset=UTF-8");
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET,POST,HEAD,PUT,DELETE");
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("destroy {}", "TestFilter.destroy");
	}

}
