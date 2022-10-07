package com.julongtech.system.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色按钮对应关系表
 * @author julong
 * @date 2022年8月19日 下午9:32:13
 * @desc 
 */
public class SystemRoleButtonMappedInfo implements Serializable{
    /**
	 * @author julong
	 * @date 2022年8月19日 下午9:32:12
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 角色菜单关系编号
     * This field corresponds to the database column system_role_button_mapped.mapped_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    private Integer mappedId;

    /**
     * 角色编号
     * This field corresponds to the database column system_role_button_mapped.role_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    private Integer roleId;

    /**
     * 菜单编号
     * This field corresponds to the database column system_role_button_mapped.menu_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    private String menuId;

    /**
     * 按钮编号
     * This field corresponds to the database column system_role_button_mapped.button_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    private String buttonId;

    /**
     * 组织机构编码
     * This field corresponds to the database column system_role_button_mapped.org_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    private String orgId;

    /**
     * 角色菜单关系描述
     * This field corresponds to the database column system_role_button_mapped.mapped_desc
     *
     * @author julong 2022-08-19 09:21:04
     */
    private String mappedDesc;

    /**
     * 角色菜单关系创建时间
     * This field corresponds to the database column system_role_button_mapped.mapped_create_time
     *
     * @author julong 2022-08-19 09:21:04
     */
    private Date mappedCreateTime;

    /**
     * 角色菜单关系创建人
     * This field corresponds to the database column system_role_button_mapped.user_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    private String userId;

    /**
     * 角色菜单关系修改时间
     * This field corresponds to the database column system_role_button_mapped.mapped_update_time
     *
     * @author julong 2022-08-19 09:21:04
     */
    private Date mappedUpdateTime;

    /**
     * 角色菜单关系状态:0正常1禁用
     * This field corresponds to the database column system_role_button_mapped.mapped_status
     *
     * @author julong 2022-08-19 09:21:04
     */
    private String mappedStatus;

    /**
     * 备用字段1
     * This field corresponds to the database column system_role_button_mapped.mapped_reserve_a
     *
     * @author julong 2022-08-19 09:21:04
     */
    private String mappedReserveA;

    /**
     * 备用字段2
     * This field corresponds to the database column system_role_button_mapped.mapped_reserve_b
     *
     * @author julong 2022-08-19 09:21:04
     */
    private String mappedReserveB;

    /**
     * 备用字段3
     * This field corresponds to the database column system_role_button_mapped.mapped_reserve_c
     *
     * @author julong 2022-08-19 09:21:04
     */
    private String mappedReserveC;

    /**
     * 角色菜单关系编号
     * 
     * This method returns the value of the database column system_role_button_mapped.mapped_id
     *
     * @return the value of system_role_button_mapped.mapped_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public Integer getMappedId() {
        return mappedId;
    }

    /**
     * 角色菜单关系编号
     * 
     * This method sets the value of the database column system_role_button_mapped.mapped_id
     *
     * @param mappedId the value for system_role_button_mapped.mapped_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setMappedId(Integer mappedId) {
        this.mappedId = mappedId;
    }

    /**
     * 角色编号
     * 
     * This method returns the value of the database column system_role_button_mapped.role_id
     *
     * @return the value of system_role_button_mapped.role_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 角色编号
     * 
     * This method sets the value of the database column system_role_button_mapped.role_id
     *
     * @param roleId the value for system_role_button_mapped.role_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 菜单编号
     * 
     * This method returns the value of the database column system_role_button_mapped.menu_id
     *
     * @return the value of system_role_button_mapped.menu_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 菜单编号
     * 
     * This method sets the value of the database column system_role_button_mapped.menu_id
     *
     * @param menuId the value for system_role_button_mapped.menu_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * 按钮编号
     * 
     * This method returns the value of the database column system_role_button_mapped.button_id
     *
     * @return the value of system_role_button_mapped.button_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public String getButtonId() {
        return buttonId;
    }

    /**
     * 按钮编号
     * 
     * This method sets the value of the database column system_role_button_mapped.button_id
     *
     * @param buttonId the value for system_role_button_mapped.button_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setButtonId(String buttonId) {
        this.buttonId = buttonId == null ? null : buttonId.trim();
    }

    /**
     * 组织机构编码
     * 
     * This method returns the value of the database column system_role_button_mapped.org_id
     *
     * @return the value of system_role_button_mapped.org_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 组织机构编码
     * 
     * This method sets the value of the database column system_role_button_mapped.org_id
     *
     * @param orgId the value for system_role_button_mapped.org_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 角色菜单关系描述
     * 
     * This method returns the value of the database column system_role_button_mapped.mapped_desc
     *
     * @return the value of system_role_button_mapped.mapped_desc
     *
     * @author julong 2022-08-19 09:21:04
     */
    public String getMappedDesc() {
        return mappedDesc;
    }

    /**
     * 角色菜单关系描述
     * 
     * This method sets the value of the database column system_role_button_mapped.mapped_desc
     *
     * @param mappedDesc the value for system_role_button_mapped.mapped_desc
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setMappedDesc(String mappedDesc) {
        this.mappedDesc = mappedDesc == null ? null : mappedDesc.trim();
    }

    /**
     * 角色菜单关系创建时间
     * 
     * This method returns the value of the database column system_role_button_mapped.mapped_create_time
     *
     * @return the value of system_role_button_mapped.mapped_create_time
     *
     * @author julong 2022-08-19 09:21:04
     */
    public Date getMappedCreateTime() {
        return mappedCreateTime;
    }

