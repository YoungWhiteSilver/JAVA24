package com.kaishengit.mappers;

import com.kaishengit.entity.Personnel;

/**
 *
 * @author silver
 * @date 2017/10/24
 */
public interface PersonnelMapper {

    /**
     * 查询队员信息和所属战队
     * @param id 队员Id
     * @return 返回Personnel对象
     */
    Personnel findById(int id);

    /**
     * 查询队员信息和所属战队
     * @param id 队员Id
     * @return 返回Personnel对象
     */
    Personnel findByIdTwo(int id);

}
