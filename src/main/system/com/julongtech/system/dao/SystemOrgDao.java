package com.julongtech.system.dao;

import java.sql.SQLException;
import java.util.List;

import com.julongtech.system.dao.entity.SystemOrgInfo;
import com.julongtech.system.service.dto.SystemOrgDTO;
/**
 * 组织机构数据库操作类
 * @author julong
 * @date 2016-10-12 上午11:58:01
 */
public interface SystemOrgDao {
	
	/**
	 * 查询单个对象的方法
	 * @param orgId 参数对象
	 * @return SystemOrgDTO
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:14
	 */
	public abstract SystemOrgDTO selectByPrimaryKey(String orgId) throws SQLException;
	/**
	 * 查询对象集合信息的方法
	 * @param systemOrgDTO 参数对象
	 * @return List<SystemOrgDTO>
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:16:32
	 */
	public abstract List<SystemOrgDTO> selectBySelective(SystemOrgDTO systemOrgDTO) throws SQLException;
	
	/**
	 * 查询等级
	 * @param systemOrgDTO 参数对象
	 * @param pageInfo 分页对象
	 * @return List<SystemOrgDTO>
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:17:24
	 */
	public abstract List<SystemOrgDTO> selectByLevel(SystemOrgDTO systemOrgDTO) throws SQLException;

	
	/**
	 * 新增对象的方法
	 * @param systemOrgInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:08
	 */
	public abstract int insert(SystemOrgInfo systemOrgInfo) throws SQLException;
	
	/**
	 * 新增对象的方法
	 * @param systemOrgInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:08
	 */
	public abstract int insertSelective(SystemOrgInfo systemOrgInfo) throws SQLException;
	
	/**
	 * 删除对象的方法
	 * @param orgId 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:10
	 */
	public abstract int deleteByPrimaryKey(String orgId) throws SQLException;
	
	/**
	 * 更新对象的方法
	 * @param systemOrgInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKeySelective(SystemOrgInfo systemOrgInfo) throws SQLException;
	
	/**
	 * 更新对象的方法
	 * @param systemOrgInfo 参数对象
	 * @return -1 失败  0 sql执行0行受影响  大于0成功
	 * @throws SQLException
	 * @author julong
	 * @date 2016-7-1 下午01:25:12
	 */
	public abstract int updateByPrimaryKey(SystemOrgInfo systemOrgInfo) throws SQLException;
	
	/**
	 * 获取某一个数据信息
	 * @param systemOrgDTO 参数对象
	 * @return Object
	 * @throws SQLException
	 * @author julong
	 * @date 2017-1-6 下午02:43:41
	 */
	public abstract Object selectCount(SystemOrgDTO systemOrgDTO) throws SQLException;
}
