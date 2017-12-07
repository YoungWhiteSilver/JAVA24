package com.kaishengit.springboot.controller;

import com.kaishengit.springboot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/7
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(HttpSession session,Model model) {

        model.addAttribute("user", new User(1, "里斯", "cadaca"));

        List<User> userList = new ArrayList<>();

        userList.add(new User(2, "tom", "USA"));
        userList.add(new User(3, "luxka", "USB"));
        userList.add(new User(4, "jack", "USE"));
        session.setAttribute("msg", "hello,session");
        model.addAttribute("userList", userList);

        return "hello";

    }

    @GetMapping("/model")
    public String toIndex() {

        return "index";

    }

}
