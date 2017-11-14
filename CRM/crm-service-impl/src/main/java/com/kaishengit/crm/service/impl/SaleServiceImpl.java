package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.mappers.CustomerMapper;
import com.kaishengit.crm.mappers.SaleChanceMapper;
import com.kaishengit.crm.service.SaleService;
import com.kaishengit.crm.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/13
 */
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleChanceMapper saleChaceMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Value("#{'${progress}'.split(',')}")
    private List<String> progressList;

    /**
     * 查找员工所有的销售机会
     *
     * @param account
     * @return
     */
    @Override
    public List<SaleChance> findByAccound(Account account) {

        if(account == null) {

            throw new ServiceException("非法用户访问，正在磨刀。。。");

        }

        return saleChaceMapper.selectCustomerSaleChanceByAccountId(account.getId());

    }

    /**
     * 查找所有的跟进进度
     *
     * @return
     */
    @Override
    public List<String> findAllProgress() {
        return progressList;
    }

    /**
     * 添加机会
     *
     * @param saleChance
     * @param account
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newSaleChance(SaleChance saleChance, Account account) {

        if(saleChance != null && (saleChance.getAccountId().equals(account.getId()))) {

            saleChaceMapper.insertSelective(saleChance);

            Customer customer = new Customer();
            customer.setId(saleChance.getCustId());
            customer.setUpdateName(new Timestamp(System.currentTimeMillis()));

            customerMapper.updateByPrimaryKeySelective(customer);

        } else {

            throw new ServiceException("非法访问");

        }

    }

    /**
     * 查询详情通过Id
     *
     * @param saleId
     * @param account
     * @return
     */
    @Override
    public SaleChance findById(Integer saleId, Account account) {

        SaleChance saleChance = saleChaceMapper.selectByPrimaryKey(saleId);

        if(! validate(saleChance.getAccountId(), account)) {
            throw new ServiceException("没有访问权限");
        }

        return saleChance;

    }

    /**
     * 修改跟进进度
     *
     * @param saleChanceId
     * @param progress
     * @param account
     */
    @Override
    public void updateProgess(Integer saleChanceId, String progress, Account account) {

        SaleChance saleChance = saleChaceMapper.selectByPrimaryKey(saleChanceId);

        if(!validate(saleChance.getAccountId(), account)) {
            throw new ServiceException("非法访问");
        }

        saleChance.setProgress(progress);
        saleChance.setLastTime(new Timestamp(System.currentTimeMillis()));

    }


    /**
     * 验证Accound是否有权限
     * @param account
     * @return
     */
    private boolean validate(Integer id,Account account) {

        if(id != null && id.equals(account.getId())) {

            return true;

        }

        return false;
    }

}
