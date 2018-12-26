package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.User;
import com.ooad.scriptpro.service.ScriptService;
import com.ooad.scriptpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {
    @Autowired
    UserService userService;
    @Autowired
    ScriptService scriptService;

    @GetMapping(value = "/userHome")
    public String userHomeController(Model model,
                                     HttpSession httpSession){
        User user = (User)httpSession.getAttribute("user");
        System.out.println("call userHomeController, username:"+user.getUsername());
        List<Script> topScripts = scriptService.getTopFiveLatest();
        List<Script> recentScripts = scriptService.getTopFiveLatest();
//        for (Script script:topScripts)
//            System.out.println(script.getDescription());

        model.addAttribute("user",user);
        model.addAttribute("topScripts",topScripts);
        model.addAttribute("recentScripts",recentScripts);
        return "/userHome";
    }
}
