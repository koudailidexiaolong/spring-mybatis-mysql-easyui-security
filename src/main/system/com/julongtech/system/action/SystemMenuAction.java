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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.julongtech.page.PageParam;
import com.julongtech.page.PageUtils;
import com.julongtech.system.action.vo.SystemMenuVO;
import com.julongtech.system.aspect.LoggerMethod;
import com.julongtech.system.aspect.LoggerModule;
import com.julongtech.system.aspect.LoggerProxy;
import com.julongtech.system.service.SystemMenuService;
import com.julongtech.system.service.dto.SystemMenuDTO;
import com.julongtech.system.session.UserSession;

/**
 * 系统菜单模块
 * @author julong
 * @date 2017-10-23 下午8:04:18
 */
@Controller
@RequestMapping("menu")
public class SystemMenuAction {

	private static final Logger logger = LoggerFactory.getLogger(SystemMenuAction.class);
	
	
	@Autowired
	private SystemMenuService systemMenuServiceImpl;
	/**
	 * 统一资源请求重定向
	 * @param menuURL
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午12:01:26
	 */
	@RequestMapping("/sendMenu")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_MENU,description="跳转不同菜单")
	public String sendMenu(@RequestParam("menuURL") String menuURL) throws Exception{
		logger.info("【界面跳转】:输入参数menuURL:{}",menuURL);
		//请求转发
		return "forward:"+menuURL;
	}
	
	
	/**
	 * 加载主界面的方法
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午12:53:40
	 */
	@RequestMapping("/index")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_MENU,description="加载菜单模块主页面")
	public String loadPage() throws Exception{
		logger.info("【菜单管理】-加载主界面");
		return "system/menu/menu_index";
	}
	
	/**
	 * 分页查询菜单基本信息集合的方法
	 * @param systemRoleVO
	 * @param page
	 * @return Map<String,Object>
	 * @author julong
	 * @date 2017-10-28 上午11:53:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/selectMenuListByPage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_MENU,description="分页查询菜单列表信息")
	public Map<String,Object> selectMenuListByPage(SystemMenuVO systemMenuVO,PageParam<?> page) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		logger.debug("【菜单管理】-分页查询菜单信息的方法输入参数systemMenuVO:{},page：{}",systemMenuVO,page);
		try {
			//分页的方法
			PageInfo<SystemMenuDTO> pages = (PageInfo<SystemMenuDTO>) PageUtils.getPageHelper(page);
			//查询数据的方法
			PageHelper.startPage(pages.getPageNum(),pages.getPageSize());
			List<SystemMenuDTO> list = this.systemMenuServiceImpl.getMenuListByPage(systemMenuVO);
			//分页获取的方法
			pages = new PageInfo<SystemMenuDTO>(list);
			//格式化返回结果
			maps = PageUtils.formatPage(pages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【菜单管理】-查询菜单信息集合发生异常",e);
		}
		return maps;
	}
	
	/**
	 * 加载新增菜单界面的方法
	 * @param systemRoleVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:29:34
	 */
	@RequestMapping("/loadAddMenu")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_MENU,description="加载菜单新增页面")
	public String loadAddMenu(SystemMenuVO systemMenuVO) throws Exception{
		logger.info("【菜单管理模块】-加载菜单新增界面的方法");
		return "system/menu/menu_add";
	}
	
	
	/**
	 * 新增菜单信息
	 * @param systemMenuVO
	 * @return
	 * @author julong
	 * @date 2017-10-27 上午11:22:01
	 */
	@RequestMapping("/saveMenu")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.INSERT,module = LoggerModule.SYSTEM_MENU,description="新增菜单信息")
	public Map<String,Object> saveRole(SystemMenuVO systemMenuVO) throws Exception{
		logger.info("【菜单模块】-新增菜单信息输入参数systemMenuVO:{}",systemMenuVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemMenuServiceImpl.saveSystemMenu(systemMenuVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【菜单管理】-新增菜单信息发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【菜单模块】-新增菜单信息返回结果result:{}",result);
		return maps;
	}
	
	
	
	/**
	 * 校验菜单编号是否存在信息的方法
	 * @param systemRoleVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/validateMenu")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_MENU,description="校验菜单编号是否存在")
	public Map<String,Object> validateMenu(SystemMenuVO systemMenuVO) throws Exception{
		logger.info("【菜单模块】-校验菜单编号是否存在输入参数systemMenuVO:{}",systemMenuVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			result = this.systemMenuServiceImpl.validateMenuExist(systemMenuVO);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【菜单管理】-校验菜单名称信息的方法发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【菜单模块】-校验菜单编号是否存在返回结果result:{}",result);
		return maps;
	}
	
	
	/**
	 * 校验菜单编号是否存在信息的方法
	 * @param systemRoleVO
	 * @return List<SystemMenuDTO>
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/selectMenuParentList")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_MENU,description="校验菜单编号是否存在信息的方法")
	public List<SystemMenuDTO> selectMenuParentList(SystemMenuVO systemMenuVO) throws Exception{
		logger.info("【菜单模块】-校验菜单编号是否存在输入参数systemMenuVO:{}",systemMenuVO);
		List<SystemMenuDTO> menuList = new ArrayList<SystemMenuDTO>();
		try {
			menuList = this.systemMenuServiceImpl.getMenuList(systemMenuVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【菜单管理】-查询菜单名称信息的方法发生异常",e);
		}
		return menuList;
	}
	
	/**
	 * 删除菜单信息的方法
	 * @param systemMenuVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/deleteMenu")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.DELETE,module = LoggerModule.SYSTEM_MENU,description="删除菜单信息")
	public Map<String,Object> deleteMenu(SystemMenuVO systemMenuVO) throws Exception{
		logger.info("【菜单模块】-删除菜单信息输入参数systemMenuVO:{}",systemMenuVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = -1;
		try {
			result = this.systemMenuServiceImpl.deleteSystemMenu(systemMenuVO);
			maps.put("result", result);
		} catch (Exception e) {
			logger.error("【菜单管理】-删除菜单信息的方法发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【菜单模块】-删除菜单信息返回结果:{}",result);
		return maps;
	}
	

	/**
	 * 根据编号查询菜单信息的方法
	 * @param systemMenuVO
	 * @param model
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:29:48
	 * @desc
	 */
	@RequestMapping("/getMenu")
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_MENU,description="根据编号查询菜单信息")
	public String getMenu(SystemMenuVO systemMenuVO,Model model) throws Exception{
		logger.info("【菜单管理】-根据编号信息的方法输入参数systemMenuVO:{},model：{}",systemMenuVO,model);
		try {
			SystemMenuDTO systemMenuDTO = this.systemMenuServiceImpl.getSystemMenu(systemMenuVO);
			model.addAttribute("systemMenuDTO", systemMenuDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【菜单管理】-查询菜单信息发生异常",e);
		}
		return "system/menu/menu_info";
	}
	
	
	/**
	 * 加载修改菜单信息的方法
	 * @param systemMenuVO
	 * @param model
	 * @return String
	 * @author julong
	 * @date 2017-10-28 上午11:56:11
	 */
	@RequestMapping(value="/loadEditMenu",method={RequestMethod.GET,RequestMethod.POST})
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_MENU,description="加载修改菜单信息的页面")
	public String loadEditMenu(SystemMenuVO systemMenuVO,Model model) throws Exception{
		logger.info("【菜单管理模块】-加载菜单修改界面的方法输入参数systemMenuVO:{},{}",systemMenuVO,model);
		try {
			SystemMenuDTO systemMenuDTO = new SystemMenuDTO();
			systemMenuDTO = this.systemMenuServiceImpl.getSystemMenu(systemMenuVO);
			model.addAttribute("systemMenuDTO", systemMenuDTO);//返回参数
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【菜单管理】-查询菜单信息发生异常",e);
		}
		return "system/menu/menu_edit";
	}
	
	/**
	 * 修改菜单信息的方法
	 * @param systemMenuVO
	 * @return
	 * @author julong
	 * @date 2017-10-28 上午11:57:44
	 */
	@RequestMapping("/updateMenu")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_ROLE,description="修改菜单信息的方法")
	public Map<String,Object> updateRole(SystemMenuVO systemMenuVO) throws Exception{
		logger.info("【菜单模块】-修改菜单信息输入参数systemMenuVO:{}",systemMenuVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemMenuServiceImpl.updateSystemMenu(systemMenuVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【菜单管理】-修改菜单信息发生异常",e);
			maps.put("result", -1);
		}
		return maps;
	}
}
