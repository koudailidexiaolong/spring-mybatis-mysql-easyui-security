package com.julongtech.system.service;

import java.util.List;

import com.julongtech.system.action.vo.SystemDictionaryVO;
import com.julongtech.system.service.dto.SystemDictionaryDTO;
import com.julongtech.system.session.UserSession;

/**
 * 系统数据字典模块
 * @author julong
 * @date 2017-10-18 上午9:42:22
 */
public interface SystemDictionaryService {
	/**
	 * 获取数据字典的集合
	 * @param systemDictionaryVO 数据字典对象
	 * @param userInfo 当前用户对象
	 * @return List<SystemDictionaryDTO> 
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-18 下午02:02:23
	 */
	public abstract List<SystemDictionaryDTO> getSystemDictionaryList(SystemDictionaryVO systemDictionaryVO) throws Exception;
	
	/**
	 * 根据父级code获取数据字典的集合
	 * @param systemDictionaryVO 数据字典对象
	 * @param userInfo 当前用户对象
	 * @return List<SystemDictionaryDTO> 
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-18 下午02:02:23
	 */
	public abstract List<SystemDictionaryDTO> getSystemDictionaryByParentCode(SystemDictionaryVO systemDictionaryVO) throws Exception;
	/**
	 * 获取数据字典的集合
	 * @param systemDictionaryVO 数据字典对象
	 * @param userInfo 当前用户对象
	 * @return List<SystemDictionaryDTO> 
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-18 下午02:02:23
	 */
	public abstract List<SystemDictionaryDTO> getSystemDictionaryByType(SystemDictionaryVO systemDictionaryVO) throws Exception;
	
	/**
	 * 获取数据字典信息的方法
	 * @param systemDictionaryVO 数据字典对象
	 * @param userInfo 当前用户对象
	 * @return SystemDictionaryDTO
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-18 下午02:07:08
	 */
	public abstract SystemDictionaryDTO getSystemDictionary(SystemDictionaryVO systemDictionaryVO)throws Exception;
	
	/**
	 * 分页查询数据字典信息的方法
	 * @param systemDictionaryVO 数据字典对象
	 * @return List<SystemDictionaryDTO>
	 * @throws Exception
	 * @author julong
	 * @date 2016-7-18 下午02:07:44
	 */
	public abstract List<SystemDictionaryDTO> getSystemDictionaryListByPage(SystemDictionaryVO systemDictionaryVO)throws Exception;
	
	/**
	 * 新增数据字典信息的方法
	 * @param systemDictionaryVO 数据字典对象
	 * @param userSession 当前用户对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:50:42
	 */
	public abstract int saveSystemDictionary(SystemDictionaryVO systemDictionaryVO,UserSession userSession)throws Exception;
	
	/**
	 * 更新数据字典信息的方法
	 * @param systemDictionaryVO 数据字典对象
	 * @param userSession 当前用户对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:50:42
	 */
	public abstract int updateSystemDictionary(SystemDictionaryVO systemDictionaryVO,UserSession userSession)throws Exception;
	/**
	 * 更新数据字典信息状态的方法
	 * @param systemDictionaryVO 数据字典对象
	 * @param userSession 当前用户对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:50:42
	 */
	public abstract int updateSystemDictionaryStatus(SystemDictionaryVO systemDictionaryVO,UserSession userSession)throws Exception;
	
	/**
	 * 删除数据字典信息的方法
	 * @param systemDictionaryVO 数据字典对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:50:42
	 */
	public abstract int deleteSystemDictionary(SystemDictionaryVO systemDictionaryVO)throws Exception;
	/**
	 * 校验是否存在数据字典信息
	 * @param systemDictionaryVO 数据字典对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:50:42
	 */
	public abstract int validateSystemDictionary(SystemDictionaryVO systemDictionaryVO)throws Exception;
}
