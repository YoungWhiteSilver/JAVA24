package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.SaleService;
import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/13
 */
@Controller
@RequestMapping("/sale")
public class SaleController extends BaseController{

    @Autowired
    private SaleService saleService;

    @Autowired
    private CustomerService customerService;


    /**
     * 当前员工存贮在seesion中属性名子
     */
    private static final String USER = "curr_account";

    @RequestMapping("/my/chance")
    public String mySaleChance(HttpSession session, Model model) {

        Account account = getAccount(USER, session);

        try{

            List<SaleChance> saleChanceList  = saleService.findByAccound(account);
            model.addAttribute("saleChanceList", saleChanceList);
            model.addAttribute("progressList", saleService.findAllProgress());
            model.addAttribute("customerList", customerService.findAllByAccountId(account));

        } catch (ServiceException e) {

            e.printStackTrace();
            model.addAttribute("message", e.getMessage());

        }

        return "sale/list";
    }

    @PostMapping("/my/new")
    public AjaxResult newSaleChance(@PathVariable SaleChance saleChance,
                                    HttpSession session) {

        Account account = getAccount(USER, session);

        try{

            saleService.newSaleChance(saleChance, account);
            return AjaxResult.success();

        } catch (ServiceException e) {

            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }

    }



}
