package com.julongtech.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.julongtech.page.PageParam;
import com.julongtech.page.PageUtils;
import com.julongtech.system.action.vo.SystemDictionaryVO;
import com.julongtech.system.aspect.LoggerMethod;
import com.julongtech.system.aspect.LoggerModule;
import com.julongtech.system.aspect.LoggerProxy;
import com.julongtech.system.service.RedisCacheService;
import com.julongtech.system.service.SystemDictionaryService;
import com.julongtech.system.service.dto.SystemDictionaryDTO;
import com.julongtech.system.session.UserSession;

/**
 * 数据字典模块
 * @author julong
 * @date 2017-10-27 下午4:45:39
 */
@Controller
@RequestMapping("dictionary")
public class SystemDictionaryAction {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemDictionaryAction.class);
	
	@Autowired
	private SystemDictionaryService systemDictionaryServiceImpl;
	
	@Autowired
	private RedisCacheService redisCacheServiceImpl;
	
	/**
	 * 加载主界面的方法
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午12:53:40
	 */
	@RequestMapping("/index")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_DICTIONARY,description="加载数据字典主界面")
	public String loadPage()  throws Exception{
		logger.info("【数据字典管理】-加载数据字典主界面");
		return "system/dictionary/dictionary_index";
	}
	
	/**
	 * 分页查询数据字典基本信息集合的方法
	 * @param systemDictionaryVO
	 * @param page
	 * @return Map<String,Object>
	 * @author julong
	 * @date 2017-10-28 上午11:53:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getDictionaryListByPage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_DICTIONARY,description="分页查询数据字典基本信息集合的方法")
	public Map<String,Object> getDictionaryListByPage(SystemDictionaryVO systemDictionaryVO,PageParam<SystemDictionaryDTO> page) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		logger.info("【数据字典管理】-分页查询数据字典信息的方法{}",systemDictionaryVO);
		try {
			//分页的方法
			PageInfo<SystemDictionaryDTO> pages = (PageInfo<SystemDictionaryDTO>) PageUtils.getPageHelper(page);
			//查询数据的方法
			PageHelper.startPage(pages.getPageNum(),pages.getPageSize());
			List<SystemDictionaryDTO> list = this.systemDictionaryServiceImpl.getSystemDictionaryListByPage(systemDictionaryVO);
			//分页获取的方法
			pages = new PageInfo<SystemDictionaryDTO>(list);
			//格式化返回结果
			maps = PageUtils.formatPage(pages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【数据字典管理】-分页查询数据字典信息集合发生异常",e);
		}
		return maps;
	}
	
	/**
	 * 加载修改数据字典信息的方法
	 * @param systemDictionaryVO
	 * @param model
	 * @return String
	 * @author julong
	 * @date 2017-10-28 上午11:56:11
	 */
	@RequestMapping(value="/loadEditDictionary",method={RequestMethod.GET,RequestMethod.POST})
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_DICTIONARY,description="加载修改数据字典信息的方法")
	public String loadEditDictionary(SystemDictionaryVO systemDictionaryVO,Model model) throws Exception{
		logger.info("【数据字典管理】-加载数据字典修改界面的方法:{},{}",systemDictionaryVO,model);
		try {
			SystemDictionaryDTO systemDictionaryDTO = this.systemDictionaryServiceImpl.getSystemDictionary(systemDictionaryVO);
			model.addAttribute("systemDictionaryDTO", systemDictionaryDTO);//返回参数
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【数据字典管理】-加载数据字典修改界面发生异常",e);
		}
		return "system/dictionary/dictionary_edit";
	}
	
	/**
	 * 加载新增数据字典界面的方法
	 * @param systemDictionaryVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:29:34
	 */
	@RequestMapping("/loadAddDictionary")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_DICTIONARY,description="加载新增数据字典界面的方法")
	public String loadAddDictionary(SystemDictionaryVO systemDictionaryVO) throws Exception{
		logger.info("【数据字典管理】-加载数据字典新增界面的方法");
		return "system/dictionary/dictionary_add";
	}
	
	
	/**
	 * 修改数据字典信息的方法
	 * @param systemDictionaryVO
	 * @return
	 * @author julong
	 * @date 2017-10-28 上午11:57:44
	 */
	@RequestMapping("/updateDictionary")
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_DICTIONARY,description="修改数据字典信息的方法")
	public Map<String,Object> updateDictionary(SystemDictionaryVO systemDictionaryVO) throws Exception{
		logger.info("【数据字典模块】-修改数据字典信息的方法:{}",systemDictionaryVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemDictionaryServiceImpl.updateSystemDictionary(systemDictionaryVO, userSession);
			maps.put("result", result);
			this.redisCacheServiceImpl.refreshDictionaryCache();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【数据字典管理】-修改数据字典信息发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【数据字典模块】-修改数据字典信息返回结果:{}",result);
		return maps;
	}
	/**
	 * 修改数据字典状态的方法
	 * @param systemDictionaryVO
	 * @return Map<String,Object>
	 * @author julong
	 * @date 2018-4-18 下午3:41:58
	 */
	@RequestMapping("/updateDictionaryStatus")
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_DICTIONARY,description="修改数据字典状态的方法")
	public Map<String,Object> updateDictionaryStatus(SystemDictionaryVO systemDictionaryVO) throws Exception{
		logger.info("【数据字典模块】-修改数据字典状态的方法:{}",systemDictionaryVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemDictionaryServiceImpl.updateSystemDictionaryStatus(systemDictionaryVO, userSession);
			maps.put("result", result);
			this.redisCacheServiceImpl.refreshDictionaryCache();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【数据字典管理】-修改数据字典状态发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【数据字典模块】-修改数据字典状态返回结果:{}",result);
		return maps;
	}

	/**
	 * 查询数据字典信息的方法
	 * @param systemDictionaryVO
	 * @param model
	 * @return
	 * @author julong
	 * @date 2018-4-18 下午3:43:40
	 */
	@RequestMapping("/getDictionary")
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_DICTIONARY,description="查询数据字典信息的方法")
	public String getDictionary(SystemDictionaryVO systemDictionaryVO,Model model) throws Exception{
		logger.info("【数据字典模块】-查询数据字典信息的方法:{},{}",systemDictionaryVO,model);
		try {
			SystemDictionaryDTO systemDictionaryDTO = this.systemDictionaryServiceImpl.getSystemDictionary(systemDictionaryVO);
			model.addAttribute("systemDictionaryDTO", systemDictionaryDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【数据字典管理】-查询数据字典发生异常",e);
		}
		return "system/dictionary/dictionary_info";
	}
	
	
	/**
	 * 保存数据字典信息的方法
	 * @param systemDictionaryVO
	 * @return
	 * @author julong
	 * @date 2017-10-27 上午11:22:01
	 */
	@RequestMapping("/saveDictionary")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.INSERT,module = LoggerModule.SYSTEM_DICTIONARY,description="保存数据字典信息的方法")
	public Map<String,Object> saveDictionary(SystemDictionaryVO systemDictionaryVO) throws Exception{
		logger.info("【数据字典管理】-新增数据字典信息方法:{}",systemDictionaryVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemDictionaryServiceImpl.saveSystemDictionary(systemDictionaryVO, userSession);
			maps.put("result", result);
			this.redisCacheServiceImpl.refreshDictionaryCache();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【数据字典管理】-新增数据字典信息发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【数据字典管理】-新增数据字典信息返回结果:{}",result);
		return maps;
	}
	
	/**
	 * 删除数据字典信息的方法
	 * @param systemDictionaryVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/deleteDictionary")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.DELETE,module = LoggerModule.SYSTEM_DICTIONARY,description="删除数据字典信息的方法")
	public Map<String,Object> deleteDictionary(SystemDictionaryVO systemDictionaryVO) throws Exception{
		logger.info("【数据字典管理】-删除数据字典信息的方法:{}",systemDictionaryVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			result = this.systemDictionaryServiceImpl.deleteSystemDictionary(systemDictionaryVO);
			maps.put("result", result);
			this.redisCacheServiceImpl.refreshDictionaryCache();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【数据字典管理】-删除数据字典信息的方法发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【数据字典管理】-删除数据字典信息返回结果:{}",result);
		return maps;
	}
	
	
	/**
	 * 根据编号查询数据字典信息的方法
	 * @param systemDictionaryVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/getCode")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_DICTIONARY,description="根据编号查询数据字典信息的方法")
	public List<SystemDictionaryDTO> getCode(SystemDictionaryVO systemDictionaryVO) throws Exception{
		List<SystemDictionaryDTO> result = new ArrayList<SystemDictionaryDTO>();
		logger.info("【数据字典模块】-查询数据字典信息的方法:{}",systemDictionaryVO);
		try {
			result = this.systemDictionaryServiceImpl.getSystemDictionaryList(systemDictionaryVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【数据字典管理】-查询数据字典信息的方法发生异常",e);
		}
		return result;
	}
	
	/**
	 * 校验是否已经存在
	 * @param systemDictionaryVO
	 * @return
	 * @author julong
	 * @date 2018-6-5 下午6:14:00
	 */
	@RequestMapping("/validateDictionary")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_DICTIONARY,description="校验是否已经存在")
	public Map<String,Object> validateDictionary(SystemDictionaryVO systemDictionaryVO) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		logger.info("【数据字典模块】-查询数据字典信息的方法:{}",systemDictionaryVO);
		int result = -1;
		try {
			result = this.systemDictionaryServiceImpl.validateSystemDictionary(systemDictionaryVO);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【数据字典管理】-查询数据字典信息的方法发生异常",e);
			maps.put("result", result);
		}
		return maps;
	}
	
	
}
