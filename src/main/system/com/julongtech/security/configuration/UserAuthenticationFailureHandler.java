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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julongtech.system.util.DefaultUtil;

/**
 * 用户登录失败处理类
 * @author julong
 * @date 2022年9月11日 下午7:50:51
 * @desc 
 */
public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);
	
    private ObjectMapper mapper = new ObjectMapper();
    
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("【登录失败模块】-进入onAuthenticationFailure处理方法{}",exception.toString());
		
		response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
		//判断是否为ajax校验
		if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase(DefaultUtil.X_REQUESTED_WITH)){
			logger.debug("【请求处理模块】-请求为ajax 请求");
			response.setContentType("application/json;charset=utf-8");
			Map<String,Object> maps = new HashMap<String,Object>();
			maps.put("result", false);
			//第二种配置
			maps.put("message", exception.getMessage());
			//第一种配置 需要判断异常类型来进行判断
//			if(exception instanceof BadCredentialsException){
//				maps.put("message", "账户或密码错误");
//			}else{
//				maps.put("message", "登录失败");
//			}
			response.getWriter().write(mapper.writeValueAsString(maps));
		}else{
			super.onAuthenticationFailure(request, response, exception);
		}
		
	}

}
