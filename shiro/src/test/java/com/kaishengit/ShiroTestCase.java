package com.kaishengit;

import com.hazelcast.security.UsernamePasswordCredentials;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/24
 */
public class ShiroTestCase {

    @Test
    public void shiroTest() {

        //1.创建SecurityManager 工厂
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        //2. 使用工厂创建SecurityManager对象
        SecurityManager securityManager = factory.getInstance();

        //3.使用SecurityUtils设置SecurityManager
        SecurityUtils.setSecurityManager(securityManager);

        //4.根据SecurityUtils创建Subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("tom","1234567");

        try{

            subject.login(usernamePasswordToken);
            System.out.println("success");

        } catch (UnknownAccountException e) {
            System.out.println("UnknownAccountException");
            System.out.println("该账号不存在");
        } catch (LockedAccountException e) {
            System.out.println("LockedAccountException");
            System.out.println("该账号已被禁用");
        } catch (IncorrectCredentialsException e) {
            System.out.println("IncorrectCredentialsException");
            System.out.println("账号密码错误");
        } catch (AuthenticationException e) {
            System.out.println("AuthenticationException");
        }

        subject.logout();

    }



    @Test
    public void shiroTest1() {

        //1.创建SecurityManager 工厂
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2. 使用工厂创建SecurityManager对象
        SecurityManager securityManager = factory.getInstance();

        //3.使用SecurityUtils设置SecurityManager
        SecurityUtils.setSecurityManager(securityManager);

        //4.根据SecurityUtils创建Subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("tom","1234567");

        try{

            subject.login(usernamePasswordToken);
            System.out.println("success");

        } catch (UnknownAccountException e) {
            System.out.println("UnknownAccountException");
            System.out.println("该账号不存在");
        } catch (LockedAccountException e) {
            System.out.println("LockedAccountException");
            System.out.println("该账号已被禁用");
        } catch (IncorrectCredentialsException e) {
            System.out.println("IncorrectCredentialsException");
            System.out.println("账号密码错误");
        } catch (AuthenticationException e) {
            System.out.println("AuthenticationException");
        }

        subject.logout();

    }


    @Test
    public void shiroTest2() {

        //1.创建SecurityManager 工厂
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-roles.ini");
        //2. 使用工厂创建SecurityManager对象
        SecurityManager securityManager = factory.getInstance();

        //3.使用SecurityUtils设置SecurityManager
        SecurityUtils.setSecurityManager(securityManager);

        //4.根据SecurityUtils创建Subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("tom","1234567");

        try{

            subject.login(usernamePasswordToken);
            System.out.println("success");
            System.out.println("is Admin ? " + subject.hasRole("admin"));
            System.out.println("is CEO and Admin ? " + subject.hasAllRoles(Arrays.asList("admin","CEO")));
            boolean[] resultArray = subject.hasRoles(Arrays.asList("admin", "HR"));

            for(boolean result: resultArray) {
                System.out.println("----" + result);
            }

            System.out.println("=========================================");

            System.out.println("task.create ? " + subject.isPermitted("task:create"));

            //subject.checkPermission("task.delete");



        } catch (UnknownAccountException e) {
            System.out.println("UnknownAccountException");
            System.out.println("该账号不存在");
        } catch (LockedAccountException e) {
            System.out.println("LockedAccountException");
            System.out.println("该账号已被禁用");
        } catch (IncorrectCredentialsException e) {
            System.out.println("IncorrectCredentialsException");
            System.out.println("账号密码错误");
        } catch (AuthenticationException e) {
            System.out.println("AuthenticationException");
        }

        subject.logout();

    }































}
