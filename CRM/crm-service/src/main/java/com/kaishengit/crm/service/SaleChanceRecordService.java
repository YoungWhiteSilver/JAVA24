package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.SaleChanceRecord;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/14
 */
public interface SaleChanceRecordService {

    /**
     * 查询所有的跟进记录 通过销售机会Id
     * @param id
     * @return
     */
    List<SaleChanceRecord> findAllBySaleId(Integer id);

    /**
     * 修改跟进进度，并增加一条跟进记录
     * @param saleChanceId
     * @param progress
     * @param account
     */
    void saveRecordAndUpdateProgress(Integer saleChanceId, String progress, Account account);

    /**
     * 新增=一条跟进记录
     * @param saleId
     * @param content
     * @param account
     */
    void saveRecord(Integer saleId, String content, Account account);

}
