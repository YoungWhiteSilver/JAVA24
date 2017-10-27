package com.kaishengit.utils;

import com.kaishengit.exception.BatisException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 *sqlSession工具类
 * @author silver
 * @date 2017/10/27
 */
public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {

        try {

            InputStream inputStream = Resources.getResourceAsStream("myBatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * 获得SqlSessionFactory
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory() {

        if(sqlSessionFactory == null) {

            throw new BatisException("sqlSessionFactory为null");

        }

        return sqlSessionFactory;

    }

    /**
     * 获得sqlSession
     * @return
     */
    public static SqlSession getSqlSession() {

        return getSqlSessionFactory().openSession();

    }

}
