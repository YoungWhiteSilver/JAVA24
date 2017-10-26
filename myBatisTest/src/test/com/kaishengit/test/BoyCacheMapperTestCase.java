package com.kaishengit.test;

import com.kaishengit.entity.Boy;
import com.kaishengit.mappers.BoyCacheMapper;
import com.kaishengit.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.List;

/**
 * Created by silver on 2017/10/26.
 */
public class BoyCacheMapperTestCase {

    private SqlSession sqlSession;
    private BoyCacheMapper boyCacheMapper;

    @Before
    public void init() {

        sqlSession = MyBatisUtil.getSqlSession();
        boyCacheMapper = sqlSession.getMapper(BoyCacheMapper.class);

    }

    @After
    public void close() {

        sqlSession.close();

    }

    @Test()
    public void oneCacheTest() {

        List<Boy> boyList1 = boyCacheMapper.findAll();
        List<Boy> boyList =boyCacheMapper.findAll();

        /*可以在日志里看到 只有一次sql语句的打印，
        这说明：myBatis的一级缓存是默认存在的！
        一级缓存的条件是：使用同一个 sqlSession 查询同一个对象！
        当满足条件时，一级缓存只会在第一次查询，其余从缓存中取出
        */

        for(Boy boy: boyList) {

            System.out.println("<<<<<<<<<<<<<<<<======================================>>>>>>>>>>>" + boy);

        }

    }



























}
