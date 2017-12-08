package com.kaishengit.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/8
 */
public class User implements Serializable{



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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

