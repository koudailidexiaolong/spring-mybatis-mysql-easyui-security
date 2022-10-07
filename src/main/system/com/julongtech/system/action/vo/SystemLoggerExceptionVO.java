package com.julongtech.system.action.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统异常日志界面交互类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 */
public class SystemLoggerExceptionVO  implements Serializable{
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
     * 日志异常信息
     */
    private String loggerExceptionContext;


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
	 * 访问响应时间 毫秒
	 */
	private String loggerResponseTime;

    /**
     * 日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列
     * @return loggerExceptionId
     */
    public String getLoggerExceptionId() {
        return this.loggerExceptionId;
    }

    /**
     * 日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列
     * @param loggerExceptionId
     */
    public void setLoggerExceptionId(String loggerExceptionId) {
        this.loggerExceptionId = loggerExceptionId;
    }

    /**
     * 日志类型
     * @return loggerExceptionType
     */
    public String getLoggerExceptionType() {
        return this.loggerExceptionType;
    }

    /**
     * 日志类型
     * @param loggerExceptionType
     */
    public void setLoggerExceptionType(String loggerExceptionType) {
        this.loggerExceptionType = loggerExceptionType;
    }

    /**
     * 日志创建时间
     * @return loggerExceptionCreateTime
     */
    public Timestamp getLoggerExceptionCreateTime() {
        return this.loggerExceptionCreateTime;
    }

    /**
     * 日志创建时间
     * @param loggerExceptionCreateTime
     */
    public void setLoggerExceptionCreateTime(Timestamp loggerExceptionCreateTime) {
        this.loggerExceptionCreateTime = loggerExceptionCreateTime;
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
     * @return loggerExceptionIp
     */
    public String getLoggerExceptionIp() {
        return this.loggerExceptionIp;
    }

    /**
     * 日志IP
     * @param loggerExceptionIp
     */
    public void setLoggerExceptionIp(String loggerExceptionIp) {
        this.loggerExceptionIp = loggerExceptionIp;
    }

    /**
     * 日志调用模块
     * @return loggerExceptionModule
     */
    public String getLoggerExceptionModule() {
        return this.loggerExceptionModule;
    }

    /**
     * 日志调用模块
     * @param loggerExceptionModule
     */
    public void setLoggerExceptionModule(String loggerExceptionModule) {
        this.loggerExceptionModule = loggerExceptionModule;
    }

    /**
     * 日志调用模块方法
     * @return loggerExceptionMethod
     */
    public String getLoggerExceptionMethod() {
        return this.loggerExceptionMethod;
    }

    /**
     * 日志调用模块方法
     * @param loggerExceptionMethod
     */
    public void setLoggerExceptionMethod(String loggerExceptionMethod) {
        this.loggerExceptionMethod = loggerExceptionMethod;
    }

    /**
     * 日志描述
     * @return loggerExceptionDescription
     */
    public String getLoggerExceptionDescription() {
        return this.loggerExceptionDescription;
    }

    /**
     * 日志描述
     * @param loggerExceptionDescription
     */
    public void setLoggerExceptionDescription(String loggerExceptionDescription) {
        this.loggerExceptionDescription = loggerExceptionDescription;
    }

    /**
     * 日志异常信息
     * @return loggerExceptionContext
     */
    public String getLoggerExceptionContext() {
        return this.loggerExceptionContext;
    }

    /**
     * 日志异常信息
     * @param loggerExceptionContext
     */
    public void setLoggerExceptionContext(String loggerExceptionContext) {
        this.loggerExceptionContext = loggerExceptionContext;
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


    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemLoggerExceptionVO{");
        sb.append("loggerExceptionId='").append(loggerExceptionId).append('\'');
        sb.append(", loggerExceptionType='").append(loggerExceptionType).append('\'');
        sb.append(", loggerExceptionCreateTime=").append(loggerExceptionCreateTime);
        sb.append(", orgId='").append(orgId).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", loggerExceptionIp='").append(loggerExceptionIp).append('\'');
        sb.append(", loggerExceptionModule='").append(loggerExceptionModule).append('\'');
        sb.append(", loggerExceptionMethod='").append(loggerExceptionMethod).append('\'');
        sb.append(", loggerExceptionDescription='").append(loggerExceptionDescription).append('\'');
        sb.append(", loggerExceptionContext='").append(loggerExceptionContext).append('\'');
        sb.append(", loggerOperatingSystem='").append(loggerOperatingSystem).append('\'');
        sb.append(", loggerBrowserType='").append(loggerBrowserType).append('\'');
        sb.append(", loggerBrowserVersion='").append(loggerBrowserVersion).append('\'');
        sb.append(", loggerResponseTime='").append(loggerResponseTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}