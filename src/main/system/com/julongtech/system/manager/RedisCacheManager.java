package com.julongtech.system.manager;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 缓存模块
 * @author julong
 * @date 2017-12-12 上午8:47:56
 */
public interface RedisCacheManager {


	/**
	 * 缓存一个对象 
	 * @param key 键值对关系
	 * @param obj
	 * @return boolean
	 * @author julong
	 * @date 2017-12-8 上午9:33:21
	 */
	public abstract boolean setCacheObject(String key,Object obj);

	/**
	 * 从缓存中取值
	 * @param key 缓存的编号
	 * @return 
	 * @author julong
	 * @date 2017-12-8 上午9:35:58
	 */
	public abstract Object getCacheObject(String key);

	/**
	 * 缓存集合list 存储 实现右插
	 * @param key
	 * @param dataList
	 * @return
	 * @author julong
	 * @throws IOException 
	 * @date 2017-12-12 下午2:50:05
	 */
	public abstract long setCacheList(String key,List<? extends Object> dataList);
	
	/**
	 * 缓存对象到list 存储 实现右插
	 * @param key
	 * @param dataList
	 * @return
	 * @author julong
	 * @throws IOException 
	 * @date 2017-12-12 下午2:50:05
	 */
	public abstract long setCacheObjectToList(String key,Object object);

	/**
	 * 缓存集合 list 取值 实现左取
	 * @param key
	 * @return List<? extends Object>
	 * @author julong
	 * @date 2017-12-8 上午9:54:57
	 */
	public abstract List<? extends Object> getCacheList(String key);

	/**
	 * 判断是否存在此list缓存
	 * @param key
	 * @return
	 * @author julong
	 * @date 2017-12-12 上午10:13:17
	 */
	public abstract boolean existList(String key);

	/**
	 * 获取所有的keys
	 * @return
	 * @author julong
	 * @date 2017-12-12 上午11:46:50
	 */
	public abstract Set<String> getKeys(String key);
	
	/**
	 * 获取key保存的值的长度
	 * @return
	 * @author julong
	 * @date 2017-12-12 上午11:46:50
	 */
	public abstract long getListSize(String key);
	
	
	/**
	 * 获取所有逇key 和 值长度
	 * @param key
	 * @return
	 * @author julong
	 * @date 2018-6-10 下午2:51:27
	 */
	public abstract Map<String,String> getKeysLen(String key);
	/**
	 * 缓存map
	 * @param key
	 * @param dataMap
	 * @return
	 * @author julong
	 * @date 2017-12-8 上午10:23:53
	 */
	public abstract boolean setCacheMap(String key,Map<String,Object> dataMap);

	/**
	 * map中取值
	 * @param key
	 * @return
	 * @author julong
	 * @date 2017-12-8 上午10:24:03
	 */
	public abstract Map<Object,Object> getCacheMap(String key);

	/**
	 * 删除单个缓存
	 * @param key
	 * @author julong
	 * @date 2017-12-11 下午8:55:53
	 */
	public abstract boolean deleteCache(String key);

	/**
	 * 根据集合删除缓存
	 * @param keys
	 * @author julong
	 * @date 2017-12-11 下午8:56:13
	 */
	public abstract boolean deleteCache(Set<String> keys);

	/**
	 * 判断是否存在此key
	 * @param key
	 * @return boolean
	 */
	public abstract boolean hasKey(String key);


	/**
	 * 判断是否连接redis成功
	 * @return
	 * @author julong
	 * @date 2018-5-28 下午5:07:23
	 */
	public abstract boolean connection();
}
