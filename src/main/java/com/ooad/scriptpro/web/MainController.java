package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.ScriptForm;
import com.ooad.scriptpro.model.ScriptFormText;
import com.ooad.scriptpro.model.User;
import com.ooad.scriptpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String welcomeControl(){
        return "index";
    }

    @GetMapping(value = {"/createScript","/createScript.html"})
    public String editControl(Model model,
                              HttpSession session){
        User user = (User)session.getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("scriptForm", new ScriptForm());
        model.addAttribute("scriptFormText", new ScriptFormText());
        return "createScript";
    }
//    @GetMapping(value = {"/uploadscript","/uploadscript.html"})
//    public String uploadControl(){
//        return "uploadscript";
//    }

    @GetMapping(value = {"/search","/search.html"})
    public String searchControl(){
        return "search";
    }

    @GetMapping(value = {"/index","/welcome","/welcome.html"})
    public String infoControl(){
        return "index";
    }

//    @GetMapping(value = {"/info","/info.html"})
//    public String scriptDetailControl(){
//        return "info";
//    }
}
