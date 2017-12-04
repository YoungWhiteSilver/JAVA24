package com.kaishengit;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * 不用任何注解，也不用配置，原生态，只需要导入maven依赖
 *
 * @author: 67675
 * @date: 2017/12/4
 */
public class JedisTestCase {

    Jedis jedis = null;

//    @Before
//    public void before() {
//        jedis = new Jedis("192.168.253.98", 6379);
//    }
//
//    @After
//    public void after() {
//
//        jedis.close();
//    }

    @Test
    public void setTest() {


        jedis.set("name", "tom1");

    }

    @Test
    public void getStringTest() {

        String name = jedis.get("name");
        System.out.println(name);

    }

    @Test
    public void setList() {

        jedis.rpush("number", "1","1","1","1","1");

    }

    @Test
    public void getList() {

        List<String> stringList = jedis.lrange("number", 0, -1);

        for (String str: stringList) {

            System.out.println(str);

        }

    }

    /**
     * redis连接池
     */
    @Test
    public void pooled() {

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();

        //设置总连接数
        config.setMaxTotal(5);

        //设置最大连接数
        config.setMaxIdle(5);

        //设置最小连接数
        config.setMinIdle(3);

        JedisPool jedisPool = new JedisPool(config, "192.168.253.98", 6379);
        Jedis jedis = jedisPool.getResource();

        Map<String, String> map = new HashMap<String, String>();

        map.put("id", "1");
        map.put("age", "2");
        map.put("address", "USA");

        jedis.hmset("user", map);

        jedis.close();
        jedisPool.destroy();

    }

    @Test
    public void getHash() {

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();

        //设置总连接数
        config.setMaxTotal(5);

        //设置最大连接数
        config.setMaxIdle(5);

        //设置最小连接数
        config.setMinIdle(3);

        JedisPool jedisPool = new JedisPool(config, "192.168.253.98", 6379);
        Jedis jedis = jedisPool.getResource();

        Map<String, String> map = jedis.hgetAll("user");

        Set<Map.Entry<String, String>> set = map.entrySet();

        for(Map.Entry mapSet: set) {

            System.out.println(mapSet.getKey());
            System.out.println(mapSet.getValue());

        }

        jedis.close();
        jedisPool.destroy();

    }

}
