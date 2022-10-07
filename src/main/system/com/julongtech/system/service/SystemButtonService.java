package com.julongtech.system.service;

import java.util.List;

import com.julongtech.system.action.vo.SystemButtonVO;
import com.julongtech.system.service.dto.SystemButtonDTO;
import com.julongtech.system.session.UserSession;

/**
 * 系统按钮处理模块
 * @author julong
 * @date 2017-10-18 上午9:41:47
 */
public interface SystemButtonService {
	
	/**
	 * 查询所有的菜单按钮信息集合的方法
	 * @param systemButtonVO
	 * @return List<SystemButtonDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-18 上午9:53:18
	 */
	public abstract List<SystemButtonDTO> selectSystemButtonListByPage(SystemButtonVO systemButtonVO) throws Exception;
	
	/**
	 * 根据编号查询菜单按钮基本信息的方法
	 * @param systemButtonVO 菜单按钮对象
	 * @return SystemButtonDTO
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	public abstract SystemButtonDTO getSystemButton(SystemButtonVO systemButtonVO) throws Exception;
	
	/**
	 * 根据编号查询菜单按钮基本信息的方法
	 * @param systemButtonVO 菜单按钮对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	public abstract int updateSystemButton(SystemButtonVO systemButtonVO,UserSession userSession) throws Exception;
	
	/**
	 * 根据编号查询菜单按钮基本信息的方法
	 * @param SystemButtonVO 菜单按钮对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	public abstract int saveSystemButton(SystemButtonVO systemButtonVO,UserSession userSession) throws Exception;
	
	/**
	 * 禁用停用菜单按钮基本信息的方法
	 * @param SystemButtonVO
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-18 上午9:56:43
	 */
	public abstract int updateSystemButtonStatus(SystemButtonVO systemButtonVO,UserSession userSession) throws Exception;
	
	/**
	 * 删除菜单基本按钮信息的方法
	 * @param systemButtonVO
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-18 上午9:56:36
	 */
	public abstract int deleteSystemButton(SystemButtonVO systemButtonVO) throws Exception;
	
	/**
	 * 查询所有的菜单按钮信息集合的方法
	 * @param systemButtonVO
	 * @return List<SystemButtonDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-18 上午9:56:26
	 */
	public abstract List<SystemButtonDTO> getSystemButtonList(SystemButtonVO systemButtonVO) throws Exception;
	
	/**
	 * 查询菜单按钮是否存在
	 * @param systemButtonVO 
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:33:07
	 */
	public abstract int validateButtonExist(SystemButtonVO systemButtonVO) throws Exception;
	
}
