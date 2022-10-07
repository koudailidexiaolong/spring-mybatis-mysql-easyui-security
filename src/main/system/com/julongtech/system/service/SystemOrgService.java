package com.julongtech.system.service;

import java.util.List;
import java.util.Map;

import com.julongtech.system.action.vo.SystemOrgVO;
import com.julongtech.system.service.dto.SystemOrgDTO;
import com.julongtech.system.session.UserSession;

/**
 * 组织机构处理模块
 * @author julong
 * @date 2017-10-18 上午9:44:41
 */
public interface SystemOrgService {

	/**
	 * 分页查询组织机构（管理部门）功能
	 * @param SystemOrgVO 管理部门信息
	 * @param pageInfo 分页参数
	 * @return List<SystemOrgDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:22:26
	 */
	public abstract List<SystemOrgDTO> getSystemOrgList(SystemOrgVO systemOrgVO) throws Exception;
	
	/**
	 * 分页查询组织机构（管理部门）功能
	 * @param SystemOrgVO 管理部门信息
	 * @return List<SystemOrgDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:22:26
	 */
	public abstract List<SystemOrgDTO> getSystemOrgListByPage(SystemOrgVO systemOrgVO) throws Exception;
	
	/**
	 * 查询组织机构（管理部门）功能
	 * @param SystemOrgVO 管理部门信息
	 * @param userInfo 用户参数
	 * @return SystemOrgVO 管理部门信息
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:24:32
	 */
	public abstract SystemOrgDTO getSystemOrg(SystemOrgVO systemOrgVO) throws Exception;
	
	/**
	 * 删除组织机构（管理部门信息）
	 * @param SystemOrgVO 管理部门信息
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:26:27
	 */
	public abstract int deleteSystemOrg(SystemOrgVO systemOrgVO) throws Exception;
	
	/**
	 * 更新组织机构（管理部门信息）
	 * @param SystemOrgVO 管理部门信息
	 * @param userSession 用户参数
	 * @return int 
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:31:34
	 */
	public abstract int updateSystemOrg(SystemOrgVO systemOrgVO,UserSession userSession) throws Exception;
	
	/**
	 * 新增组织机构（管理部门信息）
	 * @param SystemOrgVO 管理部门信息
	 * @param userSession 用户参数
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:31:44
	 */
	public abstract int saveSystemOrg(SystemOrgVO systemOrgVO,UserSession userSession) throws Exception;
	/**
	 * 校验是否存在此机构
	 * @param SystemOrgVO 管理部门信息
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:31:44
	 */
	public abstract int validateSystemOrg(SystemOrgVO systemOrgVO) throws Exception;
	
	/**
	 * 导入组织机构（管理部门信息）
	 * @param SystemOrgVO 管理部门信息
	 * @return Map<String,Object>
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:31:44
	 */
	public abstract Map<String,Object> importOrgInfoList(List<SystemOrgVO> systemOrgList) throws Exception;
	
	/**
	 * 停用禁用组织机构（管理部门信息）
	 * @param SystemOrgVO 管理部门信息
	 * @param userSession 用户参数
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:31:49
	 */
	public abstract int updateSystemOrgStatus(SystemOrgVO systemOrgVO,UserSession userSession) throws Exception;
	
	/**
	 * 分页查询组织机构（管理部门）功能
	 * @param SystemOrgVO 管理部门信息
	 * @param pageInfo 分页参数
	 * @return List<SystemOrgDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:22:26
	 */
	public abstract List<SystemOrgDTO> getSystemOrgParentList(SystemOrgVO systemOrgVO) throws Exception;
	
	
}
