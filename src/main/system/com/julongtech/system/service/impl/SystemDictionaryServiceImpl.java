package com.julongtech.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julongtech.system.action.vo.SystemDictionaryVO;
import com.julongtech.system.dao.SystemDictionaryDao;
import com.julongtech.system.dao.entity.SystemDictionaryInfo;
import com.julongtech.system.manager.RedisCacheManager;
import com.julongtech.system.service.SystemDictionaryService;
import com.julongtech.system.service.dto.SystemDictionaryDTO;
import com.julongtech.system.session.UserSession;
import com.julongtech.system.util.DefaultUtil;
import com.julongtech.util.DateUtils;
/**
 * 数据字典信息模块
 * @author julong
 * @date 2017-10-18 上午11:09:38
 */
@Service
public class SystemDictionaryServiceImpl implements SystemDictionaryService {

	private static final Logger logger = LoggerFactory.getLogger(SystemDictionaryServiceImpl.class);
	@Autowired
	private SystemDictionaryDao systemDictionaryDaoImpl;

	@Autowired
	private RedisCacheManager redisCacheManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemDictionaryDTO> getSystemDictionaryList(SystemDictionaryVO systemDictionaryVO)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【数据字典】-查询数据信息集合输入参数systemDictionaryVO：{}",systemDictionaryVO);
		SystemDictionaryDTO systemDictionaryDTO = new SystemDictionaryDTO();
		systemDictionaryDTO.setDictionaryId(systemDictionaryVO.getDictionaryId());
		systemDictionaryDTO.setDictionaryCode(systemDictionaryVO.getDictionaryCode());
		systemDictionaryDTO.setDictionaryParentCode(systemDictionaryVO.getDictionaryParentCode());
		systemDictionaryDTO.setDictionaryType(systemDictionaryVO.getDictionaryType());
		systemDictionaryDTO.setDictionaryStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
		List<SystemDictionaryDTO> dataList = null;
		//加入缓存
		if(null != systemDictionaryVO && null != systemDictionaryVO.getDictionaryType()){
			if(this.redisCacheManager.existList(systemDictionaryVO.getDictionaryType())){
				logger.debug("【数据字典】-从缓存中取值{}",systemDictionaryVO.getDictionaryType());
				dataList = (List<SystemDictionaryDTO>) this.redisCacheManager.getCacheList(systemDictionaryVO.getDictionaryType());
			}else{
				dataList = this.systemDictionaryDaoImpl.selectBySelective(systemDictionaryDTO);
				this.redisCacheManager.setCacheList(systemDictionaryVO.getDictionaryType(), dataList);
			}
		}
		logger.debug("【数据字典】-查询数据信息集合返回结果dataList：{}",dataList);
		return dataList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemDictionaryDTO> getSystemDictionaryByParentCode(SystemDictionaryVO systemDictionaryVO)
			throws Exception {
		logger.debug("【数据字典】-查询数据信息集合输入参数systemDictionaryVO：{}",systemDictionaryVO);
		SystemDictionaryDTO systemDictionaryDTO = new SystemDictionaryDTO();
		systemDictionaryDTO.setDictionaryParentCode(systemDictionaryVO.getDictionaryParentCode());
		systemDictionaryDTO.setDictionaryStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
		List<SystemDictionaryDTO> dataList = new ArrayList<SystemDictionaryDTO>();
		//加入缓存
		if(null != systemDictionaryVO){
			if(this.redisCacheManager.existList(systemDictionaryVO.getDictionaryParentCode())){
				logger.debug("【数据字典】-从缓存中取值{parentCode}",systemDictionaryVO.getDictionaryParentCode());
				dataList = (List<SystemDictionaryDTO>) this.redisCacheManager.getCacheList(systemDictionaryVO.getDictionaryParentCode());
			}else{
				dataList = this.systemDictionaryDaoImpl.selectBySelective(systemDictionaryDTO);
				this.redisCacheManager.setCacheList(systemDictionaryVO.getDictionaryParentCode(), dataList);
			}
		}
		logger.debug("【数据字典】-查询数据信息集合{}",dataList);
		return dataList;
	}

