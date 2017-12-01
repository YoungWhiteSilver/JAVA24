package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.example.CustomerExample;
import com.kaishengit.crm.mappers.CustomerMapper;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.exception.ServiceException;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerMapper customerMapper;


    /**
     * 修改客户信息
     * @param id 员工Id
     * @param customer
     */
    @Override
    public void editCustomer(Integer id, Customer customer) {

        if(id != null && id.equals(customer.getAccountId())) {

            customerMapper.updateByPrimaryKeySelective(customer);

        } else {

            throw new ServiceException("该客户不是您的客户");

        }

    }

    /**
     * 导出Excel为csv类型
     *
     * @param outputStream
     * @param account
     */
    @Override
    public void exprotExcelCsv(OutputStream outputStream, Account account) throws IOException {

        List<Customer> customerList = findAllCustomerByAccountId(account);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("姓名")
                .append(",")
                .append("联系电话")
                .append(",")
                .append("职位")
                .append(",")
                .append("地址")
                .append("\r\n");

        for (Customer customer: customerList) {

            stringBuilder.append(customer.getCustName())
                    .append(",")
                    .append(customer.getMobile())
                    .append(",")
                    .append(customer.getJobTitle())
                    .append(",")
                    .append(customer.getAddress())
                    .append(",")
                    .append("\r\n");

        }

        IOUtils.write(stringBuilder.toString(), outputStream, "GBK");

        outputStream.flush();
        outputStream.close();

    }

    /**
     * 导出Excel为xls类型
     *
     * @param outputStream
     * @param account
     * @throws IOException
     */
    @Override
    public void exportExcelXls(OutputStream outputStream, Account account) throws IOException {

        List<Customer> customerList = findAllCustomerByAccountId(account);

        //创建工作表
        Workbook workbook = new HSSFWorkbook();

        //创建sheet
        Sheet sheet = workbook.createSheet("我的客户");

        //创建行
        Row titleRow = sheet.createRow(0);

        //创建单元格
        Cell nameCell = titleRow.createCell(0);

        nameCell.setCellValue("姓名");
        titleRow.createCell(1).setCellValue("电话");
        titleRow.createCell(2).setCellValue("职位");
        titleRow.createCell(3).setCellValue("地址");

        for(int i = 0; i < customerList.size(); i ++) {

            Customer customer = customerList.get(i);

            Row dataRow = sheet.createRow(i + 1);

            dataRow.createCell(0).setCellValue(customer.getCustName());
            dataRow.createCell(1).setCellValue(customer.getMobile());
            dataRow.createCell(2).setCellValue(customer.getJobTitle());
            dataRow.createCell(3).setCellValue(customer.getAddress());

        }

        workbook.write(outputStream);

        outputStream.flush();
        outputStream.close();

    }

    /**
     * 转让客户
     *  @param customerId
     * @param toAccountId
     * @param account
     */
    @Override
    public void transferCustomer(Integer customerId, Integer toAccountId, Account account) {

        Customer customer = customerMapper.selectByPrimaryKey(customerId);

        if(customer != null && customer.getAccountId().equals(account.getId())) {

            customer.setAccountId(toAccountId);

            customerMapper.updateByPrimaryKeySelective(customer);

        } else {

            throw new ServiceException("你的权限不足");

        }

    }

    /**
     * 查找登陆员工的所有客户，
     *
     * @param account
     * @return
     */
    @Override
    public List<Customer> findAllByAccountId(Account account) {

        return findAllCustomerByAccountId(account);

    }

    /**
     * 把客户移交公海
     *
     * @param customerId
     */
    @Override
    public void customerPublic(Integer customerId, Account account) {

        Customer customer = customerMapper.selectByPrimaryKey(customerId);

        if(customer != null && customer.getAccountId().equals(account.getId())) {

            customer.setAccountId(0);
            customerMapper.updateByPrimaryKey(customer);

        } else {
            throw new ServiceException("权限不足");
        }

    }

    /**
     * 查询公海客户并分页
     *
     * @param account
     * @return
     */
    @Override
    public PageInfo<Customer> findAllByAccountIdPage(Integer p, Account account) {

        PageHelper.startPage(p, 10);
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andAccountIdEqualTo(account.getId());

        return new PageInfo<>(customerMapper.selectByExample(customerExample));

    }

    /**
     * 查询每月客户增加的数量
     *
     * @return
     */
    @Override
    public List<Map<String, String>> coountByMonth() {


        return customerMapper.countByCreateTime();
    }

    /**
     * 销售漏斗图
     *
     * @return
     */
    @Override
    public List<Map<String, String>> saleFunnelListByProgress() {

        return customerMapper.saleFunnel();
    }

    /**
     * 从共公海拿客户
     * @param id
     * @param account
     */
    @Override
    public void taskPublicCustomer(Integer id, Account account) {

        Customer customer = customerMapper.selectByPrimaryKey(id);

        if(customer != null && customer.getAccountId() == 0) {

            customer.setAccountId(account.getId());

            customerMapper.updateByPrimaryKey(customer);

         } else {

            throw new ServiceException("验证失败");

        }

    }


    private List<Customer> findAllCustomerByAccountId(Account account) {

        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andAccountIdEqualTo(account.getId());

        return customerMapper.selectByExample(customerExample);

    }


}
