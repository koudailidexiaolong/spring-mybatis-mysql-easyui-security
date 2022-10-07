package com.julongtech.system.action;

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
import com.julongtech.system.action.vo.SystemRoleVO;
import com.julongtech.system.action.vo.SystemUserRoleMappedVO;
import com.julongtech.system.aspect.LoggerMethod;
import com.julongtech.system.aspect.LoggerModule;
import com.julongtech.system.aspect.LoggerProxy;
import com.julongtech.system.service.SystemRoleService;
import com.julongtech.system.service.dto.SystemRoleDTO;
import com.julongtech.system.session.UserSession;

/**
 * 角色模块
 * @author julong
 * @date 2017-10-27 下午4:45:39
 */
@Controller
@RequestMapping("role")
public class SystemRoleAction {
	private static final Logger logger = LoggerFactory.getLogger(SystemRoleAction.class);
	
	@Autowired
	private SystemRoleService systemRoleServiceImpl;
	
	/**
	 * 加载主界面的方法
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午12:53:40
	 */
	@RequestMapping("/index")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_ROLE,description="加载角色主页面")
	public String loadPage() throws Exception{
		logger.info("【角色模块】-加载主界面");
		return "system/role/role_index";
	}
	
	/**
	 * 分页查询角色基本信息集合的方法
	 * @param systemRoleVO
	 * @param page
	 * @return Map<String,Object>
	 * @author julong
	 * @date 2017-10-28 上午11:53:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getRoleListByPage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_ROLE,description="分页查询角色列表")
	public Map<String,Object> getRoleListByPage(SystemRoleVO systemRoleVO,PageParam<?> page) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		logger.debug("【角色模块】-分页查询角色信息的方法:{},{}",systemRoleVO,page);
		try {
			//分页的方法
			PageInfo<SystemRoleDTO> pages = (PageInfo<SystemRoleDTO>) PageUtils.getPageHelper(page);
			//查询数据的方法
			PageHelper.startPage(pages.getPageNum(),pages.getPageSize());
			List<SystemRoleDTO> list = this.systemRoleServiceImpl.getRoleListByPage(systemRoleVO);
			//分页获取的方法
			pages = new PageInfo<SystemRoleDTO>(list);
			//格式化返回结果
			maps = PageUtils.formatPage(pages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【角色管理】-查询角色信息集合发生异常",e);
			throw new Exception(e);
		}
		return maps;
	}
	
	/**
	 * 加载修改角色信息的方法
	 * @param systemRoleVO
	 * @param model
	 * @return String
	 * @author julong
	 * @date 2017-10-28 上午11:56:11
	 */
	@RequestMapping(value="/loadEditRole",method={RequestMethod.GET,RequestMethod.POST})
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_ROLE,description="加载修改角色信息的方法")
	public String loadEditRole(SystemRoleVO systemRoleVO,Model model) throws Exception{
		logger.info("【角色管理模块】-加载角色修改界面的方法:{},{}",systemRoleVO,model);
		try {
			SystemRoleDTO systemRoleDTO = new SystemRoleDTO();
			systemRoleDTO = this.systemRoleServiceImpl.getSystemRole(systemRoleVO);
			model.addAttribute("systemRoleDTO", systemRoleDTO);//返回参数
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【角色管理】-查询角色信息发生异常",e);
			throw new Exception(e);
		}
		return "system/role/role_edit";
	}
	
	/**
	 * 加载新增角色界面的方法
	 * @param systemRoleVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:29:34
	 */
	@RequestMapping("/loadAddRole")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_ROLE,description="加载新增角色界面的方法")
	public String loadAddRole(SystemRoleVO systemRoleVO) throws Exception{
		logger.info("【角色管理模块】-加载角色新增界面的方法");
		return "system/role/role_add";
	}
	
	
	/**
	 * 修改角色信息的方法
	 * @param systemRoleVO
	 * @return
	 * @author julong
	 * @date 2017-10-28 上午11:57:44
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_ROLE,description="修改角色信息的方法")
	public Map<String,Object> updateRole(SystemRoleVO systemRoleVO) throws Exception{
		logger.info("【角色模块】-修改信息的方法:{}",systemRoleVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemRoleServiceImpl.updateSystemRole(systemRoleVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【角色管理】-修改角色信息发生异常",e);
			throw new Exception(e);
		}
		return maps;
	}
	/**
	 * 修改角色信息的方法
	 * @author julong
	 * @date 2017-10-26 上午9:29:48
	 */
	@RequestMapping("/updateRoleStatus")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_ROLE,description="修改角色信息的方法")
	public Map<String,Object> updateRoleStatus(SystemRoleVO systemRoleVO) throws Exception{
		logger.info("【角色模块】-修改状态信息的方法:{}",systemRoleVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemRoleServiceImpl.updateSystemRoleStatus(systemRoleVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【角色管理】-修改角色信息发生异常",e);
			throw new Exception(e);
		}
		return maps;
	}

	/**
	 * 根据编号角色信息的方法
	 * @param systemRoleVO
	 * @param model
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:29:48
	 * @desc
	 */
	@RequestMapping("/getRole")
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_ROLE,description="根据编号角色信息的方法")
	public String getRole(SystemRoleVO systemRoleVO,Model model) throws Exception{
		logger.info("【角色管理】-根据编号信息的方法:{},{}",systemRoleVO,model);
		try {
			SystemRoleDTO systemRoleDTO = this.systemRoleServiceImpl.getSystemRole(systemRoleVO);
			model.addAttribute("systemRoleDTO", systemRoleDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【角色管理】-查询角色信息发生异常",e);
			throw new Exception(e);
		}
		return "system/role/role_info";
	}
	
	
	/**
	 * 新增角色信息
	 * @param systemRoleVO
	 * @return
	 * @author julong
	 * @date 2017-10-27 上午11:22:01
	 */
	@RequestMapping("/saveRole")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.INSERT,module = LoggerModule.SYSTEM_ROLE,description="新增角色信息")
	public Map<String,Object> saveRole(SystemRoleVO systemRoleVO) throws Exception{
		logger.info("【角色模块】-新增信息的方法:{}",systemRoleVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemRoleServiceImpl.saveSystemRole(systemRoleVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【角色管理】-新增角色信息发生异常",e);
			throw new Exception(e);
		}
		logger.info("【角色模块】-新增角色信息返回结果:{}",result);
		return maps;
	}
	
	/**
	 * 删除角色信息的方法
	 * @param systemRoleVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.DELETE,module = LoggerModule.SYSTEM_ROLE,description="删除角色信息的方法")
	public Map<String,Object> deleteRole(SystemRoleVO systemRoleVO) throws Exception{
		logger.info("【角色模块】-删除角色信息的方法:{}",systemRoleVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = -1;
		try {
			SystemUserRoleMappedVO systemUserRoleMappedVO = new SystemUserRoleMappedVO();
			systemUserRoleMappedVO.setRoleId(systemRoleVO.getRoleId());
			result = this.systemRoleServiceImpl.deleteSystemRole(systemRoleVO);
			maps.put("result", result);
		} catch (Exception e) {
			logger.error("【角色管理】-删除角色信息的方法发生异常",e);
			throw new Exception(e);
		}
		logger.info("【角色模块】-删除角色信息返回结果:{}",result);
		return maps;
	}
	/**
	 * 校验角色名称信息的方法
	 * @param systemRoleVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/validateRole")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_ROLE,description="校验角色名称信息的方法")
	public Map<String,Object> validateRole(SystemRoleVO systemRoleVO) throws Exception{
		logger.info("【角色模块】-校验角色名称是否存在的方法:{}",systemRoleVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			result = this.systemRoleServiceImpl.validateRoleExist(systemRoleVO);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【角色管理】-校验角色名称信息的方法发生异常",e);
			throw new Exception(e);
		}
		logger.info("【角色模块】-校验角色名称是否存在返回结果result:{}",result);
		return maps;
	}
	
}