	@Override
	public List<SystemDictionaryDTO> getSystemDictionaryByType(SystemDictionaryVO systemDictionaryVO)	throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【数据字典】-查询数据信息集合输入参数systemDictionaryVO：{}",systemDictionaryVO);
		SystemDictionaryDTO systemDictionaryDTO = new SystemDictionaryDTO();
		systemDictionaryDTO.setDictionaryCode(systemDictionaryVO.getDictionaryCode());
		systemDictionaryDTO.setDictionaryType(systemDictionaryVO.getDictionaryType());
		systemDictionaryDTO.setDictionaryStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
		List<SystemDictionaryDTO> dataList = this.systemDictionaryDaoImpl.selectByType(systemDictionaryDTO);
		logger.debug("【数据字典】-查询数据信息集合{}",dataList);
		return dataList;
	}

	@Override
	public SystemDictionaryDTO getSystemDictionary(SystemDictionaryVO systemDictionaryVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【数据字典】-查询数据信息输入参数systemDictionaryVO：{}",systemDictionaryVO);
		int dictionaryId = systemDictionaryVO.getDictionaryId();
		SystemDictionaryDTO systemDictionaryDTO = this.systemDictionaryDaoImpl.selectByPrimaryKey(dictionaryId);
		return systemDictionaryDTO;
	}

	@Override
	public List<SystemDictionaryDTO> getSystemDictionaryListByPage(SystemDictionaryVO systemDictionaryVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【数据字典】-分页查询数据信息输入参数systemDictionaryVO：{}",systemDictionaryVO);
		SystemDictionaryDTO systemDictionaryDTO = new SystemDictionaryDTO();
		if(StringUtils.isNotEmpty(systemDictionaryVO.getDictionaryName())){
			systemDictionaryDTO.setDictionaryName("%"+systemDictionaryVO.getDictionaryName()+"%");
		}
		if(StringUtils.isNotEmpty(systemDictionaryVO.getDictionaryType())){
			systemDictionaryDTO.setDictionaryType("%"+systemDictionaryVO.getDictionaryType()+"%");
		}
		systemDictionaryDTO.setDictionaryStatus(systemDictionaryVO.getDictionaryStatus());
		List<SystemDictionaryDTO> list = this.systemDictionaryDaoImpl.selectBySelective(systemDictionaryDTO);
		return list;
	}

	@Override
	public int saveSystemDictionary(SystemDictionaryVO systemDictionaryVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【数据字典】-新增数据信息输入参数systemDictionaryVO：{}",systemDictionaryVO);
		int result = -1;
		SystemDictionaryInfo systemDictionaryInfo = new SystemDictionaryInfo();
		systemDictionaryInfo.setDictionaryId(DefaultUtil.DEFAULT_SEQUENCE);
		systemDictionaryInfo.setDictionaryName(systemDictionaryVO.getDictionaryName());
		systemDictionaryInfo.setDictionaryCode(systemDictionaryVO.getDictionaryCode());
		systemDictionaryInfo.setDictionaryOrder(systemDictionaryVO.getDictionaryOrder());
		systemDictionaryInfo.setDictionaryParentCode(systemDictionaryVO.getDictionaryParentCode());
		systemDictionaryInfo.setDictionaryStatus(systemDictionaryVO.getDictionaryStatus());
		systemDictionaryInfo.setDictionaryType(systemDictionaryVO.getDictionaryType());
		systemDictionaryInfo.setDictionaryDesc(systemDictionaryVO.getDictionaryDesc());
		systemDictionaryInfo.setDictionaryCreateUserId(userSession.getUserId());
		result = this.systemDictionaryDaoImpl.insertSelective(systemDictionaryInfo);
		logger.debug("【数据字典】-更新数据执行结果result：{}",result);
		return result;
	}

	@Override
	public int updateSystemDictionary(SystemDictionaryVO systemDictionaryVO,UserSession userSession) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【数据字典】-更新数据信息输入参数systemDictionaryVO：{}",systemDictionaryVO);
		int result = -1;
		SystemDictionaryInfo systemDictionaryInfo = new SystemDictionaryInfo();
		systemDictionaryInfo.setDictionaryId(systemDictionaryVO.getDictionaryId());
		systemDictionaryInfo.setDictionaryName(systemDictionaryVO.getDictionaryName());
		systemDictionaryInfo.setDictionaryCode(systemDictionaryVO.getDictionaryCode());
		systemDictionaryInfo.setDictionaryOrder(systemDictionaryVO.getDictionaryOrder());
		systemDictionaryInfo.setDictionaryParentCode(systemDictionaryVO.getDictionaryParentCode());
		systemDictionaryInfo.setDictionaryStatus(systemDictionaryVO.getDictionaryStatus());
		systemDictionaryInfo.setDictionaryType(systemDictionaryVO.getDictionaryType());
		systemDictionaryInfo.setDictionaryDesc(systemDictionaryVO.getDictionaryDesc());
		systemDictionaryInfo.setDictionaryUpdateUserId(userSession.getUserId());
		systemDictionaryInfo.setDictionaryUpdateTime(DateUtils.getTimestamp());
		result = this.systemDictionaryDaoImpl.updateByPrimaryKeySelective(systemDictionaryInfo);
		logger.debug("【数据字典】-更新数据执行结果result：{}",result);
		return result;
	}

	@Override
	public int updateSystemDictionaryStatus(SystemDictionaryVO systemDictionaryVO, UserSession userSession)	throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【数据字典】-更新数据信息输入参数systemDictionaryVO：{}",systemDictionaryVO);
		int result = -1;
		SystemDictionaryInfo systemDictionaryInfo = new SystemDictionaryInfo();
		systemDictionaryInfo.setDictionaryId(systemDictionaryVO.getDictionaryId());
		systemDictionaryInfo.setDictionaryStatus(systemDictionaryVO.getDictionaryStatus());
		result = this.systemDictionaryDaoImpl.updateByPrimaryKeySelective(systemDictionaryInfo);
		logger.debug("【数据字典】-更新数据执行结果result：{}",result);
		return result;
	}

	@Override
	public int deleteSystemDictionary(SystemDictionaryVO systemDictionaryVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【数据字典】-删除数据信息输入参数systemDictionaryVO：{}",systemDictionaryVO);
		int result = -1;
		int dictionaryId = systemDictionaryVO.getDictionaryId();
		result = this.systemDictionaryDaoImpl.deleteByPrimaryKey(dictionaryId);
		logger.debug("【数据字典】-删除数据执行结果result：{}",result);
		return result;
	}

	/**
	 * 校验是否存在数据字典信息
	 * @param systemDictionaryVO 数据字典对象
	 * @param userSession 当前用户对象
	 * @return int
	 * @throws Exception
	 * @author julong
	 * @date 2017-1-9 下午07:50:42
	 */
	@Override
	public int validateSystemDictionary(SystemDictionaryVO systemDictionaryVO) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("【数据字典】-校验数据信息数据信息输入参数systemDictionaryVO：{}",systemDictionaryVO);
		SystemDictionaryDTO systemDictionaryDTO = new SystemDictionaryDTO();
		systemDictionaryDTO.setDictionaryId(systemDictionaryVO.getDictionaryId());
		systemDictionaryDTO.setDictionaryCode(systemDictionaryVO.getDictionaryCode());
		systemDictionaryDTO.setDictionaryType(systemDictionaryVO.getDictionaryType());
		Object obj = this.systemDictionaryDaoImpl.uniqueKey(systemDictionaryDTO);
		return Integer.valueOf(obj+"");
	}


}
