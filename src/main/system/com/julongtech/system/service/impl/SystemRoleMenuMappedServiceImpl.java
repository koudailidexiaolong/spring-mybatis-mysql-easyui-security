package com.julongtech.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemRoleMenuMappedVO;
import com.julongtech.system.dao.SystemRoleButtonMappedDao;
import com.julongtech.system.dao.SystemRoleMenuMappedDao;
import com.julongtech.system.dao.entity.SystemRoleButtonMappedInfo;
import com.julongtech.system.dao.entity.SystemRoleMenuMappedInfo;
import com.julongtech.system.service.SystemRoleMenuMappedService;
import com.julongtech.system.service.dto.SystemRoleMenuMappedDTO;
import com.julongtech.system.session.UserSession;
import com.julongtech.system.util.DefaultUtil;
import com.julongtech.util.DateUtils;
/**
 * 角色菜单关系模块
 * @author julong
 * @date 2017-10-18 上午11:06:23
 */
@Service
public class SystemRoleMenuMappedServiceImpl implements	SystemRoleMenuMappedService {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemRoleMenuMappedServiceImpl.class);
	
	@Autowired
	private SystemRoleMenuMappedDao systemRoleMenuMappedDaoImpl;
	
	@Autowired
	private SystemRoleButtonMappedDao systemRoleButtonMappedDaoImpl;
	
	
	@Override
	public int saveSystemRoleMenuMappedList(List<SystemRoleMenuMappedVO> systemRoleMenuMappeds,	UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-批量新增角色菜单对应关系信息");
		int count = 0;
		//删除映射关系
		int mappedId = systemRoleMenuMappeds.get(0).getRoleId();
		int result = this.systemRoleMenuMappedDaoImpl.deleteByRoleId(mappedId);
		logger.debug("【角色菜单关系模块】-删除角色菜单关系执行结果result：{}",result);
		result = this.systemRoleButtonMappedDaoImpl.deleteByRoleId(mappedId);
		logger.debug("【角色菜单关系模块】-删除角色按钮关系执行结果result：{}",result);
		List<SystemRoleMenuMappedInfo> menuMappeds = new ArrayList<SystemRoleMenuMappedInfo>();
		List<SystemRoleButtonMappedInfo> buttonMappeds = new ArrayList<SystemRoleButtonMappedInfo>();
		for (SystemRoleMenuMappedVO systemRoleMenuMappedVO : systemRoleMenuMappeds) {
			SystemRoleMenuMappedInfo mapped = new SystemRoleMenuMappedInfo();
			mapped.setMappedId(DefaultUtil.DEFAULT_SEQUENCE);
			mapped.setMappedMenuArray(systemRoleMenuMappedVO.getMappedMenuArray());
			mapped.setMappedStatus(systemRoleMenuMappedVO.getMappedStatus());
			mapped.setMenuId(systemRoleMenuMappedVO.getMenuId());
			mapped.setRoleId(systemRoleMenuMappedVO.getRoleId());
			mapped.setMappedCreateTime(DateUtils.getTimestamp());
			//按钮数组 判断是否存在
			if(StringUtils.isNotBlank(systemRoleMenuMappedVO.getMappedMenuArray())){
				String[] buttonArray = StringUtils.split(systemRoleMenuMappedVO.getMappedMenuArray(), ",");
				for (int i = 0; i < buttonArray.length; i++) {
					//保存按钮组
					SystemRoleButtonMappedInfo buttonMapped = new SystemRoleButtonMappedInfo();
					buttonMapped.setButtonId(buttonArray[i]);
					buttonMapped.setMappedCreateTime(DateUtils.getTimestamp());
					buttonMapped.setMenuId(systemRoleMenuMappedVO.getMenuId());
					buttonMapped.setRoleId(systemRoleMenuMappedVO.getRoleId());
					buttonMapped.setMappedStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
					if(null != userSession){
						buttonMapped.setUserId(userSession.getUserId());
					}
					buttonMappeds.add(buttonMapped);
				}
			}
			if(null != userSession){
				mapped.setUserId(userSession.getUserId());
			}
			menuMappeds.add(mapped);
		}
		//批量新增角色菜单对应关系
		if(menuMappeds.size() >0){
			count = this.systemRoleMenuMappedDaoImpl.insertBatch(menuMappeds);
		}
		logger.debug("【角色菜单关系模块】-批量新增角色菜单关系结果count：{}",count);
		//批量新增角色菜单按钮关系
		if(buttonMappeds.size() >0){
			count = this.systemRoleButtonMappedDaoImpl.insertBatch(buttonMappeds);
		}
		logger.debug("【角色菜单关系模块】-批量新增角色按钮关系结果count：{}",count);
		return count;
	}

	@Override
	public int saveSystemRoleMenuMapped(SystemRoleMenuMappedVO systemRoleMenuMappedVO,
			UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-新增关系信息输入参数systemRoleMenuMappedVO：{}",systemRoleMenuMappedVO);
		int result = -1;
		SystemRoleMenuMappedInfo systemRoleMenuMappedInfo = new SystemRoleMenuMappedInfo();
		systemRoleMenuMappedInfo.setMappedId(DefaultUtil.DEFAULT_SEQUENCE);
		systemRoleMenuMappedInfo.setMappedStatus(systemRoleMenuMappedVO.getMappedStatus());
		systemRoleMenuMappedInfo.setMenuId(systemRoleMenuMappedVO.getMenuId());
		systemRoleMenuMappedInfo.setRoleId(systemRoleMenuMappedVO.getRoleId());
		systemRoleMenuMappedInfo.setMappedCreateTime(DateUtils.getTimestamp());
		logger.debug("【角色菜单关系模块】-新增关系信息输入参数systemRoleMenuMappedInfo：{}",systemRoleMenuMappedInfo);
		result = this.systemRoleMenuMappedDaoImpl.insertSelective(systemRoleMenuMappedInfo);
		return result;
	}

	@Override
	public int deleteSystemRoleMenuMapped(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-删除关系信息输入参数systemRoleMenuMappedVO：{}",systemRoleMenuMappedVO);
		int result = -1;
		int mappedId = systemRoleMenuMappedVO.getRoleId();
		result = this.systemRoleMenuMappedDaoImpl.deleteByRoleId(mappedId);
		logger.debug("【角色菜单关系模块】-删除角色菜单关系执行结果result：{}",result);
		result = this.systemRoleButtonMappedDaoImpl.deleteByRoleId(mappedId);
		logger.debug("【角色菜单关系模块】-删除角色按钮关系执行结果result：{}",result);
		return result;
	}
	
	/**
	 * 删除角色菜单按钮三者对应关系信息的方法
	 * @param SystemRoleMenuMapped 角色菜单关系信息对象
	 * @param userSession 用户对象
	 * @return  int 是否成功
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-28 上午09:19:09
	 */
	@Override
	public int deleteSystemRoleMenuButton(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-删除关系信息输入参数systemRoleMenuMappedVO：{}",systemRoleMenuMappedVO);
		int result = -1;
		int mappedId = systemRoleMenuMappedVO.getRoleId();
		result = this.systemRoleMenuMappedDaoImpl.deleteByRoleId(mappedId);
		logger.debug("【角色菜单关系模块】-删除角色菜单对应关系执行结果result：{}",result);
		result = this.systemRoleButtonMappedDaoImpl.deleteByRoleId(mappedId);
		logger.debug("【角色菜单关系模块】-删除角色按钮对应关系执行结果result：{}",result);
		return result;
	}

	@Override
	public int deleteSystemRoleMenuMappedList(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-批量删除关系信息");
//		this.systemRoleMenuMappedDaoImpl.deleteObject("com.julongtech.system.mapper.SystemRoleMenuMappedInfoMapper.", object);
		return 0;
	}

	@Override
	public int updateSystemRoleMenuMapped(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-更新关系信息");
		int result = -1;
		SystemRoleMenuMappedInfo systemRoleMenuMappedInfo = new SystemRoleMenuMappedInfo();
		this.systemRoleMenuMappedDaoImpl.updateByPrimaryKeySelective(systemRoleMenuMappedInfo);
		return result;
	}

	@Override
	public int updateSystemRoleMenuMappedList(List<SystemRoleMenuMappedVO> SystemRoleMenuMappeds,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-更新关系信息");
		int result = -1;
		SystemRoleMenuMappedInfo systemRoleMenuMappedInfo = new SystemRoleMenuMappedInfo();
		result = this.systemRoleMenuMappedDaoImpl.updateByPrimaryKeySelective(systemRoleMenuMappedInfo);
		return result;
	}

	@Override
	public int updateSystemRoleMenuMappedStatus(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-修改关系信息");
		int result = -1;
		SystemRoleMenuMappedInfo systemRoleMenuMappedInfo = new SystemRoleMenuMappedInfo();
		result = this.systemRoleMenuMappedDaoImpl.updateByPrimaryKeySelective(systemRoleMenuMappedInfo);
		return result;
	}

	@Override
	public SystemRoleMenuMappedDTO getSystemRoleMenuMapped(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-查询关系信息");
		SystemRoleMenuMappedDTO systemRoleMenuMappedDTO = this.systemRoleMenuMappedDaoImpl.selectByPrimaryKey(systemRoleMenuMappedVO.getRoleId());
		return systemRoleMenuMappedDTO;
	}

	@Override
	public List<SystemRoleMenuMappedDTO> getSystemRoleMenuMappedList(SystemRoleMenuMappedVO systemRoleMenuMappedVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-查询集合关系信息输入参数systemRoleMenuMappedVO：{}",systemRoleMenuMappedVO);
		SystemRoleMenuMappedDTO systemRoleMenuMappedDTO = new SystemRoleMenuMappedDTO();
		systemRoleMenuMappedDTO.setRoleId(systemRoleMenuMappedVO.getRoleId());
		List<SystemRoleMenuMappedDTO> list = this.systemRoleMenuMappedDaoImpl.selectBySelective(systemRoleMenuMappedDTO);
		return list;
	}

	@Override
	public List<SystemRoleMenuMappedDTO> getSystemRoleMenuMappedListByPage(SystemRoleMenuMappedVO systemRoleMenuMappedVO, UserSession userSession)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【角色菜单关系模块】-分页查询关系信息");
		SystemRoleMenuMappedDTO systemRoleMenuMappedDTO = new SystemRoleMenuMappedDTO();
		List<SystemRoleMenuMappedDTO> list = this.systemRoleMenuMappedDaoImpl.selectBySelective(systemRoleMenuMappedDTO);
		return list;
	}

}
