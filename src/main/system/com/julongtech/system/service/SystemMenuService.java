package com.julongtech.system.service;

import java.util.List;

import com.julongtech.system.action.vo.SystemMenuVO;
import com.julongtech.system.service.dto.SystemMenuDTO;
import com.julongtech.system.session.UserSession;

/**
 * 系统菜单模块
 * @author julong
 * @date 2017-10-18 上午9:44:18
 */
public interface SystemMenuService {
	
	/**
	 * 分页查询所有的菜单信息集合的方法
	 * @param systemMenuVO 菜单对象
	 * @param userInfo 当前登录用户
	 * @return List<SystemMenuDTO> 菜单集合
	 * @author julong
	 * @date 2016-7-6 上午11:50:43
	 */
	public abstract List<SystemMenuDTO> getMenuListByPage(SystemMenuVO systemMenuVO) throws Exception;
	
	/**
	 * 查询所有的菜单信息集合的方法
	 * @param SystemMenuVO 菜单对象
	 * @return List<SystemMenuVO> 菜单集合
	 * @author julong
	 * @date 2016-7-6 上午11:50:43
	 */
	public abstract List<SystemMenuDTO> getMenuList(SystemMenuVO systemMenuVO) throws Exception;
	
	/**
	 * 查询菜单树信息的方法
	 * @param systemMenuVO 菜单对象
	 * @return SystemMenuDTO 菜单集合
	 * @author julong
	 * @date 2016-7-6 上午11:50:43
	 */
	public abstract List<SystemMenuDTO>  getMenuListTree(SystemMenuVO systemMenuVO) throws Exception;
	
	/**
	 * 根据编号查询菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @return SystemMenuDTO
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	public abstract SystemMenuDTO getSystemMenu(SystemMenuVO systemMenuVO) throws Exception;
	
	/**
	 * 根据编号查询菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	public abstract int updateSystemMenu(SystemMenuVO systemMenuVO,UserSession userSession) throws Exception;
	
	/**
	 * 根据编号查询菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	public abstract int saveSystemMenu(SystemMenuVO systemMenuVO,UserSession userSession) throws Exception;
	
	/**
	 * 禁用停用菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	public abstract int updateSystemMenuStatus(SystemMenuVO systemMenuVO,UserSession userSession) throws Exception;
	
	/**
	 * 删除菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	public abstract int deleteSystemMenu(SystemMenuVO systemMenuVO) throws Exception;
	/**
	 * 级联删除菜单基本信息的方法
	 * @param SystemMenuVO 菜单对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	public abstract int deleteAllSystemMenu(SystemMenuVO systemMenuVO) throws Exception;
	
	
	/**
	 * 查询菜单是否存在
	 * @param systemMenuVO 
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:33:07
	 */
	public abstract int validateMenuExist(SystemMenuVO systemMenuVO) throws Exception;
}
