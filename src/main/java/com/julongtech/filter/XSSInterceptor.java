package com.julongtech.filter;

import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * xss安全拦截
 * @author julong
 * @date 2022年9月9日 下午9:00:39
 * @desc 
 */
public class XSSInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(XSSInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("进入spring XSSInterceptor 类中的 preHandle方法");
		
		//无需登录，允许访问的地址  
		String[] allowUrls =new String[]{"/login","/loginOut","/captcha","/getKey"};  
		//获取请求地址  
		String url =request.getRequestURL().toString();  
		for (String strUrl : allowUrls) {  
			if(url.contains(strUrl)){  
				return true;  
			}  
		}  
		
		//获取参数名称
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			logger.debug("【过滤xss攻击】-过滤参数{}",name);
			String[] value = request.getParameterValues(name);
			if(null != value){
				for (int i = 0; i < value.length; i++) {
					logger.debug("【过滤xss攻击】-过滤参数前{}",value[i]);
					value[i] = xssEncode(value[i]);
					logger.debug("【过滤xss攻击】-过滤参数后{}",value[i]);
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("进入spring XSSInterceptor 类中的 postHandle方法");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("进入spring XSSInterceptor 类中的 afterCompletion方法");
	}
	
	/**
	 * 将容易引起xss漏洞的半角字符直接替换成全角字符 在保证不删除数据的情况下保存
	 * @param value
	 * @return
	 * @author julong
	 * @date 2018-6-7 下午12:59:47
	 */
	private static String xssEncode(String value) {  
		if (value == null || value.isEmpty()) {  
			return value;  
		}  
		Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");
        // 删除单个的 </script> 标签
        scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");
        // 删除单个的<script ...> 标签
        scriptPattern = Pattern.compile("<script(.*?)>",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");
        // 避免 eval(...) 形式表达式
        scriptPattern = Pattern.compile("eval\\((.*?)\\)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");
        // 避免 onload= 表达式
        scriptPattern = Pattern.compile("onload(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");
        // 避免 javascript: 表达式
        scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");
        // 避免 alert: 表达式
        scriptPattern = Pattern.compile("alert(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");
        //过滤><
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		return value;
	}
	
}
