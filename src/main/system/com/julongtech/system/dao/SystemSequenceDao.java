package com.julongtech.system.dao;

import java.sql.SQLException;

/**
 * 按钮数据库交互类
 * @author julong
 * @date 2016-7-4 上午11:01:45
 */
public interface SystemSequenceDao {

	/**
	 * 查询单个对象的方法
	 * @param code 参数对象
	 * @return SystemUserDTO
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:14
	 */
	public abstract Object selectByPrimaryKey(String code) throws SQLException;
}
