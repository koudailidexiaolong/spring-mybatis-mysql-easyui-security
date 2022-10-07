package com.julongtech.system.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户角色关系映射信息类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 * @database table SYSTEM_USER_ROLE_MAPPED
 */
public class SystemUserRoleMappedDTO  implements Serializable{
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
     * 用户角色关系创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Timestamp mappedCreateTime;

    /** 
     * 用户角色关系创建人
     */
    private String mappedCreateUserId;

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
     * 用户角色关系创建时间
     * @return mappedCreateTime
     */
    public Timestamp getMappedCreateTime() {
        return this.mappedCreateTime;
    }

    /**
     * 用户角色关系创建时间
     * @param mappedCreateTime
     */
    public void setMappedCreateTime(Timestamp mappedCreateTime) {
        this.mappedCreateTime = mappedCreateTime;
    }

    /**
     * 用户角色关系创建人
     * @return mappedCreateUserId
     */
    public String getMappedCreateUserId() {
        return this.mappedCreateUserId;
    }

    /**
     * 用户角色关系创建人
     * @param mappedCreateUserId
     */
    public void setMappedCreateUserId(String mappedCreateUserId) {
        this.mappedCreateUserId = mappedCreateUserId;
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
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemUserRoleMappedDTO{");
        sb.append("mappedId=").append(mappedId);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", roleId=").append(roleId);
        sb.append(", orgId='").append(orgId).append('\'');
        sb.append(", mappedStatus='").append(mappedStatus).append('\'');
        sb.append(", mappedCreateTime=").append(mappedCreateTime);
        sb.append(", mappedCreateUserId='").append(mappedCreateUserId).append('\'');
        sb.append(", mappedReserveA='").append(mappedReserveA).append('\'');
        sb.append(", mappedReserveB='").append(mappedReserveB).append('\'');
        sb.append(", mappedReserveC='").append(mappedReserveC).append('\'');
        sb.append('}');
        return sb.toString();
    }
}