package com.julongtech.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemRoleVO;
import com.julongtech.system.dao.SystemRoleDao;
import com.julongtech.system.dao.SystemRoleMenuMappedDao;
import com.julongtech.system.dao.SystemUserRoleMappedDao;
import com.julongtech.system.dao.entity.SystemRoleInfo;
import com.julongtech.system.dao.entity.SystemUserRoleMappedInfo;
import com.julongtech.system.service.SystemRoleService;
import com.julongtech.system.service.dto.SystemRoleDTO;
import com.julongtech.system.session.UserSession;
import com.julongtech.system.util.DefaultUtil;
import com.julongtech.util.DateUtils;
/**
 * 角色模块
 * @author julong
 * @date 2017-10-18 上午11:05:20
 */
@Service
public class SystemRoleServiceImpl implements SystemRoleService {

	private static final Logger logger = LoggerFactory.getLogger(SystemRoleServiceImpl.class);
	@Autowired
	private SystemRoleDao systemRoleDaoImpl;
	
	@Autowired
	private SystemUserRoleMappedDao systemUserRoleMappedDaoImpl;
	
	@Autowired
	private SystemRoleMenuMappedDao systemRoleMenuMappedDaoImpl;
	
	/**
	 * 分页查询角色信息的方法
	 * @param systemRoleVO 角色信息对象
	 * @param userSession 用户对象
	 * @return List<SystemRoleDTO> 
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:30:21
	 */
	@Override
	public List<SystemRoleDTO> getRoleListByPage(SystemRoleVO systemRoleVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色模块】-分页查询的方法输入参数systemRoleVO:{}",systemRoleVO);
		SystemRoleDTO systemRoleDTO = new SystemRoleDTO();
		systemRoleDTO.setRoleId(systemRoleVO.getRoleId());
		if(StringUtils.isNotEmpty(systemRoleVO.getRoleName())){
			systemRoleDTO.setRoleName(systemRoleVO.getRoleName()+"%");
		}
		systemRoleDTO.setRoleStatus(systemRoleVO.getRoleStatus());
		systemRoleDTO.setOrgId(systemRoleVO.getOrgId());
		logger.debug("【角色模块】-分页查询的方法输入参数systemRoleDTO:{}",systemRoleDTO);
		List<SystemRoleDTO> list = this.systemRoleDaoImpl.selectBySelective(systemRoleDTO);
		return list;
	}
	
	/**
	 * 查询角色信息列表的方法
	 * @param SystemRole 角色信息对象
	 * @param userSession 用户对象
	 * @return List<SystemRoleDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:30:21
	 */
	@Override
	public List<SystemRoleDTO> getRoleList(SystemRoleVO systemRoleVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色模块】-查询集合的方法输入参数systemRoleVO:{}",systemRoleVO);
		SystemRoleDTO systemRoleDTO = new SystemRoleDTO();
		systemRoleDTO.setRoleId(systemRoleVO.getRoleId());
		systemRoleDTO.setRoleStatus(systemRoleVO.getRoleStatus());
		logger.debug("【角色模块】-查询集合的方法输入参数systemRoleDTO:{}",systemRoleDTO);
		List<SystemRoleDTO> list = this.systemRoleDaoImpl.selectBySelective(systemRoleDTO);
		return list;
	}

	/**
	 * 根据角色编号查询角色的基本信息
	 * @param SystemRole 角色信息对象
	 * @param userSession 用户对象
	 * @return SystemRoleDTO
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午05:11:51
	 */
	@Override
	public SystemRoleDTO getSystemRole(SystemRoleVO systemRoleVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色模块】-查询的方法输入参数systemRoleVO:{}",systemRoleVO);
		SystemRoleDTO systemRoleDTO = (SystemRoleDTO) this.systemRoleDaoImpl.selectByPrimaryKey(systemRoleVO.getRoleId());
		return systemRoleDTO;
	}

	/**
	 * 新增角色信息的方法
	 * @param SystemRole 角色信息对象
	 * @param userSession 用户对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:30:43
	 */
	@Override
	public int saveSystemRole(SystemRoleVO systemRoleVO, UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【角色模块】-新增的方法输入参数systemRoleVO:{}",systemRoleVO);
		SystemRoleInfo systemRoleInfo = new SystemRoleInfo();
		systemRoleInfo.setRoleId(DefaultUtil.DEFAULT_SEQUENCE);
		systemRoleInfo.setOrgId(systemRoleVO.getOrgId());
		systemRoleInfo.setRoleName(systemRoleVO.getRoleName());
		systemRoleInfo.setRoleStatus(systemRoleVO.getRoleStatus());
		systemRoleInfo.setRoleType(systemRoleVO.getRoleType());
		if(null != userSession){
			systemRoleInfo.setRoleCreateUserId(userSession.getUserId());
		}
		systemRoleInfo.setRoleCreateTime(DateUtils.getTimestamp());
		systemRoleInfo.setRoleDesc(systemRoleVO.getRoleDesc());
		logger.debug("【角色模块】-新增的方法输入参数systemRoleInfo:{}",systemRoleInfo);
		result = this.systemRoleDaoImpl.insertSelective(systemRoleInfo);
		logger.debug("【角色模块】-新增角色执行结果result:{}",result);
		return result;
	}

