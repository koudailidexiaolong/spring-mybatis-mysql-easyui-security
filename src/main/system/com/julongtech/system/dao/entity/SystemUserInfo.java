package com.julongtech.system.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户信息类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 * @database table SYSTEM_USER
 */
public class SystemUserInfo  implements Serializable{
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
     * 用户创建时间
     */
    private Timestamp userCreateTime;

    /** 
     * 用户创建人
     */
    private String userCreateUserId;

    /** 
     * 用户修改时间
     */
    private Timestamp userUpdateTime;

    /** 
     * 用户修改人
     */
    private String userUpdateUserId;

    /** 
     * 用户密码修改时间
     */
    private Timestamp userUpdatePasswordTime;

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
     * 备用字段1
     */
    private String userReserveA;

    /** 
     * 备用字段2
     */
    private String userReserveB;

    /** 
     * 备用字段3
     */
    private String userReserveC;

    /**
     * 用户登录名
     * @return  the value of SYSTEM_USER.USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户登录名
     * @param userId the value for SYSTEM_USER.USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 组织机构编码
     * @return  the value of SYSTEM_USER.ORG_ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 组织机构编码
     * @param orgId the value for SYSTEM_USER.ORG_ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 用户登录密码
     * @return  the value of SYSTEM_USER.USER_PASSWORD
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 用户登录密码
     * @param userPassword the value for SYSTEM_USER.USER_PASSWORD
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * 用户姓名
     * @return  the value of SYSTEM_USER.USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户姓名
     * @param userName the value for SYSTEM_USER.USER_NAME
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 用户年龄：1-999
     * @return  the value of SYSTEM_USER.USER_AGE
     */
    public String getUserAge() {
        return userAge;
    }

    /**
     * 用户年龄：1-999
     * @param userAge the value for SYSTEM_USER.USER_AGE
     */
    public void setUserAge(String userAge) {
        this.userAge = userAge == null ? null : userAge.trim();
    }

    /**
     * 用户性别：0：男 1：女2：未知
     * @return  the value of SYSTEM_USER.USER_SEX
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * 用户性别：0：男 1：女2：未知
     * @param userSex the value for SYSTEM_USER.USER_SEX
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    /**
     * 用户身份证号码
     * @return  the value of SYSTEM_USER.USER_IDENTITY
     */
    public String getUserIdentity() {
        return userIdentity;
    }

