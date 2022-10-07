package com.julongtech.system.action.vo;

import java.io.Serializable;

/**
 * 角色菜单映射关系界面交互类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 */
public class SystemRoleMenuMappedVO  implements Serializable{
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
     * 角色菜单关系状态:0正常1禁用
     */
    private String mappedStatus;

    /**
     * 菜单数组
     */
    private String menuArray;


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
     * 菜单数组
     * @return menuArray
     */
    public String getMenuArray() {
        return this.menuArray;
    }

    /**
     * 菜单数组
     * @param menuArray
     */
    public void setMenuArray(String menuArray) {
        this.menuArray = menuArray;
    }

    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemRoleMenuMappedVO{");
        sb.append("mappedId=").append(mappedId);
        sb.append(", roleId=").append(roleId);
        sb.append(", menuId='").append(menuId).append('\'');
        sb.append(", orgId='").append(orgId).append('\'');
        sb.append(", mappedMenuArray='").append(mappedMenuArray).append('\'');
        sb.append(", mappedStatus='").append(mappedStatus).append('\'');
        sb.append(", menuArray='").append(menuArray).append('\'');
        sb.append('}');
        return sb.toString();
    }
}