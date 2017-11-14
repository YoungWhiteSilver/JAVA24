package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.SaleChance;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/13
 */
public interface SaleService {
    /**
     * 查找员工所有的销售机会
     * @param account
     * @return
     */
    List<SaleChance> findByAccound(Account account);


    /**
     * 查找所有的跟进进度
     * @return
     */
    List<String> findAllProgress();

    /**
     * 添加机会
     * @param saleChance
     * @param account
     */
    void newSaleChance(SaleChance saleChance, Account account);

    /**
     * 查询详情通过Id
     * @param saleId
     * @param account
     * @return
     */
    SaleChance findById(Integer saleId, Account account);

    /**
     * 修改跟进进度
     * @param saleChanceId
     * @param progress
     * @param account
     */
    void updateProgess(Integer saleChanceId, String progress, Account account);

}
