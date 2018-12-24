package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.User;
import com.ooad.scriptpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public String welcomeControl(){
        return "index";
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

    @GetMapping(value = {"/index","/welcome","/welcome.html"})
    public String infoControl(){
        return "index";
    }


}
