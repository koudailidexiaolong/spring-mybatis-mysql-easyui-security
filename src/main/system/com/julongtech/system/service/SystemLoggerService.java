package com.julongtech.system.service;

import java.util.List;

import com.julongtech.system.action.vo.SystemLoggerVO;
import com.julongtech.system.service.dto.SystemLoggerDTO;
import com.julongtech.system.session.UserSession;

/**
 * 系统日志处理类
 * @author julong
 * @date 2017-10-14 下午2:57:45
 */
public interface SystemLoggerService {

	/**
	 * 新增系统日志信息的方法
	 * @param systemLoggerVO
	 * @param userSession
	 * @return
	 * @author julong
	 * @date 2017-10-17 下午7:40:18
	 */
	public abstract int saveSystemLogger(SystemLoggerVO systemLoggerVO,UserSession userSession) throws Exception;
	
	/**
	 * 分页查询系统日志信息的方法
	 * @param systemLoggerVO
	 * @param userSession
	 * @return List<SystemLoggerDTO>
	 * @author julong
	 * @date 2017-10-18 上午10:40:16
	 */
	public abstract List<SystemLoggerDTO> getSystemLoggerListByPage(SystemLoggerVO systemLoggerVO,UserSession userSession)throws Exception;
	
	/**
	 * 批量删除日志信息的方法
	 * @param systemLoggerVO
	 * @param userSession
	 * @return int
	 * @author julong
	 * @date 2017-10-18 上午10:40:59
	 */
	public abstract int deleteSystemLogger(List<SystemLoggerVO> systemLoggerVO,UserSession userSession)throws Exception;
	
	/**
	 * 获取自定义序列 
	 * @param type 可以是 机构号  也可以是字符 不超过 
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2017-10-24 下午8:40:17
	 */
	public abstract String getSEQ(String type)throws Exception;
}
