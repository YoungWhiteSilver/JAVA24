package com.kaishengit.service.job;

import com.kaishengit.entity.Product;
import com.kaishengit.mappers.ProductMapper;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/6
 */
public class SpringQuartzJob implements Job{

    private Logger logger = LoggerFactory.getLogger(SpringQuartzJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        String productId = jobDataMap.getString("productId");
        JedisPool jedisPool = null;
        Jedis jedis = null;

        try {

            ApplicationContext context = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("springApplicationContext");

            ProductMapper productMapper = (ProductMapper) context.getBean("productMapper");

            jedisPool = (JedisPool) context.getBean("jedisPool");


            jedis = jedisPool.getResource();

            Long size = jedis.scard("product:" + productId + ":set");

            Product product = productMapper.selectByPrimaryKey(Integer.valueOf(productId));

            product.setProductNumber(size.intValue());

            productMapper.updateByPrimaryKeySelective(product);

            logger.info("商品{}修改库存成功{},>>>>>>>>>>>>>>>>>>>>>>>>>>",productId,size);

        } catch (SchedulerException e) {

            e.printStackTrace();

        } finally {

            if(jedis != null) {
                jedis.close();
            }

        }


    }

































}
