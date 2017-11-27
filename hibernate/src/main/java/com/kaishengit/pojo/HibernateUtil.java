package com.kaishengit.pojo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/27
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * 获得SessionFactory 单利模式
     * @return
     */
    public static SessionFactory  getSessionFactory() {

        Configuration configuration = new Configuration().configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * 获得session
     * @return
     */
    public static Session getSession() {

        return getSessionFactory().getCurrentSession();

    }


}
