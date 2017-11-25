package com.kaishengit.crm.auth;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.service.WebService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/24
 */
public class ShiroRealm extends AuthorizingRealm{

    private WebService webService;

    public void setWebService(WebService webService) {
        this.webService = webService;
    }



    /**
     * 角色或权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Account account = (Account) principalCollection.getPrimaryPrincipal();


        List<Dept> depts = webService.findDeptById(account.getId());

        List<String> deptNameList = new ArrayList<>();

        for(Dept dept: depts) {
            deptNameList.add(dept.getDeptName());
            System.out.println(dept.getDeptName());
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(deptNameList);

        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        String userName = usernamePasswordToken.getUsername();

        Account account = webService.findByMoblie(userName);

        if(account != null) {
            return new SimpleAuthenticationInfo(account, account.getPassword(), getName());
        }

        return null;
    }


}
