package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.service.*;
import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private WebService webService;

    @Autowired
    private SaleChanceRecordService saleChanceRecordService;

    @Autowired
    private TaskService taskService;

    /**
     * 当前员工存贮在seesion中属性名子
     */
    private static final String USER = "curr_account";

    @GetMapping("/my/chance")
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
    @ResponseBody
    public AjaxResult newSaleChance(SaleChance saleChance,
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

    @GetMapping("/my/detail")
    public String saleChanceDetail(Integer saleId,
                                   HttpSession session,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        Account account = getAccount(USER, session);
        try{

            SaleChance saleChance = saleService.findById(saleId, account);
            model.addAttribute("saleChance", saleChance);
            model.addAttribute("customer", webService.customerDetail(saleChance.getCustId(), account.getId()));
            model.addAttribute("RecordList", saleChanceRecordService.findAllBySaleId(saleChance.getId()));
            model.addAttribute("taskList", taskService.findBySaleId(saleChance.getId()));
            model.addAttribute("processList", saleService.findAllProgress());
            return "sale/detail";

        } catch (ServiceException e) {

            e.printStackTrace();
            redirectAttributes.addAttribute("message", e.getMessage());
            return "redirect:/sale/my/chance";
        }

    }

    @PostMapping("/my/progress/update")
    public String updateProgress(@RequestParam Integer saleChanceId,
                                 @RequestParam String progress,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        Account account = getAccount(USER, session);
        try{

            saleChanceRecordService.saveRecordAndUpdateProgress(saleChanceId, progress, account);
            return  "redirect:/sale/my/detail?saleId=" + saleChanceId;

        } catch (ServiceException e) {

            e.printStackTrace();
            redirectAttributes.addAttribute("message", e.getMessage());
            return "redirect:/sale/my/chance";
        }
    }
    @PostMapping("/my/new/record")
    public String saveRecord(Integer saleId,
                             String content,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {

        Account account = getAccount(USER, session);

        try{

            saleChanceRecordService.saveRecord(saleId, content, account);
            return  "redirect:/sale/my/detail?saleId=" + saleId;

        } catch (ServiceException e) {

            e.printStackTrace();
            redirectAttributes.addAttribute("message", e.getMessage());
            return "redirect:/sale/my/chance";
        }

    }

}
