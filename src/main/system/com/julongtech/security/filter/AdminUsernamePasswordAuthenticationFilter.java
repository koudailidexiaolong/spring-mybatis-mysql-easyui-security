package com.julongtech.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 自定义密码验证
 * @author julong
 * @date 2022年9月15日 下午7:39:50
 * @desc 
 */
public class AdminUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(AdminUsernamePasswordAuthenticationFilter.class);
	/**
	 * 验证码
	 * @author julong
	 * @date 2022年9月14日 下午8:57:55
	 */
	public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "captcha";
	/**
	 * 验证码
	 * @author julong
	 * @date 2022年9月15日 下午7:40:06
	 */
	public static final String SPRING_CAPTCHA_CODE = "SPRING_CAPTCHA_CODE";
	/**
	 * 验证码过期时间
	 * @author julong
	 * @date 2022年9月15日 下午7:40:14
	 */
	public static final String SPRING_CAPTCHA_EXPIRE_TIME = "SPRING_CAPTCHA_EXPIRE_TIME";

	private String captchaParameter = SPRING_SECURITY_FORM_CAPTCHA_KEY;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		logger.debug("【自定义登录账户密码拦截】-执行拦截方法");
		// TODO Auto-generated method stub
		String captchaRequest = obtainCaptcha(request);
		if (captchaRequest == null) {
			captchaRequest = "";
			throw new BadCredentialsException("验证码不能为空");
		}
		logger.debug("【自定义登录账户密码拦截】-执行拦截方法用户输入验证码captchaRequest：{}",captchaRequest);
		//获取用户输入的
		String captchaSession = (String) request.getSession().getAttribute(SPRING_CAPTCHA_CODE);
		logger.debug("【自定义登录账户密码拦截】-执行拦截方法session保存验证码captchaSession：{}",captchaSession);
		if(!captchaSession.equalsIgnoreCase(captchaRequest)){
			throw new BadCredentialsException("验证码输入错误");
		}
		logger.debug("【自定义登录账户密码拦截】-执行验证码拦截方法验证码匹配成功");
		//销毁session中的验证码
		request.getSession().removeAttribute(SPRING_CAPTCHA_CODE);
		return super.attemptAuthentication(request, response);
	}

	protected String obtainCaptcha(HttpServletRequest request) {
		return request.getParameter(captchaParameter);
	}
}
