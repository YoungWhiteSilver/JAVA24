package com.kaishengit.controller;

import com.kaishengit.entity.User;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *springMVC的Controller是非线程安全的，避免在类中添加声明有状态的属性
 * @author silver
 * @date 2017/11/2
 */
@Controller
public class HelloController {

    /** @GetMapping("/hello") 所有注解中只有一个值得时候，默认为value="/hello",当注解中写了多个，就必须表明value=“”
     * 所有的  @GetMapping("/hello")注解中的名字推荐加上 “/”
     * @return
     */
    @GetMapping("/hello")
    public String say() {

        System.out.println("Hello");
        return "hello";
    }

    /*@GetMapping("/send")
    public ModelAndView send() {

        ModelAndView modelAndView = new ModelAndView();

        //设置视图的名字
        modelAndView.setViewName("send");
        modelAndView.addObject("message", "你好");

        return modelAndView;
    }*/

    @GetMapping("/send")
    public String send(Model model) {
        model.addAttribute("message","你好");
        return "send";
    }

    @GetMapping("/blog")
    public String blog(Integer id, String name, Model model) {
        System.out.println("id---->" + id + "--name---->" + name );

        model.addAttribute("id", id);
        model.addAttribute("name", name);

        return "blog";
    }

    @GetMapping("/class/{className}/user/{userId}")
    public String findUser(@PathVariable String className,
                           @PathVariable Integer userId,
                           Model model) {

        System.out.println("ClassName: " + className + " UserId:" + userId);
        model.addAttribute("id", userId);
        model.addAttribute("name", className);

        return "blog";
    }

    @GetMapping("/form")
    public String save() {
        return "form";
    }

    /**
     *表单的提交 和文件上传
     * 当表单提交的name属性和一个对象里的属性名相同的时候，可以用该对象来作为参数列表，springmvc会自动封装
     *
     * 上传的时候： MultipartFile photo 的名字要与上传的input的name属性相同，否则需要加
     *？？？（文件上传时用那个）
     *
     * @RequestParam(name = "tag",required = false,defaultValue = "1"） 适用于url为/xxx？id=x&name=？
     *  required = false 当为false可以不传递该参数， defaultValue = "1" 默认值
     *
     * @PathVairable("input的name属性值") 适用于url为/class/{className}/user/{userName}这种url
     *
     * @param user
     * @param email
     * @param photo
     * @param redirectAttributes 重定向传值的方法
     * @return
     */
    @PostMapping("/form")
    public String save(User user,
                       String email,
                       MultipartFile photo,
                       RedirectAttributes redirectAttributes) {

        System.out.println("UserName---" + user.getUserName() + "---UserPassword---" + user.getPassword());
        if(!photo.isEmpty()) {
            System.out.println("文件名称" + photo.getOriginalFilename());
            System.out.println("文件大小" + photo.getSize());
            System.out.println("MIMEType" + photo.getContentType());

            try {

                IOUtils.copy(photo.getInputStream(), new FileOutputStream("G:/upload/up" +photo.getOriginalFilename()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        redirectAttributes.addFlashAttribute("message", "正在审核");
        return "redirect:/form";

    }
}
