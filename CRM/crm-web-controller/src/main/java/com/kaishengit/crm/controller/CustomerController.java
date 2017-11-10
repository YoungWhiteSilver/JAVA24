package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.WebService;
import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author silver
 * @date 2017/11/9
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController{

    @Autowired
    private WebService webService;

    @Autowired
    private CustomerService customerService;

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

    /**
     * 添加用户
     * @param customer
     * @return
     */
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

    /**
     * 修改用户信息
     * @param customerId
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/{customerId:\\d+}/edit")
    public String editCustomer(@PathVariable(required = false, name = "customerId") Integer customerId,
                               HttpSession session,
                               Model model) {

        Account account = getAccount("curr_account", session);

        model.addAttribute("customer", webService.customerDetail(customerId, account.getId()));

        return "customer/edit";

    }

    /**
     * 修改用户信息
     * @param customer
     * @param session
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/edit")
    public String editCustomer(Customer customer,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        Account account = getAccount("curr_account", session);
        try{

            customerService.editCustomer(account.getId(), customer);

        } catch (ServiceException e) {

            e.printStackTrace();
            redirectAttributes.addAttribute("message", e.getMessage());

        }

        return "redirect:/customer/detail?id=" + customer.getId();

    }


    /**
     * 导出文件为csv
     * @param session
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/my/export.csv")
    public void exportExcelCsv(HttpSession session,
                                 HttpServletResponse response,
                                 RedirectAttributes redirectAttributes) throws IOException{

        Account account = getAccount("curr_account", session);

        response.setContentType("text/csv;charset=GBK");
        response.addHeader("Content-Disposition","attachment; filename=\"costomer.csv\"");

        OutputStream outputStream =  response.getOutputStream();
        customerService.exprotExcelCsv(outputStream, account);

    }

    @GetMapping("/my/export.xls")
    public void exportExcelXls(HttpServletResponse response,
                                 HttpSession session) throws IOException{

        Account account = getAccount("curr_account", session);

        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition","attachment; filename=\"costomer.xls\"");

        OutputStream outputStream = response.getOutputStream();
        customerService.exportExcelXls(outputStream, account);


    }













}
