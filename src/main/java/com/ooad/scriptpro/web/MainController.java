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
    @GetMapping("/edit.html")
    public String editControl(){
        return "edit";
    }
}
