package com.example.springbootshiro.controller;

import com.example.springbootshiro.domain.TUser;
import com.example.springbootshiro.service.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/user/update")
    @RequiresPermissions("user:update")
    public String update(){
        return "user/update";
    }

    @GetMapping("/user/add")
    @RequiresPermissions("user:add")
    public String add(){
        return "user/add";
    }

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String name, String password, Model model,String code,HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //try-catch中代码将该类与UserRealm相连接
        try {
            if(((String)session.getAttribute("code")).equalsIgnoreCase(code)){
                subject.login(token);
            }else {
                model.addAttribute("msg","验证码错误");
                return null;
            }
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户不存在！");
            return "login";
            //e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误！");
            return "login";
        }
        return "redirect:/index";
    }
    @GetMapping("/unauthorized")
    @ResponseBody
    public String unauthorized(){
        return "未授权！";
        }

    @GetMapping("user/delete")
    @RequiresPermissions("user:delete")
    @ResponseBody
    public String delete(){
        return "删除";
    }

    @GetMapping("user/find")
    @RequiresPermissions("user:find")
    @ResponseBody
    public String find(){
        return "查询";
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }
    @PostMapping("/register")
    public String register(TUser user){
        TUser register = userService.register(user);
            return "/index";
    }

    @GetMapping("/checkcode")
    public void checkCode(HttpServletResponse response, HttpSession session) throws IOException {
        DefaultKaptcha dk = userService.getDk();
        String text = dk.createText();
        session.setAttribute("code",text);
        BufferedImage image = dk.createImage(text);
        ImageIO.write(image,"jpg",response.getOutputStream());
    }
}