	/**
	 * 更新角色信息的方法
	 * @param SystemRole 角色信息对象
	 * @param userSession 用户对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:30:43
	 */
	@Override
	public int updateSystemRole(SystemRoleVO systemRoleVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色模块】-更新的方法输入参数systemRoleVO：{}",systemRoleVO);
		int result = -1;
		SystemRoleInfo systemRoleInfo = new SystemRoleInfo();
		systemRoleInfo.setRoleId(systemRoleVO.getRoleId());
		systemRoleInfo.setOrgId(systemRoleVO.getOrgId());
		systemRoleInfo.setRoleName(systemRoleVO.getRoleName());
		systemRoleInfo.setRoleStatus(systemRoleVO.getRoleStatus());
		systemRoleInfo.setRoleType(systemRoleVO.getRoleType());
		if(null != userSession){
			systemRoleInfo.setRoleUpdateUserId(userSession.getUserId());
		}
		systemRoleInfo.setRoleUpdateTime(DateUtils.getTimestamp());
		systemRoleInfo.setRoleDesc(systemRoleVO.getRoleDesc());
		SystemUserRoleMappedInfo systemUserRoleMappedInfo = new SystemUserRoleMappedInfo();
		systemUserRoleMappedInfo.setMappedStatus(systemRoleVO.getRoleStatus());
		systemUserRoleMappedInfo.setRoleId(systemRoleVO.getRoleId());
		logger.debug("【角色模块】-更新的方法输入参数systemUserRoleMappedInfo：{}",systemUserRoleMappedInfo);
		result = this.systemUserRoleMappedDaoImpl.updateByRole(systemUserRoleMappedInfo);
		logger.debug("【角色模块】-更新用户角色映射信息执行结果result:{}",result);
		result = this.systemRoleDaoImpl.updateByPrimaryKeySelective(systemRoleInfo);
		logger.debug("【角色模块】-更新用户角色信息执行结果result:{}",result);
		return result;
	}

	/**
	 * 级联删除用户角色信息的方法
	 * @param SystemRole 角色信息对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:35:40
	 */
	@Override
	public int deleteSystemRole(SystemRoleVO systemRoleVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色模块】-删除的方法输入参数systemRoleVO：{}",systemRoleVO);
		int result = -1;
		int roleId = systemRoleVO.getRoleId();
		result = this.systemRoleDaoImpl.deleteByPrimaryKey(roleId);
		logger.debug("【角色模块】-删除角色信息执行结果：{}",result);
		//删除角色对应的映射关系
		result = this.systemRoleMenuMappedDaoImpl.deleteByRoleId(roleId);
		logger.debug("【角色模块】-删除角色菜单对应关系执行结果result：{}",result);
		return result;
	}
	
	/**
	 * 级联删除用户角色信息的方法
	 * @param SystemRole 角色信息对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:35:40
	 */
	@Override
	public int deleteAllSystemRole(SystemRoleVO systemRoleVO) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【角色模块】-级联删除的方法输入参数");
		
		return result;
	}

	/**
	 *  级联删除角色信息的方法
	 * @param roleList 角色集合
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-8-10 下午04:09:46
	 */
	@Override
	public int deleteSystemRoleList(List<SystemRoleVO> roleList) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色模块】-批量删除的方法输入参数");
		
		return 0;
	}

	/**
	 * 禁用停用角色信息的方法
	 * @param SystemRole 角色信息对象
	 * @param userSession 用户对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午03:30:43
	 */
	@Override
	public int updateSystemRoleStatus(SystemRoleVO systemRoleVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【角色模块】-更新状态的方法输入参数systemRoleVO：{}",systemRoleVO);
		SystemRoleInfo systemRoleInfo = new SystemRoleInfo();
		systemRoleInfo.setOrgId(systemRoleVO.getOrgId());
		systemRoleInfo.setRoleStatus(systemRoleVO.getRoleStatus());
		systemRoleInfo.setRoleDesc(systemRoleVO.getRoleDesc());
		if(null != userSession){
			systemRoleInfo.setRoleUpdateUserId(userSession.getUserId());
		}
		systemRoleInfo.setRoleId(systemRoleVO.getRoleId());
		systemRoleInfo.setRoleUpdateTime(DateUtils.getTimestamp());
		SystemUserRoleMappedInfo systemUserRoleMappedInfo = new SystemUserRoleMappedInfo();
		systemUserRoleMappedInfo.setMappedStatus(systemRoleVO.getRoleStatus());
		systemUserRoleMappedInfo.setRoleId(systemRoleVO.getRoleId());
		result = this.systemUserRoleMappedDaoImpl.updateByRole(systemUserRoleMappedInfo);
		logger.debug("【角色模块】-更新用户角色状态执行结果result：{}",result);
		result = this.systemRoleDaoImpl.updateByPrimaryKeySelective(systemRoleInfo);
		logger.debug("【角色模块】-更新角色状态执行结果result：{}",result);
		return result;
	}
	
	/**
	 * 统计角色名称
	 * @param systemRoleVO 
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:33:07
	 */
	@Override
	public int validateRoleExist(SystemRoleVO systemRoleVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色模块】-查询总数的方法输入参数systemRoleVO：{}",systemRoleVO);
		int result = -1;
		SystemRoleDTO systemRole = new SystemRoleDTO();
		systemRole.setRoleName(systemRoleVO.getRoleName());
		systemRole.setRoleId(systemRoleVO.getRoleId());
		Object obj = this.systemRoleDaoImpl.selectCount(systemRole);
		if(null != obj){
			return Integer.valueOf(obj+"");
		}
		return result;
	}
	
}
