package com.example.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @GetMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String name, String password, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //try-catch中代码将该类与UserRealm相连接
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户不存在！");
            return "login";
            //e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误！");
            return "login";
        }
        return "index";
    }
    @GetMapping("/unauthorized")
    @ResponseBody
    public String unauthorized(){
        return "未授权！";
        }
}
