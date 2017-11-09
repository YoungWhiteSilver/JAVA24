package com.kaishengit.crm.mappers;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.example.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    long countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    Account selectByMobile(@Param("mobile") String mobile);

    List<Account> selectAccountAndDept(
            @Param("start") Integer start,
            @Param("length") Integer length,
            @Param("deptId") Integer deptId,
            @Param("accountName") String accountName);

    Long countAllByDeptId(@Param("deptId") Integer deptId);

    Long countfilte(@Param("deptId") Integer deptId,
                    @Param("accountName") String accountName);
}