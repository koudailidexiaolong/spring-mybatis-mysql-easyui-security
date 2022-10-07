package com.julongtech.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * xssfilter过滤
 * @author julong
 * @date 2018-6-7 下午4:45:39
 */
public class XSSFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		XSSHttpServletRequestWrapper xSSHttpServletRequestWrapper = new XSSHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xSSHttpServletRequestWrapper, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
