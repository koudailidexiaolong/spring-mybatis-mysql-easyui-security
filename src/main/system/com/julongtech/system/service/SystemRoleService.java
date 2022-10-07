package com.julongtech.system.service;

import java.util.List;

import com.julongtech.system.action.vo.SystemRoleVO;
import com.julongtech.system.service.dto.SystemRoleDTO;
import com.julongtech.system.session.UserSession;

/**
 * 系统角色模块
 * @author julong
 * @date 2017-10-18 上午9:45:03
 */
public interface SystemRoleService {
	/**
	 * 分页查询角色信息的方法
	 * @param systemRoleVO 角色信息对象
	 * @return List<SystemRoleDTO> 
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:30:21
	 */
	public abstract List<SystemRoleDTO> getRoleListByPage(SystemRoleVO systemRoleVO) throws Exception;
	
	/**
	 * 查询角色信息列表的方法
	 * @param SystemRole 角色信息对象
	 * @return List<SystemRoleDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:30:21
	 */
	public abstract List<SystemRoleDTO> getRoleList(SystemRoleVO systemRoleVO) throws Exception;
	
	/**
	 * 根据角色编号查询角色的基本信息
	 * @param SystemRole 角色信息对象
	 * @return SystemRoleDTO
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午05:11:51
	 */
	public abstract SystemRoleDTO getSystemRole(SystemRoleVO systemRoleVO) throws Exception;
	
	/**
	 * 新增角色信息的方法
	 * @param SystemRole 角色信息对象
	 * @param userSession 用户对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:30:43
	 */
	public abstract int saveSystemRole(SystemRoleVO systemRoleVO,UserSession userSession) throws Exception;
	
	/**
	 * 更新角色信息的方法
	 * @param SystemRole 角色信息对象
	 * @param userSession 用户对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:30:43
	 */
	public abstract int updateSystemRole(SystemRoleVO systemRoleVO,UserSession userSession) throws Exception;
	
	/**
	 * 级联删除用户角色信息的方法
	 * @param SystemRole 角色信息对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:35:40
	 */
	public abstract int deleteSystemRole(SystemRoleVO systemRoleVO) throws Exception;
	
	/**
	 * 级联删除用户角色信息的方法
	 * @param SystemRole 角色信息对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:35:40
	 */
	public abstract int deleteAllSystemRole(SystemRoleVO systemRoleVO) throws Exception;
	
	/**
	 *  级联删除角色信息的方法
	 * @param roleList 角色集合
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-8-10 下午04:09:46
	 */
	public abstract int deleteSystemRoleList(List<SystemRoleVO> roleList) throws Exception;
	
	/**
	 * 禁用停用角色信息的方法
	 * @param SystemRole 角色信息对象
	 * @param userSession 用户对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:30:43
	 */
	public abstract int updateSystemRoleStatus(SystemRoleVO systemRoleVO,UserSession userSession) throws Exception;
	
	/**
	 * 统计角色名称
	 * @param systemRoleVO 
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:33:07
	 */
	public abstract int validateRoleExist(SystemRoleVO systemRoleVO) throws Exception;
	
}
