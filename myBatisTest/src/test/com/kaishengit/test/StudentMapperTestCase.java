package com.kaishengit.test;

import com.kaishengit.entity.Student;
import com.kaishengit.mappers.StudentMapper;
import com.kaishengit.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void Random() {

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> studentList = new ArrayList<>();

        for(int i = 0; i < 50; i ++) {

            int ageRandom = (int)(Math.random() * 20) + 15;

            int forRandom = ((int)(Math.random() * 3) + 1) + 2;

            StringBuffer nameBuffer = new StringBuffer();

            for(int j = 0; j < forRandom; j ++) {

                String chars = "abcdefghijklmnopqrstuvwxyz";
                nameBuffer.append(chars.charAt((int)(Math.random() * 26)));
            }

            String[] addresses = {"北京", "上海", "广州", "深圳", "天津", "哈尔冰", "纽约", "渥太华", "洛杉矶", "肯尼亚"};

            int addressRandom = (int)(Math.random() * 10);

            String address = addresses[addressRandom];

            Student student = new Student(nameBuffer.toString(), ageRandom , address);

            studentList.add(student);

        }

        int saveSome = studentMapper.saveSome(studentList);

        System.out.println(saveSome);

        sqlSession.commit();

    }

    @Test
    public void Test() {

        System.out.println(((int)(Math.random() * 3) + 1) + 2);

    }
























}
