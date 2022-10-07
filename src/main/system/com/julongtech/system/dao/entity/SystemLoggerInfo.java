package com.julongtech.system.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统日志信息类
 * @author julong 
 * @date Mon Oct 16 16:14:13 CST 2017
 * @database table SYSTEM_LOGGER
 */
public class SystemLoggerInfo implements Serializable{
	/**
	 * @author julong
	 * @date 2017-10-16 下午4:14:27
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * 日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列
	 */
	private String loggerId;

	/** 
	 * 日志类型
	 */
	private String loggerType;

	/** 
	 * 日志类型名称
	 */
	private String loggerTypeName;

	/** 
	 * 日志创建时间
	 */
	private Timestamp loggerCreateTime;

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
	private String loggerIp;

	/** 
	 * 操作的业务模块
	 */
	private String loggerModule;

	/** 
	 * 操作的业务模块的方法
	 */
	private String loggerModuleMethod;

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
	 * 日志描述
	 */
	private String loggerDescription;

	/** 
	 * 备用字段1
	 */
	private String loggerReserveA;

	/** 
	 * 备用字段2
	 */
	private String loggerReserveB;

	/** 
	 * 备用字段3
	 */
	private String loggerReserveC;

	/**
	 * 日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列
	 * @return  the value of SYSTEM_LOGGER.LOGGER_ID
	 */
	public String getLoggerId() {
		return loggerId;
	}

	/**
	 * 日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列
	 * @param loggerId the value for SYSTEM_LOGGER.LOGGER_ID
	 */
	public void setLoggerId(String loggerId) {
		this.loggerId = loggerId == null ? null : loggerId.trim();
	}

	/**
	 * 日志类型
	 * @return  the value of SYSTEM_LOGGER.LOGGER_TYPE
	 */
	public String getLoggerType() {
		return loggerType;
	}

	/**
	 * 日志类型
	 * @param loggerType the value for SYSTEM_LOGGER.LOGGER_TYPE
	 */
	public void setLoggerType(String loggerType) {
		this.loggerType = loggerType == null ? null : loggerType.trim();
	}

	/**
	 * 日志类型名称
	 * @return  the value of SYSTEM_LOGGER.LOGGER_TYPE_NAME
	 */
	public String getLoggerTypeName() {
		return loggerTypeName;
	}

	/**
	 * 日志类型名称
	 * @param loggerTypeName the value for SYSTEM_LOGGER.LOGGER_TYPE_NAME
	 */
	public void setLoggerTypeName(String loggerTypeName) {
		this.loggerTypeName = loggerTypeName == null ? null : loggerTypeName.trim();
	}

	/**
	 * 日志创建时间
	 * @return  the value of SYSTEM_LOGGER.LOGGER_CREATE_TIME
	 */
	public Timestamp getLoggerCreateTime() {
		return loggerCreateTime;
	}

	/**
	 * 日志创建时间
	 * @param loggerCreateTime the value for SYSTEM_LOGGER.LOGGER_CREATE_TIME
	 */
	public void setLoggerCreateTime(Timestamp loggerCreateTime) {
		this.loggerCreateTime = loggerCreateTime;
	}

	/**
	 * 组织机构
	 * @return  the value of SYSTEM_LOGGER.ORG_ID
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 组织机构
	 * @param orgId the value for SYSTEM_LOGGER.ORG_ID
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId == null ? null : orgId.trim();
	}

	/**
	 * 用户编号
	 * @return  the value of SYSTEM_LOGGER.USER_ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户编号
	 * @param userId the value for SYSTEM_LOGGER.USER_ID
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * 用户姓名
	 * @return  the value of SYSTEM_LOGGER.USER_NAME
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用户姓名
	 * @param userName the value for SYSTEM_LOGGER.USER_NAME
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * 日志IP
	 * @return  the value of SYSTEM_LOGGER.LOGGER_IP
	 */
	public String getLoggerIp() {
		return loggerIp;
	}

	/**
	 * 日志IP
	 * @param loggerIp the value for SYSTEM_LOGGER.LOGGER_IP
	 */
	public void setLoggerIp(String loggerIp) {
		this.loggerIp = loggerIp == null ? null : loggerIp.trim();
	}

