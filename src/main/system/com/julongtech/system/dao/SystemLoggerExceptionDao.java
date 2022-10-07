package com.julongtech.system.dao;

import java.sql.SQLException;
import java.util.List;

import com.julongtech.system.dao.entity.SystemLoggerExceptionInfo;
import com.julongtech.system.service.dto.SystemLoggerExceptionDTO;

/**
 * 系统日子异常处理类
 * @author julong
 * @date 2017-10-16 下午4:37:26
 */
public interface SystemLoggerExceptionDao {
	

	/**
	 * 新增对象的方法
	 * @param systemLoggerExceptionInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:06
	 */
	public abstract int insert(SystemLoggerExceptionInfo systemLoggerExceptionInfo) throws SQLException;
	
	/**
	 * 新增对象的方法
	 * @param systemLoggerExceptionInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:08
	 */
	public abstract int insertSelective(SystemLoggerExceptionInfo systemLoggerExceptionInfo) throws SQLException;
	
	/**
	 * 删除对象的方法
	 * @param loggerExceptionId 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:10
	 */
	public abstract int deleteByPrimaryKey(String loggerExceptionId) throws SQLException;
	
	/**
	 * 更新对象的方法
	 * @param systemLoggerExceptionInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKeySelective(SystemLoggerExceptionInfo systemLoggerExceptionInfo) throws SQLException;
	
	
	/**
	 * 更新对象的方法
	 * @param systemLoggerExceptionInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKeyWithBLOBs(SystemLoggerExceptionInfo systemLoggerExceptionInfo) throws SQLException;
	
	/**
	 * 更新对象的方法
	 * @param systemLoggerExceptionInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKey(SystemLoggerExceptionInfo systemLoggerExceptionInfo) throws SQLException;
	
	/**
	 * 查询单个对象的方法
	 * @param loggerExceptionId 参数对象
	 * @return T
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:14
	 */
	public abstract SystemLoggerExceptionDTO selectByPrimaryKey(String loggerExceptionId) throws SQLException;
	
	/**
	 * 查询列表对象
	 * @param systemLoggerExceptionDTO 参数对象
	 * @return List<SystemLoggerExceptionDTO>
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:14
	 */
	public abstract List<SystemLoggerExceptionDTO> selectBySelective(SystemLoggerExceptionDTO systemLoggerExceptionDTO) throws SQLException;
	

}
