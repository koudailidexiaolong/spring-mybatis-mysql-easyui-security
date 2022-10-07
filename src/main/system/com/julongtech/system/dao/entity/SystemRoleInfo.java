package com.julongtech.system.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 角色信息类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 * @database table SYSTEM_ROLE
 */
public class SystemRoleInfo  implements Serializable{
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
     * 角色创建时间
     */
    private Timestamp roleCreateTime;

    /** 
     * 角色创建人
     */
    private String roleCreateUserId;

    /** 
     * 角色修改时间
     */
    private Timestamp roleUpdateTime;

    /** 
     * 角色修改人
     */
    private String roleUpdateUserId;

    /** 
     * 角色状态：0：正常1：禁用
     */
    private String roleStatus;

    /** 
     * 备用字段1
     */
    private String roleReserveA;

    /** 
     * 备用字段2
     */
    private String roleReserveB;

    /** 
     * 备用字段3
     */
    private String roleReserveC;

    /**
     * 角色编号
     * @return  the value of SYSTEM_ROLE.ROLE_ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 角色编号
     * @param roleId the value for SYSTEM_ROLE.ROLE_ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 角色名称
     * @return  the value of SYSTEM_ROLE.ROLE_NAME
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     * @param roleName the value for SYSTEM_ROLE.ROLE_NAME
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 组织机构
     * @return  the value of SYSTEM_ROLE.ORG_ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 组织机构
     * @param orgId the value for SYSTEM_ROLE.ORG_ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 角色描述
     * @return  the value of SYSTEM_ROLE.ROLE_DESC
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 角色描述
     * @param roleDesc the value for SYSTEM_ROLE.ROLE_DESC
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    /**
     * 角色类型
     * @return  the value of SYSTEM_ROLE.ROLE_TYPE
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * 角色类型
     * @param roleType the value for SYSTEM_ROLE.ROLE_TYPE
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    /**
     * 角色创建时间
     * @return  the value of SYSTEM_ROLE.ROLE_CREATE_TIME
     */
    public Timestamp getRoleCreateTime() {
        return roleCreateTime;
    }

    /**
     * 角色创建时间
     * @param roleCreateTime the value for SYSTEM_ROLE.ROLE_CREATE_TIME
     */
    public void setRoleCreateTime(Timestamp roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    /**
     * 角色创建人
     * @return  the value of SYSTEM_ROLE.ROLE_CREATE_USER_ID
     */
    public String getRoleCreateUserId() {
        return roleCreateUserId;
    }

    /**
     * 角色创建人
     * @param roleCreateUserId the value for SYSTEM_ROLE.ROLE_CREATE_USER_ID
     */
    public void setRoleCreateUserId(String roleCreateUserId) {
        this.roleCreateUserId = roleCreateUserId == null ? null : roleCreateUserId.trim();
    }

    /**
     * 角色修改时间
     * @return  the value of SYSTEM_ROLE.ROLE_UPDATE_TIME
     */
    public Timestamp getRoleUpdateTime() {
        return roleUpdateTime;
    }

    /**
     * 角色修改时间
     * @param roleUpdateTime the value for SYSTEM_ROLE.ROLE_UPDATE_TIME
     */
    public void setRoleUpdateTime(Timestamp roleUpdateTime) {
        this.roleUpdateTime = roleUpdateTime;
    }

    /**
     * 角色修改人
     * @return  the value of SYSTEM_ROLE.ROLE_UPDATE_USER_ID
     */
    public String getRoleUpdateUserId() {
        return roleUpdateUserId;
    }

    /**
     * 角色修改人
     * @param roleUpdateUserId the value for SYSTEM_ROLE.ROLE_UPDATE_USER_ID
     */
    public void setRoleUpdateUserId(String roleUpdateUserId) {
        this.roleUpdateUserId = roleUpdateUserId == null ? null : roleUpdateUserId.trim();
    }

    /**
     * 角色状态：0：正常1：禁用
     * @return  the value of SYSTEM_ROLE.ROLE_STATUS
     */
    public String getRoleStatus() {
        return roleStatus;
    }

    /**
     * 角色状态：0：正常1：禁用
     * @param roleStatus the value for SYSTEM_ROLE.ROLE_STATUS
     */
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus == null ? null : roleStatus.trim();
    }

    /**
     * 备用字段1
     * @return  the value of SYSTEM_ROLE.ROLE_RESERVE_A
     */
    public String getRoleReserveA() {
        return roleReserveA;
    }

    /**
     * 备用字段1
     * @param roleReserveA the value for SYSTEM_ROLE.ROLE_RESERVE_A
     */
    public void setRoleReserveA(String roleReserveA) {
        this.roleReserveA = roleReserveA == null ? null : roleReserveA.trim();
    }

    /**
     * 备用字段2
     * @return  the value of SYSTEM_ROLE.ROLE_RESERVE_B
     */
    public String getRoleReserveB() {
        return roleReserveB;
    }

    /**
     * 备用字段2
     * @param roleReserveB the value for SYSTEM_ROLE.ROLE_RESERVE_B
     */
    public void setRoleReserveB(String roleReserveB) {
        this.roleReserveB = roleReserveB == null ? null : roleReserveB.trim();
    }

    /**
     * 备用字段3
     * @return  the value of SYSTEM_ROLE.ROLE_RESERVE_C
     */
    public String getRoleReserveC() {
        return roleReserveC;
    }

    /**
     * 备用字段3
     * @param roleReserveC the value for SYSTEM_ROLE.ROLE_RESERVE_C
     */
    public void setRoleReserveC(String roleReserveC) {
        this.roleReserveC = roleReserveC == null ? null : roleReserveC.trim();
    }

	@Override
	public String toString() {
		return "SystemRoleInfo [roleId=" + roleId + ", roleName=" + roleName
				+ ", orgId=" + orgId + ", roleDesc=" + roleDesc + ", roleType="
				+ roleType + ", roleCreateTime=" + roleCreateTime
				+ ", roleCreateUserId=" + roleCreateUserId
				+ ", roleUpdateTime=" + roleUpdateTime + ", roleUpdateUserId="
				+ roleUpdateUserId + ", roleStatus=" + roleStatus
				+ ", roleReserveA=" + roleReserveA + ", roleReserveB="
				+ roleReserveB + ", roleReserveC=" + roleReserveC + "]";
	}
    
}