    /**
     * 角色菜单关系创建时间
     * 
     * This method sets the value of the database column system_role_button_mapped.mapped_create_time
     *
     * @param mappedCreateTime the value for system_role_button_mapped.mapped_create_time
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setMappedCreateTime(Date mappedCreateTime) {
        this.mappedCreateTime = mappedCreateTime;
    }

    /**
     * 角色菜单关系创建人
     * 
     * This method returns the value of the database column system_role_button_mapped.user_id
     *
     * @return the value of system_role_button_mapped.user_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 角色菜单关系创建人
     * 
     * This method sets the value of the database column system_role_button_mapped.user_id
     *
     * @param userId the value for system_role_button_mapped.user_id
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 角色菜单关系修改时间
     * 
     * This method returns the value of the database column system_role_button_mapped.mapped_update_time
     *
     * @return the value of system_role_button_mapped.mapped_update_time
     *
     * @author julong 2022-08-19 09:21:04
     */
    public Date getMappedUpdateTime() {
        return mappedUpdateTime;
    }

    /**
     * 角色菜单关系修改时间
     * 
     * This method sets the value of the database column system_role_button_mapped.mapped_update_time
     *
     * @param mappedUpdateTime the value for system_role_button_mapped.mapped_update_time
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setMappedUpdateTime(Date mappedUpdateTime) {
        this.mappedUpdateTime = mappedUpdateTime;
    }

    /**
     * 角色菜单关系状态:0正常1禁用
     * 
     * This method returns the value of the database column system_role_button_mapped.mapped_status
     *
     * @return the value of system_role_button_mapped.mapped_status
     *
     * @author julong 2022-08-19 09:21:04
     */
    public String getMappedStatus() {
        return mappedStatus;
    }

    /**
     * 角色菜单关系状态:0正常1禁用
     * 
     * This method sets the value of the database column system_role_button_mapped.mapped_status
     *
     * @param mappedStatus the value for system_role_button_mapped.mapped_status
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setMappedStatus(String mappedStatus) {
        this.mappedStatus = mappedStatus == null ? null : mappedStatus.trim();
    }

    /**
     * 备用字段1
     * 
     * This method returns the value of the database column system_role_button_mapped.mapped_reserve_a
     *
     * @return the value of system_role_button_mapped.mapped_reserve_a
     *
     * @author julong 2022-08-19 09:21:04
     */
    public String getMappedReserveA() {
        return mappedReserveA;
    }

    /**
     * 备用字段1
     * 
     * This method sets the value of the database column system_role_button_mapped.mapped_reserve_a
     *
     * @param mappedReserveA the value for system_role_button_mapped.mapped_reserve_a
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setMappedReserveA(String mappedReserveA) {
        this.mappedReserveA = mappedReserveA == null ? null : mappedReserveA.trim();
    }

    /**
     * 备用字段2
     * 
     * This method returns the value of the database column system_role_button_mapped.mapped_reserve_b
     *
     * @return the value of system_role_button_mapped.mapped_reserve_b
     *
     * @author julong 2022-08-19 09:21:04
     */
    public String getMappedReserveB() {
        return mappedReserveB;
    }

    /**
     * 备用字段2
     * 
     * This method sets the value of the database column system_role_button_mapped.mapped_reserve_b
     *
     * @param mappedReserveB the value for system_role_button_mapped.mapped_reserve_b
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setMappedReserveB(String mappedReserveB) {
        this.mappedReserveB = mappedReserveB == null ? null : mappedReserveB.trim();
    }

    /**
     * 备用字段3
     * 
     * This method returns the value of the database column system_role_button_mapped.mapped_reserve_c
     *
     * @return the value of system_role_button_mapped.mapped_reserve_c
     *
     * @author julong 2022-08-19 09:21:04
     */
    public String getMappedReserveC() {
        return mappedReserveC;
    }

    /**
     * 备用字段3
     * 
     * This method sets the value of the database column system_role_button_mapped.mapped_reserve_c
     *
     * @param mappedReserveC the value for system_role_button_mapped.mapped_reserve_c
     *
     * @author julong 2022-08-19 09:21:04
     */
    public void setMappedReserveC(String mappedReserveC) {
        this.mappedReserveC = mappedReserveC == null ? null : mappedReserveC.trim();
    }

	@Override
	public String toString() {
		return "SystemRoleButtonMappedInfo [mappedId=" + mappedId + ", roleId=" + roleId + ", menuId=" + menuId
				+ ", buttonId=" + buttonId + ", orgId=" + orgId + ", mappedDesc=" + mappedDesc + ", mappedCreateTime="
				+ mappedCreateTime + ", userId=" + userId + ", mappedUpdateTime=" + mappedUpdateTime + ", mappedStatus="
				+ mappedStatus + ", mappedReserveA=" + mappedReserveA + ", mappedReserveB=" + mappedReserveB
				+ ", mappedReserveC=" + mappedReserveC + "]";
	}
    
    
}