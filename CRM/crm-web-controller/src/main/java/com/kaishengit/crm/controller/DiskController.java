package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.utils.AjaxResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    public String toList(@RequestParam(required = false, defaultValue = "0", name = "_") Integer pId, Model model) {

        model.addAttribute("diskList", diskService.findAll(pId));

        model.addAttribute("disk", diskService.findByPid(pId));

        return "disk/list";

    }

    /**
     * 新建文件夹
     *
     * @param pId
     * @param accountId
     * @param name
     * @return
     */
    @PostMapping("/new/folder")
    @ResponseBody
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

    /**
     * 文件上传
     *
     *  @param pId
     * @param accountId
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(Integer pId, Integer accountId, MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return AjaxResult.error("请选择上传文件");
        }

        try {

            //获得输入流
            InputStream inputStream = file.getInputStream();

            //获得文件大小
            Long fileSize = file.getSize();

            //获得上传文件名
            String fileName = file.getOriginalFilename();
            diskService.saveFile(inputStream, fileSize, fileName, pId, accountId);

            List<Disk> diskList = diskService.findAll(pId);
            return AjaxResult.success(diskList);

        } catch (ServiceException e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam(name = "_", required = false, defaultValue = "0") Integer id,
                             @RequestParam(required = false, defaultValue = "") String fileName,
                             HttpServletResponse response) {


        try {

            OutputStream outputStream = response.getOutputStream();
            InputStream inputStream = diskService.downloadFile(id);

            if(StringUtils.isNotEmpty(fileName)) {

                response.setContentType("application/octet-stream");
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                //设置下载框里的名字
                response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName +"\"");

            }

            IOUtils.copy(inputStream, outputStream);

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("上传出错了");
        } catch (ServiceException e) {
            e.printStackTrace();

        }


    }


}
