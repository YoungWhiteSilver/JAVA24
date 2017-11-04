package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Student;
import com.kaishengit.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author silver
 * @date 2017/11/4
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private WebService webService;

    /*@GetMapping
    public String studentList(@RequestParam(required = false, defaultValue = "1") Integer p, Model model) {

        PageInfo<Student> studentPageInfo = webService.findByPage(p);
        model.addAttribute("studentPageInfo", studentPageInfo);

        return "student/list";
    }*/
    @GetMapping
    public String studentList(@RequestParam(name = "p", required = false, defaultValue = "1") Integer p,
                              @RequestParam( required = false, defaultValue = "") String stuName,
                              @RequestParam( required = false, defaultValue = "") Integer stuClass,
                              @RequestParam( required = false, defaultValue = "") Integer stuAge,
                              Model model) {

        Map<String, Object> map = new HashMap();

        map.put("stuName", stuName);
        map.put("stuAge", stuAge);
        map.put("stuClass", stuClass);

        model.addAttribute("studentPageInfo", webService.findByPage(p, map));
        model.addAttribute("studentAgeList", webService.selectAgeAll());
        model.addAttribute("studentClass", webService.selectClassNameAll());
        return "student/list";

    }

    @GetMapping("/{id:\\d+}")
    public String studentDet(@PathVariable Integer id, Model model) {


        model.addAttribute("student",webService.findById(id));

        return "student/details";

    }

    @GetMapping("/{id:\\d+}/edit")
    public String studentEdit(@PathVariable Integer id, Model model) {

        model.addAttribute("student",webService.findById(id));
        model.addAttribute("studentClass", webService.selectClassNameAll());
        return "student/edit";

    }

    @PostMapping("/{id:\\d+}/edit")
    public String studentEdit(Student student,
                             @PathVariable Integer id,
                              RedirectAttributes redirectAttributes ) {

        webService.updateStudent(student);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/student/" + id;

    }

    @GetMapping("/new")
    public String studentNew(Model model) {

        model.addAttribute("studentClass", webService.selectClassNameAll());

        return "/student/new";

    }

    @PostMapping("/new")
    public String studentNew(Student student,
                             RedirectAttributes redirectAttributes) {

        webService.save(student);
        redirectAttributes.addFlashAttribute("message","保存成功");

        return "redirect:/student";

    }

}

