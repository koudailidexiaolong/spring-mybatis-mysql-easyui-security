package com.julongtech.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.julongtech.system.aspect.LoggerMethod;
import com.julongtech.system.aspect.LoggerModule;
import com.julongtech.system.aspect.LoggerProxy;
import com.julongtech.system.manager.RedisCacheManager;
import com.julongtech.system.service.RedisCacheService;
import com.julongtech.system.service.dto.RedisCacheDTO;

/**
 * 缓存信息管理
 * @author julong
 * @date 2017-12-13 下午3:10:16
 */
@Controller
@RequestMapping("cache")
public class RedisCacheAction {

	private static final Logger logger = LoggerFactory.getLogger(RedisCacheAction.class);

	@Autowired
	private RedisCacheManager redisCacheManager;
	
	@Autowired
	private RedisCacheService redisCacheServiceImpl;
	
	
	/**
	 * 首页
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2022年8月24日 下午2:17:05
	 * @desc
	 */
	@RequestMapping("/index")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_CACHE,description="缓存首页")
	public String index()  throws Exception{
		logger.info("【缓存信息管理】");
		return "system/cache/cache_index";
	}
	
	/**
	 * 根据key查询缓存集合信息的方法
	 * @param key
	 * @return
	 * @author julong
	 * @date 2018-4-18 下午3:21:01
	 */
	@RequestMapping(value="/getCacheList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_CACHE,description="根据key查询缓存集合信息的方法")
	public Map<String,Object> getCacheList(@RequestParam("key") String key) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		logger.info("【缓存信息管理】查询缓存信息的方法{}",key);
		try {
			List<RedisCacheDTO> list = new ArrayList<RedisCacheDTO>();
			boolean connection  = this.redisCacheManager.connection();
			if(connection){
				Map<String, String> keys = this.redisCacheManager.getKeysLen(key);
				list = RedisCacheDTO.initCache(keys);
			}
			maps.put("rows", list);
			maps.put("total", 100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【缓存信息管理】-根据key查询缓存集合信息发生异常",e);
			throw new Exception(e);
		}
		return maps;
	}
	
	/**
	 * 删除缓存信息
	 * @param key
	 * @return
	 * @author julong
	 * @date 2017-12-14 上午8:49:33
	 */
	@RequestMapping(value="/deleteCache",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.DELETE,module = LoggerModule.SYSTEM_CACHE,description="删除缓存信息")
	public Map<String,Object> deleteCache(@RequestParam("key") String key) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		boolean result = false;
		logger.info("【缓存信息管理】-删除缓存信息{}",key);
		try {
			boolean connection  = this.redisCacheManager.connection();
			if(connection){
				result = this.redisCacheManager.deleteCache(key);
			}
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【缓存信息管理】-删除缓存信息",e);
			throw new Exception(e);
		}
		logger.info("【缓存信息管理】-删除缓存信息{}",key);
		return maps;
	}
	
	/**
	 * 刷新缓存信息
	 * @return
	 * @author julong
	 * @date 2017-12-14 上午8:49:33
	 */
	@RequestMapping(value="/reloadCache",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_CACHE,description="刷新缓存信息")
	public Map<String,Object> reloadCache() throws Exception{
		logger.info("【缓存信息管理】-刷新缓存信息");
		Map<String,Object> maps = new HashMap<String,Object>();
		boolean result = false;
		try {
			boolean connection  = this.redisCacheManager.connection();
			if(connection){
				result = this.redisCacheServiceImpl.refushAllCache();
			}
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【缓存信息管理】-刷新缓存信息",e);
			throw new Exception(e);
		}
		logger.info("【缓存信息管理】-刷新缓存信息{}",result);
		return maps;
	}
	
	/**
	 * 根据编号获取缓存信息
	 * @param key
	 * @return
	 * @author julong
	 * @date 2018-1-31 下午3:27:24
	 */
	@RequestMapping(value="/getCache",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_CACHE,description="根据编号获取缓存信息")
	public Map<String,Object> getCache(@RequestParam String key) throws Exception{
		logger.info("【缓存信息管理】-获取缓存信息输入参数key:{}",key);
		Map<String,Object> maps = new HashMap<String,Object>();
		try {
			boolean connection  = this.redisCacheManager.connection();
			if(connection){
				@SuppressWarnings("unchecked")
				List<Object> list =  (List<Object>) this.redisCacheManager.getCacheList(key);
				maps.put("result", list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【缓存信息管理】-查询缓存信息发生异常",e);
			throw new Exception(e);
		}
		return maps;
	}
}
