package com.kaishengit.test;

import com.kaishengit.entity.Student;
import com.kaishengit.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by silver on 2017/10/23.
 */
public class MyBatisTestCase {

    @Test
    public void SaveTest() {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        Student student = new Student("kuls", 21, "天京");

        sqlSession.insert("com.kaishengit.mappers.StudentMapper.save", student);

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void findAllTest() {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        List<Student> studentList =  sqlSession.selectList("com.kaishengit.mappers.StudentMapper.findAll");

        for(Student n: studentList) {

            System.out.println(n);

        }

        sqlSession.close();

    }

    @Test
    public void updateNameTest() {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        Student student = sqlSession.selectOne("com.kaishengit.mappers.StudentMapper.findById", 1);
        student.setStuName("梦露");

        sqlSession.update("com.kaishengit.mappers.StudentMapper.updateName",student);

        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void deleteById() {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        sqlSession.delete("com.kaishengit.mappers.StudentMapper.deleteById", 2);

        sqlSession.commit();
        sqlSession.close();

    }

}
