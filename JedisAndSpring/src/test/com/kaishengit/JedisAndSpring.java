package com.kaishengit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/4
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class JedisAndSpring {

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {

        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new StringRedisSerializer());
    }

    @Test
    public void setValue() {

        redisTemplate.opsForValue().set("dataName", "Spring");

    }

    @Test
    public void getValue() {

        String value = redisTemplate.opsForValue().get("dataName");
        String value1 = redisTemplate.opsForValue().get("name");
        System.out.println(value);
        System.out.println(value1);

    }

    @Test
    public void setList() {

        redisTemplate.opsForList().rightPushAll("data-list", "1", "2", "3");

    }

    @Test
    public void getList() {

        List<String>  stringList = redisTemplate.opsForList().range("data-list", 0 ,-1);

        System.out.println(stringList);

    }

}
