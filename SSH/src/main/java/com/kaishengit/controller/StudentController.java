package com.kaishengit.controller;

import com.kaishengit.pojo.Student;
import com.kaishengit.service.StudentService;
import com.kaishengit.utils.Page;
import com.kaishengit.utils.QueryUtil;
import com.kaishengit.utils.voentity.RequestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
                        HttpServletRequest request,
                        Model model) {

        List<RequestQuery> requestQueryList = QueryUtil.getQueryList(request);

        Page<Student> studentPage = studentService.selectAllByPage(p, requestQueryList);

        model.addAttribute("studentPageInfo", studentPage);

        return "list";

    }


}
