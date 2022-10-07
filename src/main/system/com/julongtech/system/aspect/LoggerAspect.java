package com.julongtech.system.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.julongtech.system.action.vo.SystemLoggerExceptionVO;
import com.julongtech.system.action.vo.SystemLoggerVO;
import com.julongtech.system.manager.SystemLoggerManager;
import com.julongtech.system.session.UserSession;
import com.julongtech.system.util.DefaultUtil;

import eu.bitwalker.useragentutils.UserAgent;
/**
 * 日志拦截处理类
 * @author julong
 * @date 2018-5-15 上午10:49:23
 */
public class LoggerAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

	@Autowired
	private SystemLoggerManager systemLoggerManagerImpl;

	private HttpServletRequest request =null;

	private UserSession userSession = null;
	
	/**
	 * 开始时间
	 * @author julong
	 * @date 2022年8月24日 上午11:24:15
	 */
	private long beginTimeMillis = 0; 
	
	/**
	 * 结束时间
	 * @author julong
	 * @date 2022年8月24日 上午11:24:21
	 */
	 private long endTimeMillis = 0; 

	/**
	 * 类名
	 * @author julong
	 * @date 2018-5-15 下午4:28:28
	 */
	private String targetName = "";
	
	/**
	 * 方法名
	 * @author julong
	 * @date 2018-5-15 下午4:28:40
	 */
	private String methodName = "";

	/**
	 * 方法执行前
	 * @param joinPoint
	 * @author julong
	 * @date 2018-5-15 下午2:48:20
	 */
	public void before(JoinPoint joinPoint){
		logger.debug(":before");
		this.request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
		this.targetName = joinPoint.getTarget().getClass().getName();  
		this.methodName = joinPoint.getSignature().getName(); 
		// 拦截的方法参数
		Object[] args = joinPoint.getArgs();
		logger.debug("当前浏览器版本：{}",this.request.getHeader("User-Agent"));
//		UserAgent userAgent = UserAgent.parseUserAgentString(this.request.getHeader("User-Agent"));
		logger.debug("类名targetName:{},methodName:{}",this.targetName,this.methodName);
		logger.debug("参数args:{}",args);
		this.userSession = UserSession.getUserSession();
		this.beginTimeMillis = System.currentTimeMillis(); //记录方法开始执行的时间  
		logger.debug("参数getLocalAddr:{}",this.request.getLocalAddr());
	}

	/**
	 * 方法执行后
	 * @param joinPoint
	 * @author julong
	 * @date 2018-5-15 下午2:48:43
	 */
	public void after(JoinPoint joinPoint){
		logger.debug(":after-targetName{},-targetName:{}",this.targetName,this.methodName);
		try {
			// 拦截的方法参数
			Object[] args = joinPoint.getArgs();
			//获取类
			Class<?> targetClass = Class.forName(this.targetName);
			//获取类中的方法
			Method[] methods = targetClass.getMethods();
			LoggerProxy loggerProxy = null;
			for (Method method : methods) {  
	            if (method.getName().equals(this.methodName)) {  
	                Class<?>[] clazzs = method.getParameterTypes();  
	                if (null != clazzs && clazzs.length == args.length) {  
	                   loggerProxy = method.getAnnotation(LoggerProxy.class);
	                   break;  
	                }  
	            }  
	        }
			//如果方法没有加LoggerProxy注解则不需要做日志记录
			if(loggerProxy == null ){
				return ;
			}
			this.endTimeMillis = System.currentTimeMillis();
			SystemLoggerVO systemLoggerVO = new SystemLoggerVO();
			systemLoggerVO.setLoggerIp(this.request.getLocalAddr());
			systemLoggerVO.setLoggerId(DefaultUtil.DEFAULT_SEQUENCE+"");
			if(null != loggerProxy){
				String loggerType = loggerProxy.method()[0].toString();
				String loggerModule = loggerProxy.module()[0].toString();
				String loggerDescription = loggerProxy.description();
				systemLoggerVO.setLoggerDescription(loggerDescription);
				systemLoggerVO.setLoggerType(loggerType);
				systemLoggerVO.setLoggerModule(loggerModule);
				systemLoggerVO.setLoggerModuleMethod(this.methodName);
			}
			logger.debug("当前浏览器版本：{}",this.request.getHeader("User-Agent"));
			UserAgent userAgent = UserAgent.parseUserAgentString(this.request.getHeader("User-Agent"));
			if(null != userAgent){
				systemLoggerVO.setLoggerOperatingSystem(userAgent.getOperatingSystem().getName());
				systemLoggerVO.setLoggerBrowserType(userAgent.getBrowser().getName());
				systemLoggerVO.setLoggerBrowserVersion(userAgent.getBrowserVersion().getVersion());
			}
			
			systemLoggerVO.setLoggerResponseTime((this.endTimeMillis-this.beginTimeMillis)+"");
			int result = this.systemLoggerManagerImpl.saveSystemLogger(systemLoggerVO, this.userSession);
			logger.debug("【切面日志模块】-插入日志信息返回结果：{}",result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【切面日志】写日志发生异常:{}",e);
		}                           
	}

	/**
	 * 发生错误后执行
	 * @param joinPoint
	 * @param exception
	 * @author julong
	 * @date 2022年8月24日 上午11:25:04
	 * @desc
	 */
	public void afterThrowing(JoinPoint joinPoint,Exception exception){
		logger.debug("错误日志:after-targetName{},-targetName:{}",this.targetName,this.methodName);
		try {
			// 拦截的方法参数
			Object[] args = joinPoint.getArgs();
			//获取类
			Class<?> targetClass = Class.forName(this.targetName);
			//获取类中的方法
			Method[] methods = targetClass.getMethods();
			LoggerProxy loggerProxy = null;
			for (Method method : methods) {  
	            if (method.getName().equals(this.methodName)) {  
					Class<?>[] clazzs = method.getParameterTypes();  
	                if (null != clazzs && clazzs.length == args.length) {  
	                   loggerProxy = method.getAnnotation(LoggerProxy.class);
	                   break;  
	                }  
	            }  
	        }
			
			this.endTimeMillis = System.currentTimeMillis();
			SystemLoggerExceptionVO systemLoggerExceptionVO = new SystemLoggerExceptionVO();
			//如果方法没有加LoggerProxy注解则不需要做日志记录
			if(loggerProxy == null ){
				systemLoggerExceptionVO.setLoggerExceptionIp("127.0.0.1");
			}else{
				systemLoggerExceptionVO.setLoggerExceptionIp(this.request.getLocalAddr());
			}
			systemLoggerExceptionVO.setLoggerExceptionId(DefaultUtil.DEFAULT_SEQUENCE+"");
			//错误信息
			systemLoggerExceptionVO.setLoggerExceptionContext(exception.getLocalizedMessage());
			String loggerType = loggerProxy.method()[0].toString();
			String loggerModule = loggerProxy.module()[0].toString();
			String loggerDescription = loggerProxy.description();
			systemLoggerExceptionVO.setLoggerExceptionDescription(loggerDescription);
			systemLoggerExceptionVO.setLoggerExceptionType(loggerType);
			systemLoggerExceptionVO.setLoggerExceptionModule(loggerModule);
			systemLoggerExceptionVO.setLoggerExceptionMethod(this.methodName);
			logger.debug("当前浏览器版本：{}",this.request.getHeader("User-Agent"));
			UserAgent userAgent = UserAgent.parseUserAgentString(this.request.getHeader("User-Agent"));
			if(null != userAgent){
				systemLoggerExceptionVO.setLoggerOperatingSystem(userAgent.getOperatingSystem().getName());
				systemLoggerExceptionVO.setLoggerBrowserType(userAgent.getBrowser().getName());
				systemLoggerExceptionVO.setLoggerBrowserVersion(userAgent.getBrowserVersion().getVersion());
			}
			
			systemLoggerExceptionVO.setLoggerResponseTime((this.endTimeMillis-this.beginTimeMillis)+"");
			int result = this.systemLoggerManagerImpl.saveSystemLoggerException(systemLoggerExceptionVO, this.userSession);
			logger.debug("【切面日志模块】-插入日志信息返回结果：{}",result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【切面日志】写日志发生异常:{}",e);
		}        
	}

}
