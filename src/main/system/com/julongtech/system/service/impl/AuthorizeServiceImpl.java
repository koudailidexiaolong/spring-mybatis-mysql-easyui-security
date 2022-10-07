package com.julongtech.system.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemUserVO;
import com.julongtech.system.dao.SystemButtonDao;
import com.julongtech.system.dao.SystemMenuDao;
import com.julongtech.system.dao.SystemRoleButtonMappedDao;
import com.julongtech.system.dao.SystemRoleMenuMappedDao;
import com.julongtech.system.dao.SystemUserRoleMappedDao;
import com.julongtech.system.manager.RedisCacheManager;
import com.julongtech.system.service.AuthorizeService;
import com.julongtech.system.service.dto.SystemButtonDTO;
import com.julongtech.system.service.dto.SystemMenuDTO;
import com.julongtech.system.service.dto.SystemRoleButtonMappedDTO;
import com.julongtech.system.service.dto.SystemRoleMenuMappedDTO;
import com.julongtech.system.service.dto.SystemUserRoleMappedDTO;
import com.julongtech.system.util.DefaultUtil;

/**
 * 权限模块
 * @author julong
 * @date 2017-11-14 下午2:50:08
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizeServiceImpl.class);
	//用户角色关系模块
	@Autowired
	private SystemUserRoleMappedDao systemUserRoleMappedDaoImpl;
	
	@Autowired
	private SystemRoleMenuMappedDao systemRoleMenuMappedDaoImpl;
	
	@Autowired
	private SystemRoleButtonMappedDao systemRoleButtonMappedDaoImpl;
	
	@Autowired
	private SystemMenuDao systemMenuDaoImpl;
	
	@Autowired
	private SystemButtonDao systemButtonDaoImpl;
	
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	
	
	/**
	 * 查询用户权限的方法
	 * @param systemUserVO
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2017-11-14 下午2:58:53
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SystemMenuDTO> getUserMenu(SystemUserVO systemUserVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【查询权限模块】-查询系统菜单输入参数systemUserVO：{}",systemUserVO);
		//查询用户角色关系对象
		SystemUserRoleMappedDTO systemUserRoleMappedDTO = new SystemUserRoleMappedDTO();
		systemUserRoleMappedDTO.setUserId(systemUserVO.getUserId());
		systemUserRoleMappedDTO.setMappedStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
		logger.debug("【查询权限模块】-查询系统菜单输入参数systemUserRoleMappedDTO：{}",systemUserRoleMappedDTO);
		//查询用户对应的角色列表
		List<SystemUserRoleMappedDTO> userRoleList = (List<SystemUserRoleMappedDTO>) this.systemUserRoleMappedDaoImpl.selectBySelective(systemUserRoleMappedDTO);
		//定义一个map对象保存已存在的数据队列
		Set<String> userMenuSet = new HashSet<String>();
		for (SystemUserRoleMappedDTO systemUserRoleMapped : userRoleList) {
			//查询角色菜单关系对象
			SystemRoleMenuMappedDTO systemRoleMenuMappedDTO = new SystemRoleMenuMappedDTO();
			systemRoleMenuMappedDTO.setRoleId(systemUserRoleMapped.getRoleId());
			logger.debug("【查询权限模块】-查询系统菜单输入参数systemRoleMenuMappedDTO：{}",systemRoleMenuMappedDTO);
			List<SystemRoleMenuMappedDTO> roleMenuList = this.systemRoleMenuMappedDaoImpl.selectBySelective(systemRoleMenuMappedDTO);
			for (SystemRoleMenuMappedDTO roleMenuMapped : roleMenuList) {
				if(userMenuSet.contains(roleMenuMapped.getMenuId())){
					continue;
				}
				userMenuSet.add(roleMenuMapped.getMenuId()); //添加到唯一队列中
			}
		}
		//查询总菜单 菜单加入缓存
		List<SystemMenuDTO>  menuList =  new ArrayList<SystemMenuDTO>();
		if(redisCacheManager.existList("menu_list")){
			logger.debug("【查询权限模块】-查询系统菜单通过缓存实现");
			menuList = (List<SystemMenuDTO>) redisCacheManager.getCacheList("menu_list");
		}else{
			logger.debug("【查询权限模块】-查询系统菜单通过查询数据库");
			//查询总菜单
			SystemMenuDTO systemMenuDTO = new SystemMenuDTO();
			systemMenuDTO.setMenuStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
			menuList = this.systemMenuDaoImpl.selectBySelective(systemMenuDTO);
			redisCacheManager.setCacheList("menu_list", menuList);
		}
		//遍历菜单 并且将用户拥有的菜单设置为显示
		for (SystemMenuDTO systemMenu : menuList) {
			if(userMenuSet.contains(systemMenu.getMenuId())){
				systemMenu.setMenuChecked("true");
			}
		}
		return menuList;
	}

	/**
	 * 查询按钮权限信息
	 * @param systemUserVO
	 * @return  List<SystemButtonDTO> 
	 * @throws Exception
	 * @author julong
	 * @date 2018-6-13 下午4:59:03
	 */
	@Override
	public List<SystemButtonDTO> getUserMenuButton(SystemUserVO systemUserVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【查询权限模块】-查询用户菜单按钮输入参数systemUserVO：{}",systemUserVO);
		//查询用户角色关系对象
		SystemUserRoleMappedDTO systemUserRoleMappedDTO = new SystemUserRoleMappedDTO();
		systemUserRoleMappedDTO.setUserId(systemUserVO.getUserId());
		systemUserRoleMappedDTO.setMappedStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
		logger.debug("【查询权限模块】-查询系统菜单输入参数systemUserRoleMappedDTO：{}",systemUserRoleMappedDTO);
		//查询用户对应的角色列表
		List<SystemUserRoleMappedDTO> userRoleList = (List<SystemUserRoleMappedDTO>) this.systemUserRoleMappedDaoImpl.selectBySelective(systemUserRoleMappedDTO);
		//角色按钮
		Set<String> menuButtonSet = new HashSet<String>();
		for (SystemUserRoleMappedDTO systemUserRoleMapped : userRoleList) {
			//查询角按钮关系对象
			SystemRoleButtonMappedDTO systemRoleButtonMappedDTO = new SystemRoleButtonMappedDTO();
			systemRoleButtonMappedDTO.setRoleId(systemUserRoleMapped.getRoleId());
			logger.debug("【查询权限模块】-查询用户角色按钮输入参数systemRoleButtonMappedDTO：{}",systemRoleButtonMappedDTO);
			List<SystemRoleButtonMappedDTO> roleButtonList = this.systemRoleButtonMappedDaoImpl.selectBySelective(systemRoleButtonMappedDTO);
			for (SystemRoleButtonMappedDTO roleButtonMapped : roleButtonList) {
				if(menuButtonSet.contains(roleButtonMapped.getButtonId())){
					continue;
				}
				menuButtonSet.add(roleButtonMapped.getButtonId()); //添加到唯一队列中
			}
		}
		
		//查询所有按钮
		SystemButtonDTO systemButtonDTO = new SystemButtonDTO();
		systemButtonDTO.setButtonStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
		List<SystemButtonDTO> buttonList = this.systemButtonDaoImpl.selectBySelective(systemButtonDTO);
		Iterator<SystemButtonDTO> iterator = buttonList.iterator();
		//判断是否存在此按钮 不存在则删除
		while (iterator.hasNext()) {
			SystemButtonDTO dto = iterator.next();
			if(!menuButtonSet.contains(dto.getButtonId())){
				iterator.remove();
			}
		}
		return buttonList;
	}

}
