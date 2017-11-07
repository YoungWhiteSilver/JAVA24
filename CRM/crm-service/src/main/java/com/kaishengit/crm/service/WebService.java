package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;

import java.util.Map;

/**
 *
 * @author silver
 * @date 2017/11/7
 */
public interface WebService {


    /**
     * 登陆
     * @param name
     * @param password
     * @return 返回map
     */
    Map<String, Object> login(String name, String password);

    PageInfo<Dept> homePage(Integer p, Integer id);
}
