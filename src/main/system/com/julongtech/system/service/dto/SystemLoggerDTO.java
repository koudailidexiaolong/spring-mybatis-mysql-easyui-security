package com.julongtech.system.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 系统日志信息类
 * @author julong 
 * @date Mon Oct 16 16:14:13 CST 2017
 */
public class SystemLoggerDTO implements Serializable{
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
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
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
	 * 日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列
	 * @return loggerId
	 */
	public String getLoggerId() {
		return this.loggerId;
	}

	/**
	 * 日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列
	 * @param loggerId
	 */
	public void setLoggerId(String loggerId) {
		this.loggerId = loggerId;
	}

	/**
	 * 日志类型
	 * @return loggerType
	 */
	public String getLoggerType() {
		return this.loggerType;
	}

	/**
	 * 日志类型
	 * @param loggerType
	 */
	public void setLoggerType(String loggerType) {
		this.loggerType = loggerType;
	}

	/**
	 * 日志类型名称
	 * @return loggerTypeName
	 */
	public String getLoggerTypeName() {
		return this.loggerTypeName;
	}

	/**
	 * 日志类型名称
	 * @param loggerTypeName
	 */
	public void setLoggerTypeName(String loggerTypeName) {
		this.loggerTypeName = loggerTypeName;
	}

	/**
	 * 日志创建时间
	 * @return loggerCreateTime
	 */
	public Timestamp getLoggerCreateTime() {
		return this.loggerCreateTime;
	}

	/**
	 * 日志创建时间
	 * @param loggerCreateTime
	 */
	public void setLoggerCreateTime(Timestamp loggerCreateTime) {
		this.loggerCreateTime = loggerCreateTime;
	}

	/**
	 * 组织机构
	 * @return orgId
	 */
	public String getOrgId() {
		return this.orgId;
	}

	/**
	 * 组织机构
	 * @param orgId
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 用户编号
	 * @return userId
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 用户编号
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 用户姓名
	 * @return userName
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 用户姓名
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 日志IP
	 * @return loggerIp
	 */
	public String getLoggerIp() {
		return this.loggerIp;
	}

	/**
	 * 日志IP
	 * @param loggerIp
	 */
	public void setLoggerIp(String loggerIp) {
		this.loggerIp = loggerIp;
	}

	/**
	 * 操作的业务模块
	 * @return loggerModule
	 */
	public String getLoggerModule() {
		return this.loggerModule;
	}

	/**
	 * 操作的业务模块
	 * @param loggerModule
	 */
	public void setLoggerModule(String loggerModule) {
		this.loggerModule = loggerModule;
	}

	/**
	 * 操作的业务模块的方法
	 * @return loggerModuleMethod
	 */
	public String getLoggerModuleMethod() {
		return this.loggerModuleMethod;
	}

	/**
	 * 操作的业务模块的方法
	 * @param loggerModuleMethod
	 */
	public void setLoggerModuleMethod(String loggerModuleMethod) {
		this.loggerModuleMethod = loggerModuleMethod;
	}

	/**
	 * 访问响应时间 毫秒
	 * @return loggerResponseTime
	 */
	public String getLoggerResponseTime() {
		return this.loggerResponseTime;
	}

	/**
	 * 访问响应时间 毫秒
	 * @param loggerResponseTime
	 */
	public void setLoggerResponseTime(String loggerResponseTime) {
		this.loggerResponseTime = loggerResponseTime;
	}

	/**
	 * 使用的操作系统
	 * @return loggerOperatingSystem
	 */
	public String getLoggerOperatingSystem() {
		return this.loggerOperatingSystem;
	}

	/**
	 * 使用的操作系统
	 * @param loggerOperatingSystem
	 */
	public void setLoggerOperatingSystem(String loggerOperatingSystem) {
		this.loggerOperatingSystem = loggerOperatingSystem;
	}

	/**
	 * 浏览器类型
	 * @return loggerBrowserType
	 */
	public String getLoggerBrowserType() {
		return this.loggerBrowserType;
	}

	/**
	 * 浏览器类型
	 * @param loggerBrowserType
	 */
	public void setLoggerBrowserType(String loggerBrowserType) {
		this.loggerBrowserType = loggerBrowserType;
	}

	/**
	 * 浏览器版本
	 * @return loggerBrowserVersion
	 */
	public String getLoggerBrowserVersion() {
		return this.loggerBrowserVersion;
	}

	/**
	 * 浏览器版本
	 * @param loggerBrowserVersion
	 */
	public void setLoggerBrowserVersion(String loggerBrowserVersion) {
		this.loggerBrowserVersion = loggerBrowserVersion;
	}

	/**
	 * 日志描述
	 * @return loggerDescription
	 */
	public String getLoggerDescription() {
		return this.loggerDescription;
	}

	/**
	 * 日志描述
	 * @param loggerDescription
	 */
	public void setLoggerDescription(String loggerDescription) {
		this.loggerDescription = loggerDescription;
	}

	@java.lang.Override
	public java.lang.String toString() {
		final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemLoggerDTO{");
		sb.append("loggerId='").append(loggerId).append('\'');
		sb.append(", loggerType='").append(loggerType).append('\'');
		sb.append(", loggerTypeName='").append(loggerTypeName).append('\'');
		sb.append(", loggerCreateTime=").append(loggerCreateTime);
		sb.append(", orgId='").append(orgId).append('\'');
		sb.append(", userId='").append(userId).append('\'');
		sb.append(", userName='").append(userName).append('\'');
		sb.append(", loggerIp='").append(loggerIp).append('\'');
		sb.append(", loggerModule='").append(loggerModule).append('\'');
		sb.append(", loggerModuleMethod='").append(loggerModuleMethod).append('\'');
		sb.append(", loggerResponseTime='").append(loggerResponseTime).append('\'');
		sb.append(", loggerOperatingSystem='").append(loggerOperatingSystem).append('\'');
		sb.append(", loggerBrowserType='").append(loggerBrowserType).append('\'');
		sb.append(", loggerBrowserVersion='").append(loggerBrowserVersion).append('\'');
		sb.append(", loggerDescription='").append(loggerDescription).append('\'');
		sb.append('}');
		return sb.toString();
	}
}