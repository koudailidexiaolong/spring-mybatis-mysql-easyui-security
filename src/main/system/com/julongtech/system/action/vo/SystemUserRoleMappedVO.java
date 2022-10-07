package com.julongtech.system.action.vo;

import java.io.Serializable;

/**
 * 用户角色关系映射界面交互类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 */
public class SystemUserRoleMappedVO  implements Serializable{
    /**
	 * @author julong
	 * @date 2017-10-16 下午3:54:51
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * 用户角色关系编号
     */
    private Integer mappedId;

    /** 
     * 用户编号
     */
    private String userId;

    /** 
     * 角色编号
     */
    private Integer roleId;

    /** 
     * 组织机构编码
     */
    private String orgId;

    /** 
     * 用户角色关系状态：0正常1：停用禁用
     */
    private String mappedStatus;

    /**
     * 角色数组
     */
    private String roleArray;


    /**
     * 用户角色关系编号
     * @return mappedId
     */
    public Integer getMappedId() {
        return this.mappedId;
    }

    /**
     * 用户角色关系编号
     * @param mappedId
     */
    public void setMappedId(Integer mappedId) {
        this.mappedId = mappedId;
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
     * 用户角色关系状态：0正常1：停用禁用
     * @return mappedStatus
     */
    public String getMappedStatus() {
        return this.mappedStatus;
    }

    /**
     * 用户角色关系状态：0正常1：停用禁用
     * @param mappedStatus
     */
    public void setMappedStatus(String mappedStatus) {
        this.mappedStatus = mappedStatus;
    }

    /**
     * 角色数组
     * @return roleArray
     */
    public String getRoleArray() {
        return this.roleArray;
    }

    /**
     * 角色数组
     * @param roleArray
     */
    public void setRoleArray(String roleArray) {
        this.roleArray = roleArray;
    }

    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemUserRoleMappedVO{");
        sb.append("mappedId=").append(mappedId);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", roleId=").append(roleId);
        sb.append(", orgId='").append(orgId).append('\'');
        sb.append(", mappedStatus='").append(mappedStatus).append('\'');
        sb.append(", roleArray='").append(roleArray).append('\'');
        sb.append('}');
        return sb.toString();
    }
}