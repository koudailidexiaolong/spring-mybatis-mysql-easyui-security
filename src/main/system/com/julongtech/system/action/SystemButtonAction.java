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
import com.julongtech.system.action.vo.SystemButtonVO;
import com.julongtech.system.aspect.LoggerMethod;
import com.julongtech.system.aspect.LoggerModule;
import com.julongtech.system.aspect.LoggerProxy;
import com.julongtech.system.service.SystemButtonService;
import com.julongtech.system.service.dto.SystemButtonDTO;
import com.julongtech.system.session.UserSession;

/**
 * 按钮模块
 * @author julong
 * @date 2017-10-27 下午4:45:39
 */
@Controller
@RequestMapping("button")
public class SystemButtonAction {
	private static final Logger logger = LoggerFactory.getLogger(SystemButtonAction.class);
	
	@Autowired
	private SystemButtonService systemButtonServiceImpl;
	
	/**
	 * 加载主界面的方法
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午12:53:40
	 */
	@RequestMapping("/index")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_BUTTON,description="加载按钮主页面")
	public String loadPage() throws Exception{
		logger.info("【按钮模块】-加载主界面");
		return "system/button/button_index";
	}
	
	/**
	 * 分页查询按钮基本信息集合的方法
	 * @param systemButtonVO
	 * @param page
	 * @return Map<String,Object>
	 * @author julong
	 * @date 2017-10-28 上午11:53:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/selectButtonListByPage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_BUTTON,description="分页查询按钮列表")
	public Map<String,Object> selectButtonListByPage(SystemButtonVO systemButtonVO,PageParam<?> page) throws Exception{
		Map<String,Object> maps = new HashMap<String,Object>();
		logger.debug("【按钮模块】-分页查询按钮信息的方法输入参数systemButtonVO:{},page：{}",systemButtonVO,page);
		try {
			//分页的方法
			PageInfo<SystemButtonDTO> pages = (PageInfo<SystemButtonDTO>) PageUtils.getPageHelper(page);
			//查询数据的方法
			PageHelper.startPage(pages.getPageNum(),pages.getPageSize());
			List<SystemButtonDTO> list = this.systemButtonServiceImpl.selectSystemButtonListByPage(systemButtonVO);
			//分页获取的方法
			pages = new PageInfo<SystemButtonDTO>(list);
			//格式化返回结果
			maps = PageUtils.formatPage(pages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【按钮管理】-查询按钮信息集合发生异常",e);
			throw new Exception(e);
		}
		return maps;
	}
	
	/**
	 * 加载修改按钮信息的方法
	 * @param systemButtonVO
	 * @param model
	 * @return String
	 * @author julong
	 * @date 2017-10-28 上午11:56:11
	 */
	@RequestMapping(value="/loadEditButton",method={RequestMethod.GET,RequestMethod.POST})
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_BUTTON,description="加载修改按钮信息的方法")
	public String loadEditButton(SystemButtonVO systemButtonVO,Model model) throws Exception{
		logger.info("【按钮管理模块】-加载按钮修改界面的方法:{},{}",systemButtonVO,model);
		try {
			SystemButtonDTO systemButtonDTO = new SystemButtonDTO();
			systemButtonDTO = this.systemButtonServiceImpl.getSystemButton(systemButtonVO);
			model.addAttribute("systemButtonDTO", systemButtonDTO);//返回参数
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【按钮管理】-查询按钮信息发生异常",e);
			throw new Exception(e);
		}
		return "system/button/button_edit";
	}
	
	/**
	 * 加载新增按钮界面的方法
	 * @param systemButtonVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:29:34
	 */
	@RequestMapping("/loadAddButton")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_BUTTON,description="加载新增按钮界面的方法")
	public String loadAddButton(SystemButtonVO systemButtonVO) throws Exception{
		logger.info("【按钮管理模块】-加载按钮新增界面的方法");
		return "system/button/button_add";
	}
	
	
	/**
	 * 修改按钮信息的方法
	 * @param systemButtonVO
	 * @return
	 * @author julong
	 * @date 2017-10-28 上午11:57:44
	 */
	@RequestMapping("/updateButton")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_BUTTON,description="修改按钮信息的方法")
	public Map<String,Object> updateButton(SystemButtonVO systemButtonVO) throws Exception{
		logger.info("【按钮模块】-修改信息输入参数systemButtonVO:{}",systemButtonVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemButtonServiceImpl.updateSystemButton(systemButtonVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【按钮管理】-修改按钮信息发生异常",e);
			throw new Exception(e);
		}
		return maps;
	}
	/**
	 * 修改按钮信息的方法
	 * @author julong
	 * @date 2017-10-26 上午9:29:48
	 */
	@RequestMapping("/updateButtonStatus")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.UPDATE,module = LoggerModule.SYSTEM_BUTTON,description="修改按钮信息的方法")
	public Map<String,Object> updateButtonStatus(SystemButtonVO systemButtonVO) throws Exception{
		logger.info("【按钮模块】-修改状态信息的方法:{}",systemButtonVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemButtonServiceImpl.updateSystemButtonStatus(systemButtonVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【按钮管理】-修改按钮信息发生异常",e);
			throw new Exception(e);
		}
		return maps;
	}

	/**
	 * 根据编号按钮信息的方法
	 * @param systemButtonVO
	 * @param model
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:29:48
	 * @desc
	 */
	@RequestMapping("/getButton")
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_BUTTON,description="根据编号按钮信息的方法")
	public String getButton(SystemButtonVO systemButtonVO,Model model) throws Exception{
		logger.info("【按钮管理】-根据编号信息的方法:{},{}",systemButtonVO,model);
		try {
			SystemButtonDTO systemButtonDTO = this.systemButtonServiceImpl.getSystemButton(systemButtonVO);
			model.addAttribute("systemButtonDTO", systemButtonDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【按钮管理】-查询按钮信息发生异常",e);
			throw new Exception(e);
		}
		return "system/button/button_info";
	}
	
	
	/**
	 * 新增按钮信息
	 * @param systemButtonVO
	 * @return
	 * @author julong
	 * @date 2017-10-27 上午11:22:01
	 */
	@RequestMapping("/saveButton")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.INSERT,module = LoggerModule.SYSTEM_BUTTON,description="新增按钮信息")
	public Map<String,Object> saveButton(SystemButtonVO systemButtonVO) throws Exception{
		logger.info("【按钮模块】-新增信息的方法:{}",systemButtonVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			UserSession userSession = UserSession.getUserSession();
			result = this.systemButtonServiceImpl.saveSystemButton(systemButtonVO, userSession);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【按钮管理】-新增按钮信息发生异常",e);
			throw new Exception(e);
		}
		logger.info("【按钮模块】-新增按钮信息返回结果:{}",result);
		return maps;
	}
	
	/**
	 * 删除按钮信息的方法
	 * @param systemButtonVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/deleteButton")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.DELETE,module = LoggerModule.SYSTEM_BUTTON,description="删除按钮信息的方法")
	public Map<String,Object> deleteButton(SystemButtonVO systemButtonVO) throws Exception{
		logger.info("【按钮模块】-删除按钮信息的方法:{}",systemButtonVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = -1;
		try {
			result = this.systemButtonServiceImpl.deleteSystemButton(systemButtonVO);
			maps.put("result", result);
		} catch (Exception e) {
			logger.error("【按钮管理】-删除按钮信息的方法发生异常",e);
			throw new Exception(e);
		}
		logger.info("【按钮模块】-删除按钮信息返回结果:{}",result);
		return maps;
	}
	/**
	 * 校验按钮名称信息的方法
	 * @param systemButtonVO
	 * @return
	 * @author julong
	 * @date 2017-10-26 上午9:28:08
	 */
	@RequestMapping("/validateButton")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_BUTTON,description="校验按钮名称信息的方法")
	public Map<String,Object> validateButton(SystemButtonVO systemButtonVO) throws Exception{
		logger.info("【按钮模块】-校验按钮名称是否存在的方法:{}",systemButtonVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		int result = 0;
		try {
			result = this.systemButtonServiceImpl.validateButtonExist(systemButtonVO);
			maps.put("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【按钮管理】-校验按钮名称信息的方法发生异常",e);
			throw new Exception(e);
		}
		logger.info("【按钮模块】-校验按钮名称是否存在返回结果result:{}",result);
		return maps;
	}
	
}
