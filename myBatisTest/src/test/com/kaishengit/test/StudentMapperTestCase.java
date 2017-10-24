package com.kaishengit.test;

import com.kaishengit.entity.Student;
import com.kaishengit.mappers.StudentMapper;
import com.kaishengit.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by silver on 2017/10/24.
 */
public class StudentMapperTestCase {

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
    public void saveTest() {

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = new Student("童无敌", 17, "California");
        // 方法返回值为受影响的行数
        int updateRows = studentMapper.save(student);

        sqlSession.commit();

        // 取出自动增长的朱家的方法
        System.out.println("ID===========================>>>>>" + student.getId());

        System.out.println("updateRows ===================>>>> " + updateRows);

    }

    @Test
    public void updateNameTest() {

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.findById(9);
        student.setStuName("vitor");
        studentMapper.updateName(student);

        sqlSession.commit();

    }


























}
