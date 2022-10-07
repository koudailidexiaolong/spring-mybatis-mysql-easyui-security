package com.julongtech.system.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.julongtech.system.action.vo.SystemUserVO;
import com.julongtech.system.aspect.LoggerMethod;
import com.julongtech.system.aspect.LoggerModule;
import com.julongtech.system.aspect.LoggerProxy;
import com.julongtech.system.service.AuthorizeService;
import com.julongtech.system.service.SystemUserService;
import com.julongtech.system.service.dto.SystemButtonDTO;
import com.julongtech.system.service.dto.SystemMenuDTO;
import com.julongtech.system.service.dto.SystemUserDTO;
import com.julongtech.system.session.UserSession;
import com.julongtech.system.util.CaptchaUtils;
import com.julongtech.system.util.DefaultUtil;
import com.julongtech.util.PropertyPlaceholderUtils;
import com.julongtech.util.crypto.DESUtil;
import com.julongtech.util.crypto.RSAUtil;

/**
 * 登录系统处理类
 * @author julong
 * @date 2017-10-22 下午12:41:19
 */
@Controller
@RequestMapping("login")
public class LoginAction {

	private static final Logger logger = LoggerFactory.getLogger(SystemUserAction.class);

	@Autowired 
	private SystemUserService systemUserServiceImpl;

	@Autowired
	private AuthorizeService authorizeServiceImpl;

