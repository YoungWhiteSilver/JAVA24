package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;

import java.io.IOException;
import java.io.OutputStream;
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
}
