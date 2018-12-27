package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.User;
import com.ooad.scriptpro.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    ScriptService scriptService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String scriptController(Model model,
                                   HttpSession session,
                                   @RequestParam String str){
        User user = (User)session.getAttribute("user");
        List<Script> scriptList = scriptService.vagueSearch(str);
        model.addAttribute("scriptList", scriptList);
        model.addAttribute("user",user);
        return "/search";
    }
}
