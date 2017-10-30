package com.kaishengit.dao;

import org.springframework.stereotype.Repository;

/**
 * @Repository dao一般用这种Spring注释来放入Spring容器里
 *
 * @author silver
 * @date 2017/10/30
 */
@Repository
public class UserDao {

    public void say() {

        System.out.println("Hello");

    }

    public int count() {

        return (1 + 1);
    }

}