	/**
	 * 操作的业务模块
	 * @return  the value of SYSTEM_LOGGER.LOGGER_MODULE
	 */
	public String getLoggerModule() {
		return loggerModule;
	}

	/**
	 * 操作的业务模块
	 * @param loggerModule the value for SYSTEM_LOGGER.LOGGER_MODULE
	 */
	public void setLoggerModule(String loggerModule) {
		this.loggerModule = loggerModule == null ? null : loggerModule.trim();
	}

	/**
	 * 操作的业务模块的方法
	 * @return  the value of SYSTEM_LOGGER.LOGGER_MODULE_METHOD
	 */
	public String getLoggerModuleMethod() {
		return loggerModuleMethod;
	}

	/**
	 * 操作的业务模块的方法
	 * @param loggerModuleMethod the value for SYSTEM_LOGGER.LOGGER_MODULE_METHOD
	 */
	public void setLoggerModuleMethod(String loggerModuleMethod) {
		this.loggerModuleMethod = loggerModuleMethod == null ? null : loggerModuleMethod.trim();
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
	 * @param loggerResponseTime the value for SYSTEM_LOGGER.LOGGER_RESPONSE_TIME
	 */
	public void setLoggerResponseTime(String loggerResponseTime) {
		this.loggerResponseTime = loggerResponseTime == null ? null : loggerResponseTime.trim();
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
	 * 日志描述
	 * @return  the value of SYSTEM_LOGGER.LOGGER_DESCRIPTION
	 */
	public String getLoggerDescription() {
		return loggerDescription;
	}
	/**
	 * 日志描述
	 * @param loggerDescription the value for SYSTEM_LOGGER.LOGGER_DESCRIPTION
	 */
	public void setLoggerDescription(String loggerDescription) {
		this.loggerDescription = loggerDescription == null ? null : loggerDescription.trim();
	}

	/**
	 * 备用字段1
	 * @return  the value of SYSTEM_LOGGER.LOGGER_RESERVE_A
	 */
	public String getLoggerReserveA() {
		return loggerReserveA;
	}

	/**
	 * 备用字段1
	 * @param loggerReserveA the value for SYSTEM_LOGGER.LOGGER_RESERVE_A
	 */
	public void setLoggerReserveA(String loggerReserveA) {
		this.loggerReserveA = loggerReserveA == null ? null : loggerReserveA.trim();
	}

	/**
	 * 备用字段2
	 * @return  the value of SYSTEM_LOGGER.LOGGER_RESERVE_B
	 */
	public String getLoggerReserveB() {
		return loggerReserveB;
	}

	/**
	 * 备用字段2
	 * @param loggerReserveB the value for SYSTEM_LOGGER.LOGGER_RESERVE_B
	 */
	public void setLoggerReserveB(String loggerReserveB) {
		this.loggerReserveB = loggerReserveB == null ? null : loggerReserveB.trim();
	}

	/**
	 * 备用字段3
	 * @return  the value of SYSTEM_LOGGER.LOGGER_RESERVE_C
	 */
	public String getLoggerReserveC() {
		return loggerReserveC;
	}

	/**
	 * 备用字段3
	 * @param loggerReserveC the value for SYSTEM_LOGGER.LOGGER_RESERVE_C
	 */
	public void setLoggerReserveC(String loggerReserveC) {
		this.loggerReserveC = loggerReserveC == null ? null : loggerReserveC.trim();
	}

	@Override
	public String toString() {
		return "SystemLoggerInfo [loggerId=" + loggerId + ", loggerType=" + loggerType + ", loggerTypeName="
				+ loggerTypeName + ", loggerCreateTime=" + loggerCreateTime + ", orgId=" + orgId + ", userId=" + userId
				+ ", userName=" + userName + ", loggerIp=" + loggerIp + ", loggerModule=" + loggerModule
				+ ", loggerModuleMethod=" + loggerModuleMethod + ", loggerResponseTime=" + loggerResponseTime
				+ ", loggerOperatingSystem=" + loggerOperatingSystem + ", loggerBrowserType=" + loggerBrowserType
				+ ", loggerBrowserVersion=" + loggerBrowserVersion + ", loggerDescription=" + loggerDescription
				+ ", loggerReserveA=" + loggerReserveA + ", loggerReserveB=" + loggerReserveB + ", loggerReserveC="
				+ loggerReserveC + "]";
	}



}