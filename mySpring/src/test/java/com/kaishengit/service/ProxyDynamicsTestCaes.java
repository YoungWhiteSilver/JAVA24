package com.kaishengit.service;

import com.kaishengit.service.impl.DellSalesImpl;
import com.kaishengit.service.impl.jdkProxy.MyInvocationHandler;
import com.kaishengit.service.impl.jdkProxy.MyInvocationHandlerStrong;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by silver on 2017/10/29.
 */
public class ProxyDynamicsTestCaes {

    /*动态代理模式
    * jdk自带的动态代理模式 ，解决静态代理模式代理类过多时，增加接口中的方法，目标
    * 对象和代理对象都要维护
    *
    * 优点：1，代理对象不需要实现接口
    * 2，代理对象的生成是利用JDK的API，
    * 动态的在内存中构建的。（需要我们指定
    * 代理对象和目标对象实现的接口类型）
    * */
    @Test
    public void salesTest() {

        DellSalesImpl dellSales = new DellSalesImpl();

        MyInvocationHandler invocationHandler = new MyInvocationHandler(dellSales);

        Sales sales = (Sales) Proxy.newProxyInstance(
                //指定当前目标对象使用类加载器。获得加载器的方法是固定的
                dellSales.getClass().getClassLoader(),
                //目标对象实现的接口类型，使用泛型的方式确认类型
                dellSales.getClass().getInterfaces(),
               /* 事件处理，执行目标对象的方法时，会触发事件处理器的方法，会把当前
                执行目标对象的方法作为参数传入*/
                invocationHandler);

        sales.sales();
    }

    @Test
    public void salesStrongTest() {

        DellSalesImpl dellSales = new DellSalesImpl();

        Sales sales = (Sales)new MyInvocationHandlerStrong(dellSales).getProxyNewProxyInstance();

        sales.sales();


    }

}
