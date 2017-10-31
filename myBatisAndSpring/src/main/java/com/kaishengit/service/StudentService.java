package com.kaishengit.service;

import com.kaishengit.entity.Student;
import com.kaishengit.entity.StudentExample;

import java.util.List;

/**
 *
 * @author silver
 * @date 2017/10/31
 */
public interface StudentService {

    /**
     * 测试
     * @param id id
     * @return 对象
     */
    Student findById(Integer id);

    /**
     * 测试
     * @param studentExample
     * @return
     */
    List<Student> findByAddress(StudentExample studentExample);


}
