package com.kaishengit.crm.controller;

import com.google.common.collect.Maps;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.service.WebService;
import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.utils.AjaxResult;
import com.kaishengit.utils.DataTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author silver
 * @date 2017/11/7
 */
@Controller
@RequestMapping("/employee")
public class AccountComtroller {

    @Autowired
    private WebService webService;

    @GetMapping
    public String home() {

        return "employee/list";
    }

    /**
     * ztree插件返回的json
     * @return
     */
    @GetMapping("/dept.json")
    @ResponseBody
    public List<Dept> deptList() {

        List<Dept> deptList = webService.deptList();

        return deptList;

    }

    /**
     * 添加部门
     *
     * @param dataName
     * @return
     */
    @PostMapping("/dept/new")
    @ResponseBody
    public AjaxResult newDept(String dataName) {
        try{

            webService.saveDept(dataName);
            return AjaxResult.success();

        } catch (ServiceException e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());

        }

    }

    /**
     * DataTable 插件的请求
     *该插件会向后台传来很多值，这些只是其中用到的一部分，
     * @param draw 第几次访问
     * @param start 每页从第几条开始
     * @param length 每页有几条·
     * @param deptId bumenId
     * @param request 用来获取前端的关键字
     * @return
     */
    @GetMapping("/table.json")
    @ResponseBody
    public DataTableResult<Account> dataTableResult(Integer draw, Integer start, Integer length,
                                           Integer deptId, HttpServletRequest request) {

        String key = request.getParameter("search[value]");

        Map<String, Object> map = Maps.newHashMap();

        map.put("start", start);
        map.put("length", length);
        map.put("deptId", deptId);
        map.put("key", key);

        List<Account> accounts = webService.findAccountAndDept(map);
        Long total = webService.countAll(deptId);
        Long filterNum = webService.countfilte(map);

        return new DataTableResult<>(draw, total.intValue(), filterNum.intValue(), accounts);

    }

    @PostMapping("/new")
    @ResponseBody
    public AjaxResult newEmployee(String userName, String password, String mobile, Integer[] deptId) {

        try{

            webService.saveEmployee(userName, password, mobile, deptId);
            return AjaxResult.success();

        } catch (ServiceException e) {

            e.printStackTrace();
            return AjaxResult.error(e.getMessage());

        }

    }


}
