package com.julongtech.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemLoggerExceptionVO;
import com.julongtech.system.dao.SystemLoggerExceptionDao;
import com.julongtech.system.dao.entity.SystemLoggerExceptionInfo;
import com.julongtech.system.service.SystemLoggerExceptionService;
import com.julongtech.system.service.dto.SystemLoggerExceptionDTO;
import com.julongtech.system.session.UserSession;
/**
 * 系统异常日志处理模块
 * @author julong
 * @date 2017-10-18 上午11:09:00
 */
@Service
public class SystemLoggerExceptionServiceImpl implements SystemLoggerExceptionService {

	private static final Logger logger = LoggerFactory.getLogger(SystemLoggerExceptionServiceImpl.class);
	
	@Autowired
	private SystemLoggerExceptionDao systemLoggerExceptionDaoImpl;
	
	@Override
	public int saveSystemLoggerException(SystemLoggerExceptionVO systemLoggerExceptionVO, UserSession userSession)throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【系统异常日志模块】-新增异常日志输入参数systemLoggerExceptionVO：{}",systemLoggerExceptionVO);
		int result = -1;
		SystemLoggerExceptionInfo systemLoggerExceptionInfo = new SystemLoggerExceptionInfo();
		systemLoggerExceptionInfo.setLoggerExceptionId(systemLoggerExceptionVO.getLoggerExceptionId());
		systemLoggerExceptionInfo.setLoggerExceptionIp(systemLoggerExceptionVO.getLoggerExceptionIp());
		systemLoggerExceptionInfo.setLoggerExceptionMethod(systemLoggerExceptionVO.getLoggerExceptionMethod());
		systemLoggerExceptionInfo.setLoggerExceptionModule(systemLoggerExceptionVO.getLoggerExceptionModule());
		systemLoggerExceptionInfo.setLoggerExceptionContext(systemLoggerExceptionVO.getLoggerExceptionContext());
		systemLoggerExceptionInfo.setLoggerExceptionDescription(systemLoggerExceptionVO.getLoggerExceptionDescription());
		systemLoggerExceptionInfo.setLoggerExceptionCreateTime(systemLoggerExceptionVO.getLoggerExceptionCreateTime());
		systemLoggerExceptionInfo.setLoggerExceptionType(systemLoggerExceptionVO.getLoggerExceptionType());
		systemLoggerExceptionInfo.setOrgId(systemLoggerExceptionVO.getOrgId());
		systemLoggerExceptionInfo.setUserId(systemLoggerExceptionVO.getUserId());
		systemLoggerExceptionInfo.setUserName(systemLoggerExceptionVO.getUserName());
		systemLoggerExceptionInfo.setLoggerResponseTime(systemLoggerExceptionVO.getLoggerResponseTime());
		systemLoggerExceptionInfo.setLoggerBrowserType(systemLoggerExceptionVO.getLoggerBrowserType());
		systemLoggerExceptionInfo.setLoggerBrowserVersion(systemLoggerExceptionVO.getLoggerBrowserVersion());
		systemLoggerExceptionInfo.setLoggerOperatingSystem(systemLoggerExceptionVO.getLoggerOperatingSystem());
		if(userSession != null){
			systemLoggerExceptionInfo.setUserId(userSession.getUserId());
			systemLoggerExceptionInfo.setUserName(userSession.getUsername());
			systemLoggerExceptionInfo.setOrgId(userSession.getOrgId());
		}
		logger.debug("【系统异常日志模块】-新增异常日志输入参数systemLoggerExceptionInfo：{}",systemLoggerExceptionInfo);
		result = this.systemLoggerExceptionDaoImpl.insertSelective(systemLoggerExceptionInfo);
		return result;
	}

	@Override
	public List<SystemLoggerExceptionDTO> getSystemLoggerExceptionListByPage(SystemLoggerExceptionVO systemLoggerExceptionVO,UserSession userSession)throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【系统日志】-分页查询日志信息输入参数systemLoggerExceptionVO:{}",systemLoggerExceptionVO);
		SystemLoggerExceptionDTO systemLogger = new SystemLoggerExceptionDTO();
		systemLogger.setLoggerExceptionType(systemLoggerExceptionVO.getLoggerExceptionType());
		systemLogger.setLoggerExceptionModule(systemLoggerExceptionVO.getLoggerExceptionModule());
		List<SystemLoggerExceptionDTO> list = this.systemLoggerExceptionDaoImpl.selectBySelective(systemLogger);
		return list;
	}

	@Override
	public int deleteSystemLoggerException(List<SystemLoggerExceptionVO> systemLoggerExceptionVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
