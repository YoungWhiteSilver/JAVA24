package com.kaishengit;


import com.kaishengit.pojo.HibernateUtil;
import com.kaishengit.pojo.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;



/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/27
 */
public class StudentHidernateTestCase {

    @Test
    public void set() {

        //1.读取classpath中的名为hibernate.cfg.xml的配置文件
        Configuration configuration = new Configuration().configure();


        /*
        //2.创建sessionFactory 5.x 版本使用
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        */

        //2.2.创建sessionFactory 4.x 版本使用
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);


        //3.创建session
        Session session = sessionFactory.getCurrentSession();

        //4.开启事务
        Transaction transaction = session.beginTransaction();

        //5.执行操作
        Student student = (Student) session.get(com.kaishengit.pojo.Student.class, 100);

        System.out.println(student);

        //6.提交或事务回滚
        transaction.commit();

    }

    @Test
    public void update() {

        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        Student student = (Student) session.get(Student.class, 100);

        student.setStuAddress("大连");

        session.getTransaction().commit();

    }





























}
