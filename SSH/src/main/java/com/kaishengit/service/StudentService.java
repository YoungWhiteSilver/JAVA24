package com.kaishengit.service;

import com.kaishengit.dao.StudentDao;
import com.kaishengit.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<Student> selectAllByPage(Integer p, Integer age, Integer className, String stuName) {

        Integer newPage = p == 0 ? 1 : p;

        return studentDao.selectAllByPage((newPage - 1)* 10, age, className, stuName);

    }


}
