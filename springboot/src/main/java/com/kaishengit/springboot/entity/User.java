package com.kaishengit.springboot.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/7
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userName;
    private String address;

    public User() {
    }

    public User(Integer id, String userName, String address) {
        this.id = id;
        this.userName = userName;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
