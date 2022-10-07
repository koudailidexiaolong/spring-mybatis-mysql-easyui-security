package com.julongtech.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemButtonVO;
import com.julongtech.system.dao.SystemButtonDao;
import com.julongtech.system.dao.entity.SystemButtonInfo;
import com.julongtech.system.service.SystemButtonService;
import com.julongtech.system.service.dto.SystemButtonDTO;
import com.julongtech.system.session.UserSession;
import com.julongtech.util.DateUtils;

/**
 * 按钮信息处理模块
 * @author julong
 * @date 2017-10-18 上午11:09:54
 */
@Service
public class SystemButtonServiceImpl implements SystemButtonService {

	private static final Logger logger = LoggerFactory.getLogger(SystemButtonServiceImpl.class);
	@Autowired
	private SystemButtonDao systemButtonDaoImpl;
	
	/**
	 * 查询所有的菜单按钮信息集合的方法
	 * @param systemButtonVO
	 * @param pageInfo
	 * @param userSession
	 * @return List<SystemButtonDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-18 上午9:53:18
	 */
	@Override
	public List<SystemButtonDTO> selectSystemButtonListByPage(SystemButtonVO systemButtonVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("分页查询按钮信息集合信息的方法");
		SystemButtonDTO systemButtonDTO = new SystemButtonDTO();
		systemButtonDTO.setButtonStatus(systemButtonVO.getButtonStatus());
		if(StringUtils.isNotBlank(systemButtonVO.getButtonName())){
			systemButtonDTO.setButtonName("%"+systemButtonVO.getButtonName()+"%");
		}
		List<SystemButtonDTO> list = this.systemButtonDaoImpl.selectBySelective(systemButtonDTO);
		return list;
	}

	/**
	 * 根据编号查询菜单按钮基本信息的方法
	 * @param systemButtonVO 菜单按钮对象
	 * @return SystemButtonDTO
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	@Override
	public SystemButtonDTO getSystemButton(SystemButtonVO systemButtonVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("查询按钮信息信息输入参数systemButtonVO：{}",systemButtonVO);
		SystemButtonDTO systemButtonDTO = (SystemButtonDTO) this.systemButtonDaoImpl.selectByPrimaryKey(systemButtonVO.getButtonId());
		return systemButtonDTO;
	}
	
	/**
	 * 根据编号查询菜单按钮基本信息的方法
	 * @param systemButtonVO 菜单按钮对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	@Override
	public int updateSystemButton(SystemButtonVO systemButtonVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【执行按钮模块】-更新按钮信息");
		SystemButtonInfo systemButtonInfo = new SystemButtonInfo();
		systemButtonInfo.setButtonId(systemButtonVO.getButtonId());
		systemButtonInfo.setButtonName(systemButtonVO.getButtonName());
		systemButtonInfo.setButtonCode(systemButtonVO.getButtonCode());
		systemButtonInfo.setButtonStatus(systemButtonVO.getButtonStatus());
		systemButtonInfo.setButtonUrl(systemButtonVO.getButtonUrl());
		systemButtonInfo.setMenuId(systemButtonVO.getMenuId());
		systemButtonInfo.setButtonUpdateTime(DateUtils.getTimestamp());
		systemButtonInfo.setButtonIco(systemButtonVO.getButtonIco());
		systemButtonInfo.setButtonOrder(systemButtonVO.getButtonOrder());
		result = this.systemButtonDaoImpl.updateByPrimaryKeySelective(systemButtonInfo);
		return result;
	}

	/**
	 * 根据编号查询菜单按钮基本信息的方法
	 * @param SystemButtonVO 菜单按钮对象
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-20 下午06:18:47
	 */
	@Override
	public int saveSystemButton(SystemButtonVO systemButtonVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("新增钮信息信息的方法");
		SystemButtonInfo systemButtonInfo = new SystemButtonInfo();
		systemButtonInfo.setButtonId(systemButtonVO.getButtonId());
		systemButtonInfo.setButtonName(systemButtonVO.getButtonName());
		systemButtonInfo.setButtonCode(systemButtonVO.getButtonCode());
		systemButtonInfo.setButtonStatus(systemButtonVO.getButtonStatus());
		systemButtonInfo.setButtonUrl(systemButtonVO.getButtonUrl());
		systemButtonInfo.setMenuId(systemButtonVO.getMenuId());
		systemButtonInfo.setButtonCreateTime(DateUtils.getTimestamp());
		systemButtonInfo.setButtonIco(systemButtonVO.getButtonIco());
		systemButtonInfo.setButtonOrder(systemButtonVO.getButtonOrder());
		if(null != userSession){
			systemButtonInfo.setUserId(systemButtonVO.getUserId());
		}
		result = this.systemButtonDaoImpl.insertSelective(systemButtonInfo);
		return result;
	}

	/**
	 * 禁用停用菜单按钮基本信息的方法
	 * @param SystemButtonVO
	 * @param userSession 当前登录用户
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-18 上午9:56:43
	 */
	@Override
	public int updateSystemButtonStatus(SystemButtonVO systemButtonVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("更新钮信息信息的方法");
		SystemButtonInfo systemButtonInfo = new SystemButtonInfo();
		systemButtonInfo.setButtonId(systemButtonVO.getButtonId());
		systemButtonInfo.setButtonStatus(systemButtonVO.getButtonStatus());
		systemButtonInfo.setButtonUpdateTime(DateUtils.getTimestamp());
		result = this.systemButtonDaoImpl.updateByPrimaryKeySelective(systemButtonInfo);
		return result;
	}

	/**
	 * 删除菜单基本按钮信息的方法
	 * @param systemButtonVO
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-18 上午9:56:36
	 */
	@Override
	public int deleteSystemButton(SystemButtonVO systemButtonVO) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("删除钮信息信息的方法");
		String buttonId = systemButtonVO.getButtonId();
		//删除按钮
		result = this.systemButtonDaoImpl.deleteByPrimaryKey(buttonId);
		return result;
	}

	/**
	 * 查询所有的菜单按钮信息集合的方法
	 * @param SystemButtonVO
	 * @return List<SystemButtonDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-18 上午9:56:26
	 */
	@Override
	public List<SystemButtonDTO> getSystemButtonList(SystemButtonVO systemButtonVO)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("查询按钮信息集合信息的方法");
		SystemButtonDTO systemButtonDTO = new SystemButtonDTO();
		systemButtonDTO.setButtonStatus(systemButtonVO.getButtonStatus());
		systemButtonDTO.setMenuId(systemButtonVO.getMenuId());
		List<SystemButtonDTO> list = this.systemButtonDaoImpl.selectBySelective(systemButtonDTO);
		return list;
	}
	
	/**
	 * 查询菜单按钮是否存在
	 * @param systemButtonVO 
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:33:07
	 */
	@Override
	public int validateButtonExist(SystemButtonVO systemButtonVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【按钮模块】-查询按钮是否存在输入参数systemButtonVO：{}",systemButtonVO);
		SystemButtonDTO systemButton = this.systemButtonDaoImpl.selectByPrimaryKey(systemButtonVO.getMenuId());
		if(null != systemButton){
			return 1;
		}
		return 0;
	}
}
