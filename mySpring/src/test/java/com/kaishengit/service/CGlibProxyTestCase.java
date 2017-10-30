package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.impl.CGlibProxy.MyCGlib;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 *由于CGlib效率低，一般用在没有接口，只有父类
 * 的时候！！
 * Created by silver on 2017/10/30.
 */
public class CGlibProxyTestCase {


    @Test
    public void CGlibTest() {

        //创建net.sf.cglib.proxy.Enhancer类的对象
        Enhancer enhancer = new Enhancer();

        //设置目标对象
        enhancer.setSuperclass(UserDao.class);

        //设置MethodInterceptor接口的实现类 该实现就是模板，需自己创建
        // ，并实现接口MethodInterceptor接口
        enhancer.setCallback(new MyCGlib());

        //创建子类，产生目标对象的子类（动态代理类），使用父类来接受
        UserDao userDao = (UserDao)enhancer.create();

        userDao.sayHi();

    }

}
