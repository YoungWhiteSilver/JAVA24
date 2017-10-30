package com.kaishengit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于DI注入的spring注入
 * @RunWith(SpringJUnit4ClassRunner.class)
 * @ContextConfiguration(locations = "classpath:Spring.xml") classpath: 该为一个约定，
 * 只要spring扫描到就会去classpath里找相应的文件。
 *
 * 当用Spring。class替代Spring。xml的时候, @ContextConfiguration(classes = Spring.class)
 * Created by silver on 2017/10/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:Spring.xml")
/*@ContextConfiguration(classes = Spring.class)*/
public class UserServiceImpTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void sayTest() {

        userService.say();

    }

    @Test
    public void countTest() {

        int a = userService.count();
        System.out.println(a);

    }

}
