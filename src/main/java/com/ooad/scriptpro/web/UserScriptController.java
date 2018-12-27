package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.User;
import com.ooad.scriptpro.service.ScriptService;
import com.ooad.scriptpro.service.UserService;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserScriptController {
    @Autowired
    ScriptService scriptService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/myscripts")
    public String scriptController(Model model,
                                   HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        List<Script> userScripts = userService.getUserScripts(user);
        System.out.println("Read user scripts");
        model.addAttribute("userScripts", userScripts);
        model.addAttribute("user",user);
        return "myscripts";
    }

    @GetMapping(value = "/myscripts/delete")
    public String deleteScriptController(Model model,
                                         HttpSession httpSession,
                                         @RequestParam long sid) {
        User user = (User) httpSession.getAttribute("user");
        List<Script> userScripts = userService.getUserScripts(user);
        for(Script s : userScripts){
            if(s.getId() == sid){
                scriptService.deleteById(sid);
                break;
            }
        }
        //model.addAttribute("userScripts", userScripts);
        //model.addAttribute("user",user);
        return "redirect:/myscripts";
    }
}
