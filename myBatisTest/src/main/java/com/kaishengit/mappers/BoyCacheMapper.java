package com.kaishengit.mappers;

import com.kaishengit.entity.Boy;

import java.util.List;

/**
 *缓存
 * @author silver
 * @date 2017/10/26
 */
public interface BoyCacheMapper {

    /**
     * 测试一级缓存是否存在
     * @return
     */
    List<Boy> findAll();


}
