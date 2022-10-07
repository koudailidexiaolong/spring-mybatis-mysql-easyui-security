package com.julongtech.system.service;

import java.util.List;

import com.julongtech.system.action.vo.SystemUserRoleMappedVO;
import com.julongtech.system.service.dto.SystemUserRoleMappedDTO;
import com.julongtech.system.session.UserSession;

/**
 * 用户角色映射模块
 * @author julong
 * @date 2017-10-18 上午9:46:20
 */
public interface SystemUserRoleMappedService {
	/**
	 * 新增用户角色对应关系信息的方法
	 * @param systemUserRoleMappedVO 用户角色类
	 * @param userSession 用户类
	 * @return int
	 * @author julong
	 * @date 2017-1-9 下午08:36:32
	 */
	public abstract int saveUserRoleMapped(SystemUserRoleMappedVO systemUserRoleMappedVO,UserSession userSession) throws Exception;
	
	/**
	 * 新增用户角色对应关系信息的方法
	 * @param systemUserRoleMappedVO 用户角色类
	 * @param userSession 用户类
	 * @return int
	 * @author julong
	 * @date 2017-1-9 下午08:36:32
	 */
	public abstract int updateUserRoleMapped(SystemUserRoleMappedVO systemUserRoleMappedVO,UserSession userSession) throws Exception;

	/**
	 * 删除用户角色对应关系信息的方法
	 * @param systemUserRoleMappedVO 用户角色类
	 * @return int
	 * @author julong
	 * @date 2017-1-9 下午08:36:32
	 */
	public abstract int deleteUserRoleMapped(SystemUserRoleMappedVO systemUserRoleMappedVO) throws Exception;
	
	/**
	 * 获取用户角色对应关系信息的方法
	 * @param systemUserRoleMappedVO
	 * @return SystemUserRoleMappedDTO
	 * @author julong
	 * @date 2017-1-9 下午09:42:44
	 */
	public abstract SystemUserRoleMappedDTO getUserRoleMapped(SystemUserRoleMappedVO systemUserRoleMappedVO) throws Exception;
	
	/**
	 * 分页查询用户角色关系信息的方法
	 * @param systemUserRoleMappedVO 用户角色关系类
	 * @param pageInfo 分页参数
	 * @return List<SystemUserRoleMappedDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-10 下午03:36:48
	 */
	public abstract List<SystemUserRoleMappedDTO> getUserRoleMappedListByPage(SystemUserRoleMappedVO systemUserRoleMappedVO)throws Exception;
	
	/**
	 * 查询用户角色关系信息集合的方法
	 * @param systemUserRoleMappedVO 用户角色关系类
	 * @return List<SystemUserRoleMappedDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-10 下午03:36:48
	 */
	public abstract List<SystemUserRoleMappedDTO> getUserRoleMappedList(SystemUserRoleMappedVO systemUserRoleMappedVO)throws Exception;
	
	/**
	 * 新增用户角色对应关系信息的方法
	 * @param systemUserRoleMappedVO 用户角色类
	 * @param userSession 用户类
	 * @return int
	 * @author julong
	 * @date 2017-1-9 下午08:36:32
	 */
	public abstract int saveUserRoleMappedList(List<SystemUserRoleMappedVO> userRoleList,UserSession userSession,String userId) throws Exception;
}
