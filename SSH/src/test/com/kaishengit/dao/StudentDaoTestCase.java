package com.kaishengit.dao;

import com.kaishengit.pojo.Student;
import com.kaishengit.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-hibernate.xml")
public class StudentDaoTestCase {


    @Autowired
    private StudentService studentService;

    @Test
    public void Test() {

        List<Student> studentList = studentService.selectAllByPage(1, 21, 5, "MaryAnn");

        /*现在这里我懒加载会出异常*/

        for(Student student: studentList) {
            System.out.println(student.getStuName() +" ---------xxxx-----------" +
                    "-----------------------xxxx-- "+student.getStudentClass().getClassName());
        }
    }

}
