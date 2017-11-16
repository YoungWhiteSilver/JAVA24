package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/16
 */
@Controller
@RequestMapping("/disk")
public class DiskController {

    @Autowired
    private DiskService diskService;

    @RequestMapping(method = RequestMethod.GET)
    public String toList(@RequestParam(required = false, defaultValue = "0") Integer pId,Model model) {

        model.addAttribute("diskList", diskService.findAll(pId));
        return "disk/list";

    }

    /**
     * 新建文件夹
     * @param pId
     * @param accountId
     * @param name
     * @return
     */
    @PostMapping("/new/folder")
    public AjaxResult saveFolder(@RequestParam(required = false, defaultValue = "0") Integer pId,
                                 Integer accountId,
                                 String name) {
        try {

            diskService.saveFolder(pId, accountId, name);

            List<Disk> diskList = diskService.findAll(pId);

            return AjaxResult.success(diskList);

        } catch (ServiceException e) {

            e.printStackTrace();
            return AjaxResult.error(e.getMessage());

        }

    }




}
