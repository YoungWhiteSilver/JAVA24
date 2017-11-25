package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.service.WebService;
import com.kaishengit.utils.AjaxResult;
import com.kaishengit.utils.Config;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 *
 * @author silver
 * @date 2017/11/7
 */
@Controller
public class LoginController {

    @Autowired
    private WebService webService;

    @GetMapping("/")
    public String login() {

        return "login";

    }

    @PostMapping("/")
    @ResponseBody
    public AjaxResult login(String moblie, String password,
                            HttpServletRequest request,
                            HttpSession session) {

        //Map<String, Object> map = webService.login(moblie, password);
        //session.setAttribute("curr_account", map.get("data"));
        try{

            String salt = Config.get("user.password.salt");
            String saltPassword = DigestUtils.md5Hex(password + salt);

            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(moblie, new Md5Hash(salt, password).toString());

            subject.login(usernamePasswordToken);

            Account account = (Account) subject.getPrincipal();
            session.setAttribute("curr_account", account);

            String url = "/home";
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);

            if(savedRequest != null) {
                url = savedRequest.getRequestUrl();
            }

            return AjaxResult.success(url);

        } catch (AuthenticationException e) {
            e.printStackTrace();
            return AjaxResult.error("账号或密码错误");
        }


    }

    @GetMapping("/logout")
    public String loginOut( RedirectAttributes redirectAttributes) {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }

}
