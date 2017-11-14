package com.kaishengit.crm.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private Timestamp finishTime;

    /**
     * 提醒时间
     */
    private Timestamp remindTime;

    /**
     * 是否完成，0：未完成
     */
    private Integer done;

    private Integer accountId;

    private Integer custId;

    private Integer saleId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    private String title;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public Timestamp getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Timestamp remindTime) {
        this.remindTime = remindTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}