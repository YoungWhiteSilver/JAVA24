package com.kaishengit.controller;

import com.kaishengit.entity.Student;
import com.kaishengit.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @author silver
 * @date 2017/11/3
 */
@Controller
public class StudentController {

    @Autowired
    private WebService webService;

    @GetMapping("/findAll")
    public String findAll(Model model) {

        List<Student> studentList = webService.findAll();

        model.addAttribute("studentList", studentList);

        return "student";
    }

    /**
     * 分页的应用
     *
     * @ResponseBody 返回json时需要使用该注解，该注解在方法上表示该方法返回的为json，在类名头上表示该里所有返回值为json
     * @param pageNo
     * @return
     */
    @GetMapping("/findByPage")
    @ResponseBody
    public List<Student> findAllByPageNo(@RequestParam(defaultValue = "1", required = false, name = "p") Integer pageNo) {

        return webService.findAllByPage(pageNo);

    }

    /**这个/{name} 是必须的吗？
     * @param name
     * @return
     */
    @GetMapping("/findByName/{name}")
    @ResponseBody
    public List<Student> findByName(
            @PathVariable(required = false) String name) {

        return webService.findByName(name);

    }

}
