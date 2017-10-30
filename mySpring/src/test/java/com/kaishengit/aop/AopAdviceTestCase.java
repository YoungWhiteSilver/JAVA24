package com.kaishengit.aop;

import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by silver on 2017/10/30.
 */
public class AopAdviceTestCase {

    @Test
    public void sayHiTest() {

        ApplicationContext context = new ClassPathXmlApplicationContext("AopSpring.xml");

        UserService userService = (UserService) context.getBean("userService");

        userService.sayHi();


    }


}
