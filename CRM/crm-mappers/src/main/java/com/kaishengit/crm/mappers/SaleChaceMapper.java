package com.kaishengit.crm.mappers;

import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.example.SaleChanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleChaceMapper {
    long countByExample(SaleChanceExample example);

    int deleteByExample(SaleChanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaleChance record);

    int insertSelective(SaleChance record);

    List<SaleChance> selectByExample(SaleChanceExample example);

    SaleChance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaleChance record, @Param("example") SaleChanceExample example);

    int updateByExample(@Param("record") SaleChance record, @Param("example") SaleChanceExample example);

    int updateByPrimaryKeySelective(SaleChance record);

    int updateByPrimaryKey(SaleChance record);

    List<SaleChance> selectCustomerSaleChanceByAccountId(@Param("accId") Integer accId);
}