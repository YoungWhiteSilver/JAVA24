package com.kaishengit.crm.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 
 */
public class Customer implements Serializable {
    private Integer id;

    private String custName;

    /**
     * 职位
     */
    private String jobTitle;

    /**
     * 电话，手机号
     */
    private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 行业
     */
    private String trade;

    /**
     * 客户来源
     */
    private String source;

    /**
     * 星级
     */
    private String level;

    /**
     * 备注
     */
    private String mark;

    /**
     * 客户所属的员工Id
     */
    private Integer accountId;

    private Timestamp createTime;

    private Timestamp updateName;

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateName() {
        return updateName;
    }

    public void setUpdateName(Timestamp updateName) {
        this.updateName = updateName;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getCreateTime() {
        return createTime;
    }



}