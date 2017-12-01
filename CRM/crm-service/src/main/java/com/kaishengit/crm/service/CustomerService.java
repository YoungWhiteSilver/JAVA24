package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.SaleChance;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/10
 */
public interface CustomerService {


    /**
     * 修改客户信息
     * @param id
     * @param customer
     */
    void editCustomer(Integer id, Customer customer);


    /**
     * 导出Excel为csv类型
     * @param outputStream
     * @param account
     */
    void exprotExcelCsv (OutputStream outputStream, Account account) throws IOException;


    /**
     * 导出Excel为xls类型
     * @param outputStream
     * @param account
     * @throws IOException
     */
    void exportExcelXls(OutputStream outputStream, Account account) throws IOException;

    /**
     * 转让客户
     * @param customerId
     * @param toAccountId
     * @param account
     */
    void transferCustomer(Integer customerId, Integer toAccountId, Account account);


    /**
     * 查找登陆员工的所有客户，
     * @param account
     * @return
     */
    List<Customer> findAllByAccountId(Account account);


    /**
     * 将客户移交公海
     *
     * @param customerId
     * @param account
     */
    void customerPublic(Integer customerId, Account account);


    /**
     * 查询公海客户
     * @param p
     * @param account
     * @return
     */
    PageInfo<Customer> findAllByAccountIdPage(Integer p, Account account);

    /**
     * 查询每月客户增加的数量
     * @return
     */
    List<Map<String,String>> coountByMonth();


    /**
     * 销售漏斗图
     * @return
     */
    List<Map<String,String>> saleFunnelListByProgress();


    /**
     * 从共公海拿客户
     * @param id
     * @param account
     */
    void taskPublicCustomer(Integer id, Account account);
}
