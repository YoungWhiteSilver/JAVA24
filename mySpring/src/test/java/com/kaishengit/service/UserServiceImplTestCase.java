package com.kaishengit.service;

import com.kaishengit.entity.User;
import com.kaishengit.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by silver on 2017/10/28.
 */
public class UserServiceImplTestCase {

    @Test
    public void sqyHi() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");

        userService.sayHi();

    }

}
