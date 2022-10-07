package com.julongtech.system.action.vo;

import java.io.Serializable;

/**
 * 用户界面交互类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 * @database table SYSTEM_USER
 */
public class SystemUserVO  implements Serializable{
    /**
	 * @author julong
	 * @date 2017-10-16 下午3:54:54
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * 用户登录名
     */
    private String userId;

    /** 
     * 组织机构编码
     */
    private String orgId;

    /** 
     * 用户登录密码
     */
    private String userPassword;

    /** 
     * 用户姓名
     */
    private String userName;

    /** 
     * 用户年龄：1-999
     */
    private String userAge;

    /** 
     * 用户性别：0：男 1：女2：未知
     */
    private String userSex;

    /** 
     * 用户身份证号码
     */
    private String userIdentity;

    /** 
     * 用户地址
     */
    private String userAddress;

    /** 
     * 用户电话
     */
    private String userPhone;

    /** 
     * 用户邮箱
     */
    private String userMail;

    /** 
     * 用户描述
     */
    private String userDesc;

    /** 
     * 用户图像url
     */
    private String userImage;


    /** 
     * 用户状态：0：正常1：停用
     */
    private String userStatus;

    /** 
     * 用户皮肤
     */
    private String userSkin;

    /** 
     * 用户等级
     */
    private String userLevel;

    /**
     * 开始日期
     */
    private String beginDate;
    
    /**
     * 结束日期
     */
    private String endDate;


    /**
     * 用户登录名
     * @return userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 用户登录名
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * 用户登录密码
     * @return userPassword
     */
    public String getUserPassword() {
        return this.userPassword;
    }

    /**
     * 用户登录密码
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 用户姓名
     * @return userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 用户姓名
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户年龄：1-999
     * @return userAge
     */
    public String getUserAge() {
        return this.userAge;
    }

    /**
     * 用户年龄：1-999
     * @param userAge
     */
    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    /**
     * 用户性别：0：男 1：女2：未知
     * @return userSex
     */
    public String getUserSex() {
        return this.userSex;
    }

    /**
     * 用户性别：0：男 1：女2：未知
     * @param userSex
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    /**
     * 用户身份证号码
     * @return userIdentity
     */
    public String getUserIdentity() {
        return this.userIdentity;
    }

    /**
     * 用户身份证号码
     * @param userIdentity
     */
    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    /**
     * 用户地址
     * @return userAddress
     */
    public String getUserAddress() {
        return this.userAddress;
    }

    /**
     * 用户地址
     * @param userAddress
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * 用户电话
     * @return userPhone
     */
    public String getUserPhone() {
        return this.userPhone;
    }

    /**
     * 用户电话
     * @param userPhone
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 用户邮箱
     * @return userMail
     */
    public String getUserMail() {
        return this.userMail;
    }

    /**
     * 用户邮箱
     * @param userMail
     */
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    /**
     * 用户描述
     * @return userDesc
     */
    public String getUserDesc() {
        return this.userDesc;
    }

    /**
     * 用户描述
     * @param userDesc
     */
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    /**
     * 用户图像url
     * @return userImage
     */
    public String getUserImage() {
        return this.userImage;
    }

    /**
     * 用户图像url
     * @param userImage
     */
    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    /**
     * 用户状态：0：正常1：停用
     * @return userStatus
     */
    public String getUserStatus() {
        return this.userStatus;
    }

    /**
     * 用户状态：0：正常1：停用
     * @param userStatus
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 用户皮肤
     * @return userSkin
     */
    public String getUserSkin() {
        return this.userSkin;
    }

    /**
     * 用户皮肤
     * @param userSkin
     */
    public void setUserSkin(String userSkin) {
        this.userSkin = userSkin;
    }

    /**
     * 用户等级
     * @return userLevel
     */
    public String getUserLevel() {
        return this.userLevel;
    }

    /**
     * 用户等级
     * @param userLevel
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * 开始日期
     * @return beginDate
     */
    public String getBeginDate() {
        return this.beginDate;
    }

    /**
     * 开始日期
     * @param beginDate
     */
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 结束日期
     * @return endDate
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * 结束日期
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemUserVO{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", orgId='").append(orgId).append('\'');
        sb.append(", userPassword='").append(userPassword).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", userAge='").append(userAge).append('\'');
        sb.append(", userSex='").append(userSex).append('\'');
        sb.append(", userIdentity='").append(userIdentity).append('\'');
        sb.append(", userAddress='").append(userAddress).append('\'');
        sb.append(", userPhone='").append(userPhone).append('\'');
        sb.append(", userMail='").append(userMail).append('\'');
        sb.append(", userDesc='").append(userDesc).append('\'');
        sb.append(", userImage='").append(userImage).append('\'');
        sb.append(", userStatus='").append(userStatus).append('\'');
        sb.append(", userSkin='").append(userSkin).append('\'');
        sb.append(", userLevel='").append(userLevel).append('\'');
        sb.append(", beginDate='").append(beginDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}