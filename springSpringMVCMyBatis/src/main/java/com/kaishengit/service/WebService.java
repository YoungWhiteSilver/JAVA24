package com.kaishengit.service;

import com.kaishengit.entity.Student;
import com.kaishengit.entity.StudentExample;

import java.util.List;

/**
 *
 * @author silver
 * @date 2017/11/3
 */
public interface WebService {


    /**
     * 查找所有
     * @return
     */
    List<Student> findAll();

    /**
     * 分页查询
     * @return
     */
    List<Student> findAllByPage(Integer pageNo);

    /**
     * test
     * @param name
     * @return
     */
    List<Student> findByName(String name);

}
