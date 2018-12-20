package com.ooad.scriptpro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/index")
    public String indexControl(){
        System.out.println("call main controller");
        return "index";
    }
    @GetMapping("/edit")
    public String editControl(){
        return "edit";
    }
    @GetMapping("/myscripts")
    public String myscriptsControl(){
        return "myscripts";
    }
    @GetMapping("/search")
    public String searchControl(){
        return "search";
    }
    @GetMapping("/signin")
    public String signinControl(){
        return "signin";
    }
    @GetMapping("/signup")
    public String signupControl(){
        return "signup";
    }
    @GetMapping("/welcome")
    public String infoControl(){
        return "welcome";
    }
}
