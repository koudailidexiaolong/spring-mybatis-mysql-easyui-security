package com.julongtech.security.configuration;

import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.julongtech.util.PropertyPlaceholderUtils;
import com.julongtech.util.crypto.DESUtil;
import com.julongtech.util.crypto.RSAUtil;

/**
 * 验证自定义密码 拦截处理类
 * @author julong
 * @date 2022年9月13日 下午10:48:07
 * @desc 
 */
public class UserDaoAuthenticationProvider extends DaoAuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoAuthenticationProvider.class);

	@SuppressWarnings("deprecation")
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		logger.debug("【自定义校验密码】-additionalAuthenticationChecks");
		if (authentication.getCredentials() == null) {
            logger.debug("【自定义校验密码】-Authentication failed: no credentials provided");
            throw new BadCredentialsException("密码不能为空", userDetails);
        }
		//获取密码
        String presentedPassword = authentication.getCredentials().toString();
        try {
        	 //密码ras解密
            presentedPassword = RSAUtil.getInstance().privateDecrypt(Base64.decodeBase64(presentedPassword));
            logger.debug("【自定义校验密码】-rsa解密：{}",presentedPassword);
		} catch (Exception e) {
			// TODO: handle exception
			 logger.debug("【自定义校验密码】-解密出现错误",e);
			throw new BadCredentialsException("密码解密出现错误", userDetails);
		}
        //对密码进行加密
        String userPassword = DESUtil.encrypt(presentedPassword, PropertyPlaceholderUtils.getInstance().getProperty("USERKEY"));
        logger.debug("【自定义校验密码】-userPassword：{}",userPassword);
        logger.debug("【自定义校验密码】-getPassword：{}", userDetails.getPassword());
        //比对密码是否相等
        if (!userPassword.equals(userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }
        //重新定义对象
        authentication = new UsernamePasswordAuthenticationToken(authentication.getName(),userPassword);
		super.additionalAuthenticationChecks(userDetails, authentication);
	}

	
	
}
