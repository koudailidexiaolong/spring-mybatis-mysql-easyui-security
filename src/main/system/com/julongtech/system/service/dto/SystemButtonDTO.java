package com.julongtech.system.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 按钮信息类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 */
public class SystemButtonDTO implements Serializable{
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Timestamp buttonCreateTime;
    
    /** 
     * 按钮修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
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
     * 按钮创建人
     */
    private String buttonCreateUserName;


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
     * 按钮创建时间
     * @return buttonCreateTime
     */
    public Timestamp getButtonCreateTime() {
        return this.buttonCreateTime;
    }

    /**
     * 按钮创建时间
     * @param buttonCreateTime
     */
    public void setButtonCreateTime(Timestamp buttonCreateTime) {
        this.buttonCreateTime = buttonCreateTime;
    }

    /**
     * 按钮修改时间
     * @return buttonUpdateTime
     */
    public Timestamp getButtonUpdateTime() {
        return this.buttonUpdateTime;
    }

    /**
     * 按钮修改时间
     * @param buttonUpdateTime
     */
    public void setButtonUpdateTime(Timestamp buttonUpdateTime) {
        this.buttonUpdateTime = buttonUpdateTime;
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

    /**
     * 备用字段1
     * @return buttonReserveA
     */
    public String getButtonReserveA() {
        return this.buttonReserveA;
    }

    /**
     * 备用字段1
     * @param buttonReserveA
     */
    public void setButtonReserveA(String buttonReserveA) {
        this.buttonReserveA = buttonReserveA;
    }

    /**
     * 备用字段2
     * @return buttonReserveB
     */
    public String getButtonReserveB() {
        return this.buttonReserveB;
    }

    /**
     * 备用字段2
     * @param buttonReserveB
     */
    public void setButtonReserveB(String buttonReserveB) {
        this.buttonReserveB = buttonReserveB;
    }

    /**
     * 备用字段3
     * @return buttonReserveC
     */
    public String getButtonReserveC() {
        return this.buttonReserveC;
    }

    /**
     * 备用字段3
     * @param buttonReserveC
     */
    public void setButtonReserveC(String buttonReserveC) {
        this.buttonReserveC = buttonReserveC;
    }

    /**
     * 按钮创建人
     * @return buttonCreateUserName
     */
    public String getButtonCreateUserName() {
        return this.buttonCreateUserName;
    }

    /**
     * 按钮创建人
     * @param buttonCreateUserName
     */
    public void setButtonCreateUserName(String buttonCreateUserName) {
        this.buttonCreateUserName = buttonCreateUserName;
    }

    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemButtonDTO{");
        sb.append("buttonId='").append(buttonId).append('\'');
        sb.append(", buttonName='").append(buttonName).append('\'');
        sb.append(", buttonCode='").append(buttonCode).append('\'');
        sb.append(", buttonUrl='").append(buttonUrl).append('\'');
        sb.append(", buttonStatus='").append(buttonStatus).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", buttonIco='").append(buttonIco).append('\'');
        sb.append(", menuId='").append(menuId).append('\'');
        sb.append(", buttonOrder=").append(buttonOrder);
        sb.append(", buttonCreateTime=").append(buttonCreateTime);
        sb.append(", buttonUpdateTime=").append(buttonUpdateTime);
        sb.append(", buttonFather='").append(buttonFather).append('\'');
        sb.append(", buttonReserveA='").append(buttonReserveA).append('\'');
        sb.append(", buttonReserveB='").append(buttonReserveB).append('\'');
        sb.append(", buttonReserveC='").append(buttonReserveC).append('\'');
        sb.append(", buttonCreateUserName='").append(buttonCreateUserName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}