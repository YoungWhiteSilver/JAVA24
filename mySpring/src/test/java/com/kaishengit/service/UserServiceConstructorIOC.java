package com.kaishengit.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by silver on 2017/10/29.
 */
public class UserServiceConstructorIOC {

    @Test
    public void constructorTest() {

        ApplicationContext context = new ClassPathXmlApplicationContext("constructorIOCSpring.xml");
        UserService userService = (UserService) context.getBean("userService");

        userService.sayHi();

    }

}
