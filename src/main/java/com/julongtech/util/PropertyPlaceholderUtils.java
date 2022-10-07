package com.julongtech.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 公共配置文件读取操作类
 * @author julong
 * @date 2016年5月10日 上午9:35:40
 */
public class PropertyPlaceholderUtils {
	//日志
	private static final Logger logger = LoggerFactory.getLogger(PropertyPlaceholderUtils.class);
	//当前对象
	private static PropertyPlaceholderUtils instance = null;
	private Properties properties = null;
	/**
	 * 配置文件
	 * @author julong
	 * @date 2016-7-25 上午11:46:27
	 */
	private static final String configFile = "application.properties";

	/**
	 * 单例化对象
	 * @return
	 * @author julong
	 * @date 2016年5月11日 上午11:50:48
	 */
	public static synchronized PropertyPlaceholderUtils getInstance() {
		if (null == instance) {
			instance = new PropertyPlaceholderUtils();
		}
		return instance;
	}
	
	/**
	 * 私有化构造函数
	 * @author julong
	 * @date 2016年5月11日 上午11:50:26
	 */
	private PropertyPlaceholderUtils() {
		logger.debug("Init the config properties...");
		this.properties = new Properties();
		InputStream is = null;
		try {
			is = this.getClass().getClassLoader().getResourceAsStream(PropertyPlaceholderUtils.configFile);
			properties.load(is);
		} catch (IOException e) {
			throw new RuntimeException("返回码配置文件读取失败");
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException("关闭返回码配置文件读取流失败");
				}
			}
		}
		logger.debug("Finish read config properties.");
	}
	
	/**
	 * 获取当前是否存在键值对
	 * @param key
	 * @return
	 * @author julong
	 * @date 2016年5月10日 上午9:39:37
	 */
	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}

	/**
	 * 获取key对应的value
	 * @param retCode
	 * @return
	 * @author julong
	 * @date 2016年5月10日 上午9:40:11
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyPlaceholderUtils configProperties = new PropertyPlaceholderUtils();
		boolean result = configProperties.containsKey("000000");
		System.out.println(result);
		String value = configProperties.getProperty("000000");
		System.out.println(value);
		
		
	}

}
