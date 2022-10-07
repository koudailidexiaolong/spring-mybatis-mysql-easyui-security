package com.julongtech.system.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统异常日志信息类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 * @database table SYSTEM_LOGGER_EXCEPTION
 */
public class SystemLoggerExceptionInfo  implements Serializable{
    /**
	 * @author julong
	 * @date 2017-10-16 下午3:55:08
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * 日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列
     */
    private String loggerExceptionId;

    /** 
     * 日志类型
     */
    private String loggerExceptionType;

    /** 
     * 日志创建时间
     */
    private Timestamp loggerExceptionCreateTime;

    /** 
     * 组织机构
     */
    private String orgId;

    /** 
     * 用户编号
     */
    private String userId;

    /** 
     * 用户姓名
     */
    private String userName;

    /** 
     * 日志IP
     */
    private String loggerExceptionIp;

    /** 
     * 日志调用模块
     */
    private String loggerExceptionModule;

    /** 
     * 日志调用模块方法
     */
    private String loggerExceptionMethod;

    /** 
     * 日志描述
     */
    private String loggerExceptionDescription;
	/** 
	 * 访问响应时间 毫秒
	 */
	private String loggerResponseTime;
    
    /** 
	 * 使用的操作系统
	 */
	private String loggerOperatingSystem;

	/** 
	 * 浏览器类型
	 */
	private String loggerBrowserType;

	/** 
	 * 浏览器版本
	 */
	private String loggerBrowserVersion;
    
    /** 
     * 日志异常信息
     */
    private String loggerExceptionContext;

    /** 
     * 备用字段1
     */
    private String loggerExceptionReserveA;

    /** 
     * 备用字段2
     */
    private String loggerExceptionReserveB;

    /** 
     * 备用字段3
     */
    private String loggerExceptionReserveC;


    /**
     * 日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_ID
     */
    public String getLoggerExceptionId() {
        return loggerExceptionId;
    }

    /**
     * 日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列
     * @param loggerExceptionId the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_ID
     */
    public void setLoggerExceptionId(String loggerExceptionId) {
        this.loggerExceptionId = loggerExceptionId == null ? null : loggerExceptionId.trim();
    }

    /**
     * 日志类型
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_TYPE
     */
    public String getLoggerExceptionType() {
        return loggerExceptionType;
    }

    /**
     * 日志类型
     * @param loggerExceptionType the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_TYPE
     */
    public void setLoggerExceptionType(String loggerExceptionType) {
        this.loggerExceptionType = loggerExceptionType == null ? null : loggerExceptionType.trim();
    }

    /**
     * 日志创建时间
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_CREATE_TIME
     */
    public Timestamp getLoggerExceptionCreateTime() {
        return loggerExceptionCreateTime;
    }

    /**
     * 日志创建时间
     * @param loggerExceptionTime the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_CREATE_TIME
     */
    public void setLoggerExceptionCreateTime(Timestamp loggerExceptionCreateTime) {
        this.loggerExceptionCreateTime = loggerExceptionCreateTime;
    }

    /**
     * 组织机构
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.ORG_ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 组织机构
     * @param orgId the value for SYSTEM_LOGGER_EXCEPTION.ORG_ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 用户编号
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户编号
     * @param userId the value for SYSTEM_LOGGER_EXCEPTION.USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 用户姓名
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户姓名
     * @param userName the value for SYSTEM_LOGGER_EXCEPTION.USER_NAME
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }


	/**
	 * 访问响应时间 毫秒
	 * @return  the value of SYSTEM_LOGGER.LOGGER_RESPONSE_TIME
	 */
	public String getLoggerResponseTime() {
		return loggerResponseTime;
	}

	/**
	 * 访问响应时间 毫秒
	 * @param loggerResponseTime the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_RESPONSE_TIME
	 */
	public void setLoggerResponseTime(String loggerResponseTime) {
		this.loggerResponseTime = loggerResponseTime == null ? null : loggerResponseTime.trim();
	}

	
    /**
     * 日志IP
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_IP
     */
    public String getLoggerExceptionIp() {
        return loggerExceptionIp;
    }

    /**
     * 日志IP
     * @param loggerExceptionIp the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_IP
     */
    public void setLoggerExceptionIp(String loggerExceptionIp) {
        this.loggerExceptionIp = loggerExceptionIp == null ? null : loggerExceptionIp.trim();
    }

    /**
     * 日志调用模块
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_MODULE
     */
    public String getLoggerExceptionModule() {
        return loggerExceptionModule;
    }

    /**
     * 日志调用模块
     * @param loggerExceptionModule the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_MODULE
     */
    public void setLoggerExceptionModule(String loggerExceptionModule) {
        this.loggerExceptionModule = loggerExceptionModule == null ? null : loggerExceptionModule.trim();
    }

    /**
     * 日志调用模块方法
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_METHOD
     */
    public String getLoggerExceptionMethod() {
        return loggerExceptionMethod;
    }

    /**
     * 日志调用模块方法
     * @param loggerExceptionMethod the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_METHOD
     */
    public void setLoggerExceptionMethod(String loggerExceptionMethod) {
        this.loggerExceptionMethod = loggerExceptionMethod == null ? null : loggerExceptionMethod.trim();
    }

