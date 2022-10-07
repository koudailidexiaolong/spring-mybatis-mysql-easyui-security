package com.julongtech.system.dao;

import java.sql.SQLException;
import java.util.List;

import com.julongtech.system.dao.entity.SystemMenuInfo;
import com.julongtech.system.service.dto.SystemMenuDTO;

/**
 * 菜单数据库交互类
 * @author julong
 * @date 2017-10-16 下午4:25:38
 */
public interface SystemMenuDao {

	/**
	 * 查询对象集合信息的方法
	 * @param sqlId mybatis 中的sqlId
	 * @param object 参数对象
	 * @return List<? extends Object>
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:16:32
	 */
	public abstract List<SystemMenuDTO> selectBySelective(SystemMenuDTO systemMenuDTO) throws SQLException;
	
	/**
	 * 新增对象的方法
	 * @param systemMenuInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:06
	 */
	public abstract int insertSelective(SystemMenuInfo systemMenuInfo) throws SQLException;
	
	/**
	 * 新增对象的方法
	 * @param systemMenuInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:08
	 */
	public abstract int insert(SystemMenuInfo systemMenuInfo) throws SQLException;
	
	/**
	 * 删除对象的方法
	 * @param menuId 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:10
	 */
	public abstract int deleteByPrimaryKey(String menuId) throws SQLException;
	
	/**
	 * 更新对象的方法
	 * @param systemMenuInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKeySelective(SystemMenuInfo systemMenuInfo) throws SQLException;
	
	/**
	 * 更新对象的方法
	 * @param systemMenuInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKey(SystemMenuInfo systemMenuInfo) throws SQLException;
	
	/**
	 * 查询单个对象的方法
	 * @param menuId 参数对象
	 * @return T
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:14
	 */
	public abstract SystemMenuDTO selectByPrimaryKey(String menuId) throws SQLException;
	
}
