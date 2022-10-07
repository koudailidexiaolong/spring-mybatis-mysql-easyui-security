package com.julongtech.system.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户信息类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 */
public class SystemUserDTO  implements Serializable{
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
	 * 用户创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Timestamp userCreateTime;

	/** 
	 * 用户创建人
	 */
	private String userCreateUserId;
	/** 
	 * 用户创建人
	 */
	private String userCreateUserName;
	/** 
	 * 用户修改时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Timestamp userUpdateTime;

	/** 
	 * 用户修改人
	 */
	private String userUpdateUserId;
	/** 
	 * 用户修改人
	 */
	private String userUpdateUserName;

	/**
	 * 开始日期
	 * @author julong
	 * @date 2017-12-14 下午2:40:49
	 */
	private Timestamp beginDate;

	/**
	 * 结束日期
	 * @author julong
	 * @date 2017-12-14 下午2:40:57
	 */
	private Timestamp endDate;


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
	 * 用户密码修改时间
	 * @return userUpdatePasswordTime
	 */
	public Timestamp getUserUpdatePasswordTime() {
		return this.userUpdatePasswordTime;
	}

	/**
	 * 用户密码修改时间
	 * @param userUpdatePasswordTime
	 */
	public void setUserUpdatePasswordTime(Timestamp userUpdatePasswordTime) {
		this.userUpdatePasswordTime = userUpdatePasswordTime;
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
	 * 用户创建时间
	 * @return userCreateTime
	 */
	public Timestamp getUserCreateTime() {
		return this.userCreateTime;
	}

	/**
	 * 用户创建时间
	 * @param userCreateTime
	 */
	public void setUserCreateTime(Timestamp userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

	/**
	 * 用户创建人
	 * @return userCreateUserId
	 */
	public String getUserCreateUserId() {
		return this.userCreateUserId;
	}

	/**
	 * 用户创建人
	 * @param userCreateUserId
	 */
	public void setUserCreateUserId(String userCreateUserId) {
		this.userCreateUserId = userCreateUserId;
	}

	/**
	 * 用户创建人
	 * @return userCreateUserName
	 */
	public String getUserCreateUserName() {
		return this.userCreateUserName;
	}

	/**
	 * 用户创建人
	 * @param userCreateUserName
	 */
	public void setUserCreateUserName(String userCreateUserName) {
		this.userCreateUserName = userCreateUserName;
	}

	/**
	 * 用户修改时间
	 * @return userUpdateTime
	 */
	public Timestamp getUserUpdateTime() {
		return this.userUpdateTime;
	}

	/**
	 * 用户修改时间
	 * @param userUpdateTime
	 */
	public void setUserUpdateTime(Timestamp userUpdateTime) {
		this.userUpdateTime = userUpdateTime;
	}

	/**
	 * 用户修改人
	 * @return userUpdateUserId
	 */
	public String getUserUpdateUserId() {
		return this.userUpdateUserId;
	}

	/**
	 * 用户修改人
	 * @param userUpdateUserId
	 */
	public void setUserUpdateUserId(String userUpdateUserId) {
		this.userUpdateUserId = userUpdateUserId;
	}

	/**
	 * 用户修改人
	 * @return userUpdateUserName
	 */
	public String getUserUpdateUserName() {
		return this.userUpdateUserName;
	}

	/**
	 * 用户修改人
	 * @param userUpdateUserName
	 */
	public void setUserUpdateUserName(String userUpdateUserName) {
		this.userUpdateUserName = userUpdateUserName;
	}

	/**
	 * 开始日期
	 * @return beginDate
	 */
	public Timestamp getBeginDate() {
		return this.beginDate;
	}

	/**
	 * 开始日期
	 * @param beginDate
	 */
	public void setBeginDate(Timestamp beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 结束日期
	 * @return endDate
	 */
	public Timestamp getEndDate() {
		return this.endDate;
	}

	/**
	 * 结束日期
	 * @param endDate
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	@java.lang.Override
	public java.lang.String toString() {
		final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemUserDTO{");
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
		sb.append(", userUpdatePasswordTime=").append(userUpdatePasswordTime);
		sb.append(", userStatus='").append(userStatus).append('\'');
		sb.append(", userSkin='").append(userSkin).append('\'');
		sb.append(", userLevel='").append(userLevel).append('\'');
		sb.append(", userCreateTime=").append(userCreateTime);
		sb.append(", userCreateUserId='").append(userCreateUserId).append('\'');
		sb.append(", userCreateUserName='").append(userCreateUserName).append('\'');
		sb.append(", userUpdateTime=").append(userUpdateTime);
		sb.append(", userUpdateUserId='").append(userUpdateUserId).append('\'');
		sb.append(", userUpdateUserName='").append(userUpdateUserName).append('\'');
		sb.append(", beginDate=").append(beginDate);
		sb.append(", endDate=").append(endDate);
		sb.append('}');
		return sb.toString();
	}
}