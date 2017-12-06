import com.alibaba.fastjson.JSON;
import com.kaishengit.entity.Product;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-*.xml")
public class KeysTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void Test() {


        try( Jedis jedis = jedisPool.getResource();) {

            Set<String> stringSet = jedis.keys("product[0-9]*");

            for(String str: stringSet) {
                System.out.println(str);
            }

        }



    }


}
