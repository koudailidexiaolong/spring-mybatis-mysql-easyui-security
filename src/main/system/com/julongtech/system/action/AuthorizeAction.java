package com.julongtech.system.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.julongtech.system.action.vo.SystemButtonVO;
import com.julongtech.system.action.vo.SystemMenuVO;
import com.julongtech.system.action.vo.SystemRoleButtonMappedVO;
import com.julongtech.system.action.vo.SystemRoleMenuMappedVO;
import com.julongtech.system.action.vo.SystemRoleVO;
import com.julongtech.system.action.vo.SystemUserRoleMappedVO;
import com.julongtech.system.aspect.LoggerMethod;
import com.julongtech.system.aspect.LoggerModule;
import com.julongtech.system.aspect.LoggerProxy;
import com.julongtech.system.service.SystemButtonService;
import com.julongtech.system.service.SystemMenuService;
import com.julongtech.system.service.SystemRoleButtonMappedService;
import com.julongtech.system.service.SystemRoleMenuMappedService;
import com.julongtech.system.service.SystemRoleService;
import com.julongtech.system.service.SystemUserRoleMappedService;
import com.julongtech.system.service.dto.SystemButtonDTO;
import com.julongtech.system.service.dto.SystemMenuDTO;
import com.julongtech.system.service.dto.SystemRoleButtonMappedDTO;
import com.julongtech.system.service.dto.SystemRoleDTO;
import com.julongtech.system.service.dto.SystemRoleMenuMappedDTO;
import com.julongtech.system.service.dto.SystemUserRoleMappedDTO;
import com.julongtech.system.session.UserSession;
import com.julongtech.system.util.DefaultUtil;

/**
 * 系统授权管理
 * @author julong
 * @date 2017-11-2 下午9:28:53
 */
