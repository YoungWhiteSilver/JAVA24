package com.kaishengit;

import com.kaishengit.pojo.HibernateUtil;
import com.kaishengit.pojo.Student;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/29
 */
public class EhcacheTestCase {

//    private Session session;
//
//    //@Before
//    public void before() {
//
//        session = HibernateUtil.getSession();
//        session.beginTransaction();
//
//    }

//    @After
//    public void after() {
//
//        session.getTransaction().commit();
//
//    }

    /**
     * 默认开启一级缓存
     */
//    @Test
//    public void firstLevelCache() {
//
//        Student student = (Student) session.get(Student.class, 67);
//        student = (Student) session.get(Student.class, 67);
//        student = (Student) session.get(Student.class, 67);
//        System.out.println(student.toString());
//
//        session.getTransaction().commit();
//
//    }


    @Test
    public void secondLevelCache() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Student student = (Student)session.get(Student.class, 67);

        session.evict(student);

        Cache cache = sessionFactory.getCache();

        cache.evictEntityRegion(Student.class);

        session.getTransaction().commit();


        Session session1 = sessionFactory.getCurrentSession();

        session1.beginTransaction();

        Student student1 = (Student)session1.get(Student.class, 67);

        session1.getTransaction().commit();

    }


















}
