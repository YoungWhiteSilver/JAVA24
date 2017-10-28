package com.kaishengit.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by silver on 2017/10/28.
 */
public class UserDaoTestCase {

    @Test
    public void sayHiTest() {

        //获取Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //从Spring容器中获取Bean 括号里写的为Bean的ID，Name或class的值
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.sayHi();

    }

    @Test
    public void ScopePrototypeTest() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        UserDao userDao1 = (UserDao) applicationContext.getBean("userDao");

        System.out.println(userDao == userDao1);

    }

    @Test
    public void Test() {





    }


}
