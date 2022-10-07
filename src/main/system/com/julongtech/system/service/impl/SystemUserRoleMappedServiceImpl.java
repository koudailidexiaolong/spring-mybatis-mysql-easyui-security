package com.julongtech.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemUserRoleMappedVO;
import com.julongtech.system.dao.SystemUserRoleMappedDao;
import com.julongtech.system.dao.entity.SystemUserRoleMappedInfo;
import com.julongtech.system.service.SystemUserRoleMappedService;
import com.julongtech.system.service.dto.SystemUserRoleMappedDTO;
import com.julongtech.system.session.UserSession;
import com.julongtech.system.util.DefaultUtil;
import com.julongtech.util.DateUtils;
/**
 * 用户角色映射关系模块
 * @author julong
 * @date 2017-10-18 上午11:05:03
 */
@Service
public class SystemUserRoleMappedServiceImpl implements	SystemUserRoleMappedService {

	private static final Logger logger = LoggerFactory.getLogger(SystemUserRoleMappedServiceImpl.class);
	
	@Autowired
	private SystemUserRoleMappedDao systemUserRoleMappedDaoImpl;
	
	@Override
	public int saveUserRoleMapped(SystemUserRoleMappedVO systemUserRoleMappedVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【用户角色关系模块】-新增关系");
		SystemUserRoleMappedInfo systemUserRoleMappedInfo = new SystemUserRoleMappedInfo();
		result = this.systemUserRoleMappedDaoImpl.insertSelective(systemUserRoleMappedInfo);
		return result;
	}

	@Override
	public int updateUserRoleMapped(SystemUserRoleMappedVO systemUserRoleMappedVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户角色关系模块】-修改关系");
		int result = -1;
		SystemUserRoleMappedInfo systemUserRoleMappedInfo = new SystemUserRoleMappedInfo();
		result = this.systemUserRoleMappedDaoImpl.updateByPrimaryKeySelective(systemUserRoleMappedInfo);
		return result;
	}

	@Override
	public int deleteUserRoleMapped(SystemUserRoleMappedVO systemUserRoleMappedVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户角色关系模块】-删除关系");
		int result = -1;
		String userId = systemUserRoleMappedVO.getUserId();
		result = this.systemUserRoleMappedDaoImpl.deleteByUserId(userId);
		return result;
	}

	@Override
	public SystemUserRoleMappedDTO getUserRoleMapped(SystemUserRoleMappedVO systemUserRoleMappedVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户角色关系模块】-查询关系");
		SystemUserRoleMappedDTO systemUserRoleMapped = new SystemUserRoleMappedDTO();
		SystemUserRoleMappedDTO systemUserRoleMappedDTO = (SystemUserRoleMappedDTO) this.systemUserRoleMappedDaoImpl.selectBySelective(systemUserRoleMapped);
		return systemUserRoleMappedDTO;
	}

	@Override
	public List<SystemUserRoleMappedDTO> getUserRoleMappedListByPage(SystemUserRoleMappedVO systemUserRoleMappedVO)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户角色关系模块】-分页查询关系");
		SystemUserRoleMappedDTO systemUserRoleMappedDTO = new SystemUserRoleMappedDTO();
		List<SystemUserRoleMappedDTO>  list = (List<SystemUserRoleMappedDTO>) this.systemUserRoleMappedDaoImpl.selectBySelective(systemUserRoleMappedDTO);
		return list;
	}

	@Override
	public List<SystemUserRoleMappedDTO> getUserRoleMappedList(SystemUserRoleMappedVO systemUserRoleMappedVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户角色关系模块】-查新关系集合");
		SystemUserRoleMappedDTO systemUserRoleMappedDTO = new SystemUserRoleMappedDTO();
		systemUserRoleMappedDTO.setUserId(systemUserRoleMappedVO.getUserId());
		systemUserRoleMappedDTO.setRoleId(systemUserRoleMappedVO.getRoleId());
		List<SystemUserRoleMappedDTO> list = (List<SystemUserRoleMappedDTO>) this.systemUserRoleMappedDaoImpl.selectBySelective(systemUserRoleMappedDTO);
		return list;
	}

	@Override
	public int saveUserRoleMappedList(List<SystemUserRoleMappedVO> userRoleList, UserSession userSession,String userId)throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户角色关系模块】-批量新增用户角色对应关系");
		int count = 0;
		//删除映射关系
		int deleteResult = this.systemUserRoleMappedDaoImpl.deleteByUserId(userId);
		logger.debug("【用户角色关系模块】-删除用户角色对应关系执行结果：{}",deleteResult);
		for (SystemUserRoleMappedVO systemUserRoleMappedVO : userRoleList) {
			SystemUserRoleMappedInfo mapped = new SystemUserRoleMappedInfo();
			mapped.setMappedId(DefaultUtil.DEFAULT_SEQUENCE);
			mapped.setMappedStatus(systemUserRoleMappedVO.getMappedStatus());
			mapped.setRoleId(systemUserRoleMappedVO.getRoleId());
			mapped.setUserId(systemUserRoleMappedVO.getUserId());
			mapped.setOrgId(systemUserRoleMappedVO.getOrgId());
			mapped.setMappedCreateTime(DateUtils.getTimestamp());
			int result = this.systemUserRoleMappedDaoImpl.insertSelective(mapped);
			if(result>0){
				count++;
			}
		}
		return count;
	}

}
