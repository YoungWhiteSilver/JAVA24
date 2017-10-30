package com.kaishengit.service.impl;

import com.kaishengit.dao.StudentDao;
import com.kaishengit.entity.Student;
import com.kaishengit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author silver
 * @date 2017/10/30
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student findById(int id) {

        return studentDao.findById(id);

    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public void insert(Student student) {

        studentDao.insert(student);

    }

    @Override
    public Long count() {

        return studentDao.count();
        
    }

}
