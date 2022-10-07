package com.julongtech.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.julongtech.system.session.UserSession;
import com.julongtech.system.util.DefaultUtil;
import com.julongtech.util.PropertyPlaceholderUtils;


/**
 * 登录拦截器 实现未登录的信息的拦截处理
 * @author julong
 * @date 2017-10-31 下午8:10:38
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	private static final String web_host = PropertyPlaceholderUtils.getInstance().getProperty("web-host");

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
					throws Exception {
		// TODO Auto-generated method stub
		logger.info("登录拦截器LoginInterceptor 类 的 afterCompletion方法");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		logger.info("登录拦截器LoginInterceptor 类 的 postHandle方法");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object object) throws Exception {
		logger.info("登录拦截器LoginInterceptor 类 的 preHandle方法");
		//校验是否为允许的IP访问地址
//		if(!this.validateHost(request, response)){
//			return false;
//		}
		// TODO Auto-generated method stub
		logger.debug("拦截用户是否登录");
		//无需登录，允许访问的地址  
		String[] allowUrls =new String[]{"/login","/loginOut","/captcha","/getKey"};  
		//获取请求地址  
		String url =request.getRequestURL().toString();  
		for (String strUrl : allowUrls) {  
			if(url.contains(strUrl)){  
				return true;  
			}  
		}  
		
		//获得session中的用户  
		UserSession user = UserSession.getUserSession();
		if(user == null){  
			logger.debug("拦截到当前用户没有登录...");
			// 判断是否为ajax请求   x-requested-with:XMLHttpRequest 请求头 就代表 ajax 
			if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase(DefaultUtil.X_REQUESTED_WITH)){
				logger.debug("【请求处理模块】-请求为ajax 请求");
				response.addIntHeader("Response-session", -1);
			}else{
				//不符合条件的，跳转到登录界面  
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				logger.trace("打印需要跳转的界面{}",basePath);
				response.sendRedirect(basePath);
			}
		}  
		return true;
	}
	/**
	 * 校验host
	 * @param request
	 * @param response
	 * @return
	 * @author julong
	 * @date 2018-6-8 下午12:05:37
	 */
	private boolean validateHost(HttpServletRequest request, HttpServletResponse response){
		String host =request.getHeader("host");
		if(null != host && StringUtils.isNotBlank(web_host)){
			String[] hostArray = StringUtils.split(web_host, "@");
			boolean result = false;
			for (int i = 0; i < hostArray.length; i++) {
				if(host.equals(hostArray[i])){
					result = true;
					break;
				}
			}
			if(!result){
				logger.debug("【过滤器XSSFilter】-匹配host信息失败");
				response.setStatus(403);
				return result;
			}
		}
		return true;
	}

}
