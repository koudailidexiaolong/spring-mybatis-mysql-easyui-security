package com.julongtech.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.julongtech.system.action.vo.SystemOrgVO;
import com.julongtech.system.aspect.LoggerMethod;
import com.julongtech.system.aspect.LoggerModule;
import com.julongtech.system.aspect.LoggerProxy;
import com.julongtech.system.service.SystemOrgService;
import com.julongtech.system.service.dto.SystemOrgDTO;
import com.julongtech.system.session.UserSession;

/**
 * 系统组织机构模块
 * @author julong
 * @date 2017-10-23 下午8:04:18
 */
@Controller
@RequestMapping("org")
public class SystemOrgAction {

	private static final Logger logger = LoggerFactory.getLogger(SystemOrgAction.class);
	
	@Autowired
	private SystemOrgService systemOrgServiceImpl;
	
	/**
	 * 加载主界面的方法
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午12:53:40
	 */
	@RequestMapping("/index")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_ORG,description="加载机构主界面")
	public String loadPage() throws Exception{
		logger.info("【系统组织机构模块】-加载主界面");
		return "system/org/org_index";
	}
	
	
	
	/**
	 * 查询组织机构树形结构
	 * @param systemOrgVO
	 * @return
	 * @author julong
	 * @date 2018-5-30 下午5:28:09
	 */
	@RequestMapping(value="/getOrgList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_ORG,description="查询组织机构树形结构")
	public Map<String,Object> getOrgList(SystemOrgVO systemOrgVO) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		logger.info("【机构模块】-查询机构信息的方法:{}",systemOrgVO);
		try {
			//当没有输入的时候 默认查询总行 有输入的时候查询条件
			if(StringUtils.isEmpty(systemOrgVO.getOrgParentId())){
				if(StringUtils.isEmpty(systemOrgVO.getOrgLevel())){
					systemOrgVO.setOrgLevel("0");
				}
			}
			//查询数据的方法
			List<SystemOrgDTO> list = this.systemOrgServiceImpl.getSystemOrgList(systemOrgVO);
			for (SystemOrgDTO systemOrgDTO : list) {
				if(StringUtils.isEmpty(systemOrgVO.getOrgParentId())){
					break;
				}
				systemOrgDTO.set_parentId(systemOrgVO.getOrgParentId());
			}
			maps.put("total", list.size());
			maps.put("rows", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【机构管理】-查询机构信息集合发生异常",e);
		}
		return maps;
	}
	
	
	/**
	 * 加载修改机构信息的方法
	 * @param systemOrgVO
	 * @param model
	 * @return String
	 * @author julong
	 * @date 2017-10-28 上午11:56:11
	 */
	@RequestMapping(value="/loadEditOrg",method={RequestMethod.GET,RequestMethod.POST})
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_ORG,description="加载修改机构信息的方法")
	public String loadEditOrg(SystemOrgVO systemOrgVO,Model model) throws Exception{
		logger.debug("【机构管理模块】-加载机构修改界面的方法:{}",systemOrgVO.toString());
		SystemOrgDTO systemOrgDTO = new SystemOrgDTO();
		try {
			systemOrgDTO = this.systemOrgServiceImpl.getSystemOrg(systemOrgVO);
			model.addAttribute("systemOrgDTO", systemOrgDTO);//返回参数
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【机构管理】-查询机构信息发生异常",e);
		}
		return "system/org/org_edit";
	}
	
	/**
	 * 加载新增机构界面的方法
	 * @param systemOrgVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:29:34
	 */
	@RequestMapping("/loadAddOrg")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_ORG,description="加载新增机构信息的方法")
	public String loadAddOrg(SystemOrgVO systemOrgVO) throws Exception{
		logger.debug("【机构管理模块】-加载机构新增界面的方法");
		return "system/org/org_add";
	}
	
	
	/**
	 * 修改机构信息的方法
	 * @param systemOrgVO
	 * @return
	 * @author julong
	 * @date 2017-10-28 上午11:57:44
	 */
	@RequestMapping("/updateOrg")
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_ORG,description="修改机构信息的方法")
	public Map<String,Object> updateOrg(SystemOrgVO systemOrgVO) throws Exception{
		logger.info("【机构模块】-修改信息的方法:{}",systemOrgVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemOrgServiceImpl.updateSystemOrg(systemOrgVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【机构管理】-修改机构信息发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【机构模块】-修改信息返回结果:{}",result);
		return maps;
	}
	
	/**
	 * 修改机构状态信息的方法
	 * @param systemOrgVO
	 * @return
	 * @author julong
	 * @date 2018-4-20 下午5:16:10
	 */
	@RequestMapping("/updateOrgStatus")
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_ORG,description="加载修改机构状态信息的方法")
	public Map<String,Object> updateOrgStatus(SystemOrgVO systemOrgVO) throws Exception{
		logger.info("【机构模块】-修改机构状态信息的方法:{}",systemOrgVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemOrgServiceImpl.updateSystemOrgStatus(systemOrgVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【机构管理】-修改机构信息发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【机构模块】-修改信息的方法:{}",result);
		return maps;
	}

	/**
	 * 查询机构详情信息的方法
	 * @param systemOrgVO
	 * @param model
	 * @return
	 * @author julong
	 * @date 2018-4-20 下午5:16:44
	 */
	@RequestMapping("/getOrg")
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_ORG,description="查询机构详情信息的方法")
	public String getOrg(SystemOrgVO systemOrgVO,Model model) throws Exception{
		logger.info("【机构模块】-查询机构详情信息的方法:{}",systemOrgVO);
		try {
			SystemOrgDTO systemOrgDTO = this.systemOrgServiceImpl.getSystemOrg(systemOrgVO);
			model.addAttribute("systemOrgDTO", systemOrgDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【机构管理】-查询机构详情信息的方法发生异常",e);
		}
		return "system/org/org_info";
	}

	/**
	 * 校验是否存在此机构
	 * @param systemOrgVO
	 * @return Map<String,Object>
	 * @author julong
	 * @date 2017-10-27 上午11:22:01
	 */
	@RequestMapping("/validateOrg")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_ORG,description="校验是否存在此机构")
	public Map<String,Object> validateOrg(SystemOrgVO systemOrgVO) throws Exception{
		logger.info("【机构模块】-校验是否存在此机构方法:{}",systemOrgVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			result = this.systemOrgServiceImpl.validateSystemOrg(systemOrgVO);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【机构管理】-校验是否存在此机构发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【机构模块】-校验是否存在此机构返回结果:{}",result);
		return maps;
	}
	
	/**
	 * 新增机构信息的方法
	 * @param systemOrgVO
	 * @return
	 * @author julong
	 * @date 2017-10-27 上午11:22:01
	 */
	@RequestMapping("/saveOrg")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.INSERT,module = LoggerModule.SYSTEM_ORG,description="新增机构信息的方法")
	public Map<String,Object> saveOrg(SystemOrgVO systemOrgVO) throws Exception{
		logger.info("【机构模块】-新增信息的方法:{}",systemOrgVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemOrgServiceImpl.saveSystemOrg(systemOrgVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【机构管理】-新增机构信息发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【机构模块】-新增机构信息返回结果:{}",systemOrgVO);
		return maps;
	}
	
	/**
	 * 删除机构信息的方法
	 * @param systemOrgVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/deleteOrg")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.DELETE,module = LoggerModule.SYSTEM_ORG,description="删除机构信息的方法")
	public Map<String,Object> deleteOrg(SystemOrgVO systemOrgVO) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		logger.info("【机构模块】-删除机构信息的方法:{}",systemOrgVO);
		int result = 0;
		try {
			result = this.systemOrgServiceImpl.deleteSystemOrg(systemOrgVO);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【机构管理】-删除机构信息的方法发生异常",e);
		}
		logger.info("【机构模块】-删除机构信息返回结果:{}",result);
		return maps;
	}
	
	
	/**
	 * 查询上级机构信息
	 * @param systemOrgVO
	 * @return List<SystemOrgDTO>
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/getSystemOrgParentList")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_ORG,description="查询上级机构信息")
	public List<SystemOrgDTO> getSystemOrgParentList(SystemOrgVO systemOrgVO) throws Exception{
		logger.info("【机构模块】-查询上级机构信息方法:{}",systemOrgVO);
		List<SystemOrgDTO> systemOrgDTOList = new ArrayList<SystemOrgDTO>();
		try {
			systemOrgDTOList = this.systemOrgServiceImpl.getSystemOrgParentList(systemOrgVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【机构管理】-查询上级机构信息方法发生异常",e);
		}
		return systemOrgDTOList;
	}
}
