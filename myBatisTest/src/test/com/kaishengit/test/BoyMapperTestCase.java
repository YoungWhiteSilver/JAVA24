package com.kaishengit.test;

import com.kaishengit.entity.Boy;
import com.kaishengit.entity.Label;
import com.kaishengit.mappers.BoyMapper;
import com.kaishengit.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by silver on 2017/10/25.
 */
public class BoyMapperTestCase {

    private SqlSession sqlSession;
    private BoyMapper boyMapper;
    @Before
    public void init() {

        sqlSession = MyBatisUtil.getSqlSession();
        boyMapper = sqlSession.getMapper(BoyMapper.class);

    }

    @After
    public void close() {

        if(sqlSession != null) {
            sqlSession.close();
        }

    }
    @Test
    public void findBoyAndLabelTest() {

        Boy boy = boyMapper.findBoyAndLabel(1);
        System.out.println(boy.getBoyName() + "<============>" + boy.getBoyAge());
        System.out.println(boy.getLabelList().size());

        for(Label n: boy.getLabelList()) {
            System.out.println(n);
        }

    }
































}
