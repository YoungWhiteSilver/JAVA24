package com.kaishengit.service;

import com.kaishengit.entity.Student;

import java.util.List;

/**
 *
 * @author silver
 * @date 2017/10/30
 */
public interface StudentService {

    /**
     * 查找通过Id
     * @param id
     * @return 返回student 对象
     */
    Student findById(int id);

    /**
     * 查找所有
     * @return 返回所有
     */
    List<Student> findAll();

    /**
     * 插入
     * @param student
     */
    void insert(Student student);

    /**
     * 计算共有多少条
     * @return
     */
    Long count();

}
