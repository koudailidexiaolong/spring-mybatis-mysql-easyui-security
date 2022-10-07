package com.julongtech.system.dao;

import java.sql.SQLException;
import java.util.List;

import com.julongtech.system.dao.entity.SystemUserInfo;
import com.julongtech.system.service.dto.SystemUserDTO;

/**
 * 用户数据库操作类
 * @author julong
 * @date 2016-9-1 上午10:03:15
 */
public interface SystemUserDao {

	/**
	 * 查询对象集合信息的方法
	 * @param systemUserDTO 参数对象
	 * @return List<SystemUserDTO>
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:16:32
	 */
	public abstract List<SystemUserDTO> selectBySelective(SystemUserDTO systemUserDTO) throws SQLException;
	
	/**
	 * 新增对象的方法
	 * @param systemUserInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:06
	 */
	public abstract int insert(SystemUserInfo systemUserInfo) throws SQLException;
	
	/**
	 * 新增对象的方法
	 * @param systemUserInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:08
	 */
	public abstract int insertSelective(SystemUserInfo systemUserInfo) throws SQLException;
	
	/**
	 * 删除对象的方法
	 * @param userId 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:10
	 */
	public abstract int deleteByPrimaryKey(String userId) throws SQLException;
	
	/**
	 * 更新对象的方法
	 * @param systemUserInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKey(SystemUserInfo systemUserInfo) throws SQLException;
	
	/**
	 * 更新对象的方法
	 * @param systemUserInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKeySelective(SystemUserInfo systemUserInfo) throws SQLException;
	
	/**
	 * 查询单个对象的方法
	 * @param userId 参数对象
	 * @return SystemUserDTO
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:14
	 */
	public abstract SystemUserDTO selectByPrimaryKey(String userId) throws SQLException;
	
	/**
	 * 获取某一个数据信息
	 * @param systemUserDTO 参数对象
	 * @return Object
	 * @throws SQLException
	 * @author julong
	 * @date 2017-1-6 下午02:43:41
	 */
	public abstract Object selectCount(SystemUserDTO systemUserDTO) throws SQLException;
}
