package com.kaishengit.controller;

import com.kaishengit.pojo.Student;
import com.kaishengit.service.StudentClassService;
import com.kaishengit.service.StudentService;
import com.kaishengit.utils.Page;
import com.kaishengit.utils.QueryUtil;
import com.kaishengit.utils.voentity.RequestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private StudentClassService studentClassService;

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

    @GetMapping("/{id:\\d+}")
    public String detail(@PathVariable Integer id,
                         Model model) {

        model.addAttribute("student", studentService.selectById(id));

        return "details";
    }


    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id,
                       Model model) {

        model.addAttribute("student", studentService.selectById(id));
        model.addAttribute("stuClassList", studentClassService.selectAll());
        return "edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String edit(Integer id,
                       Integer stuAge,
                       String stuAddress,
                       String stuName,
                       Integer stuClass,
                       RedirectAttributes redirectAttributes) {

        Student student = new Student();

        student.setId(id);
        student.setStuName(stuName);
        student.setStuAge(stuAge);
        student.setStuAddress(stuAddress);

        student.setStudentClass(studentClassService.selectById(stuClass));

        studentService.insertOrUpdate(student);

        return "redirect:/student/"+ id;

    }

    @GetMapping("/new")
    public String newStudent(Model model) {

        model.addAttribute("studentClass", studentClassService.selectAll());
        return "new";
    }

    @PostMapping("/new")
    public String newStudent(Integer stuAge,
                             String stuAddress,
                             String stuName,
                             Integer stuClass,
                             RedirectAttributes redirectAttributes) {

        Student student = new Student();

        student.setStuName(stuName);
        student.setStuAge(stuAge);
        student.setStuAddress(stuAddress);

        student.setStudentClass(studentClassService.selectById(stuClass));

        studentService.insertOrUpdate(student);

        return "redirect:/student/";

    }

}
