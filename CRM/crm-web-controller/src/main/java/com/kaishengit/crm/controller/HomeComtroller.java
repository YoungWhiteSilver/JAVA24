package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author silver
 * @date 2017/11/7
 */
@Controller

public class HomeComtroller {

    @Autowired
    private WebService webService;

    @GetMapping("/home")
    public String home(@RequestParam(required = false, defaultValue = "1") Integer p,
                       @RequestParam(required = false, defaultValue = "") Integer id,
                       Model model) {

        PageInfo<Dept> pageInfo = webService.homePage(p, id);

        model.addAttribute("pageInfo", pageInfo);

        return "home";
    }



}
