package com.julongtech.system.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 按钮信息类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 * @database table SYSTEM_BUTTON
 */
public class SystemButtonInfo implements Serializable{
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
     * 按钮创建时间
     */
    private Timestamp buttonCreateTime;

    /** 
     * 按钮修改时间
     */
    private Timestamp buttonUpdateTime;

    /** 
     * 按钮节点
     */
    private String buttonFather;

    /** 
     * 备用字段1
     */
    private String buttonReserveA;

    /** 
     * 备用字段2
     */
    private String buttonReserveB;

    /** 
     * 备用字段3
     */
    private String buttonReserveC;

    /**
     * 按钮编号
     * @return  the value of SYSTEM_BUTTON.BUTTON_ID
     */
    public String getButtonId() {
        return buttonId;
    }

    /**
     * 按钮编号
     * @param buttonId the value for SYSTEM_BUTTON.BUTTON_ID
     */
    public void setButtonId(String buttonId) {
        this.buttonId = buttonId == null ? null : buttonId.trim();
    }

    /**
     * 按钮名称
     * @return  the value of SYSTEM_BUTTON.BUTTON_NAME
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * 按钮名称
     * @param buttonName the value for SYSTEM_BUTTON.BUTTON_NAME
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName == null ? null : buttonName.trim();
    }

    /**
     * 按钮代码
     * @return  the value of SYSTEM_BUTTON.BUTTON_CODE
     */
    public String getButtonCode() {
        return buttonCode;
    }

    /**
     * 按钮代码
     * @param buttonCode the value for SYSTEM_BUTTON.BUTTON_CODE
     */
    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode == null ? null : buttonCode.trim();
    }

    /**
     * 按钮地址URL
     * @return  the value of SYSTEM_BUTTON.BUTTON_URL
     */
    public String getButtonUrl() {
        return buttonUrl;
    }

    /**
     * 按钮地址URL
     * @param buttonUrl the value for SYSTEM_BUTTON.BUTTON_URL
     */
    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl == null ? null : buttonUrl.trim();
    }

    /**
     * 按钮状态
     * @return  the value of SYSTEM_BUTTON.BUTTON_STATUS
     */
    public String getButtonStatus() {
        return buttonStatus;
    }

    /**
     * 按钮状态
     * @param buttonStatus the value for SYSTEM_BUTTON.BUTTON_STATUS
     */
    public void setButtonStatus(String buttonStatus) {
        this.buttonStatus = buttonStatus == null ? null : buttonStatus.trim();
    }

    /**
     * 按钮创建人
     * @return  the value of SYSTEM_BUTTON.USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 按钮创建人
     * @param userId the value for SYSTEM_BUTTON.USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 按钮图标
     * @return  the value of SYSTEM_BUTTON.BUTTON_ICO
     */
    public String getButtonIco() {
        return buttonIco;
    }

    /**
     * 按钮图标
     * @param buttonIco the value for SYSTEM_BUTTON.BUTTON_ICO
     */
    public void setButtonIco(String buttonIco) {
        this.buttonIco = buttonIco == null ? null : buttonIco.trim();
    }

    /**
     * 菜单编号
     * @return  the value of SYSTEM_BUTTON.MENU_ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 菜单编号
     * @param menuId the value for SYSTEM_BUTTON.MENU_ID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * 按钮顺序
     * @return  the value of SYSTEM_BUTTON.BUTTON_ORDER
     */
    public BigDecimal getButtonOrder() {
        return buttonOrder;
    }

    /**
     * 按钮顺序
     * @param buttonOrder the value for SYSTEM_BUTTON.BUTTON_ORDER
     */
    public void setButtonOrder(BigDecimal buttonOrder) {
        this.buttonOrder = buttonOrder;
    }

    /**
     * 按钮创建时间
     * @return  the value of SYSTEM_BUTTON.BUTTON_CREATE_TIME
     */
    public Timestamp getButtonCreateTime() {
        return buttonCreateTime;
    }

    /**
     * 按钮创建时间
     * @param buttonCreateTime the value for SYSTEM_BUTTON.BUTTON_CREATE_TIME
     */
    public void setButtonCreateTime(Timestamp buttonCreateTime) {
        this.buttonCreateTime = buttonCreateTime;
    }

    /**
     * 按钮修改时间
     * @return  the value of SYSTEM_BUTTON.BUTTON_UPDATE_TIME
     */
    public Timestamp getButtonUpdateTime() {
        return buttonUpdateTime;
    }

    /**
     * 按钮修改时间
     * @param buttonUpdateTime the value for SYSTEM_BUTTON.BUTTON_UPDATE_TIME
     */
    public void setButtonUpdateTime(Timestamp buttonUpdateTime) {
        this.buttonUpdateTime = buttonUpdateTime;
    }

    /**
     * 按钮节点
     * @return  the value of SYSTEM_BUTTON.BUTTON_FATHER
     */
    public String getButtonFather() {
        return buttonFather;
    }

    /**
     * 按钮节点
     * @param buttonFather the value for SYSTEM_BUTTON.BUTTON_FATHER
     */
    public void setButtonFather(String buttonFather) {
        this.buttonFather = buttonFather == null ? null : buttonFather.trim();
    }

    /**
     * 备用字段1
     * @return  the value of SYSTEM_BUTTON.BUTTON_RESERVE_A
     */
    public String getButtonReserveA() {
        return buttonReserveA;
    }

    /**
     * 备用字段1
     * @param buttonReserveA the value for SYSTEM_BUTTON.BUTTON_RESERVE_A
     */
    public void setButtonReserveA(String buttonReserveA) {
        this.buttonReserveA = buttonReserveA == null ? null : buttonReserveA.trim();
    }

    /**
     * 备用字段2
     * @return  the value of SYSTEM_BUTTON.BUTTON_RESERVE_B
     */
    public String getButtonReserveB() {
        return buttonReserveB;
    }

    /**
     * 备用字段2
     * @param buttonReserveB the value for SYSTEM_BUTTON.BUTTON_RESERVE_B
     */
    public void setButtonReserveB(String buttonReserveB) {
        this.buttonReserveB = buttonReserveB == null ? null : buttonReserveB.trim();
    }

    /**
     * 备用字段3
     * @return  the value of SYSTEM_BUTTON.BUTTON_RESERVE_C
     */
    public String getButtonReserveC() {
        return buttonReserveC;
    }

    /**
     * 备用字段3
     * @param buttonReserveC the value for SYSTEM_BUTTON.BUTTON_RESERVE_C
     */
    public void setButtonReserveC(String buttonReserveC) {
        this.buttonReserveC = buttonReserveC == null ? null : buttonReserveC.trim();
    }

	@Override
	public String toString() {
		return "SystemButtonInfo [buttonId=" + buttonId + ", buttonName="
				+ buttonName + ", buttonCode=" + buttonCode + ", buttonUrl="
				+ buttonUrl + ", buttonStatus=" + buttonStatus + ", userId="
				+ userId + ", buttonIco=" + buttonIco + ", menuId=" + menuId
				+ ", buttonOrder=" + buttonOrder + ", buttonCreateTime="
				+ buttonCreateTime + ", buttonUpdateTime=" + buttonUpdateTime
				+ ", buttonFather=" + buttonFather + ", buttonReserveA="
				+ buttonReserveA + ", buttonReserveB=" + buttonReserveB
				+ ", buttonReserveC=" + buttonReserveC + "]";
	}
    
    
}