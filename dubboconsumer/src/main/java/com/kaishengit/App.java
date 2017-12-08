package com.kaishengit;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-dubbo.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");

        //User user = userService.findUser();

        System.out.println(userService.findOne());

        System.in.read();

    }
}
