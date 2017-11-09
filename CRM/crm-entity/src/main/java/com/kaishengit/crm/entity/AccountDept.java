package com.kaishengit.crm.entity;

/**
 *
 * @author silver
 * @date 2017/11/9
 */
public class AccountDept {

    private Integer accId;
    private Integer deptId;

    public AccountDept() {}

    public AccountDept(Integer accId, Integer deptId) {
        this.accId = accId;
        this.deptId = deptId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }
}
