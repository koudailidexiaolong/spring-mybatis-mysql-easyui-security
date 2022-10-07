package com.julongtech.system.manager;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemLoggerExceptionVO;
import com.julongtech.system.action.vo.SystemLoggerVO;
import com.julongtech.system.service.SystemLoggerExceptionService;
import com.julongtech.system.service.SystemLoggerService;
import com.julongtech.system.session.UserSession;
/**
 * 对日志接口进行统一的管理，使其他的类不用引用其日志类直接调用此类即可解决
 * @author julong
 * @date 2017-10-14 下午2:58:08
 */
@Service
@Scope("singleton")
public class SystemLoggerManager {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemLoggerManager.class);
	
	@Autowired
	private SystemLoggerService systemLoggerServiceImpl;
	
	@Autowired
	private SystemLoggerExceptionService systemLoggerExceptionServiceImpl;
	
	
	/**
	 * 新增日志接口
	 * @param systemLoggerVO
	 * @param userSession
	 * @return
	 * @author julong
	 * @date 2017-10-18 下午5:28:47
	 */
	public  int saveSystemLogger(SystemLoggerVO systemLoggerVO,UserSession userSession) {
		// TODO Auto-generated method stub
		logger.debug("【系统日志】-新增日志信息输入参数systemLoggerVO：{}",systemLoggerVO);
		int result = -1;
		try {
			result = this.systemLoggerServiceImpl.saveSystemLogger(systemLoggerVO,userSession);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("新增日志信息发生异常", e);
			return result;
		}
		return result;
	}
	
	/**
	 * 新增异常日志接口
	 * @param systemLoggerExptionVO
	 * @param userSession
	 * @return
	 * @author julong
	 * @date 2017-10-18 下午5:28:47
	 */
	public  int saveSystemLoggerException(SystemLoggerExceptionVO systemLoggerExceptionVO,UserSession userSession) {
		// TODO Auto-generated method stub
		logger.debug("【系统日志】-新增日志信息输入参数systemLoggerExptionVO：{}",systemLoggerExceptionVO);
		int result = -1;
		try {
			result = this.systemLoggerExceptionServiceImpl.saveSystemLoggerException(systemLoggerExceptionVO,userSession);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("新增日志信息发生异常", e);
			return result;
		}
		return result;
	}
	
	
	
	/**
	 * 生成序列信息的方法
	 * @param code 可以是组织机构 也可以是 表名首字母 此参数可传也可以不传
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午9:05:43
	 */
	public  String getSEQ(String code){
		logger.debug("【系统日志】-获取序列信息输入参数：{}",code);
		try {
			return systemLoggerServiceImpl.getSEQ(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("生成序列信息发生异常", e);
			return UUID.randomUUID().toString().replaceAll("-", "");
		}
	}
}
