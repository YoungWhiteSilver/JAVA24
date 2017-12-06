package com.kaishengit.service.impl;

import com.alibaba.fastjson.JSON;
import com.kaishengit.entity.Product;
import com.kaishengit.example.ProductExample;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mappers.ProductMapper;
import com.kaishengit.service.ProductService;
import com.kaishengit.service.job.SpringQuartzJob;
import com.kaishengit.utils.FileUploadAndDownLoad;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/5
 */
@Service
public class ProductServiceImpl implements ProductService{

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private JedisPool jedisPool;

    @Resource(name = "fileUploadForQiniu")
    private FileUploadAndDownLoad fileUploadAndDownLoad;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public List<Product> findAll() {

        List<Product> productList = new ArrayList<>();

        try(Jedis jedis = jedisPool.getResource();) {

            Set<String> stringSet = jedis.keys("product[0-9]*");

            if(stringSet.size() != 0) {

               for(String str: stringSet) {

                   String strProduct = jedis.get(str);

                   productList.add(JSON.parseObject(strProduct, Product.class));

               }

           } else {

                ProductExample productExample = new ProductExample();

                productList = productMapper.selectByExample(productExample);

                for(Product product: productList) {

                    toJedisCache(product);
                }

           }

        }

        return productList;

    }

    /**
     * 保存商品并上传
     *
     * @param product
     * @param file
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProduct(Product product, MultipartFile file) {

        try (Jedis jedis = jedisPool.getResource()){

            if(file.isEmpty()) {

                productMapper.insertSelective(product);

            } else {
                String key = fileUploadAndDownLoad.fileUpload(file.getInputStream(), file.getName());
                product.setProductImage(key);

                productMapper.insertSelective(product);
            }

            toJedisCache(product);

            addSchedulerJob(product.getEndTime().getTime(), product.getId());

        } catch (IOException e) {
            throw new ServiceException("上传图片失败失败");
        }

    }

    /**
     * 添加任务 在商品秒杀结束时，更新数据库
     * @param endTime
     * @param id
     */
    private void addSchedulerJob(Long endTime, Integer id) {

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAsString("productId", id);

        JobDetail jobDetail = JobBuilder.newJob(SpringQuartzJob.class)
                .setJobData(jobDataMap)
                .withIdentity(new JobKey("taskId:" + id , "productGroup"))
                .build();

        DateTime dateTime = new DateTime(endTime);

        StringBuilder cron = new StringBuilder("0")
                .append(" ")
                .append(dateTime.getMinuteOfHour())
                .append(" ")
                .append(dateTime.getHourOfDay())
                .append(" ")
                .append(dateTime.getDayOfMonth())
                .append(" ")
                .append(dateTime.getMonthOfYear())
                .append(" ? ")
                .append(dateTime.getYear());

        logger.info("CRON EX: {}" ,cron.toString());

        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron.toString());

        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(scheduleBuilder)
                .build();

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        try {

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();

        } catch (SchedulerException e) {

            throw new ServiceException("添加任务失败");

        }

    }



    /**
     * 放入redis缓存
     * @param product
     */
    private void toJedisCache(Product product) {

        try(Jedis jedis = jedisPool.getResource()) {

           jedis.set("product" + product.getId(), JSON.toJSONString(product));

           Long size = jedis.scard("product:" + product.getId() + ":set");

           if(size == 0) {

              for(int i = 0; i < product.getProductNumber(); i ++) {

                  jedis.sadd("product:" + product.getId() + ":set", "" + i);
                  logger.error("<<--------------{}-------------------->>", i);
              }

           }

        }

    }

    /**
     * 查找商品通过Id
     *
     * @param id
     * @return
     */
    @Override
    public Product findById(Integer id) {

        Product product = null;

        try(Jedis jedis = jedisPool.getResource()) {

            String productJedis = jedis.get("product" + id);

            if(StringUtils.isNotEmpty(productJedis)) {

                product = (Product) JSON.parseObject(productJedis, Product.class);

            } else {

                ProductExample productExample = new ProductExample();
                productExample.createCriteria().andIdEqualTo(id);

                product = productMapper.selectByExampleWithBLOBs(productExample).get(0);

            }

        }

        return product;
    }

    /**
     * 秒杀照片
     *
     * @param id
     */
    @Override
    public void spikeProduct(Integer id) {

        try(Jedis jedis = jedisPool.getResource()) {

            Product product = JSON.parseObject(jedis.get("product" + id), Product.class);

            if (! product.isStart()) {
                throw new ServiceException("活动还没有开始哟！");
            }

            String value = jedis.spop("product:" + product.getId() + ":set");

            if(value == null) {

                logger.info("秒杀失败了《《《《《《《《《《《《《《《《《《《《《《《");
                throw new ServiceException("被枪光了！下次加油哦！");

            } else {

                product.setProductNumber(product.getProductNumber() - 1);
                toJedisCache(product);
                logger.info("秒杀成功了》》》》》》》》》》》》》》》》》》》》》》》");

            }

        }

    }




}
