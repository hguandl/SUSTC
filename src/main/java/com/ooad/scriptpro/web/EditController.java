package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.ScriptFormText;
import com.ooad.scriptpro.model.User;
import com.ooad.scriptpro.service.ScriptService;
import com.ooad.scriptpro.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

@Controller
public class EditController {
    @Autowired
    ScriptService scriptService;

    @Autowired
    TypeService typeService;

    @GetMapping(value = "/edit")
    public String scriptController(@ModelAttribute(value="scriptFormText") ScriptFormText scriptFormText,
                                   Model model,
                                   HttpSession session,
                                   @RequestParam long sid){
        User user = (User)session.getAttribute("user");
        Script currentScript = scriptService.findById(sid);
        model.addAttribute("sid",sid);
        model.addAttribute("user",user);
        try {
            String s = scriptService.getScriptContentById((int)sid);
            model.addAttribute("currentContent",s);
            System.out.println("=============");
            System.out.println(s);
            System.out.println("=============");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("currentScript",currentScript);
        System.out.println(currentScript.getName());
        System.out.println(currentScript.getDescription());
        return "edit";
    }
}
