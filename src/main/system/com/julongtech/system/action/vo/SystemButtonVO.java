package com.julongtech.system.action.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 按钮界面交互
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 */
public class SystemButtonVO implements Serializable{
    /**
	 * @author julong
	 * @date 2017-10-16 下午3:16:22
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * 按钮编号
     */
    private String buttonId;

    /** 
     * 按钮名称
     */
    private String buttonName;

    /** 
     * 按钮代码
     */
    private String buttonCode;

    /** 
     * 按钮地址URL
     */
    private String buttonUrl;

    /** 
     * 按钮状态
     */
    private String buttonStatus;

    /** 
     * 按钮创建人
     */
    private String userId;

    /** 
     * 按钮图标
     */
    private String buttonIco;

    /** 
     * 菜单编号
     */
    private String menuId;

    /** 
     * 按钮顺序
     */
    private BigDecimal buttonOrder;

    /** 
     * 按钮节点
     */
    private String buttonFather;


    /**
     * 按钮编号
     * @return buttonId
     */
    public String getButtonId() {
        return this.buttonId;
    }

    /**
     * 按钮编号
     * @param buttonId
     */
    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    /**
     * 按钮名称
     * @return buttonName
     */
    public String getButtonName() {
        return this.buttonName;
    }

    /**
     * 按钮名称
     * @param buttonName
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    /**
     * 按钮代码
     * @return buttonCode
     */
    public String getButtonCode() {
        return this.buttonCode;
    }

    /**
     * 按钮代码
     * @param buttonCode
     */
    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    /**
     * 按钮地址URL
     * @return buttonUrl
     */
    public String getButtonUrl() {
        return this.buttonUrl;
    }

    /**
     * 按钮地址URL
     * @param buttonUrl
     */
    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
    }

    /**
     * 按钮状态
     * @return buttonStatus
     */
    public String getButtonStatus() {
        return this.buttonStatus;
    }

    /**
     * 按钮状态
     * @param buttonStatus
     */
    public void setButtonStatus(String buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    /**
     * 按钮创建人
     * @return userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 按钮创建人
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 按钮图标
     * @return buttonIco
     */
    public String getButtonIco() {
        return this.buttonIco;
    }

    /**
     * 按钮图标
     * @param buttonIco
     */
    public void setButtonIco(String buttonIco) {
        this.buttonIco = buttonIco;
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
     * 按钮顺序
     * @return buttonOrder
     */
    public BigDecimal getButtonOrder() {
        return this.buttonOrder;
    }

    /**
     * 按钮顺序
     * @param buttonOrder
     */
    public void setButtonOrder(BigDecimal buttonOrder) {
        this.buttonOrder = buttonOrder;
    }

    /**
     * 按钮节点
     * @return buttonFather
     */
    public String getButtonFather() {
        return this.buttonFather;
    }

    /**
     * 按钮节点
     * @param buttonFather
     */
    public void setButtonFather(String buttonFather) {
        this.buttonFather = buttonFather;
    }

    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemButtonVO{");
        sb.append("buttonId='").append(buttonId).append('\'');
        sb.append(", buttonName='").append(buttonName).append('\'');
        sb.append(", buttonCode='").append(buttonCode).append('\'');
        sb.append(", buttonUrl='").append(buttonUrl).append('\'');
        sb.append(", buttonStatus='").append(buttonStatus).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", buttonIco='").append(buttonIco).append('\'');
        sb.append(", menuId='").append(menuId).append('\'');
        sb.append(", buttonOrder=").append(buttonOrder);
        sb.append(", buttonFather='").append(buttonFather).append('\'');
        sb.append('}');
        return sb.toString();
    }
}