package com.julongtech.system.dao;

import java.sql.SQLException;
import java.util.List;

import com.julongtech.system.dao.entity.SystemButtonInfo;
import com.julongtech.system.service.dto.SystemButtonDTO;

/**
 * 按钮数据库交互类
 * @author julong
 * @date 2016-7-4 上午11:01:45
 */
public interface SystemButtonDao {

	/**
	 * 查询对象集合信息的方法
	 * @param systemButtonDTO 参数对象
	 * @return List<? extends Object>
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:16:32
	 */
	public abstract List<SystemButtonDTO> selectBySelective(SystemButtonDTO systemButtonDTO) throws SQLException;

	
	/**
	 * 新增对象的方法
	 * @param systemButtonInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:08
	 */
	public abstract int insertSelective(SystemButtonInfo systemButtonInfo) throws SQLException;
	
	/**
	 * 删除对象的方法
	 * @param buttonId 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:10
	 */
	public abstract int deleteByPrimaryKey(String buttonId) throws SQLException;
	
	/**
	 * 更新对象的方法
	 * @param systemButtonInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKeySelective(SystemButtonInfo systemButtonInfo) throws SQLException;
	
	/**
	 * 更新对象的方法
	 * @param systemButtonInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKey(SystemButtonInfo systemButtonInfo) throws SQLException;
	
	/**
	 * 查询单个对象的方法
	 * @param buttonId 参数对象
	 * @return T
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:14
	 */
	public abstract SystemButtonDTO selectByPrimaryKey(String buttonId) throws SQLException;
}
