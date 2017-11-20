package com.kaishengit.crm.controller;

import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/18
 */
@Controller
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String list() {
        return "charts/list";
    }

    @GetMapping("/customerTime")
    @ResponseBody
    public AjaxResult customerTime() {

        List<Map<String, String>> countCustomerTineList =  customerService.coountByMonth();
        return AjaxResult.success(countCustomerTineList);

    }

    @GetMapping("/funnel")
    @ResponseBody
    public AjaxResult saleFunnel() {

        List<Map<String, String>> saleFunnelList = customerService.saleFunnelListByProgress();

        return AjaxResult.success(saleFunnelList);
    }


}
