package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;

/**
 *
 * @author silver
 * @date 2017/10/29
 */
public class UserServiceConstructorIOCImpl implements UserService{

    private UserDao userDao;

    public UserServiceConstructorIOCImpl() {}

    public UserServiceConstructorIOCImpl(UserDao userDao) {

        this.userDao = userDao;

    }

    @Override
    public void sayHi() {

        userDao.sayHi();

    }
}
