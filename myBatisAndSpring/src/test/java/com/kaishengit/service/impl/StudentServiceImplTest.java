package com.kaishengit.service.impl;

import com.kaishengit.entity.Student;
import com.kaishengit.entity.StudentExample;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by silver on 2017/10/31.
 */
public class StudentServiceImplTest extends BaseTest{

    @Autowired
    private StudentServiceImpl studentService;

    @Test
    public void findByIdTest() {

        Student student = studentService.findById(1);

        System.out.println(student);

    }

    @Test
    public void findByAddressTest() {

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuAddressEqualTo("约德尔");

        List<Student> studentList = studentService.findByAddress(studentExample);

        for(Student student: studentList) {
            System.out.println(student);
        }
    }


}