	@Autowired
	private HttpSession httpSession;

	
	/**
	 * 登录的方法
	 * @param userId
	 * @param userPassword
	 * @return
	 * @author julong
	 * @date 2017-10-24 上午9:40:47
	 */
	@RequestMapping("/login")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.LOGIN,module = LoggerModule.SYSTEM_LOGIN,description="登录的方法")
	public Map<String,Object> login(@RequestParam("userId") String userId,@RequestParam("userPassword") String userPassword,@RequestParam("captcha") String captcha)  throws Exception{
		logger.info("【系统登录模块】-登录系统{},{}",userId,userPassword);
		//加载系统菜单列表 此处应该放入缓存中
		Map<String,Object> maps = new Hashtable<String, Object>();
		try {
			//判断收入是否为空
			if(StringUtils.isNotEmpty(captcha) && StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(userPassword)){
				String code = (String) this.httpSession.getAttribute("CODE");
				String times = this.httpSession.getAttribute("TIME")+"";
				long time = 0;
				if(StringUtils.isNotBlank(times) && null != times && !"null".equals(times)){
					time = Long.valueOf(times);
				}
				if(0 < time  && (System.currentTimeMillis()-time) < 60000){
					if(code.equalsIgnoreCase(captcha)){
						//解密用户输入的密码
						userPassword = RSAUtil.getInstance().privateDecrypt(Base64.decodeBase64(userPassword));
						SystemUserVO systemUserVO = new SystemUserVO();
						systemUserVO.setUserId(userId);
						systemUserVO.setUserPassword(userPassword);
						SystemUserDTO systemUserDTO = this.systemUserServiceImpl.getUser(systemUserVO);
						if(null != systemUserDTO){
							if(systemUserDTO.getUserStatus().equals(DefaultUtil.DEFAULT_STATUS_TRUE)){
								//通过加密秘钥解密密码
								String password = DESUtil.encrypt(userPassword, PropertyPlaceholderUtils.getInstance().getProperty("USERKEY"));
								if(StringUtils.isNotEmpty(systemUserDTO.getUserPassword()) && systemUserDTO.getUserPassword().equals(password)){
									maps.put("result", true);
//									UserSession userSession = new UserSession(systemUserDTO,null);
//									this.httpSession.setAttribute("userSession", userSession);
									logger.info("【系统登录模块】-登录系统成功{}",userId);
								}else{
									String count = (String) this.httpSession.getAttribute("COUNT");
									if(StringUtils.isNotEmpty(count)){
										int i = Integer.valueOf(count)+1;
										this.httpSession.setAttribute("COUNT", i+"");
										//判断 密码是否输入三次 
										if(Integer.valueOf(count) == 3){
											SystemUserVO user = new SystemUserVO();
											user.setUserId(userId);
											user.setUserStatus(DefaultUtil.DEFAULT_STATUS_FALSE);
											int result = this.systemUserServiceImpl.updateUserStatus(user, null);
											logger.info("【系统登录模块】-登录系统密码输入错误超过三次，请联系管理员进行解锁！{},{}",userId,result);
											maps.put("message", "密码输入错误超过三次，请联系管理员进行解锁！");
											this.httpSession.removeAttribute("COUNT");
											this.httpSession.invalidate();
										}else{
											maps.put("message", "用户名或密码错误，您已输错"+i+"次，三次将自动锁定账户！");
											this.httpSession.setAttribute("COUNT", i+"");
										}
									}else{
										this.httpSession.setAttribute("COUNT", 1+"");
										maps.put("message", "用户名或密码错误，您已输错1次，三次将自动锁定账户！");
									}
									maps.put("result", false);
								}
							}else{
								logger.debug("【登录模块】-您的账户已经被锁定，请联系管理员进行解锁！{}",userId);
								maps.put("message", "您的账户已经被锁定，请联系管理员进行解锁！");
								this.httpSession.removeAttribute("COUNT");
								this.httpSession.invalidate();
							}
						}else{
							logger.debug("【登录模块】-登陆失败没有找到匹配账户信息！{}",userId);
							maps.put("result", false);
							maps.put("message", "用户名或密码错误！");
						}
					}else{
						maps.put("result", false);
						maps.put("message", "验证码输入错误！");
					}
				}else{
					maps.put("result", false);
					maps.put("message", "验证码已失效，请重新输入！");
				}
			}else{
				maps.put("result", false);
				maps.put("message", "用户名或密码不能为空！");
			}
			this.httpSession.removeAttribute("CODE");
			this.httpSession.removeAttribute("TIME");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("【登录模块】-登录系统发生异常：",e);
			throw new Exception(e);
		}
		return maps;
	}

	/**
	 * 生成验证码的方法
	 * @param request
	 * @param response
	 * @author julong
	 * @date 2018-5-31 下午6:22:15
	 */
	@RequestMapping("/captcha")
	@ResponseBody
	@LoggerProxy(method = LoggerMethod.LOGIN,module = LoggerModule.SYSTEM_LOGIN,description="获取生成验证码")
	public void captcha(HttpServletRequest request,HttpServletResponse response)  throws Exception{
		response.setContentType("JPEG");
		BufferedImage image = CaptchaUtils.getInstance().createImage(100, 38);
		try {
			request.getSession().setAttribute("SPRING_CAPTCHA_CODE", CaptchaUtils.getInstance().captcha);
			request.getSession().setAttribute("SPRING_CAPTCHA_TIME",System.currentTimeMillis());
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("【登录模块】-生成验证码发生异常：",e);
			throw new Exception(e);
		}
	}



	/**
	 * 跳转到主界面
	 * @return
	 * @author julong
	 * @date 2017-10-31 下午2:22:20
	 */
	@RequestMapping("/index")
	@LoggerProxy(method = LoggerMethod.LOGIN,module = LoggerModule.SYSTEM_LOGIN,description="登录跳转主页面")
	public String index(Model model)  throws Exception{
		logger.info("【跳转主界面】{}",model);
		//查询权限
		try {
			//获取session
			UserSession userSession = UserSession.getUserSession();
			if(userSession == null){
				logger.info("【跳转主界面 】session 为空");
				return "refush";
			}
			SystemUserVO systemUserVO = new SystemUserVO();
			systemUserVO.setUserId(userSession.getUserId());
			//根据用户查询菜单
			List<SystemMenuDTO> list = this.authorizeServiceImpl.getUserMenu(systemUserVO);
			//按钮
			List<SystemButtonDTO> buttonList = this.authorizeServiceImpl.getUserMenuButton(systemUserVO);
			this.httpSession.setAttribute("button", buttonList);
			model.addAttribute("menuList", list);
//			this.httpSession.setAttribute("userSession", userSession);
			logger.debug("【用户拥有的菜单集合】{}",list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【登录模块】-查询系统权限发生异常：",e);
			throw new Exception(e);
		}
		return "index";
	}

	/**
	 * 系统退出登录
	 * @return
	 * @author julong
	 * @date 2017-10-31 下午7:23:35
	 */
	@RequestMapping(value = "/loginOut",method={RequestMethod.GET,RequestMethod.POST})
	@LoggerProxy(method = LoggerMethod.LOGOUT,module = LoggerModule.SYSTEM_LOGIN,description="系统退出登录")
	public String loginOut(HttpServletRequest request,HttpServletResponse response)  throws Exception{
		logger.info("退出系统,进行数据销毁");
		//获取session
		this.httpSession.removeAttribute("COUNT");
		this.httpSession.invalidate();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "refush";
	}
	/**
	 * 获取密钥
	 * @return
	 * @author julong
	 * @date 2017-10-31 下午7:23:35
	 */
	@RequestMapping(value = "/getKey",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
//	@LoggerProxy(method = LoggerMethod.SELECT,module = LoggerModule.SYSTEM_LOGIN)
	public Map<String,Object> getKey()  throws Exception{
		logger.info("获取公钥");
		Map<String,Object> maps = new HashMap<String, Object>();
		maps.put("result", RSAUtil.publicKey);
		return maps;
	}
}
