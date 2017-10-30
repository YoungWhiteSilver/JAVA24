package com.kaishengit.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by silver on 2017/10/30.
 */
public class UserDaoTest {

    @Test
    public void sayTest() {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");

        userDao.say();

    }

}
