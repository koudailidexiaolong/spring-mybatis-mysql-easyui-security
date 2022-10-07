package com.julongtech.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.julongtech.util.PropertyPlaceholderUtils;


/**
 * 系统初始化
 * @author julong
 * @date 2016-7-21 下午05:27:47
 */
public class SystemInitListener implements ServletContextListener {
	
	
	private static final Logger logger = LoggerFactory.getLogger(SystemInitListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		logger.debug("系统初始化设置.....");
		logger.info("系统初始化设置.....{}",event.getServletContext().getContextPath());
		//application对象 此对象为全局的对象整个Web程序 就一个对象
		ServletContext servletContext = event.getServletContext();
		String WEBNAME = PropertyPlaceholderUtils.getInstance().getProperty("webapp.name");
		String WEBFOOT = PropertyPlaceholderUtils.getInstance().getProperty("webapp.foot");
		servletContext.setAttribute("WEBNAME", WEBNAME);
		servletContext.setAttribute("WEBFOOT", WEBFOOT);
		logger.debug("系统初始化设置.....WEBNAME：{}，WEBFOOT{}",WEBNAME,WEBFOOT);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

}
