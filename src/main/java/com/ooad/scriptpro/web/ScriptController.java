package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.*;
import com.ooad.scriptpro.service.FileService;
import com.ooad.scriptpro.service.ScriptService;
import com.ooad.scriptpro.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Clob;

@Controller
public class ScriptController {
    @Autowired
    ScriptService scriptService;

    @Autowired
    TypeService typeService;

    @Autowired
    FileService fileService;

    @PostMapping("/createScript")
    public String SubmitScript(@ModelAttribute(value = "scriptForm") ScriptForm scriptForm,
                               HttpSession session) {
        Script script = new Script();
        script.setName(scriptForm.getName());
        script.setType(typeService.findServiceByName(scriptForm.getType()));
        script.setDescription(scriptForm.getDescription());
        String scriptContent;
        try{
            scriptContent = fileService.upload(scriptForm.getFile());
        }catch (Exception e){
            //return "upload fail";
            return "redirect:/myscripts";
        }
        if(scriptContent != null){
            try{
                Clob clob = new javax.sql.rowset.serial.SerialClob(scriptContent.toCharArray());
                script.setContent(clob);
                User user = (User)session.getAttribute("user");
                script.setAuthor(user.getUsername());
                scriptService.save(script);
                return "redirect:/myscripts";
            }catch (Exception e){
                //return "Error! String to clob fail!";
                return "redirect:/myscripts";
            }


        }else{
            //return "Error! Fail to upload!";
            return "redirect:/myscripts";
        }
    }
}

@RestController
class ScriptApi {
    @Autowired
    ScriptService scriptService;

    @PostMapping("/thumbUp/{id}")
    public String thumbUp(@PathVariable int id) {
        Script script = scriptService.findById(id);
        script.setPopularPoints(script.getPopularPoints() + 1);
        scriptService.save(script);
        return "Thanks for your support!";
    }

    @PostMapping("/runScript/{id}/{type}")
    public String runScript(@PathVariable int id, @PathVariable(name = "type") String typeName,
                            @RequestParam String args) {

        System.out.println(id);
        System.out.println(typeName);
        System.out.println(args);
        String res = scriptService.run(typeName, id, args);
        Script script = scriptService.findById(id);
        script.setRunTime(script.getRunTime() + 1);
        System.out.println("output:" + res);
        scriptService.save(script);
        return res;
    }

    @Autowired
    TypeService typeService;

    @PostMapping("/updateScriptText")
    public String SubmitScriptText(@RequestParam long sid,
                                   @RequestParam String name,
                                   @RequestParam String type,
                                   @RequestParam String description,
                                   @RequestParam String file) {
        System.out.println("call update script controller "+sid);

        Script script = scriptService.findById(sid);
        script.setName(name);
        script.setDescription(description);
        script.setType(typeService.findServiceByName(type));
        String scriptContent = file;
        if(scriptContent == null){
            System.out.println("null");
        }
        try{
            Clob clob = new javax.sql.rowset.serial.SerialClob(scriptContent.toCharArray());
            script.setContent(clob);
            script.setAuthor(scriptService.findById(sid).getAuthor());
            scriptService.save(script);
            return "Update successfully";
        }catch (Exception e){
            return "Failed";
        }
    }

    @PostMapping("/createScriptText")
    public String SubmitScriptText( @RequestParam String name,
                                    @RequestParam String type,
                                    @RequestParam String description,
                                    @RequestParam String file,
                                    HttpSession session) {
        System.out.println("call upload script controller");
        Script script = new Script();
        System.out.println(name);
        script.setName(name);

        script.setDescription(description);

        System.out.println("type:"+ type);
        script.setType(typeService.findServiceByName(type));
        String scriptContent = file;
        if(scriptContent == null){
            System.out.println("null");
        }
        try{
            Clob clob = new javax.sql.rowset.serial.SerialClob(scriptContent.toCharArray());
            script.setContent(clob);
            User user = (User)session.getAttribute("user");
            script.setAuthor(user.getUsername());
            scriptService.save(script);
            return "save success";
        }catch (Exception e){
            return "failed";
        }
    }
}
