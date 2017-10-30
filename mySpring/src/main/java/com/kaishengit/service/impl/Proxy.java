package com.kaishengit.service.impl;

import com.kaishengit.service.Sales;

/**
 *
 * @author silver
 * @date 2017/10/29
 */
public class Proxy implements Sales {

    private Sales sales;

    public Proxy(Sales sales) {

        this.sales = sales;

    }

    @Override
    public void sales() {

        System.out.println("加价五百块");
        sales.sales();
        System.out.println("送个鼠标");

    }

}
