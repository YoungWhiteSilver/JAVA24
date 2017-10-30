package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;

/**
 *
 * @author silver
 * @date 2017/10/30
 */
public class UserServiceAopImpl implements UserService{

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {

        this.userDao = userDao;

    }

    @Override
    public void sayHi() {

        userDao.sayHi();

    }


}
