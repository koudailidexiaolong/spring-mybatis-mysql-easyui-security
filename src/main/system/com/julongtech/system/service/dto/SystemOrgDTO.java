package com.julongtech.system.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 组织机构表
 * @author julong 
 * @date Fri Nov 24 15:47:06 CST 2017
 * @database table SYSTEM_ORG
 */
public class SystemOrgDTO implements Serializable{

	/**
	 * @author julong
	 * @date 2017-11-24 下午3:54:45
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * 组织机构编码
	 */
	private String orgId;
	/** 
	 * 联行行号
	 */
	private String orgCode;

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
	 * 是否有子节点
	 */
	private String orgLeaf;

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
	 * 机构建立时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Timestamp orgCreateDate;

	/** 
	 * 组织机构状态：0：正常1：禁用
	 */
	private String orgStatus;


	/** 
	 * 组织机构创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Timestamp orgCreateTime;

	/** 
	 * 组织机构创建人
	 */
	private String orgCreateUserId;

	/** 
	 * 组织机构修改时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Timestamp orgUpdateTime;

	/** 
	 * 组织机构修改人
	 */
	private String orgUpdateUserId;

	/** 
	 * 机构备注
	 */
	private String orgDesc;

	/** 
	 * 备用字段1
	 */
	private String orgReserveA;

	/** 
	 * 备用字段2
	 */
	private String orgReserveB;

	/** 
	 * 备用字段3
	 */
	private String orgReserveC;

	/**
	 * treegrid属性
	 * @author julong
	 * @date 2018-6-7 上午10:49:31
	 */
	private String state = "closed";

	/**
	 * treegrid属性
	 * @author julong
	 * @date 2018-6-7 上午10:49:26
	 */
	private String _parentId ;


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
	 * 联行行号
	 * @return orgCode
	 */
	public String getOrgCode() {
		return this.orgCode;
	}

	/**
	 * 联行行号
	 * @param orgCode
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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
	 * 机构建立时间
	 */
	public Timestamp getOrgCreateDate() {
		return this.orgCreateDate;
	}
	
	/**
	 * 机构建立时间
	 * @param orgCreateDate
	 */
	public void setOrgCreateDate(Timestamp orgCreateDate) {
		this.orgCreateDate = orgCreateDate;
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
	 * 组织机构创建时间
	 * @return orgCreateTime
	 */
	public Timestamp getOrgCreateTime() {
		return this.orgCreateTime;
	}

	/**
	 * 组织机构创建时间
	 * @param orgCreateTime
	 */
	public void setOrgCreateTime(Timestamp orgCreateTime) {
		this.orgCreateTime = orgCreateTime;
	}

	/**
	 * 组织机构创建人
	 * @return orgCreateUserId
	 */
	public String getOrgCreateUserId() {
		return this.orgCreateUserId;
	}

	/**
	 * 组织机构创建人
	 * @param orgCreateUserId
	 */
	public void setOrgCreateUserId(String orgCreateUserId) {
		this.orgCreateUserId = orgCreateUserId;
	}

	/**
	 * 组织机构修改时间
	 * @return orgUpdateTime
	 */
	public Timestamp getOrgUpdateTime() {
		return this.orgUpdateTime;
	}

	/**
	 * 组织机构修改时间
	 * @param orgUpdateTime
	 */
	public void setOrgUpdateTime(Timestamp orgUpdateTime) {
		this.orgUpdateTime = orgUpdateTime;
	}

	/**
	 * 组织机构修改人
	 * @return orgUpdateUserId
	 */
	public String getOrgUpdateUserId() {
		return this.orgUpdateUserId;
	}

	/**
	 * 组织机构修改人
	 * @param orgUpdateUserId
	 */
	public void setOrgUpdateUserId(String orgUpdateUserId) {
		this.orgUpdateUserId = orgUpdateUserId;
	}

	/**
	 * 机构备注
	 * @return orgDesc
	 */
	public String getOrgDesc() {
		return this.orgDesc;
	}

	/**
	 * 机构备注
	 * @param orgDesc
	 */
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	/**
	 * 备用字段1
	 * @return orgReserveA
	 */
	public String getOrgReserveA() {
		return this.orgReserveA;
	}

	/**
	 * 备用字段1
	 * @param orgReserveA
	 */
	public void setOrgReserveA(String orgReserveA) {
		this.orgReserveA = orgReserveA;
	}

	/**
	 * 备用字段2
	 * @return orgReserveB
	 */
	public String getOrgReserveB() {
		return this.orgReserveB;
	}

	/**
	 * 备用字段2
	 * @param orgReserveB
	 */
	public void setOrgReserveB(String orgReserveB) {
		this.orgReserveB = orgReserveB;
	}

	/**
	 * 备用字段3
	 * @return orgReserveC
	 */
	public String getOrgReserveC() {
		return this.orgReserveC;
	}

	/**
	 * 备用字段3
	 * @param orgReserveC
	 */
	public void setOrgReserveC(String orgReserveC) {
		this.orgReserveC = orgReserveC;
	}

	/**
	 * treegrid属性
	 * @return state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * treegrid属性
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * treegrid属性
	 * @return _parentId
	 */
	public String get_parentId() {
		return this._parentId;
	}

	/**
	 * treegrid属性
	 * @param _parentId
	 */
	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	@java.lang.Override
	public java.lang.String toString() {
		final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemOrgDTO{");
		sb.append("orgId='").append(orgId).append('\'');
		sb.append(", orgCode='").append(orgCode).append('\'');
		sb.append(", orgPhone='").append(orgPhone).append('\'');
		sb.append(", orgFullName='").append(orgFullName).append('\'');
		sb.append(", orgName='").append(orgName).append('\'');
		sb.append(", orgParentId='").append(orgParentId).append('\'');
		sb.append(", orgParentName='").append(orgParentName).append('\'');
		sb.append(", orgAddress='").append(orgAddress).append('\'');
		sb.append(", orgLevel='").append(orgLevel).append('\'');
		sb.append(", orgLeaf='").append(orgLeaf).append('\'');
		sb.append(", orgType='").append(orgType).append('\'');
		sb.append(", orgArea='").append(orgArea).append('\'');
		sb.append(", orgAreaCode='").append(orgAreaCode).append('\'');
		sb.append(", orgStatus='").append(orgStatus).append('\'');
		sb.append(", orgCreateTime=").append(orgCreateTime);
		sb.append(", orgCreateUserId='").append(orgCreateUserId).append('\'');
		sb.append(", orgUpdateTime=").append(orgUpdateTime);
		sb.append(", orgUpdateUserId='").append(orgUpdateUserId).append('\'');
		sb.append(", orgDesc='").append(orgDesc).append('\'');
		sb.append(", orgReserveA='").append(orgReserveA).append('\'');
		sb.append(", orgReserveB='").append(orgReserveB).append('\'');
		sb.append(", orgReserveC='").append(orgReserveC).append('\'');
		sb.append(", state='").append(state).append('\'');
		sb.append(", _parentId='").append(_parentId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}