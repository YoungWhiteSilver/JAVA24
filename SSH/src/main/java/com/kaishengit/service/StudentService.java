package com.kaishengit.service;

import com.kaishengit.dao.StudentDao;
import com.kaishengit.exception.DaoAndUtilsException;
import com.kaishengit.pojo.Student;
import com.kaishengit.utils.Page;
import com.kaishengit.utils.voentity.RequestQuery;
import org.apache.commons.lang3.StringUtils;
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

    public Page selectAllByPage(Integer p, List<RequestQuery> requestQueryList) {

        if(!StringUtils.isNumeric(p.toString())) {

            throw new DaoAndUtilsException("分页参数异常");

        }

        Integer newPageNum = p < 1 ? 1 : p;

        Long count = studentDao.countAll(requestQueryList);

        Page<Student> page = new Page<Student>(Long.valueOf(p.toString()), count);

        List<Student> studentList = studentDao.selectAllByPage(page.getPageRowStart(), requestQueryList);

        page.setPageItems(studentList);

        return page;

    }


}
