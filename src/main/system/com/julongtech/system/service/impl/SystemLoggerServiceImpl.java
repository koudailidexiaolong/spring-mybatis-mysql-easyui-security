package com.julongtech.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemLoggerVO;
import com.julongtech.system.dao.SystemLoggerDao;
import com.julongtech.system.dao.SystemSequenceDao;
import com.julongtech.system.dao.entity.SystemLoggerInfo;
import com.julongtech.system.service.SystemLoggerService;
import com.julongtech.system.service.dto.SystemLoggerDTO;
import com.julongtech.system.session.UserSession;
import com.julongtech.util.DateUtils;

/**
 * 系统日志业务类
 * @author julong
 * @date 2017-10-14 下午2:57:45
 */
@Service
public class SystemLoggerServiceImpl implements SystemLoggerService {

	private static final Logger logger = LoggerFactory.getLogger(SystemLoggerServiceImpl.class);
	@Autowired
	private SystemLoggerDao systemLoggerDaoImpl;
	@Autowired
	private SystemSequenceDao systemSequenceDaoImpl;
	
	/**
	 * 新增系统日志信息的方法
	 * @param systemLoggerVO
	 * @param userSession
	 * @return
	 * @author julong
	 * @date 2017-10-17 下午7:40:18
	 */
	@Override
	public int saveSystemLogger(SystemLoggerVO systemLoggerVO,UserSession userSession) throws Exception{
		// TODO Auto-generated method stub
		logger.debug("【系统日志】-新增日志信息输入参数systemLoggerVO：{}",systemLoggerVO);
		int result = -1;
		SystemLoggerInfo systemLoggerInfo = new SystemLoggerInfo();
		systemLoggerInfo.setLoggerId(systemLoggerVO.getLoggerId());
		systemLoggerInfo.setLoggerIp(systemLoggerVO.getLoggerIp());
		systemLoggerInfo.setLoggerType(systemLoggerVO.getLoggerType());
		systemLoggerInfo.setLoggerTypeName(systemLoggerVO.getLoggerTypeName());
		systemLoggerInfo.setLoggerModule(systemLoggerVO.getLoggerModule());
		systemLoggerInfo.setLoggerModuleMethod(systemLoggerVO.getLoggerModuleMethod());
		systemLoggerInfo.setLoggerResponseTime(systemLoggerVO.getLoggerResponseTime());
		systemLoggerInfo.setLoggerBrowserType(systemLoggerVO.getLoggerBrowserType());
		systemLoggerInfo.setLoggerBrowserVersion(systemLoggerVO.getLoggerBrowserVersion());
		systemLoggerInfo.setLoggerOperatingSystem(systemLoggerVO.getLoggerOperatingSystem());
		systemLoggerInfo.setLoggerDescription(systemLoggerVO.getLoggerDescription());
		systemLoggerInfo.setLoggerCreateTime(DateUtils.getTimestamp());
		if(null != userSession){
			systemLoggerInfo.setUserId(userSession.getUserId());
			systemLoggerInfo.setUserName(userSession.getUsername());
		}
		logger.debug("【系统日志】-新增日志信息输入参数systemLoggerInfo：{}",systemLoggerInfo);
		result = this.systemLoggerDaoImpl.insertSelective(systemLoggerInfo);
		logger.debug("【系统日志】-新增日志信息执行结果result：{}",result);
		return result;
	}

	/**
	 * 分页查询系统日志信息的方法
	 * @param systemLoggerVO
	 * @param userSession
	 * @return List<SystemLoggerDTO>
	 * @author julong
	 * @date 2017-10-18 上午10:40:16
	 */
	@Override
	public List<SystemLoggerDTO> getSystemLoggerListByPage(SystemLoggerVO systemLoggerVO,
			UserSession userSession)  throws Exception{
		// TODO Auto-generated method stub
		logger.debug("【系统日志】-分页查询日志信息输入参数systemLoggerVO:{}",systemLoggerVO);
		SystemLoggerDTO systemLogger = new SystemLoggerDTO();
		systemLogger.setLoggerType(systemLoggerVO.getLoggerType());
		systemLogger.setLoggerModule(systemLoggerVO.getLoggerModule());
		List<SystemLoggerDTO> list = this.systemLoggerDaoImpl.selectBySelective(systemLogger);
		return list;
	}

	@Override
	public int deleteSystemLogger(List<SystemLoggerVO> systemLoggerVO,UserSession userSession) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 获取自定义序列 
	 * @param code 可以是机构号、或者表名的首字母、能够标示此条数据的 例如user表  U 、waiter表  W  
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-24 下午8:40:17
	 */
	@Override
	public String getSEQ(String code) throws Exception {
		// TODO Auto-generated method stub
		Object obj = this.systemSequenceDaoImpl.selectByPrimaryKey(code);
		return obj.toString();
	}
	
}
