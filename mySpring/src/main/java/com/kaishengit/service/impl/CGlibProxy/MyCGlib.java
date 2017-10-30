package com.kaishengit.service.impl.CGlibProxy;

import com.sun.org.apache.xpath.internal.SourceTree;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.invoke.MethodHandleInfo;
import java.lang.reflect.Method;

/**
 *
 * @author silver
 * @date 2017/10/30
 */
public class MyCGlib implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("大家好");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("how are you");
        return result;

    }
}
