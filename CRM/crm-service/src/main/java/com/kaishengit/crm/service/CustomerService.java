package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.SaleChance;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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


}