    /**
     * 用户身份证号码
     * @param userIdentity the value for SYSTEM_USER.USER_IDENTITY
     */
    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity == null ? null : userIdentity.trim();
    }

    /**
     * 用户地址
     * @return  the value of SYSTEM_USER.USER_ADDRESS
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * 用户地址
     * @param userAddress the value for SYSTEM_USER.USER_ADDRESS
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    /**
     * 用户电话
     * @return  the value of SYSTEM_USER.USER_PHONE
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 用户电话
     * @param userPhone the value for SYSTEM_USER.USER_PHONE
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * 用户邮箱
     * @return  the value of SYSTEM_USER.USER_MAIL
     */
    public String getUserMail() {
        return userMail;
    }

    /**
     * 用户邮箱
     * @param userMail the value for SYSTEM_USER.USER_MAIL
     */
    public void setUserMail(String userMail) {
        this.userMail = userMail == null ? null : userMail.trim();
    }

    /**
     * 用户描述
     * @return  the value of SYSTEM_USER.USER_DESC
     */
    public String getUserDesc() {
        return userDesc;
    }

    /**
     * 用户描述
     * @param userDesc the value for SYSTEM_USER.USER_DESC
     */
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc == null ? null : userDesc.trim();
    }

    /**
     * 用户图像url
     * @return  the value of SYSTEM_USER.USER_IMAGE
     */
    public String getUserImage() {
        return userImage;
    }

    /**
     * 用户图像url
     * @param userImage the value for SYSTEM_USER.USER_IMAGE
     */
    public void setUserImage(String userImage) {
        this.userImage = userImage == null ? null : userImage.trim();
    }

    /**
     * 用户创建时间
     * @return  the value of SYSTEM_USER.USER_CREATE_TIME
     */
    public Timestamp getUserCreateTime() {
        return userCreateTime;
    }

    /**
     * 用户创建时间
     * @param userCreateTime the value for SYSTEM_USER.USER_CREATE_TIME
     */
    public void setUserCreateTime(Timestamp userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    /**
     * 用户创建人
     * @return  the value of SYSTEM_USER.USER_CREATE_LOGIN_ID
     */
    public String getUserCreateUserId() {
        return userCreateUserId;
    }

    /**
     * 用户创建人
     * @param userCreateUserId the value for SYSTEM_USER.USER_CREATE_LOGIN_ID
     */
    public void setUserCreateUserId(String userCreateUserId) {
        this.userCreateUserId = userCreateUserId == null ? null : userCreateUserId.trim();
    }

    /**
     * 用户修改时间
     * @return  the value of SYSTEM_USER.USER_UPDATE_TIME
     */
    public Timestamp getUserUpdateTime() {
        return userUpdateTime;
    }

    /**
     * 用户修改时间
     * @param userUpdateTime the value for SYSTEM_USER.USER_UPDATE_TIME
     */
    public void setUserUpdateTime(Timestamp userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    /**
     * 用户修改人
     * @return  the value of SYSTEM_USER.USER_UPDATE_LOGIN_ID
     */
    public String getUserUpdateUserId() {
        return userUpdateUserId;
    }

    /**
     * 用户修改人
     * @param userUpdateUserId the value for SYSTEM_USER.USER_UPDATE_LOGIN_ID
     */
    public void setUserUpdateUserId(String userUpdateUserId) {
        this.userUpdateUserId = userUpdateUserId == null ? null : userUpdateUserId.trim();
    }

    /**
     * 用户密码修改时间
     * @return  the value of SYSTEM_USER.USER_UPDATE_PASSWORD_TIME
     */
    public Timestamp getUserUpdatePasswordTime() {
        return userUpdatePasswordTime;
    }

    /**
     * 用户密码修改时间
     * @param userUpdatePasswordTime the value for SYSTEM_USER.USER_UPDATE_PASSWORD_TIME
     */
    public void setUserUpdatePasswordTime(Timestamp userUpdatePasswordTime) {
        this.userUpdatePasswordTime = userUpdatePasswordTime;
    }

    /**
     * 用户状态：0：正常1：停用
     * @return  the value of SYSTEM_USER.USER_STATUS
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 用户状态：0：正常1：停用
     * @param userStatus the value for SYSTEM_USER.USER_STATUS
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    /**
     * 用户皮肤
     * @return  the value of SYSTEM_USER.USER_SKIN
     */
    public String getUserSkin() {
        return userSkin;
    }

    /**
     * 用户皮肤
     * @param userSkin the value for SYSTEM_USER.USER_SKIN
     */
    public void setUserSkin(String userSkin) {
        this.userSkin = userSkin == null ? null : userSkin.trim();
    }

    /**
     * 用户等级
     * @return  the value of SYSTEM_USER.USER_LEVEL
     */
    public String getUserLevel() {
        return userLevel;
    }

    /**
     * 用户等级
     * @param userLevel the value for SYSTEM_USER.USER_LEVEL
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel == null ? null : userLevel.trim();
    }

    /**
     * 备用字段1
     * @return  the value of SYSTEM_USER.USER_RESERVE_A
     */
    public String getUserReserveA() {
        return userReserveA;
    }

    /**
     * 备用字段1
     * @param userReserveA the value for SYSTEM_USER.USER_RESERVE_A
     */
    public void setUserReserveA(String userReserveA) {
        this.userReserveA = userReserveA == null ? null : userReserveA.trim();
    }

    /**
     * 备用字段2
     * @return  the value of SYSTEM_USER.USER_RESERVE_B
     */
    public String getUserReserveB() {
        return userReserveB;
    }

    /**
     * 备用字段2
     * @param userReserveB the value for SYSTEM_USER.USER_RESERVE_B
     */
    public void setUserReserveB(String userReserveB) {
        this.userReserveB = userReserveB == null ? null : userReserveB.trim();
    }

    /**
     * 备用字段3
     * @return  the value of SYSTEM_USER.USER_RESERVE_C
     */
    public String getUserReserveC() {
        return userReserveC;
    }

    /**
     * 备用字段3
     * @param userReserveC the value for SYSTEM_USER.USER_RESERVE_C
     */
    public void setUserReserveC(String userReserveC) {
        this.userReserveC = userReserveC == null ? null : userReserveC.trim();
    }

	@Override
	public String toString() {
		return "SystemUserInfo [userId=" + userId + ", orgId=" + orgId
				+ ", userPassword=" + userPassword + ", userName=" + userName
				+ ", userAge=" + userAge + ", userSex=" + userSex
				+ ", userIdentity=" + userIdentity + ", userAddress="
				+ userAddress + ", userPhone=" + userPhone + ", userMail="
				+ userMail + ", userDesc=" + userDesc + ", userImage="
				+ userImage + ", userCreateTime=" + userCreateTime
				+ ", userCreateUserId=" + userCreateUserId
				+ ", userUpdateTime=" + userUpdateTime + ", userUpdateUserId="
				+ userUpdateUserId + ", userUpdatePasswordTime="
				+ userUpdatePasswordTime + ", userStatus=" + userStatus
				+ ", userSkin=" + userSkin + ", userLevel=" + userLevel
				+ ", userReserveA=" + userReserveA + ", userReserveB="
				+ userReserveB + ", userReserveC=" + userReserveC + "]";
	}

}