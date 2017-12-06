package com.kaishengit.controller;

import com.kaishengit.entity.Product;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.ProductService;
import com.kaishengit.utils.AjaxResult;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/5
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String toList(Model model) {

        List<Product> productList = productService.findAll();

        model.addAttribute("productList", productList);
        return "product/home";

    }

    @GetMapping("/new")
    public String toNew() {

        return "product/new";

    }

    @PostMapping("/new")
    public String saveProduct(Product product,
                              String sTime,
                              String eTime,
                              MultipartFile file,
                              RedirectAttributes redirectAttributes) {


        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

        DateTime startDateTime = DateTime.parse(sTime, dateTimeFormatter);
        DateTime endDateTime = DateTime.parse(eTime, dateTimeFormatter);

        product.setStartTime(startDateTime.toDate());
        product.setEndTime(endDateTime.toDate());

        try{

            productService.saveProduct(product, file);

        } catch (ServiceException e) {

            redirectAttributes.addAttribute("message", e.getMessage());

        }

        return "redirect:/product";

    }

    @GetMapping("/{id:\\d+}")
    public String toDetail(@PathVariable Integer id, Model model) {

        Product product = productService.findById(id);

        model.addAttribute("product", product);

        return "product/detail";

    }

    @PostMapping("/seckill/{id:\\d+}")
    @ResponseBody
    public AjaxResult spikeProduct(@PathVariable Integer id) {
        try{

            productService.spikeProduct(id);

            return AjaxResult.success();

        } catch (ServiceException e) {

            return AjaxResult.error(e.getMessage());

        }

    }



}
