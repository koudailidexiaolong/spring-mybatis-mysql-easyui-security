package com.julongtech.exception;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * @author julong
 * @date 2022年8月25日 上午10:02:28
 * @desc 
 */
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);
	
	private static final String X_REQUESTED_WITH = "XMLHttpRequest";
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception exception) {
		// TODO Auto-generated method stub
		ModelAndView modelAndView = new ModelAndView();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			logger.debug("【异常处理模块】-请求头headerName：{}:{}",headerName,request.getHeader(headerName));
		}
		String headerName = request.getHeader("x-requested-with");
		// 判断是否为ajax请求   x-requested-with:XMLHttpRequest 请求头 就代表 ajax 
		if(headerName != null && headerName.equalsIgnoreCase(X_REQUESTED_WITH)){
			logger.debug("【异常处理模块】-请求为ajax 请求");
			response.addIntHeader("Response-error", -1);
			modelAndView.addObject("code","500");
			modelAndView.addObject("message", exception.getMessage());
			return modelAndView;
		}
			
		modelAndView.setViewName("errors/500");
		modelAndView.addObject("code","500");
		modelAndView.addObject("message", exception.getMessage());
		return modelAndView;
	}


}
