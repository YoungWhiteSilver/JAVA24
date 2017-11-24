package com.kaishengit;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/24
 */
public class MyRealm implements Realm {


    @Override
    public String getName() {

        return "MyRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {

        return authenticationToken instanceof UsernamePasswordToken;

    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String userName = token.getUsername();
        String password = new String(token.getPassword());

        if(! "1234567".equals(password)) {
            throw new IncorrectCredentialsException("账号或密码错误");
        }

        if(! "tom".equals(userName)) {
            throw new UnknownAccountException("账号不存在");
        }

        return new SimpleAuthenticationInfo(userName, password, getName());

    }


}
