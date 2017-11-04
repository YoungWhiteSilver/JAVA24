package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Student;
import com.kaishengit.entity.StudentClass;
import com.kaishengit.entity.StudentClassExample;
import com.kaishengit.entity.StudentExample;
import com.kaishengit.mappers.StudentClassMapper;
import com.kaishengit.mappers.StudentMapper;
import com.kaishengit.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by silver on 2017/11/4.
 */
@Service
public class WebServiceImpl implements WebService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentClassMapper classMapper;
    /**
     * 分页查询
     *
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<Student> findByPage(Integer pageNo) {

        PageHelper.startPage(pageNo, 10);

        StudentExample studentExample = new StudentExample();

        studentExample.setOrderByClause("id desc");

        List<Student> studentList = studentMapper.selectByExample(studentExample);

        return new PageInfo<>(studentList);
    }

    @Override
    public PageInfo<Student> findByPage(Integer pageNo, Map<String, Object> map) {

        PageHelper.startPage(pageNo,10);

        List<Student> studentList = studentMapper.selectStudentAndClass(map);

        return new PageInfo<>(studentList);

    }


    /**
     * 查询通过Id
     *
     * @param id
     * @return
     */
    @Override
    public Student findById(Integer id) {

        Student student = studentMapper.selectStudentAndClassName(id);

        return student;

    }

    /**
     * 修改
     *
     * @param student
     */
    @Override
    public void updateStudent(Student student) {

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(student.getId());

        studentMapper.updateByExampleSelective(student, studentExample);

    }

    /**
     * 保存
     *
     * @param student
     */
    @Override
    public void save(Student student) {

        studentMapper.insert(student);

    }

    @Override
    public List<Integer> selectAgeAll() {

        return studentMapper.selectStudentAgeAll();

    }

    @Override
    public List<StudentClass> selectClassNameAll() {

        List<StudentClass> classList = classMapper.selectByExample(new StudentClassExample());

        return classMapper.selectByExample(new StudentClassExample());

    }


}
