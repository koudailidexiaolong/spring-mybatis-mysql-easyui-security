package com.julongtech.system.action.vo;

import java.io.Serializable;

/**
 * 组织机构表
 * @author julong 
 * @date Fri Nov 24 15:47:06 CST 2017
 */
public class SystemOrgVO implements Serializable{
    /**
	 * @author julong
	 * @date 2017-11-24 下午3:54:45
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * 是否有子节点
     */
    private String orgLeaf;
	/** 
     * 组织机构编码
     */
    private String orgId;

    /** 
     * 机构网点电话
     */
    private String orgPhone;

    /** 
     * 组织机构名称全称
     */
    private String orgFullName;

    /** 
     * 组织机构名称简称
     */
    private String orgName;

    /** 
     * 组织机构父级节点编码
     */
    private String orgParentId;

    /** 
     * 组织机构父级节点名称
     */
    private String orgParentName;

    /** 
     * 组织机构地址
     */
    private String orgAddress;

    /** 
     * 组织机构等级
     */
    private String orgLevel;

    /** 
     * 组织机构类型
     */
    private String orgType;

    /** 
     * 组织机构行政区划名称
     */
    private String orgArea;

    /** 
     * 组织机构行政区划编码
     */
    private String orgAreaCode;

    /** 
     * 组织机构状态：0：正常1：禁用
     */
    private String orgStatus;
    /** 
     * 机构备注描述
     */
    private String orgDesc;

    /**
     * 是否有子节点
     * @return orgLeaf
     */
    public String getOrgLeaf() {
        return this.orgLeaf;
    }

    /**
     * 是否有子节点
     * @param orgLeaf
     */
    public void setOrgLeaf(String orgLeaf) {
        this.orgLeaf = orgLeaf;
    }

    /**
     * 组织机构编码
     * @return orgId
     */
    public String getOrgId() {
        return this.orgId;
    }

    /**
     * 组织机构编码
     * @param orgId
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 机构网点电话
     * @return orgPhone
     */
    public String getOrgPhone() {
        return this.orgPhone;
    }

    /**
     * 机构网点电话
     * @param orgPhone
     */
    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }

    /**
     * 组织机构名称全称
     * @return orgFullName
     */
    public String getOrgFullName() {
        return this.orgFullName;
    }

    /**
     * 组织机构名称全称
     * @param orgFullName
     */
    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    /**
     * 组织机构名称简称
     * @return orgName
     */
    public String getOrgName() {
        return this.orgName;
    }

    /**
     * 组织机构名称简称
     * @param orgName
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 组织机构父级节点编码
     * @return orgParentId
     */
    public String getOrgParentId() {
        return this.orgParentId;
    }

    /**
     * 组织机构父级节点编码
     * @param orgParentId
     */
    public void setOrgParentId(String orgParentId) {
        this.orgParentId = orgParentId;
    }

    /**
     * 组织机构父级节点名称
     * @return orgParentName
     */
    public String getOrgParentName() {
        return this.orgParentName;
    }

    /**
     * 组织机构父级节点名称
     * @param orgParentName
     */
    public void setOrgParentName(String orgParentName) {
        this.orgParentName = orgParentName;
    }

    /**
     * 组织机构地址
     * @return orgAddress
     */
    public String getOrgAddress() {
        return this.orgAddress;
    }

    /**
     * 组织机构地址
     * @param orgAddress
     */
    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    /**
     * 组织机构等级
     * @return orgLevel
     */
    public String getOrgLevel() {
        return this.orgLevel;
    }

    /**
     * 组织机构等级
     * @param orgLevel
     */
    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    /**
     * 组织机构类型
     * @return orgType
     */
    public String getOrgType() {
        return this.orgType;
    }

    /**
     * 组织机构类型
     * @param orgType
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * 组织机构行政区划名称
     * @return orgArea
     */
    public String getOrgArea() {
        return this.orgArea;
    }

    /**
     * 组织机构行政区划名称
     * @param orgArea
     */
    public void setOrgArea(String orgArea) {
        this.orgArea = orgArea;
    }

    /**
     * 组织机构行政区划编码
     * @return orgAreaCode
     */
    public String getOrgAreaCode() {
        return this.orgAreaCode;
    }

    /**
     * 组织机构行政区划编码
     * @param orgAreaCode
     */
    public void setOrgAreaCode(String orgAreaCode) {
        this.orgAreaCode = orgAreaCode;
    }

    /**
     * 组织机构状态：0：正常1：禁用
     * @return orgStatus
     */
    public String getOrgStatus() {
        return this.orgStatus;
    }

    /**
     * 组织机构状态：0：正常1：禁用
     * @param orgStatus
     */
    public void setOrgStatus(String orgStatus) {
        this.orgStatus = orgStatus;
    }

    /**
     * 机构备注描述
     * @return orgDesc
     */
    public String getOrgDesc() {
        return this.orgDesc;
    }

    /**
     * 机构备注描述
     * @param orgDesc
     */
    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemOrgVO{");
        sb.append("orgLeaf='").append(orgLeaf).append('\'');
        sb.append(", orgId='").append(orgId).append('\'');
        sb.append(", orgPhone='").append(orgPhone).append('\'');
        sb.append(", orgFullName='").append(orgFullName).append('\'');
        sb.append(", orgName='").append(orgName).append('\'');
        sb.append(", orgParentId='").append(orgParentId).append('\'');
        sb.append(", orgParentName='").append(orgParentName).append('\'');
        sb.append(", orgAddress='").append(orgAddress).append('\'');
        sb.append(", orgLevel='").append(orgLevel).append('\'');
        sb.append(", orgType='").append(orgType).append('\'');
        sb.append(", orgArea='").append(orgArea).append('\'');
        sb.append(", orgAreaCode='").append(orgAreaCode).append('\'');
        sb.append(", orgStatus='").append(orgStatus).append('\'');
        sb.append(", orgDesc='").append(orgDesc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}