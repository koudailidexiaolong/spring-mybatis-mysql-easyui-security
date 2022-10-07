package com.julongtech.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemUserVO;
import com.julongtech.system.dao.SystemUserDao;
import com.julongtech.system.dao.entity.SystemUserInfo;
import com.julongtech.system.service.SystemUserService;
import com.julongtech.system.service.dto.SystemUserDTO;
import com.julongtech.system.session.UserSession;
import com.julongtech.util.DateUtils;
import com.julongtech.util.PropertyPlaceholderUtils;
import com.julongtech.util.crypto.DESUtil;
import com.julongtech.util.crypto.RSAUtil;

/**
 * 用户业务处理模块
 * @author julong
 * @date 2017-1-6 上午10:29:53
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

	private static final Logger logger = LoggerFactory.getLogger(SystemUserServiceImpl.class);

	@Autowired
	private SystemUserDao systemUserDaoImpl;

	/**
	 * 删除单条用户信息的方法
	 * @param systemUserVO 用户参数对象 
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:34:36
	 */
	@Override
	public int deleteUser(SystemUserVO systemUserVO) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【用户模块】-删除用户输入参数systemUserVO:{}",systemUserVO);
		//删除用户
		result = this.systemUserDaoImpl.deleteByPrimaryKey(systemUserVO.getUserId());
		logger.debug("【用户模块】-删除用户结果result:{}",result);
		return result;
	}

	/**
	 * 分页查询用户信息集合的方法
	 * @param systemUserVO 用户参数对象
	 * @param pageInfo 分页对象
	 * @return List<SystemUserDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:30:33
	 */
	@Override
	public List<SystemUserDTO> getUserListByPage(SystemUserVO systemUserVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户模块】-查询用户信息集合的方法systemUserVO：{}",systemUserVO);
		SystemUserDTO systemUserDTO = new SystemUserDTO();
		systemUserDTO.setUserId(systemUserVO.getUserId());
		//姓名模糊查询
		if(StringUtils.isNotEmpty(systemUserVO.getUserName())){
			systemUserDTO.setUserName("%"+systemUserVO.getUserName()+"%");
		}
		systemUserDTO.setUserSex(systemUserVO.getUserSex());
		systemUserDTO.setOrgId(systemUserVO.getOrgId());
		systemUserDTO.setUserIdentity(systemUserVO.getUserIdentity());
		systemUserDTO.setUserLevel(systemUserVO.getUserLevel());
		if(StringUtils.isNotEmpty(systemUserVO.getBeginDate())){
			systemUserDTO.setBeginDate(DateUtils.parseBeginTime(systemUserVO.getBeginDate()));
		}
		if(StringUtils.isNotEmpty(systemUserVO.getEndDate())){
			systemUserDTO.setEndDate(DateUtils.parseEndTime(systemUserVO.getEndDate()));
		}
		systemUserDTO.setUserStatus(systemUserVO.getUserStatus());
		logger.debug("【用户模块】-查询用户信息集合的方法systemUserDTO：{}",systemUserDTO);
		//输入参数
		List<SystemUserDTO> list = this.systemUserDaoImpl.selectBySelective(systemUserDTO);
		return list;
	}

	/**
	 * 新增用户信息的方法
	 * @param systemUserVO 用户参数对象 
	 * @param userSession
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:34:16
	 */
	@Override
	public int saveUser(SystemUserVO systemUserVO, UserSession userSession)	throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【用户模块】-新增用户输入参数systemUserVO：{}",systemUserVO);
		SystemUserInfo systemUserInfo = new SystemUserInfo();
		systemUserInfo.setUserId(systemUserVO.getUserId());
		systemUserInfo.setUserAddress(systemUserVO.getUserAddress());
		systemUserInfo.setOrgId(systemUserVO.getOrgId());
		systemUserInfo.setUserAge(systemUserVO.getUserAge());
		systemUserInfo.setUserIdentity(systemUserVO.getUserIdentity());
		systemUserInfo.setUserName(systemUserVO.getUserName());
		systemUserInfo.setUserPhone(systemUserVO.getUserPhone());
		systemUserInfo.setUserMail(systemUserVO.getUserMail());
		systemUserInfo.setUserImage(systemUserVO.getUserImage());
		systemUserInfo.setUserLevel(systemUserVO.getUserLevel());
		systemUserInfo.setUserSkin(systemUserVO.getUserSkin());
		systemUserInfo.setUserSex(systemUserVO.getUserSex());
		if(null != userSession){
			systemUserInfo.setUserCreateUserId(userSession.getUserId());
		}
		//固定参数配置 此三项可以不用写 数据库采用默认值
		systemUserInfo.setUserCreateTime(DateUtils.getTimestamp());
		systemUserInfo.setUserUpdatePasswordTime(DateUtils.getTimestamp());
		systemUserInfo.setUserStatus(systemUserVO.getUserStatus());
		systemUserInfo.setUserPassword(DESUtil.encrypt("000000", PropertyPlaceholderUtils.getInstance().getProperty("USERKEY")));
		logger.debug("【用户模块】-新增用户输入参数systemUserInfo：{}",systemUserInfo);
		result = this.systemUserDaoImpl.insertSelective(systemUserInfo);
		logger.debug("【用户模块】-新增用户结果result：{}",result);
		return result;
	}

	/**
	 * 修改用户信息的方法
	 * @param systemUserVO
	 * @param userSession
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:34:52
	 */
	@Override
	public int updateUser(SystemUserVO systemUserVO, UserSession userSession)	throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【用户模块】-更新用户信息输入参数systemUserVO：{}",systemUserVO);
		SystemUserInfo systemUserInfo = new SystemUserInfo();
		systemUserInfo.setUserId(systemUserVO.getUserId());
		systemUserInfo.setUserName(systemUserVO.getUserName());
		systemUserInfo.setUserAge(systemUserVO.getUserAge());
		systemUserInfo.setUserId(systemUserVO.getUserId());
		systemUserInfo.setUserAddress(systemUserVO.getUserAddress());
		systemUserInfo.setOrgId(systemUserVO.getOrgId());
		systemUserInfo.setUserAge(systemUserVO.getUserAge());
		systemUserInfo.setUserIdentity(systemUserVO.getUserIdentity());
		systemUserInfo.setUserName(systemUserVO.getUserName());
		systemUserInfo.setUserPhone(systemUserVO.getUserPhone());
		systemUserInfo.setUserMail(systemUserVO.getUserMail());
		systemUserInfo.setUserImage(systemUserVO.getUserImage());
		systemUserInfo.setUserLevel(systemUserVO.getUserLevel());
		systemUserInfo.setUserSkin(systemUserVO.getUserSkin());
		systemUserInfo.setUserStatus(systemUserVO.getUserStatus());
		if(null != userSession){
			systemUserInfo.setUserUpdateUserId(userSession.getUserId());
		}
		//更新的参数
		systemUserInfo.setUserUpdateTime(DateUtils.getTimestamp());
		logger.debug("【用户模块】-更新用户信息输入参数systemUserInfo：{}",systemUserInfo);
		result = this.systemUserDaoImpl.updateByPrimaryKeySelective(systemUserInfo);
		logger.debug("【用户模块】-更新用户信息结果result：{}",result);
		return result;
	}

	/**
	 * 查询用户信息的方法
	 * @param systemUserVO
	 * @return SystemUserDTO
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:35:15
	 */
	@Override
	public SystemUserDTO getUser(SystemUserVO systemUserVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户模块】-查询用户信息输入参数systemUserVO：{}",systemUserVO);
		SystemUserDTO systemUserDTO = (SystemUserDTO) this.systemUserDaoImpl.selectByPrimaryKey(systemUserVO.getUserId());
		return systemUserDTO;
	}

	/**
	 * 停用禁用用户信息的方法
	 * @param systemUserVO
	 * @param userSession
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:35:25
	 */
	@Override
	public int updateUserStatus(SystemUserVO systemUserVO, UserSession userSession)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户模块】-停用禁用用户信息输入参数systemUserVO:{}",systemUserVO);
		int result = -1;
		SystemUserInfo systemUserInfo = new SystemUserInfo();
		systemUserInfo.setUserId(systemUserVO.getUserId());
		systemUserInfo.setUserStatus(systemUserVO.getUserStatus());
		if(null != userSession){
			systemUserInfo.setUserUpdateUserId(userSession.getUserId());
		}
		//更新的参数
		systemUserInfo.setUserUpdateTime(DateUtils.getTimestamp());
		logger.debug("【用户模块】-停用禁用用户信息输入参数systemUserInfo:{}",systemUserInfo);
		//更新条件参数
		result = this.systemUserDaoImpl.updateByPrimaryKeySelective(systemUserInfo);
		logger.debug("【用户模块】-停用禁用用户信息结果result:{}",result);
		return result;
	}

	/**
	 * 更新用户密码信息的方法
	 * @param systemUserVO
	 * @param userSession
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:35:36
	 */
	@Override
	public int updatePassword(SystemUserVO systemUserVO, UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户模块】-更新用户密码信息输入参数systemUserVO:{}",systemUserVO);
		int result = -1;
		String userPassword = RSAUtil.getInstance().privateDecrypt(Base64.decodeBase64(systemUserVO.getUserPassword()));
		SystemUserInfo systemUserInfo = new SystemUserInfo();
		systemUserInfo.setUserPassword(DESUtil.encrypt(userPassword, PropertyPlaceholderUtils.getInstance().getProperty("USERKEY")));
		systemUserInfo.setUserId(systemUserVO.getUserId());
		systemUserInfo.setUserUpdatePasswordTime(DateUtils.getTimestamp());
		if(null != userSession){
			systemUserInfo.setUserUpdateUserId(userSession.getUserId());
		}
		//更新的参数
		systemUserInfo.setUserUpdateTime(DateUtils.getTimestamp());
		logger.debug("【用户模块】-更新用户密码信息输入参数systemUserInfo:{}",systemUserInfo);
		result = this.systemUserDaoImpl.updateByPrimaryKeySelective(systemUserInfo);
		logger.debug("【用户模块】-更新用户密码结果result:{}",result);
		return result;
	}

	/**
	 * 重置用户密码信息的方法
	 * @param systemUserVO
	 * @param userSession
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:35:45
	 */
	@Override
	public int updateRestPassword(SystemUserVO systemUserVO, UserSession userSession)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户模块】-重置用户密码信息输入参数systemUserVO:{}",systemUserVO);
		int result = -1;
		SystemUserInfo systemUserInfo = new SystemUserInfo();
		systemUserInfo.setUserId(systemUserVO.getUserId());
		systemUserInfo.setUserPassword(DESUtil.encrypt("000000", PropertyPlaceholderUtils.getInstance().getProperty("USERKEY")));
		systemUserInfo.setUserUpdatePasswordTime(DateUtils.getTimestamp());
		if(null != userSession){
			systemUserInfo.setUserUpdateUserId(userSession.getUserId());
		}
		logger.debug("【用户模块】-重置用户密码信息输入参数systemUserInfo:{}",systemUserInfo);
		result = this.systemUserDaoImpl.updateByPrimaryKeySelective(systemUserInfo);
		logger.debug("【用户模块】-重置用户密码结果result:{}",result);
		return result;
	}

	@Override
	public int importExcelUser(List<SystemUserVO> userList,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【用户模块】-批量新增用户信息的开始...");
		return result;
	}

	/**
	 * 批量删除数据的方法
	 * @param userList 用户参数
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-6 上午10:27:09
	 */
	@Override
	public int deleteUserList(List<SystemUserVO> userList) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		logger.debug("【用户模块】-删除用户信息的开始...");
		for (SystemUserVO user : userList) {
			//删除用户
			result = this.deleteUser(user);
			result ++;
		}
		return result;
	}

	/**
	 * 校验用户密码的方法
	 * @param systemUserVO 用户参数
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:33:07
	 */
	@Override
	public int validatePassword(SystemUserVO systemUserVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户模块】-校验用户密码信息输入参数systemUserVO:{}",systemUserVO);
		int result = -1;
		String userId = systemUserVO.getUserId();
		SystemUserDTO systemUser = (SystemUserDTO) this.systemUserDaoImpl.selectByPrimaryKey(userId);
		if(null != systemUser){
			String userPassword = RSAUtil.getInstance().privateDecrypt(Base64.decodeBase64(systemUserVO.getUserPassword()));
			String password = DESUtil.encrypt(userPassword, PropertyPlaceholderUtils.getInstance().getProperty("USERKEY"));
			//判断是否相等
			if(systemUser.getUserPassword().equals(password)){
				result = 1;
			}
		}
		logger.debug("【用户模块】-校验用户密码执行结果result:{}",result);
		return result;
	}

	/**
	 * 统计用户
	 * @param systemUserVO 用户参数
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-17 下午7:33:07
	 */
	@Override
	public int countUser(SystemUserVO systemUserVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户模块】-统计用户是否存在输入参数systemUserVO:{}",systemUserVO);
		int result = -1;
		SystemUserDTO systemUserDTO = new SystemUserDTO();
		systemUserDTO.setUserId(systemUserVO.getUserId());
		Object obj = this.systemUserDaoImpl.selectCount(systemUserDTO);
		if(null != obj){
			return Integer.valueOf(obj+"");
		}
		return result;
	}

	@Override
	public List<SystemUserDTO> getUserList(SystemUserVO systemUserVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【用户模块】-查询用户信息集合的输入参数systemUserVO：{}",systemUserVO);
		SystemUserDTO systemUserDTO = new SystemUserDTO();
		systemUserDTO.setUserId(systemUserVO.getUserId());
		if(StringUtils.isNotEmpty(systemUserVO.getUserName())){
			systemUserDTO.setUserName("%"+systemUserVO.getUserName()+"%");
		}
		systemUserDTO.setUserStatus(systemUserVO.getUserStatus());
		systemUserDTO.setOrgId(systemUserVO.getOrgId());
		systemUserDTO.setUserIdentity(systemUserVO.getUserIdentity());
		systemUserDTO.setUserLevel(systemUserVO.getUserLevel());
		logger.debug("【用户模块】-查询用户信息集合的输入参数systemUserVO：{}",systemUserVO);
		//输入参数
		List<SystemUserDTO> list = this.systemUserDaoImpl.selectBySelective(systemUserDTO);
		return list;
	}



}
