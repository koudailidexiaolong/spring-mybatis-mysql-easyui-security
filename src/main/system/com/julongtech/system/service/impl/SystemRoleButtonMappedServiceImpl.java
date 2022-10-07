package com.julongtech.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemRoleButtonMappedVO;
import com.julongtech.system.dao.SystemRoleButtonMappedDao;
import com.julongtech.system.service.SystemRoleButtonMappedService;
import com.julongtech.system.service.dto.SystemRoleButtonMappedDTO;

/**
 * 角色按钮映射关系类
 * @author julong
 * @date 2022年8月21日 下午4:40:39
 * @desc 
 */
@Service
public class SystemRoleButtonMappedServiceImpl implements SystemRoleButtonMappedService {

	@Autowired
	private SystemRoleButtonMappedDao systemRoleButtonMappedDaoImpl;
	
	@Override
	public int deleteSystemRoleButtonMapped(Integer mappedId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSystemRoleButtonMappedByRoleId(Integer roleId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSystemRoleButtonMapped(SystemRoleButtonMappedVO systemRoleButtonMappedVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SystemRoleButtonMappedDTO getSystemRoleButtonMapped(Integer mappedId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemRoleButtonMappedDTO> selectSystemRoleButtonMappedList(SystemRoleButtonMappedVO systemRoleButtonMappedVO) throws Exception {
		// TODO Auto-generated method stub
		SystemRoleButtonMappedDTO systemRoleButtonMappedDTO = new SystemRoleButtonMappedDTO();
		systemRoleButtonMappedDTO.setMenuId(systemRoleButtonMappedVO.getMenuId());
		systemRoleButtonMappedDTO.setRoleId(systemRoleButtonMappedVO.getRoleId());
		systemRoleButtonMappedDTO.setMappedStatus(systemRoleButtonMappedVO.getMappedStatus());
		return this.systemRoleButtonMappedDaoImpl.selectBySelective(systemRoleButtonMappedDTO);
	}

	@Override
	public int updateSystemRoleButtonMapped(SystemRoleButtonMappedVO systemRoleButtonMappedVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
