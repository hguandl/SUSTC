package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.ooad.scriptpro.model.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@RestController
public class TestController {
    @Autowired
    ScriptService scriptService;
    @PostMapping("/findusers")
    public void getUsers(@RequestParam String scriptName){
        Script script = scriptService.findByScriptName(scriptName);
        ArrayList<User> users = new ArrayList<>(script.getUsers());
        System.out.println(users.get(0).getUsername());
    }

    @GetMapping("/topPopular")
    public List<Script> getTopPopular(){
        return scriptService.getTopFivePopular();
    }

    @GetMapping("/topLatest")
    public List<Script> getTopLatest(){
        return scriptService.getTopFiveLatest();
    }
}
