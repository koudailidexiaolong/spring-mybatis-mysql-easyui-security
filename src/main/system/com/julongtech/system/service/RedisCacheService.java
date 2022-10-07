package com.julongtech.system.service;

import java.util.List;

/**
 * 缓存处理模块
 * @author julong
 * @date 2018-1-29 下午5:13:36
 */
public interface RedisCacheService {
	
	/**
	 * 刷新缓存信息
	 * @return boolean
	 * @author julong
	 * @date 2018-1-29 下午5:17:02
	 */
	public abstract boolean refushAllCache() throws Exception;
	
	/**
	 * 根据类型获取
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2018-1-30 下午5:35:06
	 */
	public abstract List<Object> getCache() throws Exception;
	/**
	 * 缓存用户
	 * @throws Exception
	 * @author julong
	 * @date 2018-4-8 上午10:49:55
	 */
	public abstract boolean refreshUserCache() throws Exception;
	
	/**
	 * 缓存数据字典
	 * @throws Exception
	 * @author julong
	 * @date 2018-4-8 下午2:37:21
	 */
	public abstract boolean refreshDictionaryCache()throws Exception;
	
	/**
	 * 缓存系统菜单
	 * @throws Exception
	 * @author julong
	 * @date 2018-4-8 下午3:08:41
	 */
	public abstract boolean refreshMenuCache() throws Exception;
	
	/**
	 * 缓存系统菜单的按钮
	 * @throws Exception
	 * @author julong
	 * @date 2018-4-8 下午3:08:41
	 */
	public abstract boolean refreshButtonCache() throws Exception;
}
