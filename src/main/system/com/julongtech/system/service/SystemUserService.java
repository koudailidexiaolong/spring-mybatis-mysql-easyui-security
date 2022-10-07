package com.julongtech.system.service;

import java.util.List;

import com.julongtech.system.action.vo.SystemUserVO;
import com.julongtech.system.service.dto.SystemUserDTO;
import com.julongtech.system.session.UserSession;

/**
 * 用户业务处理模块
 * @author julong
 * @date 2017-1-6 上午10:23:12
 */
public interface SystemUserService {
	
	/**
	 * 分页查询用户信息集合的方法
	 * @param systemUserVO 用户参数对象
	 * @param pageInfo 分页对象
	 * @return List<SystemUserDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:30:33
	 */
	public abstract List<SystemUserDTO> getUserListByPage(SystemUserVO systemUserVO) throws Exception;
	
	/**
	 * 新增用户信息的方法
	 * @param systemUserVO 用户参数对象 
	 * @param userSession
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:34:16
	 */
	public abstract int saveUser(SystemUserVO systemUserVO,UserSession userSession) throws Exception;
	
	/**
	 * 删除用户信息的方法
	 * @param systemUserVO 用户参数对象 
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:34:36
	 */
	public abstract int deleteUser(SystemUserVO systemUserVO) throws Exception;
	
	/**
	 * 修改用户信息的方法
	 * @param systemUserVO
	 * @param userSession
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:34:52
	 */
	public abstract int updateUser(SystemUserVO systemUserVO,UserSession userSession) throws Exception;
	
	/**
	 * 查询用户信息的方法
	 * @param systemUserVO
	 * @return SystemUserDTO
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:35:15
	 */
	public abstract SystemUserDTO getUser(SystemUserVO systemUserVO) throws Exception;
	
	/**
	 * 停用禁用用户信息的方法
	 * @param systemUserVO
	 * @param userSession
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:35:25
	 */
	public abstract int updateUserStatus(SystemUserVO systemUserVO,UserSession userSession) throws Exception;
	
	/**
	 * 更新用户密码信息的方法
	 * @param systemUserVO
	 * @param userSession
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:35:36
	 */
	public abstract int updatePassword(SystemUserVO systemUserVO,UserSession userSession) throws Exception;
	
	/**
	 * 重置用户密码信息的方法
	 * @param systemUserVO
	 * @param userSession
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:35:45
	 */
	public abstract int updateRestPassword(SystemUserVO systemUserVO,UserSession userSession) throws Exception;
	
	/**
	 * 批量导入数据的方法
	 * @param userList 用户参数
	 * @param userSession 
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-6 上午10:27:09
	 */
	public abstract int importExcelUser(List<SystemUserVO> userList,UserSession userSession) throws Exception;
	
	/**
	 * 批量删除数据的方法
	 * @param userList 用户参数
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-6 上午10:27:09
	 */
	public abstract int deleteUserList(List<SystemUserVO> userList) throws Exception;
	
	/**
	 * 校验用户密码的方法
	 * @param systemUserVO 用户参数
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:33:07
	 */
	public abstract int validatePassword(SystemUserVO systemUserVO) throws Exception;
	
	/**
	 * 统计用户
	 * @param systemUserVO 用户参数
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:33:07
	 */
	public abstract int countUser(SystemUserVO systemUserVO) throws Exception;
	
	/**
	 * 查询用户集合
	 * @param systemUserVO
	 * @return
	 * @throws Exception
	 * @author wangfy
	 * @date 2017-11-28 上午9:17:08
	 */
	public abstract List<SystemUserDTO> getUserList(SystemUserVO systemUserVO) throws Exception;
	
	
}
