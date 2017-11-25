package com.kaishengit.crm.auth;

import com.kaishengit.crm.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/25
 */
public class ShiroUtil {

    /**
     * 获取当前对象
     * @return
     */
    public static Account getAccount() {

        return (Account) getSubject().getPrincipal();

    }

    public static Subject getSubject() {

        return SecurityUtils.getSubject();

    }

}
