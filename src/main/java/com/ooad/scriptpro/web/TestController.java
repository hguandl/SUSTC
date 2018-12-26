package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.Type;
import com.ooad.scriptpro.service.FileService;
import com.ooad.scriptpro.service.ScriptService;
import com.ooad.scriptpro.service.docker.Container;
import com.ooad.scriptpro.service.docker.DockerLog;
import com.ooad.scriptpro.service.docker.config.ScriptLang;
import com.ooad.scriptpro.web.utils.TypeAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.ooad.scriptpro.model.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@RestController
public class TestController {
    @Autowired
    ScriptService scriptService;

    @Autowired
    FileService fileService;

    @PostMapping("/findusers")
    public void getUsers(@RequestParam String scriptName){
        Script script = scriptService.findByScriptName(scriptName);
        ArrayList<User> users = new ArrayList<>(script.getUsers());
        System.out.println(users.get(0).getUsername());
    }

    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file){
        try {
            return fileService.upload(file);
        }catch (Exception e){
            return "";
        }
    }
    @GetMapping("/topPopular")
    public List<Script> getTopPopular(){
        return scriptService.getTopFivePopular();
    }

    @GetMapping("/topLatest")
    public List<Script> getTopLatest(){
        return scriptService.getTopFiveLatest();
    }

    @PostMapping("/run")
    public String run(@RequestParam(value = "type")String typename, @RequestParam(value="id") int id){
        TypeAdapter typeAdapter = new TypeAdapter();
        ScriptLang scriptLang = typeAdapter.toScriptLang(typename);
        String args[] = {};
        try{
            Container container = new Container(scriptLang, id, args);
            container.execCreateContainer();
            int ret = container.execRunContainer();

            if (ret == 0) {
				return container.getOutput();
            } else {
                return container.getOutput();
            }
        }catch (Exception e){
            e.printStackTrace();
            return "err";
        }

    }

    @GetMapping("/getScript")
    public String getScript(@RequestParam(value = "id") long id){
        Script script = scriptService.findById(id);
        if(script == null){
            return "null";
        }else{
            return script.getName();
        }
    }

    @GetMapping("/vagueSearch")
    public List<Script> vagueSearch(@RequestParam(value="str") String str){
        return scriptService.vagueSearch(str);
    }

    @GetMapping("/getContent")
    public String getContent(@RequestParam(value = "id") int id){
        try{
            return scriptService.getScriptContentById(id);
        }catch (Exception e){
            return "";
        }

    }
}
