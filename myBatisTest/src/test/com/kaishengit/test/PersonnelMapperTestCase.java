package com.kaishengit.test;

import com.kaishengit.entity.Personnel;
import com.kaishengit.mappers.PersonnelMapper;
import com.kaishengit.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by silver on 2017/10/24.
 */
public class PersonnelMapperTestCase {

    private SqlSession sqlSession;

    @Before
    public void init() {

        sqlSession = MyBatisUtil.getSqlSession();

    }
    @After
    public void close() {

        sqlSession.close();

    }

    @Test
    public void findByIdTest() {

        PersonnelMapper personnelMapper = sqlSession.getMapper(PersonnelMapper.class);
        Personnel personnel = personnelMapper.findById(1);
        System.out.println(personnel);

    }

    @Test
    public void findByIdTwo() {

        PersonnelMapper personnelMapper = sqlSession.getMapper(PersonnelMapper.class);
        Personnel personnel = personnelMapper.findByIdTwo(1);
        System.out.println(personnel);

    }


}
