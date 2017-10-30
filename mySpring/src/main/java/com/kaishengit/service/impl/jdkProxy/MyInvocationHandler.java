package com.kaishengit.service.impl.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by silver on 2017/10/29.
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {

        this.target = target;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("动态出售电脑");
        Object result = method.invoke(target, args);
        System.out.println("不要钱");
        return result;

    }

}
