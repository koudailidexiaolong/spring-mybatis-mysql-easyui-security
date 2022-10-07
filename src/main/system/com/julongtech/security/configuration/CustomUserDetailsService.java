package com.julongtech.security.configuration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.julongtech.system.dao.SystemUserDao;
import com.julongtech.system.dao.SystemUserRoleMappedDao;
import com.julongtech.system.service.dto.SystemUserDTO;
import com.julongtech.system.service.dto.SystemUserRoleMappedDTO;
import com.julongtech.system.session.UserSession;

/**
 * 自定义用户详情类
 * @author julong
 * @date 2020年4月4日 下午4:40:16
 * @desc 
 */
@Service(value="customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private SystemUserDao systemUserDaoImpl;
	
	@Autowired
	private SystemUserRoleMappedDao systemUserRoleMappedDaoImpl;
	
	//重写根据用户登录的方法
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		logger.info("【登录服务类】-获取用户登录系统用户信息-userId：{}",userId);
		SystemUserDTO systemUserDTO = null;
		try {
			systemUserDTO = this.systemUserDaoImpl.selectByPrimaryKey(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("【登录服务类】-查询信息发生异常：{}",e);
			throw new UsernameNotFoundException("查询用户信息发生异常"+userId+"的用户！");
		}
		if(systemUserDTO == null){
			logger.info("【登录服务类】-用户不存在-userId：{}",userId);
			throw new UsernameNotFoundException("没有查询到登录账户为"+userId+"的用户！");
		}
		//校验权限 查询当前用户拥有的角色权限
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//查询角色用户对应关系
		SystemUserRoleMappedDTO systemUserRoleMappedDTO = new SystemUserRoleMappedDTO();
		systemUserRoleMappedDTO.setUserId(userId);
		//角色集合
		List<SystemUserRoleMappedDTO> systemUserRoleMappedList = new ArrayList<SystemUserRoleMappedDTO>();
		try {
			systemUserRoleMappedList = this.systemUserRoleMappedDaoImpl.selectBySelective(systemUserRoleMappedDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("【登录服务类】-查询角色信息发生异常：{}",e);
			throw new UsernameNotFoundException("查询角色信息发生异常"+userId+"的用户！");
		}
		for(SystemUserRoleMappedDTO userRole : systemUserRoleMappedList){
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userRole.getRoleId()));
		}
		
//		for(UserRole userRole : userDTO.getUserRoles()){
//			authorities.add(new SimpleGrantedAuthority("ROLE_"+userRole.getRoleName()));
//		}
		
		UserSession userSession = new UserSession(systemUserDTO.getUserId(), systemUserDTO.getUserName(),systemUserDTO.getOrgId(), systemUserDTO.getUserPassword(), systemUserDTO.getUserSkin(), authorities);
		logger.info("【登录服务类】-获取用户登录系统用户信息-userSession：{}",userSession.toString());
		return userSession;
	}

}
