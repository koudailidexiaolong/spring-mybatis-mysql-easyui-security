package com.julongtech.system.service;

import java.util.List;

import com.julongtech.system.action.vo.SystemUserVO;
import com.julongtech.system.service.dto.SystemButtonDTO;
import com.julongtech.system.service.dto.SystemMenuDTO;

/**
 * 权限业务处理模块
 * @author julong
 * @date 2017-11-14 下午2:49:10
 */
public interface AuthorizeService {

	/**
	 * 查询用户权限的方法
	 * @param systemUserVO
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2017-11-14 下午2:58:53
	 */
	public abstract List<SystemMenuDTO> getUserMenu(SystemUserVO systemUserVO) throws Exception;
	
	/**
	 * 查询按钮权限信息
	 * @param systemUserVO
	 * @return  List<SystemButtonDTO> 
	 * @throws Exception
	 * @author julong
	 * @date 2018-6-13 下午4:59:03
	 */
	public abstract List<SystemButtonDTO> getUserMenuButton(SystemUserVO systemUserVO) throws Exception;
}
