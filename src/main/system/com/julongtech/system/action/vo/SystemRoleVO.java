package com.julongtech.system.action.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 角色界面交互类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 */
public class SystemRoleVO  implements Serializable{
    /**
	 * @author julong
	 * @date 2017-10-16 下午3:55:00
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * 角色编号
     */
    private Integer roleId;

    /** 
     * 角色名称
     */
    private String roleName;

    /** 
     * 组织机构
     */
    private String orgId;

    /** 
     * 角色描述
     */
    private String roleDesc;

    /** 
     * 角色类型
     */
    private String roleType;

    /** 
     * 角色状态：0：正常1：禁用
     */
    private String roleStatus;
    
    /** 
     * 角色创建时间
     */
    private Timestamp roleCreateTime;


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
     * 角色名称
     * @return roleName
     */
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * 角色名称
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
     * 角色描述
     * @return roleDesc
     */
    public String getRoleDesc() {
        return this.roleDesc;
    }

    /**
     * 角色描述
     * @param roleDesc
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    /**
     * 角色类型
     * @return roleType
     */
    public String getRoleType() {
        return this.roleType;
    }

    /**
     * 角色类型
     * @param roleType
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**
     * 角色状态：0：正常1：禁用
     * @return roleStatus
     */
    public String getRoleStatus() {
        return this.roleStatus;
    }

    /**
     * 角色状态：0：正常1：禁用
     * @param roleStatus
     */
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    /**
     * 角色创建时间
     * @return roleCreateTime
     */
    public Timestamp getRoleCreateTime() {
        return this.roleCreateTime;
    }

    /**
     * 角色创建时间
     * @param roleCreateTime
     */
    public void setRoleCreateTime(Timestamp roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemRoleVO{");
        sb.append("roleId=").append(roleId);
        sb.append(", roleName='").append(roleName).append('\'');
        sb.append(", orgId='").append(orgId).append('\'');
        sb.append(", roleDesc='").append(roleDesc).append('\'');
        sb.append(", roleType='").append(roleType).append('\'');
        sb.append(", roleStatus='").append(roleStatus).append('\'');
        sb.append(", roleCreateTime=").append(roleCreateTime);
        sb.append('}');
        return sb.toString();
    }
}