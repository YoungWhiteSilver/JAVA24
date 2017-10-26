package com.kaishengit.mappers;

import com.kaishengit.entity.Boy;

/**
 *
 * @author silver
 * @date 2017/10/25
 */
public interface BoyMapper {

    /**查找
     * @param id 传如 BOY对象的id
     * @return 返回boy对象
     */
    Boy findBoyAndLabel(Integer id);

    /**
     * 查找 会造成 n + 1
     *
     * @param id 传如 BOY对象的id
     * @return 返回boy对象
     */
    Boy findBoyAndLabelTwo(Integer id);

}
