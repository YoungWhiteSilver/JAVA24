package com.kaishengit.crm.controller;

import com.kaishengit.crm.auth.ShiroUtil;
import com.kaishengit.crm.entity.Account;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/10
 */
public abstract class BaseController {

    public Account getAccount() {

        return ShiroUtil.getAccount();

    }




}
