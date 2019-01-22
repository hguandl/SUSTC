package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.*;
import com.ooad.scriptpro.service.FileService;
import com.ooad.scriptpro.service.ScriptService;
import com.ooad.scriptpro.service.TypeService;
import com.ooad.scriptpro.service.docker.ContainerRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ScriptController {
    private final ScriptService scriptService;

    private final TypeService typeService;

    private final FileService fileService;

    @Autowired
    public ScriptController(ScriptService scriptService, TypeService typeService, FileService fileService) {
        this.scriptService = scriptService;
        this.typeService = typeService;
        this.fileService = fileService;
    }

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
                script.setContent(scriptContent);
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
    private final ScriptService scriptService;
    private final TypeService typeService;

    @Value("${sustc.docker.api-prefix}")
    String apiPrefix;

    @Autowired
    public ScriptApi(ScriptService scriptService, TypeService typeService) {
        this.scriptService = scriptService;
        this.typeService = typeService;
    }

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
        ContainerRun res = scriptService.run(apiPrefix, typeName, id, args);
        Script script = scriptService.findById(id);
        script.setRunTime(script.getRunTime() + 1);
//        System.out.println("output:" + res.getOutput());
        scriptService.save(script);
        return res.getOutput();
    }


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
        if(file == null){
            System.out.println("null");
        }
        try{
            script.setContent(file);
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
        if(file == null){
            System.out.println("null");
        }
        try{
            script.setContent(file);
            User user = (User)session.getAttribute("user");
            script.setAuthor(user.getUsername());
            scriptService.save(script);
            return "save success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }
}
