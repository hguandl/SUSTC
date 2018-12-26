package com.ooad.scriptpro.web;

import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.ScriptForm;
import com.ooad.scriptpro.model.User;
import com.ooad.scriptpro.service.FileService;
import com.ooad.scriptpro.service.ScriptService;
import com.ooad.scriptpro.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Clob;

@RestController
public class ScriptController {
    @Autowired
    ScriptService scriptService;

    @Autowired
    TypeService typeService;

    @Autowired
    FileService fileService;
    /*
    @GetMapping("/createScript")
    public String createScriptForm(Model model){
        model.addAttribute("scriptForm", new ScriptForm());
        return "edit";
    }*/

    @PostMapping("/createScript")
    public String SubmitScript(@ModelAttribute(value = "scriptForm") ScriptForm scriptForm,
                               HttpSession session) {
        Script script = new Script();
        script.setName(scriptForm.getName());
        script.setType(typeService.findServiceByName(scriptForm.getType()));
        script.setDescription(scriptForm.getDescription());
        String scriptContent = null;
        try{
            scriptContent = fileService.upload(scriptForm.getFile());
        }catch (Exception e){
            return "upload fail";
        }
        if(scriptContent != null){
            try{
                Clob clob = new javax.sql.rowset.serial.SerialClob(scriptContent.toCharArray());
                script.setContent(clob);
                User user = (User)session.getAttribute("user");
                script.setAuthor(user.getUsername());
                scriptService.save(script);
                return "success";
            }catch (Exception e){
                return "string to clob fail";
            }


        }else{
            return "upload fail";
        }
    }
}
