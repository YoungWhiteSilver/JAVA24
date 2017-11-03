package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.kaishengit.entity.Student;
import com.kaishengit.entity.StudentExample;
import com.kaishengit.mappers.StudentMapper;
import com.kaishengit.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author silver
 * @date 2017/11/3
 */
@Service
public class ServiceImpl implements WebService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {

        StudentExample studentExample = new StudentExample();

        return studentMapper.selectByExample(studentExample);

    }

    @Override
    public List<Student> findAllByPage(Integer pageNo) {

        PageHelper.startPage(pageNo, 10);

        StudentExample studentExample = new StudentExample();

        return studentMapper.selectByExample(studentExample);

    }

    @Override
    public List<Student> findByName(String name) {

        name = name.isEmpty()? "北京": name;

        StudentExample studentExample = new StudentExample();

        studentExample.createCriteria().andStuAddressEqualTo(name);

        return studentMapper.selectByExample(studentExample);

    }
}
