package com.julongtech.system.action.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色按钮参数类
 *
 * @author julong
 * @date 2022年8月21日 下午3:50:51
 * @desc
 */
public class SystemRoleButtonMappedVO implements Serializable {
    /**
     * @author julong
     * @date 2022年8月19日 下午9:32:12
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
     * 按钮编号
     */
    private String buttonId;

    /**
     * 组织机构编码
     */
    private String orgId;

    /**
     * 角色菜单关系描述
     */
    private String mappedDesc;

    /**
     * 角色菜单关系创建时间
     */
    private Date mappedCreateTime;

    /**
     * 角色菜单关系创建人
     */
    private String userId;

    /**
     * 角色菜单关系修改时间
     */
    private Date mappedUpdateTime;

    /**
     * 角色菜单关系状态:0正常1禁用
     */
    private String mappedStatus;


    /**
     * 角色菜单关系编号
     *
     * @return mappedId
     */
    public Integer getMappedId() {
        return this.mappedId;
    }

    /**
     * 角色菜单关系编号
     *
     * @param mappedId
     */
    public void setMappedId(Integer mappedId) {
        this.mappedId = mappedId;
    }

    /**
     * 角色编号
     *
     * @return roleId
     */
    public Integer getRoleId() {
        return this.roleId;
    }

    /**
     * 角色编号
     *
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 菜单编号
     *
     * @return menuId
     */
    public String getMenuId() {
        return this.menuId;
    }

    /**
     * 菜单编号
     *
     * @param menuId
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 按钮编号
     *
     * @return buttonId
     */
    public String getButtonId() {
        return this.buttonId;
    }

    /**
     * 按钮编号
     *
     * @param buttonId
     */
    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    /**
     * 组织机构编码
     *
     * @return orgId
     */
    public String getOrgId() {
        return this.orgId;
    }

    /**
     * 组织机构编码
     *
     * @param orgId
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 角色菜单关系描述
     *
     * @return mappedDesc
     */
    public String getMappedDesc() {
        return this.mappedDesc;
    }

    /**
     * 角色菜单关系描述
     *
     * @param mappedDesc
     */
    public void setMappedDesc(String mappedDesc) {
        this.mappedDesc = mappedDesc;
    }

    /**
     * 角色菜单关系创建时间
     *
     * @return mappedCreateTime
     */
    public Date getMappedCreateTime() {
        return this.mappedCreateTime;
    }

    /**
     * 角色菜单关系创建时间
     *
     * @param mappedCreateTime
     */
    public void setMappedCreateTime(Date mappedCreateTime) {
        this.mappedCreateTime = mappedCreateTime;
    }

    /**
     * 角色菜单关系创建人
     *
     * @return userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 角色菜单关系创建人
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 角色菜单关系修改时间
     *
     * @return mappedUpdateTime
     */
    public Date getMappedUpdateTime() {
        return this.mappedUpdateTime;
    }

    /**
     * 角色菜单关系修改时间
     *
     * @param mappedUpdateTime
     */
    public void setMappedUpdateTime(Date mappedUpdateTime) {
        this.mappedUpdateTime = mappedUpdateTime;
    }

    /**
     * 角色菜单关系状态:0正常1禁用
     *
     * @return mappedStatus
     */
    public String getMappedStatus() {
        return this.mappedStatus;
    }

    /**
     * 角色菜单关系状态:0正常1禁用
     *
     * @param mappedStatus
     */
    public void setMappedStatus(String mappedStatus) {
        this.mappedStatus = mappedStatus;
    }

	@java.lang.Override
	public java.lang.String toString() {
		final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemRoleButtonMappedVO{");
		sb.append("mappedId=").append(mappedId);
		sb.append(", roleId=").append(roleId);
		sb.append(", menuId='").append(menuId).append('\'');
		sb.append(", buttonId='").append(buttonId).append('\'');
		sb.append(", orgId='").append(orgId).append('\'');
		sb.append(", mappedDesc='").append(mappedDesc).append('\'');
		sb.append(", mappedCreateTime=").append(mappedCreateTime);
		sb.append(", userId='").append(userId).append('\'');
		sb.append(", mappedUpdateTime=").append(mappedUpdateTime);
		sb.append(", mappedStatus='").append(mappedStatus).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
