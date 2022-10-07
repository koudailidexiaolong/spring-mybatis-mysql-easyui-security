package com.julongtech.system.service;

import java.util.List;

import com.julongtech.system.action.vo.SystemRoleMenuMappedVO;
import com.julongtech.system.service.dto.SystemRoleMenuMappedDTO;
import com.julongtech.system.session.UserSession;

/**
 * 角色菜单关系映射模块
 * @author julong
 * @date 2017-10-18 上午9:45:41
 */
public interface SystemRoleMenuMappedService {
	
	/**
	 * 批量新增菜单角色对应关系信息的方法
	 * @param List<SystemRoleMenuMappedVO> 角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return  int 新增是否成功
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午09:46:57
	 */
	public abstract int saveSystemRoleMenuMappedList(List<SystemRoleMenuMappedVO> systemRoleMenuMappeds,UserSession userSession) throws Exception;
	
	/**
	 * 新增菜单角色对应关系信息的方法
	 * @param SystemRoleMenuMapped 角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return  int 新增是否成功
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午09:46:57
	 */
	public abstract int saveSystemRoleMenuMapped(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession) throws Exception;

	/**
	 * 删除菜单角色对应关系信息的方法
	 * @param SystemRoleMenuMapped 角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return  int 是否成功
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午09:19:09
	 */
	public abstract int deleteSystemRoleMenuMapped(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession)throws Exception;
	
	/**
	 * 删除角色菜单按钮三者对应关系信息的方法
	 * @param SystemRoleMenuMapped 角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return  int 是否成功
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午09:19:09
	 */
	public abstract int deleteSystemRoleMenuButton(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession)throws Exception;

	/**
	 * 级联删除菜单角色对应关系信息的方法
	 * @param SystemRoleMenuMapped 角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return  int 是否成功
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午09:19:09
	 */
	public abstract int deleteSystemRoleMenuMappedList(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession)throws Exception;

	/**
	 * 更新菜单角色对应关系信息
	 * @param SystemRoleMenuMapped 角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return int 是否成功
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午09:42:26
	 */
	public abstract int updateSystemRoleMenuMapped(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession)throws Exception;
	
	/**
	 * 批量修改
	 * @param SystemRoleMenuMappeds
	 * @param userSession
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-8-8 下午03:54:14
	 */
	public abstract int updateSystemRoleMenuMappedList(List<SystemRoleMenuMappedVO> SystemRoleMenuMappeds,UserSession userSession)throws Exception;
	
	/**
	 * 禁用停用菜单角色对应关系信息的方法
	 * @param SystemRoleMenuMapped 角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return int 是否成功
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午09:28:16
	 */
	public abstract int updateSystemRoleMenuMappedStatus(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession)throws Exception;
	
	/**
	 * 根据编号查询菜单角色对应信息的方法
	 * @param SystemRoleMenuMapped  角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return SystemRoleMenuMappedDTO 
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午09:51:42
	 */
	public abstract SystemRoleMenuMappedDTO getSystemRoleMenuMapped(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession)throws Exception;

	/**
	 * 查询角色菜单对应关系信息集合的方法
	 * @param systemRoleMenuMappedVO 角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return List<SystemRoleMenuMappedDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午09:59:54
	 */
	public abstract List<SystemRoleMenuMappedDTO> getSystemRoleMenuMappedList(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession)throws Exception;

	/**
	 * 分页查询角色菜单对应关系信息集合的方法
	 * @param SystemRoleMenuMapped 角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return  List<SystemRoleMenuMappedDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午10:00:23
	 */
	public abstract List<SystemRoleMenuMappedDTO> getSystemRoleMenuMappedListByPage(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession)throws Exception;

}
