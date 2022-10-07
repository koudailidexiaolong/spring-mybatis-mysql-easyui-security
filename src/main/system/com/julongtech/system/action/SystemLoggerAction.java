package com.julongtech.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.julongtech.page.PageParam;
import com.julongtech.page.PageUtils;
import com.julongtech.system.action.vo.SystemLoggerVO;
import com.julongtech.system.aspect.LoggerMethod;
import com.julongtech.system.aspect.LoggerModule;
import com.julongtech.system.aspect.LoggerProxy;
import com.julongtech.system.service.SystemLoggerService;
import com.julongtech.system.service.dto.SystemLoggerDTO;

/**
 * 系统日志信息管理
 * @author julong
 * @date 2017-10-28 下午3:09:39
 */
@Controller
@RequestMapping("logger")
public class SystemLoggerAction {
	private static final Logger logger = LoggerFactory.getLogger(SystemLoggerAction.class);
	
	@Autowired
	private SystemLoggerService systemLoggerServiceImpl;
	

	/**
	 * 加载主界面的方法
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午12:53:40
	 */
	@RequestMapping("/index")
	@LoggerProxy(method = LoggerMethod.LOAD_PAGE,module = LoggerModule.SYSTEM_LOGGER,description="加载日志页面")
	public String loadPage() throws Exception{
		logger.info("【系统日志信息管理】-加载日志主界面");
		return "system/logger/logger_index";
	}
	
	/**
	 * 分页查新日志信息的方法
	 * @param systemLoggerVO
	 * @param page
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午4:17:56
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/selectLoggerListByPage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_LOGGER,description="分页查询登录日志")
	public Map<String,Object> selectLoggerListByPage(SystemLoggerVO systemLoggerVO,PageParam<SystemLoggerDTO> page) throws Exception {
		logger.info("【系统日志信息管理】-分页查询日志信息的方法:{}",systemLoggerVO);
		Map<String,Object> maps = new HashMap<String,Object>();
		try {
			//分页的方法
			PageInfo<SystemLoggerDTO> pages = (PageInfo<SystemLoggerDTO>) PageUtils.getPageHelper(page);
			//查询数据的方法
			PageHelper.startPage(pages.getPageNum(),pages.getPageSize());
			List<SystemLoggerDTO> list = this.systemLoggerServiceImpl.getSystemLoggerListByPage(systemLoggerVO,null);
			//分页获取的方法
			pages = new PageInfo<SystemLoggerDTO>(list);
			//格式化返回结果
			maps = PageUtils.formatPage(pages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【系统日志信息管理】-分页查询日志信息集合发生异常",e);
			throw new Exception(e);
		}
		return maps;
	}
}
