package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.example.TaskExample;
import com.kaishengit.crm.mappers.TaskMapper;
import com.kaishengit.crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/14
 */
@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 通过 saleId 查找
     *
     * @param id
     * @return
     */
    @Override
    public List<Task> findBySaleId(Integer id) {

        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andSaleIdEqualTo(id);

        return taskMapper.selectByExample(taskExample);

    }
}
