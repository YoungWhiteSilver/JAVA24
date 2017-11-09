package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;

import java.util.List;
import java.util.Map;

/**
 *
 * @author silver
 * @date 2017/11/7
 */
public interface WebService {


    /**
     * 登陆
     * @param moblie
     * @param password
     * @return 返回map
     */
    Map<String, Object> login(String moblie, String password);

    /**
     * 查询所有部门 树状图
     * @return
     */
    List<Dept> deptList();

    void saveDept(String text);

    /**
     * 查找数据給DataTable插件
     * @param map
     * @return
     */
    List<Account> findAccountAndDept(Map<String, Object> map);

    Long countAll(Integer deptId);

    Long countfilte(Map<String, Object> map);

    void saveEmployee(String userName, String password, String mobile, Integer[] deptIds);

//    PageInfo<Dept> homePage(Integer p, Integer id);
}
