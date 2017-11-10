package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/10
 */
public abstract class BaseController {

    public Account getAccount(String name, HttpSession session) {

        Account account = (Account) session.getAttribute(name);
        return account;
    }



}
