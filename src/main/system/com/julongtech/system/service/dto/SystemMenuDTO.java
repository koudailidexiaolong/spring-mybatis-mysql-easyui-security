package com.julongtech.system.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 菜单信息类
 * @author julong 
 * @date Mon Oct 16 16:12:03 CST 2017
 */
public class SystemMenuDTO implements Serializable{
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
     * 菜单创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Timestamp menuCreateTime;

    /** 
     * 菜单创建人
     */
    private String userId;
    
    /** 
     * 菜单创建人
     */
    private String menuCreateUserName;
    
    /** 
     * 菜单修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Timestamp menuUpdateTime;

    /** 
     * 备用字段1
     */
    private String menuReserveA;

    /** 
     * 备用字段2
     */
    private String menuReserveB;

    /** 
     * 备用字段3
     */
    private String menuReserveC;

    /**
     * 设置默认是否选中
     * @author julong
     * @date 2017-11-3 下午8:30:29
     */
    private String menuChecked = "false";
    
    /**
     * 子菜单
     * @author julong
     * @date 2018-6-13 下午4:34:36
     */
    private List<SystemMenuDTO> children = new ArrayList<SystemMenuDTO>();
    
    /**
     * 按钮
     * @author julong
     * @date 2018-6-13 下午4:34:36
     */
    private List<SystemButtonDTO> button = new ArrayList<SystemButtonDTO>();


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

    /**
     * 菜单创建时间
     * @return menuCreateTime
     */
    public Timestamp getMenuCreateTime() {
        return this.menuCreateTime;
    }

    /**
     * 菜单创建时间
     * @param menuCreateTime
     */
    public void setMenuCreateTime(Timestamp menuCreateTime) {
        this.menuCreateTime = menuCreateTime;
    }

    /**
     * 菜单创建人
     * @return userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 菜单创建人
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 菜单创建人
     * @return menuCreateUserName
     */
    public String getMenuCreateUserName() {
        return this.menuCreateUserName;
    }

    /**
     * 菜单创建人
     * @param menuCreateUserName
     */
    public void setMenuCreateUserName(String menuCreateUserName) {
        this.menuCreateUserName = menuCreateUserName;
    }

    /**
     * 菜单修改时间
     * @return menuUpdateTime
     */
    public Timestamp getMenuUpdateTime() {
        return this.menuUpdateTime;
    }

    /**
     * 菜单修改时间
     * @param menuUpdateTime
     */
    public void setMenuUpdateTime(Timestamp menuUpdateTime) {
        this.menuUpdateTime = menuUpdateTime;
    }

    /**
     * 备用字段1
     * @return menuReserveA
     */
    public String getMenuReserveA() {
        return this.menuReserveA;
    }

    /**
     * 备用字段1
     * @param menuReserveA
     */
    public void setMenuReserveA(String menuReserveA) {
        this.menuReserveA = menuReserveA;
    }

    /**
     * 备用字段2
     * @return menuReserveB
     */
    public String getMenuReserveB() {
        return this.menuReserveB;
    }

    /**
     * 备用字段2
     * @param menuReserveB
     */
    public void setMenuReserveB(String menuReserveB) {
        this.menuReserveB = menuReserveB;
    }

    /**
     * 备用字段3
     * @return menuReserveC
     */
    public String getMenuReserveC() {
        return this.menuReserveC;
    }

    /**
     * 备用字段3
     * @param menuReserveC
     */
    public void setMenuReserveC(String menuReserveC) {
        this.menuReserveC = menuReserveC;
    }

    /**
     * 设置默认是否选中
     * @return menuChecked
     */
    public String getMenuChecked() {
        return this.menuChecked;
    }

    /**
     * 设置默认是否选中
     * @param menuChecked
     */
    public void setMenuChecked(String menuChecked) {
        this.menuChecked = menuChecked;
    }

    /**
     * 子菜单
     * @return children
     */
    public List<SystemMenuDTO> getChildren() {
        return this.children;
    }

    /**
     * 子菜单
     * @param children
     */
    public void setChildren(List<SystemMenuDTO> children) {
        this.children = children;
    }

    /**
     * 按钮
     * @return button
     */
    public List<SystemButtonDTO> getButton() {
        return this.button;
    }

    /**
     * 按钮
     * @param button
     */
    public void setButton(List<SystemButtonDTO> button) {
        this.button = button;
    }

    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemMenuDTO{");
        sb.append("menuId='").append(menuId).append('\'');
        sb.append(", menuName='").append(menuName).append('\'');
        sb.append(", menuCode='").append(menuCode).append('\'');
        sb.append(", menuFatherId='").append(menuFatherId).append('\'');
        sb.append(", menuOrder=").append(menuOrder);
        sb.append(", menuStatus='").append(menuStatus).append('\'');
        sb.append(", menuUrl='").append(menuUrl).append('\'');
        sb.append(", menuIco='").append(menuIco).append('\'');
        sb.append(", menuLevel='").append(menuLevel).append('\'');
        sb.append(", menuCreateTime=").append(menuCreateTime);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", menuCreateUserName='").append(menuCreateUserName).append('\'');
        sb.append(", menuUpdateTime=").append(menuUpdateTime);
        sb.append(", menuReserveA='").append(menuReserveA).append('\'');
        sb.append(", menuReserveB='").append(menuReserveB).append('\'');
        sb.append(", menuReserveC='").append(menuReserveC).append('\'');
        sb.append(", menuChecked='").append(menuChecked).append('\'');
        sb.append('}');
        return sb.toString();
    }
}