package com.julongtech.system.action.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜单界面交互类
 * @author julong 
 * @date Mon Oct 16 16:12:03 CST 2017
 */
public class SystemMenuVO implements Serializable{
    /**
	 * @author julong
	 * @date 2017-10-16 下午4:12:31
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * 菜单编号
     */
    private String menuId;

    /** 
     * 菜单名称
     */
    private String menuName;

    /** 
     * 菜单代码
     */
    private String menuCode;

    /** 
     * 菜单父节点
     */
    private String menuFatherId;

    /** 
     * 菜单顺序
     */
    private BigDecimal menuOrder;

    /** 
     * 菜单状态：0正常1禁用
     */
    private String menuStatus;

    /** 
     * 菜单地址URL
     */
    private String menuUrl;

    /** 
     * 菜单图标
     */
    private String menuIco;

    /** 
     * 是否有子节点
     */
    private String menuLevel;


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
     * 菜单名称
     * @return menuName
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * 菜单名称
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 菜单代码
     * @return menuCode
     */
    public String getMenuCode() {
        return this.menuCode;
    }

    /**
     * 菜单代码
     * @param menuCode
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 菜单父节点
     * @return menuFatherId
     */
    public String getMenuFatherId() {
        return this.menuFatherId;
    }

    /**
     * 菜单父节点
     * @param menuFatherId
     */
    public void setMenuFatherId(String menuFatherId) {
        this.menuFatherId = menuFatherId;
    }

    /**
     * 菜单顺序
     * @return menuOrder
     */
    public BigDecimal getMenuOrder() {
        return this.menuOrder;
    }

    /**
     * 菜单顺序
     * @param menuOrder
     */
    public void setMenuOrder(BigDecimal menuOrder) {
        this.menuOrder = menuOrder;
    }

    /**
     * 菜单状态：0正常1禁用
     * @return menuStatus
     */
    public String getMenuStatus() {
        return this.menuStatus;
    }

    /**
     * 菜单状态：0正常1禁用
     * @param menuStatus
     */
    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }

    /**
     * 菜单地址URL
     * @return menuUrl
     */
    public String getMenuUrl() {
        return this.menuUrl;
    }

    /**
     * 菜单地址URL
     * @param menuUrl
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * 菜单图标
     * @return menuIco
     */
    public String getMenuIco() {
        return this.menuIco;
    }

    /**
     * 菜单图标
     * @param menuIco
     */
    public void setMenuIco(String menuIco) {
        this.menuIco = menuIco;
    }

    /**
     * 是否有子节点
     * @return menuLevel
     */
    public String getMenuLevel() {
        return this.menuLevel;
    }

    /**
     * 是否有子节点
     * @param menuLevel
     */
    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    
    
   	public SystemMenuVO() {
   		super();
   	}

   	public SystemMenuVO(String menuId, String menuFatherId) {
   		super();
   		this.menuId = menuId;
   		this.menuFatherId = menuFatherId;
   	}

    
    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemMenuVO{");
        sb.append("menuId='").append(menuId).append('\'');
        sb.append(", menuName='").append(menuName).append('\'');
        sb.append(", menuCode='").append(menuCode).append('\'');
        sb.append(", menuFatherId='").append(menuFatherId).append('\'');
        sb.append(", menuOrder=").append(menuOrder);
        sb.append(", menuStatus='").append(menuStatus).append('\'');
        sb.append(", menuUrl='").append(menuUrl).append('\'');
        sb.append(", menuIco='").append(menuIco).append('\'');
        sb.append(", menuLevel='").append(menuLevel).append('\'');
        sb.append('}');
        return sb.toString();
    }
}