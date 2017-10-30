package com.kaishengit;

import com.kaishengit.entity.Student;
import com.kaishengit.service.StudentService;
import com.kaishengit.service.impl.StudentServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by silver on 2017/10/30.
 */
public class StudentServiceTestCase extends BaseTest{

    @Autowired
    private StudentService studentService;

    @Test
    public void findByIdTest() {

        Student student= studentService.findById(1);

        System.out.println(student);

    }
    @Test
    public void findAllTest() {

        List<Student> studentList = studentService.findAll();

        for(Student student: studentList) {

            System.out.println(student);

        }

    }

    @Test
    public void insertTest() {

        Student student = new Student("llisi", 22, "西安");

        studentService.insert(student);


    }

    @Test
    public void countTest() {

        Long count = studentService.count();

        System.out.println(count);

    }

}
