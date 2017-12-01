package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.SaleChanceRecordService;
import com.kaishengit.crm.service.WebService;
import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.utils.AjaxResult;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private SaleChanceRecordService saleChanceRecordService;

    @RequestMapping("/my")
    public String myCustomer(@RequestParam(required = false, defaultValue = "1") Integer p,
                             @RequestParam(required = false, defaultValue = "") String key,
                             Model model) {

        Account account = getAccount();
        Integer accId = account.getId();

        PageInfo<Customer> pageInfo = webService.myCustomer(p, key, accId);
        model.addAttribute("pageInfo", pageInfo);
        return "customer/list";
    }

    /**
     * 客户信息详情
     * @param id
     * @param redirectAttributes
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String customerDetail(@RequestParam(required = false) Integer id,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        Account account = getAccount();
        Integer accId = account.getId();
        try{

            Customer customer = webService.customerDetail(id, accId);
            model.addAttribute("customer", customer);
            model.addAttribute("accountList", webService.findAllEmployee());
            model.addAttribute("RecordList", saleChanceRecordService.findAllByCustomerId(id));
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
     * @param model
     * @return
     */
    @GetMapping("/{customerId:\\d+}/edit")
    public String editCustomer(@PathVariable(required = false, name = "customerId") Integer customerId,
                               Model model) {

        Account account = getAccount();

        model.addAttribute("customer", webService.customerDetail(customerId, account.getId()));

        return "customer/edit";

    }

    /**
     * 修改用户信息
     * @param customer
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/edit")
    public String editCustomer(Customer customer,
                               RedirectAttributes redirectAttributes) {

        Account account = getAccount();

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
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/my/export.csv")
    public void exportExcelCsv(HttpServletResponse response,
                               RedirectAttributes redirectAttributes) throws IOException{

        Account account = getAccount();

        response.setContentType("text/csv;charset=GBK");
        response.addHeader("Content-Disposition","attachment; filename=\"costomer.csv\"");

        OutputStream outputStream =  response.getOutputStream();
        customerService.exprotExcelCsv(outputStream, account);

    }

    @GetMapping("/my/export.xls")
    public void exportExcelXls(HttpServletResponse response) throws IOException{

        Account account = getAccount();

        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition","attachment; filename=\"costomer.xls\"");

        OutputStream outputStream = response.getOutputStream();
        customerService.exportExcelXls(outputStream, account);


    }

    /**
     * 转让客户
     * @return
     */
    @GetMapping("/my/{customerId:\\d+}/transfer/{toAccountId:\\d+}")
    public String transferCustomer(@PathVariable Integer customerId,
                                  @PathVariable Integer toAccountId,
                                   RedirectAttributes redirectAttributes) {

        Account account = getAccount();

        try{

            customerService.transferCustomer(customerId, toAccountId, account);
            redirectAttributes.addAttribute("warning","转让成功");

        } catch (ServiceException e) {

            e.printStackTrace();
            redirectAttributes.addAttribute("warning", e.getMessage());

        }

        return "redirect:/customer/my";
    }

    @GetMapping("/{customerId:\\d+}/public")
    public String publicCustomer(@PathVariable Integer customerId,
                                 RedirectAttributes redirectAttributes) {

        Account account = getAccount();

        customerService.customerPublic(customerId, account);
        redirectAttributes.addAttribute("warning", "已经移交公海");
        return "redirect:/customer/my";

    }


    @GetMapping("/public")
    public String publicCustomer(@RequestParam(required = false, defaultValue = "0") Integer p, Model model) {

        Account account = new Account();
        account.setId(0);

        model.addAttribute("pageInfo",  customerService.findAllByAccountIdPage(p, account));

        return  "customer/public";

    }

    /**
     * 从公海获得对象
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/{id:\\d+}/take")
    public String taskPublicCustomer(@PathVariable Integer id,
                                     RedirectAttributes redirectAttributes) {

        Account account = getAccount();

        try{

            customerService.taskPublicCustomer(id, account);
            redirectAttributes.addAttribute("warning", "接收客户成功");

        } catch (ServiceException e) {

            redirectAttributes.addAttribute("warning", e.getMessage());

        }

        return "redirect:/customer/my";

    }




}
