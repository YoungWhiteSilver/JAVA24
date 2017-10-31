package com.kaishengit.service.impl;

import com.kaishengit.entity.Student;
import com.kaishengit.entity.StudentExample;
import com.kaishengit.mappers.StudentMapper;
import com.kaishengit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author silver
 * @date 2017/10/31
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findById(Integer id) {

        return studentMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<Student> findByAddress(StudentExample studentExample) {
        return studentMapper.selectByExample(studentExample);
    }
}
