package com.kaishengit.service;

import com.kaishengit.service.impl.DellSalesImpl;
import com.kaishengit.service.impl.LenovoSalesImpl;

import com.kaishengit.service.impl.Proxy;
import com.kaishengit.service.impl.jdkProxy.MyInvocationHandler;
import org.junit.Test;



/**
 * Created by silver on 2017/10/29.
 */
public class ProxyTestCase {

    @Test
    public void salesTest() {

        LenovoSalesImpl lenovoSales = new LenovoSalesImpl();
        Proxy proxy = new Proxy(lenovoSales);

        proxy.sales();

    }

    @Test
    public void salesTest1() {

        DellSalesImpl dellSales = new DellSalesImpl();

        Proxy proxy = new Proxy(dellSales);

        proxy.sales();

    }




}
