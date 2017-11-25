package com.kaishengit.crm.auth;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/24
 */
public class MyRolesFilter extends RolesAuthorizationFilter{

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        //获取当前登陆对象
        Subject subject = SecurityUtils.getSubject();

        //获取用户的配置项
        String[] roleNames = (String[]) mappedValue;

        if(roleNames == null || roleNames.length == 0) {
            //所有角色都可访问
            return true;

        }

        for(String roleName: roleNames) {

                if(subject.hasRole(roleName)) {

                    return true;

                }

            }

        return  false;
    }

}
