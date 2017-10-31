package com.kaishengit;

import com.github.pagehelper.PageHelper;
import com.kaishengit.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by silver on 2017/10/27.
 */
public class StudentMapperTestCase {

    private SqlSession sqlSession;
    private StudentMapper studentMapper;

    @Before
    public void init() {

        sqlSession = MyBatisUtil.getSqlSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);

    }

    @After
    public void close() {

        if (sqlSession != null) {

            sqlSession.close();

        }

    }

    @Test
    public void insertTest() {

        Student student = new Student("jack", 23, "bololl");
        int updateRow = studentMapper.insert(student);
        sqlSession.commit();
        System.out.println(updateRow);

    }

    @Test
    public void updateTest() {

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuNameEqualTo("梦露");

        Student student = new Student();
        student.setStuAddress("约德尔");

        studentMapper.updateByExampleSelective(student, studentExample);

        sqlSession.commit();

    }

    @Test
    public void updateTwoTest() {

        Student student = new Student();
        student.setId(1);
        student.setStuName("梦露");
        student.setStuAge(22);
        student.setStuAddress("班德尔");

        studentMapper.updateByPrimaryKey(student);

        sqlSession.commit();

    }

    @Test
    public void selectAllTest() {

        //第一页，查五条五条
//        PageHelper.startPage(1,5);

        //从0条开始 查五条五条 使用分页是须在主配置文件中配置
        PageHelper.offsetPage(0,5);
        StudentExample studentExample = new StudentExample();

        List<Student> studentList = studentMapper.selectByExample(studentExample);

        for(Student student: studentList) {

            System.out.println(student);

        }

    }

    @Test
    public void selectByAddressTest() {

        //设置查询条件
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuAddressEqualTo("北京");

        List<Student> studentList = studentMapper.selectByExample(studentExample);

        for(Student student: studentList) {

            System.out.println(student);

        }

    }





















}
