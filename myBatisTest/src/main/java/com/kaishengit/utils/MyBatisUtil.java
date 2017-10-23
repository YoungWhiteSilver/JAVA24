package com.kaishengit.utils;

import com.kaishengit.exvception.BatisException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author silver
 * @date 2017/10/23
 */
public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {

        try {

            InputStream inputStream = Resources.getResourceAsStream("MyBatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

   public static SqlSessionFactory getSqlSessionFactory() {

        if(sqlSessionFactory == null) {

            throw new BatisException("sqlSessionFactory为空");

        }

        return sqlSessionFactory;

   }

    public static SqlSession getSqlSession() {

        return getSqlSessionFactory().openSession();

    }




}
