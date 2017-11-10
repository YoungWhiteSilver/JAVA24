package com.kaishengit.crm.entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 67675
 */
public class Account {

    private Integer id;

    private String userName;

    private String password;

    private Date createTime;

    private Date updateTime;

    private String mobile;

    private List<Dept> deptList;

    public Account() {}

    public Account(String userName, String password, String mobile) {

        this.userName = userName;
        this.password = password;
        this.mobile = mobile;

    }

    public List<Dept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<Dept> deptList) {
        this.deptList = deptList;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}