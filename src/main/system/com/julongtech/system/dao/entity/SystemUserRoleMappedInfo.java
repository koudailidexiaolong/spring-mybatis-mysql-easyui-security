package com.julongtech.system.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户角色关系映射信息类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 * @database table SYSTEM_USER_ROLE_MAPPED
 */
public class SystemUserRoleMappedInfo  implements Serializable{
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
     * @return  the value of SYSTEM_USER_ROLE_MAPPED.MAPPED_ID
     */
    public Integer getMappedId() {
        return mappedId;
    }

    /**
     * 用户角色关系编号
     * @param mappedId the value for SYSTEM_USER_ROLE_MAPPED.MAPPED_ID
     */
    public void setMappedId(Integer mappedId) {
        this.mappedId = mappedId;
    }

    /**
     * 用户编号
     * @return  the value of SYSTEM_USER_ROLE_MAPPED.USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户编号
     * @param userId the value for SYSTEM_USER_ROLE_MAPPED.USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 角色编号
     * @return  the value of SYSTEM_USER_ROLE_MAPPED.ROLE_ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 角色编号
     * @param roleId the value for SYSTEM_USER_ROLE_MAPPED.ROLE_ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 组织机构编码
     * @return  the value of SYSTEM_USER_ROLE_MAPPED.ORG_ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 组织机构编码
     * @param orgId the value for SYSTEM_USER_ROLE_MAPPED.ORG_ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 用户角色关系状态：0正常1：停用禁用
     * @return  the value of SYSTEM_USER_ROLE_MAPPED.MAPPED_STATUS
     */
    public String getMappedStatus() {
        return mappedStatus;
    }

    /**
     * 用户角色关系状态：0正常1：停用禁用
     * @param mappedStatus the value for SYSTEM_USER_ROLE_MAPPED.MAPPED_STATUS
     */
    public void setMappedStatus(String mappedStatus) {
        this.mappedStatus = mappedStatus == null ? null : mappedStatus.trim();
    }

    /**
     * 用户角色关系创建时间
     * @return  the value of SYSTEM_USER_ROLE_MAPPED.MAPPED_CREATE_TIME
     */
    public Timestamp getMappedCreateTime() {
        return mappedCreateTime;
    }

    /**
     * 用户角色关系创建时间
     * @param mappedCreateTime the value for SYSTEM_USER_ROLE_MAPPED.MAPPED_CREATE_TIME
     */
    public void setMappedCreateTime(Timestamp mappedCreateTime) {
        this.mappedCreateTime = mappedCreateTime;
    }

    /**
     * 用户角色关系创建人
     * @return  the value of SYSTEM_USER_ROLE_MAPPED.MAPPED_CREATE_USER_ID
     */
    public String getMappedCreateUserId() {
        return mappedCreateUserId;
    }

    /**
     * 用户角色关系创建人
     * @param mappedCreateUserId the value for SYSTEM_USER_ROLE_MAPPED.MAPPED_CREATE_USER_ID
     */
    public void setMappedCreateUserId(String mappedCreateUserId) {
        this.mappedCreateUserId = mappedCreateUserId == null ? null : mappedCreateUserId.trim();
    }

    /**
     * 备用字段1
     * @return  the value of SYSTEM_USER_ROLE_MAPPED.MAPPED_RESERVE_A
     */
    public String getMappedReserveA() {
        return mappedReserveA;
    }

    /**
     * 备用字段1
     * @param mappedReserveA the value for SYSTEM_USER_ROLE_MAPPED.MAPPED_RESERVE_A
     */
    public void setMappedReserveA(String mappedReserveA) {
        this.mappedReserveA = mappedReserveA == null ? null : mappedReserveA.trim();
    }

    /**
     * 备用字段2
     * @return  the value of SYSTEM_USER_ROLE_MAPPED.MAPPED_RESERVE_B
     */
    public String getMappedReserveB() {
        return mappedReserveB;
    }

    /**
     * 备用字段2
     * @param mappedReserveB the value for SYSTEM_USER_ROLE_MAPPED.MAPPED_RESERVE_B
     */
    public void setMappedReserveB(String mappedReserveB) {
        this.mappedReserveB = mappedReserveB == null ? null : mappedReserveB.trim();
    }

    /**
     * 备用字段3
     * @return  the value of SYSTEM_USER_ROLE_MAPPED.MAPPED_RESERVE_C
     */
    public String getMappedReserveC() {
        return mappedReserveC;
    }

    /**
     * 备用字段3
     * @param mappedReserveC the value for SYSTEM_USER_ROLE_MAPPED.MAPPED_RESERVE_C
     */
    public void setMappedReserveC(String mappedReserveC) {
        this.mappedReserveC = mappedReserveC == null ? null : mappedReserveC.trim();
    }

	@Override
	public String toString() {
		return "SystemUserRoleMappedInfo [mappedId=" + mappedId + ", userId="
				+ userId + ", roleId=" + roleId + ", orgId=" + orgId
				+ ", mappedStatus=" + mappedStatus + ", mappedCreateTime="
				+ mappedCreateTime + ", mappedCreateUserId="
				+ mappedCreateUserId + ", mappedReserveA=" + mappedReserveA
				+ ", mappedReserveB=" + mappedReserveB + ", mappedReserveC="
				+ mappedReserveC + "]";
	}
    
}