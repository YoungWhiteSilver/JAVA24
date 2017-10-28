package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author silver
 * @date 2017/10/28
 */
public class UserServiceImpl implements UserService{

    /**
     *spring set注入
     */
    private UserDao userDao;

    /**
     *spring 基本类型及集合的注入
     */
    private Integer age;
    private String name;
    private List<UserDao> userDaoList;
    private Set<UserDao> userDaoSet;
    private Map<String, UserDao> map;
    private Properties properties;

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserDaoList(List<UserDao> userDaoList) {
        this.userDaoList = userDaoList;
    }

    public void setUserDaoSet(Set<UserDao> userDaoSet) {
        this.userDaoSet = userDaoSet;
    }

    public void setMap(Map<String, UserDao> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setUserDao(UserDao userDao) {

        this.userDao = userDao;

    }

    /**
     *
     */
    @Override
    public void sayHi() {

        userDao.sayHi();

    }

}
