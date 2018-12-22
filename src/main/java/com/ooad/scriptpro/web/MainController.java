package com.ooad.scriptpro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("")
    public String welcomeControl(){
        return "index";
    }
    @GetMapping("/index")
    public String indexControl(){
        return "userHome";
    }
    @GetMapping(value = {"/edit","/edit.html"})
    public String editControl(){
        return "edit";
    }
    @GetMapping(value = {"/myscripts","/myscripts.html"})
    public String myscriptsControl(){
        return "myscripts";
    }
    @GetMapping(value = {"/search","/search.html"})
    public String searchControl(){
        return "search";
    }

    @GetMapping(value = {"/welcome","/welcome.html"})
    public String infoControl(){
        return "index";
    }
}
