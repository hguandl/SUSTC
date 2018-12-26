package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class EditController {
    @Autowired
    ScriptService scriptService;

    @GetMapping(value = "/edit")
    public String scriptController(Model model,
                                   HttpSession httpSession,
                                   @RequestParam long sid){
//        System.out.println(sid);
        Script s = scriptService.findById(sid);

        model.addAttribute("script", s);
        return "/info";
    }
}
