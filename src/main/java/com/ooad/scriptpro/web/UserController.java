package com.ooad.scriptpro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String login(@RequestParam String username,@RequestParam String password){
        System.out.println("call log in");
        System.out.println(username+"  "+password);

        if(password.equals("666")) {
            return "/index";
        } else{
            return "/welcome";
        }

    }
    @GetMapping(value = {"/signin","/signin.html"})
    public String signinControl(){
        return "signin";
    }
    @GetMapping(value={"/signup","/signup.html"})
    public String signupControl(){
        return "signup";
    }
}
