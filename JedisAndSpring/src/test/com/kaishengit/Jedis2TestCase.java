package com.kaishengit;

import com.alibaba.fastjson.JSON;
import com.kaisheng.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.xml.validation.Schema;

/**
 * Created with IntelliJ IDEA.
 *
 * 只是把Jedis放到了Spring容器里。并没有用spirng的那套
 *
 * @author: 67675
 * @date: 2017/12/4
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class Jedis2TestCase {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void setTest() {

        User user = new User(1, "tom", "beijing");

        Jedis jedis = jedisPool.getResource();

        jedis.set("user", JSON.toJSONString(user));

        jedis.close();

    }


}
