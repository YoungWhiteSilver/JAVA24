package com.kaishengit.crm.controller.interceptor;

import com.kaishengit.crm.entity.Account;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author silver
 * @date 2017/11/8
 */
public class LoginInterceptor  extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse  response,
                             Object handler) throws Exception{

        String requestUrl = request.getRequestURI();

        if(requestUrl.startsWith("/static/")) {

            return true;
        }

        if("".equals(requestUrl) || "/".equals(requestUrl)) {

            return true;
        }

        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("curr_account");

        if(account != null) {

            return true;

        }

        response.sendRedirect("/");

        return  false;

    }





}
