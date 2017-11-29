package com.kaishengit;

import com.kaishengit.pojo.Boy;
import com.kaishengit.pojo.HibernateUtil;
import com.kaishengit.pojo.Lock;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/29
 */
public class LockTestCase {

    private Session session;

    @Before
    public void before() {

        session = HibernateUtil.getSession();
        session.beginTransaction();

    }

    @After
    public void after() {

        session.getTransaction().commit();

    }

    /**
     * 乐观锁
     * 在事物提交时会校验version的值，如果不是查询时的指，则事务提交失败
     */
    @Test
    public void optimismLock() throws InterruptedException {

        Lock lock = (Lock) session.get(Lock.class, 1);
        lock.setName("lisi");

        Thread.sleep(10000);

    }

    /**
     * 悲观锁
     * 性能低下
     */
    @Test
    public void pessimisticLock() throws InterruptedException {

        Boy boy = (Boy) session.get(Boy.class, 4, LockOptions.UPGRADE);
        boy.setBoyName("Linke");
        Thread.sleep(10000);

    }


}
