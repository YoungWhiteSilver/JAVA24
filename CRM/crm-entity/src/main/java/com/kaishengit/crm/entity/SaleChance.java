package com.kaishengit.crm.entity;

import javafx.util.converter.TimeStringConverter;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 
 */
public class SaleChance implements Serializable {

    private Integer id;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 客户Id
     */
    private Integer custId;

    /**
     * 价值
     */
    private String worth;

    /**
     * 跟进记录
     */
    private String progress;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    private Timestamp lastTime;

    /**
     * 员工Id
     */
    private Integer accountId;

    private Customer customer;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getWorth() {
        return worth;
    }

    public void setWorth(String worth) {
        this.worth = worth;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}