@Controller
@RequestMapping("authorize")
public class AuthorizeAction {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizeAction.class);
	/**
	 * 系统菜单
	 * @author julong
	 * @date 2017-11-4 下午3:14:36
	 */
	@Autowired
	private SystemMenuService systemMenuServiceImpl;
	
	/**
	 * 系统按钮
	 * @author julong
	 * @date 2017-11-4 下午3:14:36
	 */
	@Autowired
	private SystemButtonService systemButtonServiceImpl;;
	
	/**
	 * session对象
	 * @author julong
	 * @date 2017-11-4 下午3:14:36
	 */
	@Autowired
	private HttpSession httpSession;
	
	/**
	 * 角色菜单映射关系
	 * @author julong
	 * @date 2017-11-4 下午3:14:36
	 */
	@Autowired 
	private SystemRoleMenuMappedService systemRoleMenuMappedServiceImpl;
	
	/**
	 * 角色按钮映射关系
	 * @author julong
	 * @date 2017-11-4 下午3:14:36
	 */
	@Autowired 
	private SystemRoleButtonMappedService systemRoleButtonMappedServiceImpl;
	
	/**
	 * 用户角色映射关系
	 * @author julong
	 * @date 2017-11-4 下午3:14:36
	 */
	@Autowired
	private SystemUserRoleMappedService systemUserRoleMappedServiceImpl;
	
	/**
	 * 角色
	 * @author julong
	 * @date 2017-11-4 下午3:14:36
	 */
	@Autowired
	private SystemRoleService systemRoleServiceImpl;
	
	/**
	 * 加载用户角色配置主界面的方法
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午12:53:40
	 */
	@RequestMapping("/user")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_AUTHORIZE,description="进入加载用户角色配置主界面")
	public String loadUserRolePage() throws Exception{
		logger.info("【系统授权管理】进入加载用户角色配置主界面的方法");
		return "system/authorize/user_role_index";
	}
	
	/**
	 * 加载角色菜单配置主界面的方法
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午12:53:40
	 */
	@RequestMapping("/menu")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_AUTHORIZE,description="进入加载角色菜单配置主界面")
	public String loadRoleMenuPage() throws Exception{
		logger.info("【系统授权管理】进入加载角色菜单配置主界面的方法");
		return "system/authorize/role_menu_index";
	}
	
	
	/**
	 * 加载角色菜单修改界面
	 * @param systemRoleMenuMappedVO
	 * @param model
	 * @return
	 * @author julong
	 * @date 2017-11-3 上午9:23:45
	 */
	@RequestMapping("/loadAddMenu")
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_AUTHORIZE,description="加载角色菜单修改界面")
	public String loadAddMenu(SystemRoleMenuMappedVO systemRoleMenuMappedVO,Model model) throws Exception{
		logger.info("【系统授权管理】进入加载角色菜单修改界面输入参数systemRoleMenuMappedVO：{}",systemRoleMenuMappedVO);
		List<SystemMenuDTO> menuList = new ArrayList<SystemMenuDTO>();
		List<SystemButtonDTO> buttonList = new ArrayList<SystemButtonDTO>();
		try {
			//查询所有的菜单信息
			SystemMenuVO systemMenuVO = new SystemMenuVO();
			systemMenuVO.setMenuStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
			menuList = this.systemMenuServiceImpl.getMenuList(systemMenuVO);
			//查询所有的按钮
			SystemButtonVO systemButtonVO = new SystemButtonVO();
			systemButtonVO.setButtonStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
			buttonList = this.systemButtonServiceImpl.getSystemButtonList(systemButtonVO);
			model.addAttribute("menuList", menuList);
			model.addAttribute("buttonList", buttonList);
			//当前角色编号
			model.addAttribute("roleId", systemRoleMenuMappedVO.getRoleId());
			//角色对应的机构编号
			model.addAttribute("orgId", systemRoleMenuMappedVO.getOrgId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【系统授权管理】加载系统角色配置菜单发生异常", e);
			throw new Exception(e);
		}
		return "system/authorize/role_menu_edit";
	}
	
	/**
	 * 修改角色对应菜单权限
	 * @param systemRoleMenuMappedVO
	 * @return
	 * @author julong
	 * @date 2017-11-4 下午1:38:04
	 */
	@RequestMapping("/editRoleMenu")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_AUTHORIZE,description="保存角色菜单对应关系")
	public Map<String,Object> editRoleMenu(SystemRoleMenuMappedVO systemRoleMenuMappedVO) throws Exception{
		logger.info("【系统授权管理】保存映射关系的方法{}",systemRoleMenuMappedVO);
		Map<String,Object> maps = new Hashtable<String, Object>();
		int result = 0;
		try {
			UserSession userSession = (UserSession) this.httpSession.getAttribute("userSession");
			List<SystemRoleMenuMappedVO> systemRoleMenuMappeds = new ArrayList<SystemRoleMenuMappedVO>();
			String menuArray = systemRoleMenuMappedVO.getMenuArray();
			String[] menu = StringUtils.split(menuArray, "-");
			Map<String,String> button = new Hashtable<String, String>();
			if(StringUtils.isNotBlank(systemRoleMenuMappedVO.getMappedMenuArray())){
				String[] buttonArray = StringUtils.split(systemRoleMenuMappedVO.getMappedMenuArray(), "@");
				for (int i = 0; i < buttonArray.length; i++) {
					String[] array = StringUtils.split(buttonArray[i], "-");
					button.put(array[0], array[1]);
				}
			}
			//循环处理
			for (int i = 0; i < menu.length; i++) {
				SystemRoleMenuMappedVO mapped = new SystemRoleMenuMappedVO();
				mapped.setMappedStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
				mapped.setMenuId(menu[i]);
				mapped.setRoleId(systemRoleMenuMappedVO.getRoleId());
				mapped.setOrgId(systemRoleMenuMappedVO.getOrgId());
				if(button.containsKey(menu[i])){
					mapped.setMappedMenuArray(button.get(menu[i]));
				}
				systemRoleMenuMappeds.add(mapped);
			}
			//如果不存在数据则认为清除权限 进行删除权限删除操作
			if(systemRoleMenuMappeds.size() > 0){
				result = this.systemRoleMenuMappedServiceImpl.saveSystemRoleMenuMappedList(systemRoleMenuMappeds, userSession);
			}else{
				SystemRoleMenuMappedVO mapped = new SystemRoleMenuMappedVO();
				mapped.setRoleId(systemRoleMenuMappedVO.getRoleId());
				result = this.systemRoleMenuMappedServiceImpl.deleteSystemRoleMenuMapped(systemRoleMenuMappedVO, userSession);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			maps.put("result", -1);
			logger.error("【系统权限配置模块】修改角色对应菜单权限发生异常", e);
			throw new Exception(e);
		}
		logger.info("【系统授权管理】保存映射关系返回结果：{}",result);
		maps.put("result", result);
		return maps;
	}
	
	/**
	 * 根据角色查询映射关系的方法
	 * @param systemRoleMenuMappedVO
	 * @return
	 * @author julong
	 * @date 2017-11-4 下午2:02:55
	 */
	@RequestMapping("/getRoleMenu")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_AUTHORIZE,description="根据角色查询映射关系")
	public Map<String,Object> getRoleMenu(SystemRoleMenuMappedVO systemRoleMenuMappedVO) throws Exception{
		logger.info("【系统授权管理】根据角色查询映射关系的方法{}",systemRoleMenuMappedVO);
		Map<String,Object> maps = new Hashtable<String, Object>();
		try {
			//根据角色查询映射关系表数据
			UserSession userSession = (UserSession) this.httpSession.getAttribute("userSession");
			//获取角色菜单
			List<SystemRoleMenuMappedDTO>  menuMappedList = this.systemRoleMenuMappedServiceImpl.getSystemRoleMenuMappedList(systemRoleMenuMappedVO, userSession);
			//获取角色按钮
			SystemRoleButtonMappedVO systemRoleButtonMappedVO = new SystemRoleButtonMappedVO();
			systemRoleButtonMappedVO.setRoleId(systemRoleMenuMappedVO.getRoleId());
			List<SystemRoleButtonMappedDTO>  buttonMappedList =  this.systemRoleButtonMappedServiceImpl.selectSystemRoleButtonMappedList(systemRoleButtonMappedVO);
		    maps.put("menuMappedList", menuMappedList);
		    maps.put("buttonMappedList", buttonMappedList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【系统权限配置模块】根据角色查询映射关系的方法发生异常", e);
			throw new Exception(e);
		}
		return maps;
	}
	
	/**
	 * 删除角色菜单映射关系的方法
	 * @param systemRoleMenuMappedVO
	 * @return
	 * @author julong
	 * @date 2017-11-4 下午2:02:55
	 */
	@RequestMapping("/deleteRoleMenu")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.DELETE,module = LoggerModule.SYSTEM_AUTHORIZE,description="删除角色菜单映射关系")
	public Map<String,Object> deleteRoleMenu(SystemRoleMenuMappedVO systemRoleMenuMappedVO) throws Exception{
		logger.info("【系统授权管理】-删除角色菜单映射关系的方法{}",systemRoleMenuMappedVO);
		Map<String,Object> maps = new Hashtable<String, Object>();
		int result = -1;
		try {
			//根据角色查询映射关系表数据
			UserSession userSession = (UserSession) this.httpSession.getAttribute("userSession");
			//根据角色编号删除映射关系
			result = this.systemRoleMenuMappedServiceImpl.deleteSystemRoleMenuMapped(systemRoleMenuMappedVO, userSession);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【系统权限配置模块】-删除角色菜单映射关系发生异常", e);
			throw new Exception(e);
		}
		logger.info("【系统授权管理】-删除角色菜单映射关系返回结果：{}",result);
		maps.put("result", result);
		return maps;
	}
	
	/**
	 * 加载用户角色修改界面
	 * @param systemRoleMenuMappedVO
	 * @param model
	 * @return
	 * @author julong
	 * @date 2017-11-3 上午9:23:45
	 */
	@RequestMapping("/loadAddRole")
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_AUTHORIZE,description="加载用户角色修改界面")
	public String loadAddRole(SystemUserRoleMappedVO systemUserRoleMappedVO,Model model) throws Exception{
		logger.info("【系统授权管理】进入加载用户角色修改界面{},{}",systemUserRoleMappedVO,model);
		try {
			//根据角色查询映射关系表数据
			//查询所有的角色信息
			SystemRoleVO systemRoleVO = new SystemRoleVO();
			systemRoleVO.setRoleStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
			List<SystemRoleDTO>  roleList = this.systemRoleServiceImpl.getRoleList(systemRoleVO);
			model.addAttribute("roleList", roleList);
			model.addAttribute("userId", systemUserRoleMappedVO.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【系统权限配置模块】进入加载用户角色修改方法发生异常", e);
			throw new Exception(e);
		}
		return "system/authorize/user_role_edit";
	}
	
	/**
	 * 删除用户角色映射关系的方法
	 * @param systemUserRoleMappedVO
	 * @return
	 * @author julong
	 * @date 2017-11-4 下午2:02:55
	 */
	@RequestMapping("/deleteRoleUser")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.DELETE,module = LoggerModule.SYSTEM_AUTHORIZE,description="删除用户角色映射关系")
	public Map<String,Object> deleteRoleUser(SystemUserRoleMappedVO systemUserRoleMappedVO) throws Exception{
		logger.info("【系统授权管理】根据角色查询映射关系的方法{}",systemUserRoleMappedVO);
		Map<String,Object> maps = new Hashtable<String, Object>();
		int result = -1;
		try {
			//根据用户查询映射关系表数据
			//根据用户编号删除映射关系
			result = this.systemUserRoleMappedServiceImpl.deleteUserRoleMapped(systemUserRoleMappedVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【系统权限配置模块】根据角色查询映射关系的方法发生异常", e);
			throw new Exception(e);
		}
		maps.put("result", result);
		return maps;
	}
	/**
	 * 修改用户对应角色权限
	 * @param systemUserRoleMappedVO
	 * @return
	 * @author julong
	 * @date 2017-11-4 下午1:38:04
	 */
	@RequestMapping("/editRoleUser")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_AUTHORIZE,description="修改用户对应角色权限")
	public Map<String,Object> editRoleUser(SystemUserRoleMappedVO systemUserRoleMappedVO) throws Exception{
		logger.info("【系统授权管理】修改用户角色映射关系的方法{}",systemUserRoleMappedVO);
		Map<String,Object> maps = new Hashtable<String, Object>();
		int result = 0;
		try {
			UserSession userSession = (UserSession) this.httpSession.getAttribute("userSession");
			List<SystemUserRoleMappedVO> userRoleList = new ArrayList<SystemUserRoleMappedVO>();
			String array = systemUserRoleMappedVO.getRoleArray();
			String[] role= array.split("-");
			//循环处理
			for (int i = 0; i < role.length; i++) {
				SystemUserRoleMappedVO mapped = new SystemUserRoleMappedVO();
				mapped.setMappedStatus(DefaultUtil.DEFAULT_STATUS_TRUE);
				mapped.setRoleId(Integer.valueOf(role[i]));
				mapped.setOrgId(systemUserRoleMappedVO.getOrgId());
				mapped.setUserId(systemUserRoleMappedVO.getUserId());
				userRoleList.add(mapped);
			}
			result = this.systemUserRoleMappedServiceImpl.saveUserRoleMappedList(userRoleList, userSession,systemUserRoleMappedVO.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【系统权限配置模块】修改用户角色权限发生异常", e);
			throw new Exception(e);
		}
		maps.put("result", result);
		return maps;
	}
	
	/**
	 * 根据用户查询映射关系的方法
	 * @param systemRoleMenuMappedVO
	 * @return
	 * @author julong
	 * @date 2017-11-4 下午2:02:55
	 */
	@RequestMapping("/getRoleUser")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_AUTHORIZE,description="根据用户查询映射关系")
	public Map<String,Object> getRoleUser(SystemUserRoleMappedVO systemUserRoleMappedVO) throws Exception{
		logger.info("【系统授权管理】根据用户查询映射关系的方法输入参数systemUserRoleMappedVO：{}",systemUserRoleMappedVO);
		Map<String,Object> maps = new Hashtable<String, Object>();
		try {
			//根据用户查询映射关系表数据
			List<SystemUserRoleMappedDTO>  mappedList = this.systemUserRoleMappedServiceImpl.getUserRoleMappedList(systemUserRoleMappedVO);
		    maps.put("mappedList", mappedList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【系统权限配置模块】根据用户查询映射关系的方法发生异常", e);
			throw new Exception(e);
		}
		return maps;
	}
	
	
	/**
	 * 加载权限按钮
	 * @param systemUserRoleMappedVO
	 * @return
	 * @author julong
	 * @date 2018-6-13 下午5:31:16
	 */
	@RequestMapping("/loadAuthorizeButton")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_AUTHORIZE,description="加载权限按钮")
	public Map<String,Object> loadAuthorizeButton(SystemUserRoleMappedVO systemUserRoleMappedVO) throws Exception{
		logger.info("【系统授权管理】根据用户查询映射关系的方法{}",systemUserRoleMappedVO);
		Map<String,Object> maps = new Hashtable<String, Object>();
		try {
			//根据用户查询映射关系表数据
			@SuppressWarnings("unchecked")
			List<SystemButtonDTO> buttonList = (List<SystemButtonDTO>) this.httpSession.getAttribute("button");
		    maps.put("buttonList", buttonList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【系统权限配置模块】根据用户查询映射关系的方法发生异常", e);
			throw new Exception(e);
		}
		return maps;
	}
	
	
	
}
