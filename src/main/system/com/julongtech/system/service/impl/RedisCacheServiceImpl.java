package com.julongtech.system.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemButtonVO;
import com.julongtech.system.action.vo.SystemDictionaryVO;
import com.julongtech.system.action.vo.SystemMenuVO;
import com.julongtech.system.action.vo.SystemUserVO;
import com.julongtech.system.manager.RedisCacheManager;
import com.julongtech.system.service.RedisCacheService;
import com.julongtech.system.service.SystemButtonService;
import com.julongtech.system.service.SystemDictionaryService;
import com.julongtech.system.service.SystemMenuService;
import com.julongtech.system.service.SystemUserService;
import com.julongtech.system.service.dto.SystemButtonDTO;
import com.julongtech.system.service.dto.SystemDictionaryDTO;
import com.julongtech.system.service.dto.SystemMenuDTO;
import com.julongtech.system.service.dto.SystemUserDTO;
import com.julongtech.system.util.DefaultUtil;

/**
 * 缓存处理模块
 * @author julong
 * @date 2018-1-29 下午5:13:36
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
	
	@Autowired
	private SystemDictionaryService systemDictionaryServiceImpl;
	
	@Autowired
	private SystemUserService systemUserServiceImpl;
	
	@Autowired
	private SystemMenuService systemMenuServiceImpl;
	
	@Autowired
	private SystemButtonService systemButtonServiceImpl;;
	
	@Autowired
	private RedisCacheManager redisCacheManagerImpl;
	
	/**
	 * 刷新缓存信息
	 * @return boolean
	 * @author julong
	 * @date 2018-1-29 下午5:17:02
	 */
	@Override
	public boolean refushAllCache() throws Exception {
		// TODO Auto-generated method stub
		//缓存菜单
		logger.debug("【开始】-刷新所有的缓存信息");
		//查询总菜单
		this.refreshMenuCache();
		//查询菜单按钮
		this.refreshButtonCache();
		//缓存数据字典
		this.refreshDictionaryCache();
		//缓存组织机构
		this.refreshUserCache();
		logger.debug("【结束】-刷新所有的缓存信息");
		//缓存客服信息
		return true;
	}

	/**
	 * 根据类型获取
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2018-1-30 下午5:35:06
	 */
	@Override
	public List<Object> getCache() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 缓存系统菜单
	 * @throws Exception
	 * @author julong
	 * @date 2018-4-8 下午3:08:41
	 */
	@Override
	public boolean refreshMenuCache() throws Exception{
		logger.debug("【开始】-缓存菜单信息");
		boolean connection = this.redisCacheManagerImpl.connection();
		if(!connection){
			return false;
		}
		SystemMenuVO systemMenuVO = new SystemMenuVO();
		systemMenuVO.setMenuStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
		List<SystemMenuDTO> menuList = this.systemMenuServiceImpl.getMenuList(systemMenuVO);
		boolean result = this.redisCacheManagerImpl.deleteCache("menu_list");
		if(result){
			this.redisCacheManagerImpl.setCacheList("menu_list", menuList);
		}
		logger.debug("【结束】-缓存菜单信息");
		return true;
	}

	/**
	 * 缓存系统菜单的按钮
	 * @throws Exception
	 * @author julong
	 * @date 2018-4-8 下午3:08:41
	 */
	@Override
	public boolean refreshButtonCache() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【开始】-缓存菜单按钮信息");
		boolean connection = this.redisCacheManagerImpl.connection();
		if(!connection){
			return false;
		}
		SystemButtonVO systemButtonVO = new SystemButtonVO();
		systemButtonVO.setButtonStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
		List<SystemButtonDTO> menuList = this.systemButtonServiceImpl.getSystemButtonList(systemButtonVO);
		boolean result = this.redisCacheManagerImpl.deleteCache("button_list");
		if(result){
			this.redisCacheManagerImpl.setCacheList("button_list", menuList);
		}
		logger.debug("【结束】-缓存菜单按钮信息");
		return true;
	}

	/**
	 * 缓存用户
	 * @throws Exception
	 * @author julong
	 * @date 2018-4-8 上午10:49:55
	 */
	@Override
	public boolean refreshUserCache() throws Exception{
		//缓存用户信息
		logger.debug("【开始】刷新用户缓存信息");
		boolean connection = this.redisCacheManagerImpl.connection();
		if(!connection){
			return false;
		}
		SystemUserVO systemUserVO = new SystemUserVO();
		systemUserVO.setUserStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
		List<SystemUserDTO>  userList = this.systemUserServiceImpl.getUserList(systemUserVO);
		boolean result = this.redisCacheManagerImpl.deleteCache("user_list");
		if(result){
			this.redisCacheManagerImpl.setCacheList("user_list", userList);
		}
		logger.debug("【结束】刷新用户缓存信息");
		return true;
	}

	/**
	 * 缓存数据字典
	 * @throws Exception
	 * @author julong
	 * @date 2018-4-8 下午2:37:21
	 */
	@Override
	public boolean refreshDictionaryCache()throws Exception{
		//缓存数据字典
		logger.debug("【开始】-缓存数据字典");
		boolean connection = this.redisCacheManagerImpl.connection();
		if(!connection){
			return false;
		}
		SystemDictionaryVO systemDictionaryVO = new SystemDictionaryVO();
		systemDictionaryVO.setDictionaryStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
		List<SystemDictionaryDTO> dictionaryDTOList =  this.systemDictionaryServiceImpl.getSystemDictionaryByType(systemDictionaryVO);
		Iterator<SystemDictionaryDTO> iterator = dictionaryDTOList.iterator();
		//先存key
		Set<String> sets = new HashSet<String>();
		while (iterator.hasNext()) {
			String type = iterator.next().getDictionaryType();
			if(!sets.contains(type)){
				sets.add(type);
			}
		}
		this.redisCacheManagerImpl.deleteCache(sets);
		Iterator<SystemDictionaryDTO> iterator1 = dictionaryDTOList.iterator();
		long result = 0;
		while (iterator1.hasNext()) {
			SystemDictionaryDTO dto = iterator1.next();
			result = this.redisCacheManagerImpl.setCacheObjectToList(dto.getDictionaryType(),dto);
		}
		logger.debug("[缓存的数据量为]{}",result);
		logger.debug("【结束】-缓存数据字典");
		return true;
	}
}
