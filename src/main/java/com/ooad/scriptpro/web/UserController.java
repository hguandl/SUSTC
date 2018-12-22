package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.User;
import com.ooad.scriptpro.service.WebSecurityConfig;
import com.ooad.scriptpro.service.auth.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account, Model model){
        return "/index";
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String login(@RequestParam String username,@RequestParam String password){
        System.out.println("call log in");
        System.out.println(username+"  "+password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        System.out.println("username: "+user.getUsername()+"  password: "+user.getPassword());
        boolean verify = loginService.verifyLogin(user);
        if(verify) {
            System.out.println("success");

            return "/index";
        } else{
            System.out.println("fail");
            return "/welcome";
        }

    }
    @GetMapping(value = {"/signin","/signin.html"})
    public String signinControl(){
        return "login";
    }

    @GetMapping(value={"/signup","/signup.html"})
    public String signupControl(){
        return "signup";
    }
}
