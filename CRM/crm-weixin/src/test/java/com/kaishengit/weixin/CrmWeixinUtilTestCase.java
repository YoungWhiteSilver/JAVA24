package com.kaishengit.weixin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-weixin.xml")
public class CrmWeixinUtilTestCase {

    @Autowired
    private CrmWeixinUtil crmWeixinUtil;

    @Test
    public void GetAccessTokenTest() {

        String accessToken = crmWeixinUtil.getAccessToken(CrmWeixinUtil.ACCESSTOKEN_TYPE_NOMAL);

        crmWeixinUtil.getAccessToken(CrmWeixinUtil.ACCESSTOKEN_TYPE_NOMAL);
        crmWeixinUtil.getAccessToken(CrmWeixinUtil.ACCESSTOKEN_TYPE_NOMAL);

        System.out.println(accessToken +" =======");

    }

    @Test
    public void addAccountTest() {

        crmWeixinUtil.craeateAccount(100, "lusin", Arrays.asList(1), "15789221242");

    }

    @Test
    public void deleteAccountTest() {

        crmWeixinUtil.deleteAccount(26);

    }

    @Test
    public void addDeptTest() {

        crmWeixinUtil.createDept(1000, "财务部", 1);

    }

    @Test
    public void deleteDeptTest() {

        crmWeixinUtil.deleteDept(110);

    }

    @Test
    public void sendTextMessageTest() throws InterruptedException {

        crmWeixinUtil.sendTextMessage("WangMingXin", "轰炸机。。。。哒哒哒");


    }



}
