package com.kaishengit.crm.mappers;

import com.kaishengit.crm.entity.AccountDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author silver
 * @date 2017/11/9
 */
public interface AccountDeptMapper {

    void insertMany(@Param("accountDeptList") List<AccountDept> accountDeptList);
}
