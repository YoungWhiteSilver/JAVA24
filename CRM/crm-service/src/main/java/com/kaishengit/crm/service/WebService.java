package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
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

    void saveDept(String name);

    /**
     * 查找数据給DataTable插件
     * @param map
     * @return
     */
    List<Account> findAccountAndDept(Map<String, Object> map);

    /**
     * 计算出总条数
     * @param deptId
     * @return
     */
    Long countAll(Integer deptId);

    /**
     * 计算过滤后的查询条数
     * @param map
     * @return
     */
    Long countfilte(Map<String, Object> map);

    /**
     * 添加·员工
     * @param userName
     * @param password
     * @param mobile
     * @param deptIds
     */
    void saveEmployee(String userName, String password, String mobile, Integer[] deptIds);


    /**
     * 我的客户
     * @param p
     * @param key
     * @param accId
     * @return
     */
    PageInfo<Customer> myCustomer(Integer p, String key, Integer accId);


    /**
     * 客户详情
     * @param id
     * @param accId
     * @return
     */
    Customer customerDetail(Integer id, Integer accId);

    /**
     * 保存用户
     * @param customer
     */
    void saveCustomer(Customer customer);

    /**
     * 查找所有的员工
     * @return
     */
    List<Account> findAllEmployee();

    /**
     * 通过moblie查找
     * @param userName
     * @return
     */
    Account findByMoblie(String userName);

    /**
     * 活的当前对象的部门
     * @param id
     * @return
     */
    List<Dept> findDeptById(Integer id);


//    PageInfo<Dept> homePage(Integer p, Integer id);
}