    /**
     * 日志描述
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_Description
     */
    public String getLoggerExceptionDescription() {
        return loggerExceptionDescription;
    }

    /**
     * 日志描述
     * @param loggerExceptionDescription the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_Description
     */
    public void setLoggerExceptionDescription(String loggerExceptionDescription) {
        this.loggerExceptionDescription = loggerExceptionDescription == null ? null : loggerExceptionDescription.trim();
    }

    /**
	 * 系统版本
	 * @return  the value of SYSTEM_LOGGER.LOGGER_OPERATING_SYSTEM
	 */
	public String getLoggerOperatingSystem() {
		return loggerOperatingSystem;
	}
	/**
	 * 系统版本
	 * @param loggerOperatingSystem the value for SYSTEM_LOGGER.LOGGER_OPERATING_SYSTEM
	 */
	public void setLoggerOperatingSystem(String loggerOperatingSystem) {
		this.loggerOperatingSystem = loggerOperatingSystem == null ? null : loggerOperatingSystem.trim();
	}

	/**
	 * 浏览器类型
	 * @return  the value of SYSTEM_LOGGER.LOGGER_BROWSER_TYPE
	 */
	public String getLoggerBrowserType() {
		return loggerBrowserType;
	}
	/**
	 * 浏览器类型
	 * @param loggerBrowserType the value for SYSTEM_LOGGER.LOGGER_BROWSER_TYPE
	 */
	public void setLoggerBrowserType(String loggerBrowserType) {
		this.loggerBrowserType = loggerBrowserType == null ? null : loggerBrowserType.trim();
	}

	/**
	 * 浏览器版本
	 * @return  the value of SYSTEM_LOGGER.LOGGER_BROWSER_VERSION
	 */
	public String getLoggerBrowserVersion() {
		return loggerBrowserVersion;
	}
	/**
	 * 浏览器版本
	 * @param loggerBrowserVersion the value for SYSTEM_LOGGER.LOGGER_BROWSER_VERSION
	 */
	public void setLoggerBrowserVersion(String loggerBrowserVersion) {
		this.loggerBrowserVersion = loggerBrowserVersion == null ? null : loggerBrowserVersion.trim();
	}
	
    /**
     * 备用字段1
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_RESERVE_A
     */
    public String getLoggerExceptionReserveA() {
        return loggerExceptionReserveA;
    }

    /**
     * 备用字段1
     * @param loggerExceptionReserveA the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_RESERVE_A
     */
    public void setLoggerExceptionReserveA(String loggerExceptionReserveA) {
        this.loggerExceptionReserveA = loggerExceptionReserveA == null ? null : loggerExceptionReserveA.trim();
    }

    /**
     * 备用字段2
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_RESERVE_B
     */
    public String getLoggerExceptionReserveB() {
        return loggerExceptionReserveB;
    }

    /**
     * 备用字段2
     * @param loggerExceptionReserveB the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_RESERVE_B
     */
    public void setLoggerExceptionReserveB(String loggerExceptionReserveB) {
        this.loggerExceptionReserveB = loggerExceptionReserveB == null ? null : loggerExceptionReserveB.trim();
    }

    /**
     * 备用字段3
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_RESERVE_C
     */
    public String getLoggerExceptionReserveC() {
        return loggerExceptionReserveC;
    }

    /**
     * 备用字段3
     * @param loggerExceptionReserveC the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_RESERVE_C
     */
    public void setLoggerExceptionReserveC(String loggerExceptionReserveC) {
        this.loggerExceptionReserveC = loggerExceptionReserveC == null ? null : loggerExceptionReserveC.trim();
    }

    /**
     * 日志异常信息
     * @return  the value of SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_CONTEXT
     */
    public String getLoggerExceptionContext() {
        return loggerExceptionContext;
    }

    /**
     * 日志异常信息
     * @param loggerExceptionContext the value for SYSTEM_LOGGER_EXCEPTION.LOGGER_EXCEPTION_CONTEXT
     */
    public void setLoggerExceptionContext(String loggerExceptionContext) {
        this.loggerExceptionContext = loggerExceptionContext == null ? null : loggerExceptionContext.trim();
    }

	@Override
	public String toString() {
		return "SystemLoggerExceptionInfo [loggerExceptionId=" + loggerExceptionId + ", loggerExceptionType="
				+ loggerExceptionType + ", loggerExceptionCreateTime=" + loggerExceptionCreateTime + ", orgId=" + orgId + ", userId="
				+ userId + ", userName=" + userName + ", loggerExceptionIp=" + loggerExceptionIp + ", loggerExceptionModule="
				+ loggerExceptionModule + ", loggerExceptionMethod=" + loggerExceptionMethod + ", loggerExceptionDescription="
				+ loggerExceptionDescription + ", loggerResponseTime=" + loggerResponseTime + ", loggerOperatingSystem="
				+ loggerOperatingSystem + ", loggerBrowserType=" + loggerBrowserType + ", loggerBrowserVersion="
				+ loggerBrowserVersion + ", loggerExceptionContext=" + loggerExceptionContext + ", loggerExceptionReserveA="
				+ loggerExceptionReserveA + ", loggerExceptionReserveB=" + loggerExceptionReserveB
				+ ", loggerExceptionReserveC=" + loggerExceptionReserveC + "]";
	}

	

	
    
}