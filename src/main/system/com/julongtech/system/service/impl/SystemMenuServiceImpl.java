package com.julongtech.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemMenuVO;
import com.julongtech.system.dao.SystemMenuDao;
import com.julongtech.system.dao.entity.SystemMenuInfo;
import com.julongtech.system.service.SystemMenuService;
import com.julongtech.system.service.dto.SystemMenuDTO;
import com.julongtech.system.session.UserSession;
import com.julongtech.util.DateUtils;
/**
 * 菜单模块
 * @author julong
 * @date 2017-10-18 下午4:23:20
 */
@Service
public class SystemMenuServiceImpl implements SystemMenuService {

	private static final Logger logger = LoggerFactory.getLogger(SystemMenuServiceImpl.class);
	
	@Autowired
	private SystemMenuDao systemMenuDaoImpl;
	
	/**
	 * 分页查询所有的菜单信息集合的方法
	 * @param systemMenuVO 菜单对象
	 * @param userInfo 当前登录用户
	 * @return List<SystemMenuDTO> 菜单集合
	 * @author julong
	 * @date 2016-7-6 上午11:50:43
	 */
	@Override
	public List<SystemMenuDTO> getMenuListByPage(SystemMenuVO systemMenuVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【菜单模块】-分页查询菜单信息输入参数systemMenuVO:{}",systemMenuVO);
		SystemMenuDTO systemMenuDTO = new SystemMenuDTO();
		systemMenuDTO.setMenuStatus(systemMenuVO.getMenuStatus());
		if(StringUtils.isNotBlank(systemMenuVO.getMenuName())){
			systemMenuDTO.setMenuName("%"+systemMenuVO.getMenuName()+"%");
		}
		systemMenuDTO.setMenuLevel(systemMenuVO.getMenuLevel());
		List<SystemMenuDTO> list = (List<SystemMenuDTO>) this.systemMenuDaoImpl.selectBySelective(systemMenuDTO);
		return list;
	}
	
	/**
	 * 查询菜单树信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @return SystemMenuDTO 菜单集合
	 * @author julong
	 * @date 2016-7-6 上午11:50:43
	 */
	@Override
	public List<SystemMenuDTO> getMenuListTree(SystemMenuVO systemMenuVO) throws Exception {
		// TODO Auto-generated method stub
		//一级菜单
		SystemMenuDTO systemMenuDTO = new SystemMenuDTO();
		systemMenuDTO.setMenuFatherId("0");
		List<SystemMenuDTO> menuList = (List<SystemMenuDTO>) this.systemMenuDaoImpl.selectBySelective(systemMenuDTO);
		//二级菜单
		for (SystemMenuDTO menu2 : menuList) {
			List<SystemMenuDTO> menuList2 = this.getMenuList(new SystemMenuVO(null, menu2.getMenuId()));
			for (SystemMenuDTO menu3 : menuList2) {
				List<SystemMenuDTO> menuList3 = this.getMenuList(new SystemMenuVO(null, menu3.getMenuId()));
				menu3.setChildren(menuList3);
			}
			menu2.setChildren(menuList2);
		}
		return menuList;
	}


	/**
	 * 查询所有的菜单信息集合的方法
	 * @param SystemMenuVO 菜单对象
	 * @return List<SystemMenuVO> 菜单集合
	 * @author julong
	 * @date 2016-7-6 上午11:50:43
	 */
	@Override
	public List<SystemMenuDTO> getMenuList(SystemMenuVO systemMenuVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【菜单模块】-查询菜单集合信息输入参数systemMenuVO:{}",systemMenuVO);
		SystemMenuDTO systemMenuDTO = new SystemMenuDTO();
		if(StringUtils.isNotEmpty(systemMenuVO.getMenuFatherId())){
			systemMenuDTO.setMenuFatherId(systemMenuVO.getMenuFatherId());
		}
		if(StringUtils.isNotEmpty(systemMenuVO.getMenuStatus())){
			systemMenuDTO.setMenuStatus(systemMenuVO.getMenuStatus());
		}
		if(StringUtils.isNotEmpty(systemMenuVO.getMenuLevel())){
			systemMenuDTO.setMenuLevel(systemMenuVO.getMenuLevel());
		}
		List<SystemMenuDTO> list = this.systemMenuDaoImpl.selectBySelective(systemMenuDTO);
		return list;
	}
	
