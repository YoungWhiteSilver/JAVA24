package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Task;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/14
 */
public interface TaskService {


    /**
     * 通过 saleId 查找
     * @param id
     * @return
     */
    List<Task> findBySaleId(Integer id);





}
