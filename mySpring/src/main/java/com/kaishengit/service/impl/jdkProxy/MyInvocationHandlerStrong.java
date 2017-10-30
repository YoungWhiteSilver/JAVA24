package com.kaishengit.service.impl.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *通过匿名局部内部类来做
 * @author silver
 * @date 2017/10/29
 */
public class MyInvocationHandlerStrong {

    private Object target;

    public MyInvocationHandlerStrong(Object target) {

        this.target = target;

    }

    public Object getProxyNewProxyInstance() {

        return Proxy.newProxyInstance(
                //指定当前目标对象使用类加载器。获得加载器的方法是固定的
                target.getClass().getClassLoader(),
                //目标对象实现的接口类型，使用泛型的方式确认类型
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("前置通知");

                        Object result = method.invoke(target, args);

                        System.out.println("后置通知");

                        return result;

                    }
                }
        );

    }






}
