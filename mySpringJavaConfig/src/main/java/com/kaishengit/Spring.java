package com.kaishengit;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import com.kaishengit.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *替代Spring.xml,里面不需要写方法，只有你不想要自动导注入的时候才需要写！
 * 三个注解写好就可以代替了
 * @author silver
 * @date 2017/10/30
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class Spring {

    /**
     * 手动导入UserDao
     * @return
     */
    @Bean
    public UserDao userDao() {

        return new UserDao();

    }

    /**
     * 手动的set注入
     * @return
     */
    @Bean
    public UserService userService() {

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.setUserDao(userDao());

        return userServiceImpl;
    }


}
