package com.kaishengit.controller;

import com.kaishengit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/29
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 去list页
     * @return
     *  public List<Student> selectAllByPage(Integer p, Integer age, String className, String stuName) {
     */
    @GetMapping
    public String list(@RequestParam(required = false, defaultValue = "0") Integer p,
                       @RequestParam(required = false, defaultValue = "") String stuName,
                       @RequestParam(required = false, defaultValue = "") Integer stuClass,
                       @RequestParam(required = false) Integer stuAge,
                       Model model) {

        model.addAttribute("studentPageInfo", studentService.selectAllByPage(p, stuAge, stuClass, stuName));
        return "list";
    }


}
