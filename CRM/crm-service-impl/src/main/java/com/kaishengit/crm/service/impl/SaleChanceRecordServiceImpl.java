package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.entity.SaleChanceRecord;
import com.kaishengit.crm.example.SaleChanceRecordExample;
import com.kaishengit.crm.mappers.CustomerMapper;
import com.kaishengit.crm.mappers.SaleChanceMapper;
import com.kaishengit.crm.mappers.SaleChanceRecordMapper;
import com.kaishengit.crm.service.SaleChanceRecordService;
import com.kaishengit.crm.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/14
 */
@Service
public class SaleChanceRecordServiceImpl implements SaleChanceRecordService{

    @Autowired
    private SaleChanceRecordMapper saleChanceRecordMapper;

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询所有的跟进记录 通过销售机会Id
     *
     * @param id
     * @return
     */
    @Override
    public List<SaleChanceRecord> findAllBySaleId(Integer id) {

        SaleChanceRecordExample saleChanceRecordExample = new SaleChanceRecordExample();
        saleChanceRecordExample.createCriteria().andSaleIdEqualTo(id);

        return saleChanceRecordMapper.selectByExample(saleChanceRecordExample);

    }

    /**
     * 修改跟进进度，并增加一条跟进记录
     *
     * @param saleChanceId
     * @param progress
     * @param account
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRecordAndUpdateProgress(Integer saleChanceId, String progress, Account account) {

        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(saleChanceId);

        if(!validate(saleChance.getAccountId(), account)) {

            throw new ServiceException("权限不足");
        }

        saleChance.setLastTime(new Timestamp(System.currentTimeMillis()));
        saleChance.setProgress(progress);

        saleChanceMapper.updateByPrimaryKeySelective(saleChance);

        SaleChanceRecord saleChanceRecord = new SaleChanceRecord();

        saleChanceRecord.setContent("将当前进度修改为 ["+ progress +"]");
        saleChanceRecord.setSaleId(saleChanceId);

        saleChanceRecordMapper.insertSelective(saleChanceRecord);

        Customer customer = customerMapper.selectByPrimaryKey(saleChance.getCustId());

        customer.setUpdateName(new Timestamp(System.currentTimeMillis()));

        customerMapper.updateByPrimaryKeySelective(customer);

    }

    /**
     * 新增=一条跟进记录
     *
     * @param saleId
     * @param content
     * @param account
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRecord(Integer saleId, String content, Account account) {

        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(saleId);

        if(! validate(saleChance.getAccountId(), account)) {

            throw new ServiceException("权限不足");

        }

        saleChance.setLastTime(new Timestamp(System.currentTimeMillis()));
        saleChanceMapper.updateByPrimaryKeySelective(saleChance);

        SaleChanceRecord saleChanceRecord = new SaleChanceRecord();

        saleChanceRecord.setContent(content);
        saleChanceRecord.setSaleId(saleId);

        saleChanceRecordMapper.insertSelective(saleChanceRecord);

        Customer customer = customerMapper.selectByPrimaryKey(saleChance.getCustId());

        customer.setUpdateName(new Timestamp(System.currentTimeMillis()));

        customerMapper.updateByPrimaryKeySelective(customer);

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