	/**
	 * 根据编号查询菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @return SystemMenuDTO
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	@Override
	public SystemMenuDTO getSystemMenu(SystemMenuVO systemMenuVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【菜单模块】-查询菜单信息输入参数systemMenuVO：{}",systemMenuVO);
		SystemMenuDTO systemMenuDTO = this.systemMenuDaoImpl.selectByPrimaryKey(systemMenuVO.getMenuId());
		return systemMenuDTO;
	}

	/**
	 * 根据编号查询菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	@Override
	public int updateSystemMenu(SystemMenuVO systemMenuVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【菜单模块】-更新菜单信息输入参数systemMenuVO：{}",systemMenuVO);
		SystemMenuInfo systemMenuInfo = new SystemMenuInfo();
		systemMenuInfo.setMenuId(systemMenuVO.getMenuId());
		systemMenuInfo.setMenuName(systemMenuVO.getMenuName());
		systemMenuInfo.setMenuLevel(systemMenuVO.getMenuLevel());
		systemMenuInfo.setMenuFatherId(systemMenuVO.getMenuFatherId());
		systemMenuInfo.setMenuCode(systemMenuVO.getMenuCode());
		systemMenuInfo.setMenuIco(systemMenuVO.getMenuIco());
		systemMenuInfo.setMenuUrl(systemMenuVO.getMenuUrl());
		systemMenuInfo.setMenuStatus(systemMenuVO.getMenuStatus());
		systemMenuInfo.setMenuOrder(systemMenuVO.getMenuOrder());
		systemMenuInfo.setMenuUpdateTime(DateUtils.getTimestamp());
		logger.debug("【菜单模块】-更新菜单信息输入参数systemMenuInfo：{}",systemMenuInfo);
		result = this.systemMenuDaoImpl.updateByPrimaryKeySelective(systemMenuInfo);
		logger.debug("【菜单模块】-更新菜单执行结果result：{}",result);
		return result;
	}

	/**
	 * 根据编号查询菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	@Override
	public int saveSystemMenu(SystemMenuVO systemMenuVO, UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【菜单模块】-新增菜单信息输入参数systemMenuVO：{}",systemMenuVO);
		SystemMenuInfo systemMenuInfo = new SystemMenuInfo();
		systemMenuInfo.setMenuId(systemMenuVO.getMenuId());
		systemMenuInfo.setMenuName(systemMenuVO.getMenuName());
		systemMenuInfo.setMenuLevel(systemMenuVO.getMenuLevel());
		systemMenuInfo.setMenuFatherId(systemMenuVO.getMenuFatherId());
		systemMenuInfo.setMenuCode(systemMenuVO.getMenuCode());
		systemMenuInfo.setMenuIco(systemMenuVO.getMenuIco());
		systemMenuInfo.setMenuUrl(systemMenuVO.getMenuUrl());
		systemMenuInfo.setMenuStatus(systemMenuVO.getMenuStatus());
		systemMenuInfo.setMenuOrder(systemMenuVO.getMenuOrder());
		systemMenuInfo.setUserId(userSession.getUserId());
		systemMenuInfo.setMenuCreateTime(DateUtils.getTimestamp());
		logger.debug("【菜单模块】-新增菜单信息输入参数systemMenuInfo：{}",systemMenuInfo);
		result = this.systemMenuDaoImpl.insertSelective(systemMenuInfo);
		logger.debug("【菜单模块】-新增菜单执行结果result：{}",result);
		return result;
	}

	/**
	 * 禁用停用菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	@Override
	public int updateSystemMenuStatus(SystemMenuVO systemMenuVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【菜单模块】-更新菜单状态输入参数systemMenuVO：{}",systemMenuVO);
		SystemMenuInfo systemMenuInfo = new SystemMenuInfo();
		systemMenuInfo.setMenuId(systemMenuVO.getMenuId());
		systemMenuInfo.setMenuStatus(systemMenuVO.getMenuStatus());
		logger.debug("【菜单模块】-更新菜单状态信息输入参数systemMenuInfo：{}",systemMenuInfo);
		result = this.systemMenuDaoImpl.updateByPrimaryKeySelective(systemMenuInfo);
		logger.debug("【菜单模块】-更新菜单状态执行结果result：{}",result);
		return result;
	}

	/**
	 * 删除菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	@Override
	public int deleteSystemMenu(SystemMenuVO systemMenuVO) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【菜单模块】-删除菜单信息输入参数systemMenuVO：{}",systemMenuVO);
		String menuId = systemMenuVO.getMenuId();
		result = this.systemMenuDaoImpl.deleteByPrimaryKey(menuId);
		logger.debug("【菜单模块】-删除菜单信息执行结果result：{}",result);
		return result;
	}

	/**
	 * 级联删除菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	@Override
	public int deleteAllSystemMenu(SystemMenuVO systemMenuVO) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【菜单模块】-级联删除菜单信息");
		return result;
	}

	/**
	 * 查询菜单是否存在
	 * @param systemMenuVO 
	 * @param userSession
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:33:07
	 */
	@Override
	public int validateMenuExist(SystemMenuVO systemMenuVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【菜单模块】-查询菜单是否存在输入参数systemMenuVO：{}",systemMenuVO);
		SystemMenuDTO systemMenu = this.systemMenuDaoImpl.selectByPrimaryKey(systemMenuVO.getMenuId());
		if(null != systemMenu){
			return 1;
		}
		return 0;
	}
	
}
