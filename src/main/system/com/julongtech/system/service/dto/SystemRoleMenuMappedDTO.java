package com.julongtech.system.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 角色菜单映射关系信息类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 */
public class SystemRoleMenuMappedDTO  implements Serializable{
	/**
	 * @author julong
	 * @date 2017-10-16 下午3:54:57
	 */
	private static final long serialVersionUID = 1L;
	/** 
	 * 角色菜单关系编号
	 */
	private Integer mappedId;

	/** 
	 * 角色编号
	 */
	private Integer roleId;

	/** 
	 * 菜单编号
	 */
	private String menuId;

	/** 
	 * 组织机构编码
	 */
	private String orgId;

	/** 
	 * 菜单按钮数组以，区分
	 */
	private String mappedMenuArray;

	/** 
	 * 角色菜单关系描述
	 */
	private String mappedDesc;

	/** 
	 * 角色菜单关系创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Timestamp mappedCreateTime;

	/** 
	 * 角色菜单关系创建人
	 */
	private String userId;

	/** 
	 * 角色菜单关系修改时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Timestamp mappedUpdateTime;

	/** 
	 * 角色菜单关系状态:0正常1禁用
	 */
	private String mappedStatus;

	/** 
	 * 备用字段1
	 */
	private String mappedReserveA;

	/** 
	 * 备用字段2
	 */
	private String mappedReserveB;

	/** 
	 * 备用字段3
	 */
	private String mappedReserveC;


	/**
	 * 角色菜单关系编号
	 * @return mappedId
	 */
	public Integer getMappedId() {
		return this.mappedId;
	}

	/**
	 * 角色菜单关系编号
	 * @param mappedId
	 */
	public void setMappedId(Integer mappedId) {
		this.mappedId = mappedId;
	}

	/**
	 * 角色编号
	 * @return roleId
	 */
	public Integer getRoleId() {
		return this.roleId;
	}

	/**
	 * 角色编号
	 * @param roleId
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 菜单编号
	 * @return menuId
	 */
	public String getMenuId() {
		return this.menuId;
	}

	/**
	 * 菜单编号
	 * @param menuId
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
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
	 * 菜单按钮数组以，区分
	 * @return mappedMenuArray
	 */
	public String getMappedMenuArray() {
		return this.mappedMenuArray;
	}

	/**
	 * 菜单按钮数组以，区分
	 * @param mappedMenuArray
	 */
	public void setMappedMenuArray(String mappedMenuArray) {
		this.mappedMenuArray = mappedMenuArray;
	}

	/**
	 * 角色菜单关系描述
	 * @return mappedDesc
	 */
	public String getMappedDesc() {
		return this.mappedDesc;
	}

	/**
	 * 角色菜单关系描述
	 * @param mappedDesc
	 */
	public void setMappedDesc(String mappedDesc) {
		this.mappedDesc = mappedDesc;
	}

	/**
	 * 角色菜单关系创建时间
	 * @return mappedCreateTime
	 */
	public Timestamp getMappedCreateTime() {
		return this.mappedCreateTime;
	}

	/**
	 * 角色菜单关系创建时间
	 * @param mappedCreateTime
	 */
	public void setMappedCreateTime(Timestamp mappedCreateTime) {
		this.mappedCreateTime = mappedCreateTime;
	}

	/**
	 * 角色菜单关系创建人
	 * @return userId
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 角色菜单关系创建人
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 角色菜单关系修改时间
	 * @return mappedUpdateTime
	 */
	public Timestamp getMappedUpdateTime() {
		return this.mappedUpdateTime;
	}

	/**
	 * 角色菜单关系修改时间
	 * @param mappedUpdateTime
	 */
	public void setMappedUpdateTime(Timestamp mappedUpdateTime) {
		this.mappedUpdateTime = mappedUpdateTime;
	}

	/**
	 * 角色菜单关系状态:0正常1禁用
	 * @return mappedStatus
	 */
	public String getMappedStatus() {
		return this.mappedStatus;
	}

	/**
	 * 角色菜单关系状态:0正常1禁用
	 * @param mappedStatus
	 */
	public void setMappedStatus(String mappedStatus) {
		this.mappedStatus = mappedStatus;
	}

	/**
	 * 备用字段1
	 * @return mappedReserveA
	 */
	public String getMappedReserveA() {
		return this.mappedReserveA;
	}

	/**
	 * 备用字段1
	 * @param mappedReserveA
	 */
	public void setMappedReserveA(String mappedReserveA) {
		this.mappedReserveA = mappedReserveA;
	}

	/**
	 * 备用字段2
	 * @return mappedReserveB
	 */
	public String getMappedReserveB() {
		return this.mappedReserveB;
	}

	/**
	 * 备用字段2
	 * @param mappedReserveB
	 */
	public void setMappedReserveB(String mappedReserveB) {
		this.mappedReserveB = mappedReserveB;
	}

	/**
	 * 备用字段3
	 * @return mappedReserveC
	 */
	public String getMappedReserveC() {
		return this.mappedReserveC;
	}

	/**
	 * 备用字段3
	 * @param mappedReserveC
	 */
	public void setMappedReserveC(String mappedReserveC) {
		this.mappedReserveC = mappedReserveC;
	}

	@java.lang.Override
	public java.lang.String toString() {
		final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemRoleMenuMappedDTO{");
		sb.append("mappedId=").append(mappedId);
		sb.append(", roleId=").append(roleId);
		sb.append(", menuId='").append(menuId).append('\'');
		sb.append(", orgId='").append(orgId).append('\'');
		sb.append(", mappedMenuArray='").append(mappedMenuArray).append('\'');
		sb.append(", mappedDesc='").append(mappedDesc).append('\'');
		sb.append(", mappedCreateTime=").append(mappedCreateTime);
		sb.append(", userId='").append(userId).append('\'');
		sb.append(", mappedUpdateTime=").append(mappedUpdateTime);
		sb.append(", mappedStatus='").append(mappedStatus).append('\'');
		sb.append(", mappedReserveA='").append(mappedReserveA).append('\'');
		sb.append(", mappedReserveB='").append(mappedReserveB).append('\'');
		sb.append(", mappedReserveC='").append(mappedReserveC).append('\'');
		sb.append('}');
		return sb.toString();
	}
}