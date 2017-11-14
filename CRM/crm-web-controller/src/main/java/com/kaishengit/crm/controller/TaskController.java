package com.kaishengit.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/14
 */
@Controller
@RequestMapping("/task")
public class TaskController {


    @GetMapping
    public String toList() {

        return "task/list";

    }

    @PostMapping("/new")
    public String addTask() {
        return null;
    }

















}
