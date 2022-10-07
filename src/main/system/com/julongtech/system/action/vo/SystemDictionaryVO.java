package com.julongtech.system.action.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 数据字典界面交互类
 * @author julong 
 * @date Mon Oct 16 15:08:21 CST 2017
 */
public class SystemDictionaryVO  implements Serializable{
    /**
	 * @author julong
	 * @date 2017-10-16 下午3:55:05
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * 数据字典编号
     */
    private Integer dictionaryId;

    /** 
     * 节点类型
     */
    private String dictionaryType;

    /** 
     * 节点编码
     */
    private String dictionaryCode;

    /** 
     * 节点名称
     */
    private String dictionaryName;

    /** 
     * 节点描述
     */
    private String dictionaryDesc;

    /** 
     * 父级节点
     */
    private String dictionaryParentCode;

    /** 
     * 创建时间
     */
    private Timestamp dictionaryCreateTime;

    /** 
     * 字典状态：0-正常1-禁用
     */
    private String dictionaryStatus;

    /** 
     * 顺序 默认从1开始
     */
    private BigDecimal dictionaryOrder;

    /**
     * 数据字典编号
     * @return dictionaryId
     */
    public Integer getDictionaryId() {
        return this.dictionaryId;
    }

    /**
     * 数据字典编号
     * @param dictionaryId
     */
    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    /**
     * 节点类型
     * @return dictionaryType
     */
    public String getDictionaryType() {
        return this.dictionaryType;
    }

    /**
     * 节点类型
     * @param dictionaryType
     */
    public void setDictionaryType(String dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    /**
     * 节点编码
     * @return dictionaryCode
     */
    public String getDictionaryCode() {
        return this.dictionaryCode;
    }

    /**
     * 节点编码
     * @param dictionaryCode
     */
    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    /**
     * 节点名称
     * @return dictionaryName
     */
    public String getDictionaryName() {
        return this.dictionaryName;
    }

    /**
     * 节点名称
     * @param dictionaryName
     */
    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    /**
     * 节点描述
     * @return dictionaryDesc
     */
    public String getDictionaryDesc() {
        return this.dictionaryDesc;
    }

    /**
     * 节点描述
     * @param dictionaryDesc
     */
    public void setDictionaryDesc(String dictionaryDesc) {
        this.dictionaryDesc = dictionaryDesc;
    }

    /**
     * 父级节点
     * @return dictionaryParentCode
     */
    public String getDictionaryParentCode() {
        return this.dictionaryParentCode;
    }

    /**
     * 父级节点
     * @param dictionaryParentCode
     */
    public void setDictionaryParentCode(String dictionaryParentCode) {
        this.dictionaryParentCode = dictionaryParentCode;
    }

    /**
     * 创建时间
     * @return dictionaryCreateTime
     */
    public Timestamp getDictionaryCreateTime() {
        return this.dictionaryCreateTime;
    }

    /**
     * 创建时间
     * @param dictionaryCreateTime
     */
    public void setDictionaryCreateTime(Timestamp dictionaryCreateTime) {
        this.dictionaryCreateTime = dictionaryCreateTime;
    }

    /**
     * 字典状态：0-正常1-禁用
     * @return dictionaryStatus
     */
    public String getDictionaryStatus() {
        return this.dictionaryStatus;
    }

    /**
     * 字典状态：0-正常1-禁用
     * @param dictionaryStatus
     */
    public void setDictionaryStatus(String dictionaryStatus) {
        this.dictionaryStatus = dictionaryStatus;
    }

    /**
     * 顺序 默认从1开始
     * @return dictionaryOrder
     */
    public BigDecimal getDictionaryOrder() {
        return this.dictionaryOrder;
    }

    /**
     * 顺序 默认从1开始
     * @param dictionaryOrder
     */
    public void setDictionaryOrder(BigDecimal dictionaryOrder) {
        this.dictionaryOrder = dictionaryOrder;
    }

    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("SystemDictionaryVO{");
        sb.append("dictionaryId=").append(dictionaryId);
        sb.append(", dictionaryType='").append(dictionaryType).append('\'');
        sb.append(", dictionaryCode='").append(dictionaryCode).append('\'');
        sb.append(", dictionaryName='").append(dictionaryName).append('\'');
        sb.append(", dictionaryDesc='").append(dictionaryDesc).append('\'');
        sb.append(", dictionaryParentCode='").append(dictionaryParentCode).append('\'');
        sb.append(", dictionaryCreateTime=").append(dictionaryCreateTime);
        sb.append(", dictionaryStatus='").append(dictionaryStatus).append('\'');
        sb.append(", dictionaryOrder=").append(dictionaryOrder);
        sb.append('}');
        return sb.toString();
    }
}