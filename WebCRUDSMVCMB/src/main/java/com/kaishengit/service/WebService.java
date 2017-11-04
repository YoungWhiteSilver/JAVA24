package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Student;
import com.kaishengit.entity.StudentClass;

import java.util.List;
import java.util.Map;

/**
 *
 * @author silver
 * @date 2017/11/4
 */
public interface WebService {

    /**
     * 分页查询
     * @param pageNo
     * @return
     */
    PageInfo<Student> findByPage(Integer pageNo);

    PageInfo<Student> findByPage(Integer pageNo, Map<String, Object> map);
    /**
     * 查询通过Id
     * @param id
     * @return
     */
    Student findById(Integer id);

    /**
     * 修改
     * @param student
     */
    void updateStudent(Student student);

    /**
     * 保存
     * @param student
     */
    void save(Student student);

    List<Integer> selectAgeAll();

    List<StudentClass> selectClassNameAll();
}
