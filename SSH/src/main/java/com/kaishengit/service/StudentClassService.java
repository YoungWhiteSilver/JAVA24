package com.kaishengit.service;

import com.kaishengit.dao.StudentClassDao;
import com.kaishengit.pojo.StudentClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/30
 */
@Service
public class StudentClassService {

    @Autowired
    private StudentClassDao studentClassDao;

    public StudentClass selectById(Integer id) {
        return studentClassDao.selectById(id);
    }


    /**
     * 查询所有的班级
     *
     * @return
     */
    public List<StudentClass> selectAll() {

        return studentClassDao.selectAll();

    }


}