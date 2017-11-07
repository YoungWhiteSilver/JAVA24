package com.kaishengit.crm.controller;

import com.kaishengit.crm.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 *
 * @author silver
 * @date 2017/11/7
 */
@Controller
public class LoginController {

    @Autowired
    private WebService webService;

    @GetMapping("/")
    public String login() {

        return "login";

    }

    @PostMapping("/")
    @ResponseBody
    public Map<String, Object> login(String userName, String password, HttpSession session) {

        Map<String, Object> map = webService.login(userName, password);

        session.setAttribute("curr_account", map.get("data"));

        return map;
    }


}
