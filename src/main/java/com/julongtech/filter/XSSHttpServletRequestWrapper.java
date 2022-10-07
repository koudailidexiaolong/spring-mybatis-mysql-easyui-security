package com.julongtech.filter;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 过滤xss攻击 跨站脚本攻击
 * @author julong
 * @date 2018-6-7 下午12:59:58
 */
public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private static final Logger logger = LoggerFactory.getLogger(XSSHttpServletRequestWrapper.class);
	
	public XSSHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	/*
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖 
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		logger.debug("【过滤xss攻击】-过滤参数{}",name);
		if (StringUtils.isNotEmpty(value)) {  
			value = xssEncode(value);  
		}  
		return value;  
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		return super.getParameterMap();
	}

	
	
	
	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		logger.debug("【过滤xss攻击】-过滤参数{}",name);
		String[] value = super.getParameterValues(name);
		if(null != value){
			for (int i = 0; i < value.length; i++) {
				logger.debug("【过滤xss攻击】-过滤参数前{}",value[i]);
				value[i] = xssEncode(value[i]);
				logger.debug("【过滤xss攻击】-过滤参数后{}",value[i]);
			}
		}
		return value;
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
