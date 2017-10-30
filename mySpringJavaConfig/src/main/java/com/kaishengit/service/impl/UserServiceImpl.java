package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@Service service一般通过该注释将类放入Spring容器里
 *
 * @author silver
 * @date 2017/10/30
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * @Autowired 该注释为自动注入，先匹配ID，name，如果没有匹配数据类型，
     * 所以推荐名字与bean注释里的名字一至
     */
    @Autowired
    private UserDao userDao;

    /**
     * 当@Autowired 在private UserDao userDao；的时候set方法可以不写，
     * 但是当手动注入的时候必须要写，@Autowired 可以在private UserDao
     * userDao的头上，也可以在setUserDao的头上；
     * @param userDao
     */
    public void setUserDao(UserDao userDao) {

        this.userDao = userDao;
    }

    @Override
    public void say() {

        userDao.say();

    }

    @Override
    public int count() {

        return userDao.count();

    }
}
