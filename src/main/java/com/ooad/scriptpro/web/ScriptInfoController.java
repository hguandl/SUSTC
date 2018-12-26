package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.ScriptRun;
import com.ooad.scriptpro.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class ScriptInfoController {
    @Autowired
    ScriptService scriptService;

    @GetMapping(value = "/info")
    @ModelAttribute
    public String scriptController(Model model,
                                   HttpSession httpSession,
                                   @RequestParam long sid){
//        System.out.println(sid);
        Script s = scriptService.findById(sid);
        ScriptRun scriptRun = new ScriptRun();

        model.addAttribute("script", s);
        model.addAttribute("scriptRun", scriptRun);
        model.addAttribute("result", "");
        return "/info";
    }
}
