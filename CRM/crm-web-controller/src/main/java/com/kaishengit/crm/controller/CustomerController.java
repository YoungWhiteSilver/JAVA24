package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.service.WebService;
import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author silver
 * @date 2017/11/9
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private WebService webService;

    @RequestMapping("/my")
    public String myCustomer(@RequestParam(required = false, defaultValue = "1") Integer p,
                             @RequestParam(required = false, defaultValue = "") String key,
                             HttpSession session,
                             Model model) {

        Account account = (Account)session.getAttribute("curr_account");
        Integer accId = account.getId();

        PageInfo<Customer> pageInfo = webService.myCustomer(p, key, accId);
        model.addAttribute("pageInfo", pageInfo);
        return "customer/list";
    }

    /**
     * 客户信息详情
     * @param id
     * @param session
     * @param redirectAttributes
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String customerDetail(@RequestParam(required = false) Integer id,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        Account account = (Account)session.getAttribute("curr_account");
        Integer accId = account.getId();
        try{

            Customer customer = webService.customerDetail(id, accId);
            model.addAttribute("customer", customer);

            return "customer/detail";

        } catch (ServiceException e) {

            e.printStackTrace();
            redirectAttributes.addAttribute("warning", e.getMessage());
            return "redirect:/customer/my";

        }

    }

    @PostMapping("/new")
    @ResponseBody
    public AjaxResult saveCustomer(Customer customer) {

        try {

            webService.saveCustomer(customer);
            return AjaxResult.success();

        } catch (ServiceException e) {

            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }

    }




















}
