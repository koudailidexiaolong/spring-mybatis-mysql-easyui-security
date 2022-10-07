package com.julongtech.security.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julongtech.system.util.DefaultUtil;

/**
 * 用户登录成功处理类
 * @author julong
 * @date 2022年9月11日 下午6:27:17
 * @desc 
 */
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("【登录成功模块】-进入onAuthenticationSuccess处理方法");
		if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase(DefaultUtil.X_REQUESTED_WITH)){
			logger.debug("【请求处理模块】-请求为ajax 请求");
			response.setStatus(HttpStatus.OK.value());
			response.setContentType("application/json;charset=utf-8");
			Map<String,Object> maps = new HashMap<String,Object>();
			maps.put("result", true);
			response.getWriter().write(mapper.writeValueAsString(maps));
		}else{
			response.sendRedirect("login/index.action");
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

	
}
