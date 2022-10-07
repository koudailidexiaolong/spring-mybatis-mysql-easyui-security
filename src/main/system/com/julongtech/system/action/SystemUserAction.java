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
import com.julongtech.system.action.vo.SystemUserVO;
import com.julongtech.system.aspect.LoggerMethod;
import com.julongtech.system.aspect.LoggerModule;
import com.julongtech.system.aspect.LoggerProxy;
import com.julongtech.system.service.RedisCacheService;
import com.julongtech.system.service.SystemUserService;
import com.julongtech.system.service.dto.SystemUserDTO;
import com.julongtech.system.session.UserSession;

/**
 * 用户业务处理模块
 * @author julong
 * @date 2017-10-19 上午8:48:04
 */
@Controller
@RequestMapping("user")
public class SystemUserAction {

	private static final Logger logger = LoggerFactory.getLogger(SystemUserAction.class);
	@Autowired
	private SystemUserService systemUserServiceImpl;
	@Autowired
	private RedisCacheService redisCacheServiceImpl;
	/**
	 * 加载主界面的方法
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午12:53:40
	 */
	@RequestMapping("/index")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_USER,description="加载用户主页")
	public String loadPage() throws Exception{
		logger.info("【系统用户模块】-进入加载角色菜单配置主界面的方法");
		return "system/user/user_index";
	}
	

	/**
	 * 分页查新用户信息的方法
	 * @param systemUserVO
	 * @param page
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午4:17:56
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getUserListByPage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_USER,description="分页查新用户信息的方法")
	public Map<String,Object> getUserListByPage(SystemUserVO systemUserVO,PageParam<SystemUserDTO> page) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		logger.info("【用户模块】-分页查询用户信息的方法:{},{}",systemUserVO,page);
		try {
			//分页的方法
			PageInfo<SystemUserDTO> pages = (PageInfo<SystemUserDTO>) PageUtils.getPageHelper(page);
			//查询数据的方法
			PageHelper.startPage(pages.getPageNum(),pages.getPageSize());
			List<SystemUserDTO> list = this.systemUserServiceImpl.getUserListByPage(systemUserVO);
			//分页获取的方法
			pages = new PageInfo<SystemUserDTO>(list);
			//格式化返回结果
			maps = PageUtils.formatPage(pages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理】-查询用户信息集合发生异常",e);
		}
		return maps;
	}
	
	/**
	 * 加载修改用户界面的方法
	 * @param systemUserVO
	 * @param model
	 * @return
	 * @author julong
	 * @date 2017-10-27 下午3:29:58
	 */
	@RequestMapping(value="/loadEditUser",method={RequestMethod.GET,RequestMethod.POST})
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_USER,description="加载修改用户界面的方法")
	public String loadEditUser(SystemUserVO systemUserVO,Model model) throws Exception{
		logger.info("【用户管理模块】-加载用户修改界面的方法:{}",systemUserVO);
		try {
			SystemUserDTO systemUserDTO = new SystemUserDTO();
			systemUserDTO = this.systemUserServiceImpl.getUser(systemUserVO);
			model.addAttribute("systemUserDTO", systemUserDTO);//返回参数
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理】-查询用户信息发生异常",e);
		}
		return "system/user/user_edit";
	}
	/**
	 * 加载修改用户界面的方法
	 * @param systemUserVO
	 * @param model
	 * @return
	 * @author julong
	 * @date 2017-10-27 下午3:29:58
	 */
	@RequestMapping(value="/loadCenterEditUser",method={RequestMethod.GET,RequestMethod.POST})
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_USER,description="加载修改用户界面的方法")
	public String loadCenterEditUser(SystemUserVO systemUserVO,Model model) throws Exception{
		logger.info("【用户管理-个人中心】-加载用户修改界面的方法:{}",systemUserVO);
		try {
			SystemUserDTO systemUserDTO = new SystemUserDTO();
			systemUserDTO = this.systemUserServiceImpl.getUser(systemUserVO);
			model.addAttribute("systemUserDTO", systemUserDTO);//返回参数
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理-个人中心】-查询用户信息发生异常",e);
		}
		return "system/center/user_edit";
	}
	
	/**
	 * 加载新增用户界面的方法
	 * @param systemUserVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:29:34
	 */
	@RequestMapping("/loadAddUser")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_USER,description="加载新增用户界面的方法")
	public String loadAddUser(SystemUserVO systemUserVO) throws Exception{
		logger.info("【用户管理模块】-加载用户新增界面的方法");
		return "system/user/user_add";
	}
	
	/**
	 * 修改用户信息的方法
	 * @author julong
	 * @date 2017-10-26 上午9:29:48
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_USER,description="修改用户信息的方法")
	public Map<String,Object> updateUser(SystemUserVO systemUserVO) throws Exception{
		logger.info("【用户模块】-修改信息的方法:{}",systemUserVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result =0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemUserServiceImpl.updateUser(systemUserVO, userSession);
			maps.put("result", result);
			//刷新缓存
			this.redisCacheServiceImpl.refreshUserCache();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理】-修改用户信息发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【用户模块】-修改用户信息返回结果:{}",systemUserVO);
		return maps;
	}

	/**
	 * 重置用户密码的方法
	 * @author julong
	 * @date 2017-10-26 上午9:29:48
	 */
	@RequestMapping("/resetPassword")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_USER,description="重置用户密码的方法")
	public Map<String,Object> resetPassword(SystemUserVO systemUserVO) throws Exception{
		logger.info("【用户模块】-重置用户密码的方法:{}",systemUserVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemUserServiceImpl.updateRestPassword(systemUserVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理】-修改用户信息发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【用户模块】-重置用户密码的方法:{}",result);
		return maps;
	}
	
	/**
	 * 修改用户信息的方法
	 * @author julong
	 * @date 2017-10-26 上午9:29:48
	 */
	@RequestMapping("/loadEditPassword")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_USER,description="修改用户信息的方法")
	public String loadEditPassword(SystemUserVO systemUserVO,Model model) throws Exception{
		logger.info("【用户模块-修改信息的方法】:{},{}",systemUserVO,model);
		return "system/center/user_edit_password";
	}
	
	/**
	 * 修改用户信息的方法
	 * @author julong
	 * @date 2017-10-26 上午9:29:48
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_USER,description="修改用户信息的方法")
	public Map<String,Object> updatePassword(SystemUserVO systemUserVO) throws Exception{
		logger.info("【用户模块】-修改密码信息的方法:{}",systemUserVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result =0;
		try {
			UserSession userSession = UserSession.getUserSession();
			systemUserVO.setUserId(userSession.getUserId());
			result = this.systemUserServiceImpl.updatePassword(systemUserVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理】-修改用户信息发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【用户模块】-修改密码信息的方法:{}",result);
		return maps;
	}
	
	
	
	/**
	 * 查询用户详情信息的方法
	 * @author julong
	 * @date 2017-10-26 上午9:29:48
	 */
	@RequestMapping("/getUser")
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_USER,description="查询用户详情信息的方法")
	public String getUser(SystemUserVO systemUserVO,Model model) throws Exception{
		logger.info("【用户模块】-查询用户详情信息的方法:{},{}",systemUserVO,model);
		try {
			SystemUserDTO systemUserDTO = this.systemUserServiceImpl.getUser(systemUserVO);
			model.addAttribute("systemUserDTO", systemUserDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理】-查询用户详情发生异常",e);
		}
		return "system/user/user_info";
	}
	
	
	/**
	 * 保存用户信息
	 * @param systemUserVO
	 * @return
	 * @author julong
	 * @date 2017-10-27 上午11:22:01
	 */
	@RequestMapping("/saveUser")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.INSERT,module = LoggerModule.SYSTEM_USER,description="保存用户信息")
	public Map<String,Object> saveUser(SystemUserVO systemUserVO) throws Exception{
		logger.info("【用户模块】-新增信息的方法:{}",systemUserVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemUserServiceImpl.saveUser(systemUserVO, userSession);
			//刷新缓存
			this.redisCacheServiceImpl.refreshUserCache();
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理】-新增用户信息发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【用户模块】-新增信息的方法:{}",result);
		return maps;
	}
	
	
	/**
	 * 删除用户信息的方法
	 * @param systemUserVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.DELETE,module = LoggerModule.SYSTEM_USER,description="删除用户信息")
	public Map<String,Object> deleteUser(SystemUserVO systemUserVO) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		logger.info("【用户模块-删除用户信息的方法】:{}",systemUserVO);
		int result =0;
		try {
			result = this.systemUserServiceImpl.deleteUser(systemUserVO);
			maps.put("result", result);
			this.redisCacheServiceImpl.refreshUserCache();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理】-删除用户信息的方法发生异常",e);
		}
		logger.info("【用户模块-删除用户信息的方法】:{}",result);
		return maps;
	}
	
	/**
	 * 校验用户信息的方法
	 * @param systemUserVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/validateUser")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_USER,description="校验用户信息")
	public Map<String,Object> validateUser(SystemUserVO systemUserVO) throws Exception{
		logger.info("【用户模块-校验用户是否存在的方法】:{}",systemUserVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			result = this.systemUserServiceImpl.countUser(systemUserVO);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理】-校验用户信息的方法发生异常",e);
		}
		logger.info("【用户模块-校验用户是否存在的方法】:{}",result);
		return maps;
	}
	/**
	 * 校验用户密码信息的方法
	 * @param systemUserVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/validatePassword")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_USER,description="校验用户密码信息的方法")
	public Map<String,Object> validatePassword(SystemUserVO systemUserVO) throws Exception{
		logger.info("【用户模块】-校验用户密码的方法:{}",systemUserVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			systemUserVO.setUserId(userSession.getUserId());
			result = this.systemUserServiceImpl.validatePassword(systemUserVO);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户管理】-校验用户信息的方法发生异常",e);
			maps.put("result", -1);
		}
		logger.info("【用户模块】-校验用户密码的方法:{}",result);
		return maps;
	}
	/**
	 * 修改用户状态信息方法
	 * 
	 * @param systemUserVO
	 * @return
	 * @author julong
	 * @date 2017-11-2 下午8:42:37
	 */
	@RequestMapping("/updateUserStatus")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_USER,description="修改用户状态信息方法")
	public Map<String, Object> updateUserStatus(SystemUserVO systemUserVO)  throws Exception{
		logger.info("【用户状态】-修改用户状态的方法:{}", systemUserVO);
		Map<String, Object> maps = new HashMap<String, Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemUserServiceImpl.updateUser(systemUserVO, userSession);
			maps.put("result", result);
			this.redisCacheServiceImpl.refreshUserCache();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【用户状态】-修改用户状态发生异常", e);
			maps.put("result", -1);
		}
		logger.info("【用户状态】-修改用户状态返回结果:{}", result);
		return maps;
	}

}
