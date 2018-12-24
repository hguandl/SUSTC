package com.ooad.scriptpro.service;
import com.ooad.scriptpro.api.ScriptRepository;
import com.ooad.scriptpro.api.TypeRepository;
import com.ooad.scriptpro.api.UserRepository;
import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.Type;
import com.ooad.scriptpro.model.User;
//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@Transactional
@RestController
public class DataService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    ScriptRepository scriptRepository;

    @GetMapping("/generateData")
    public void generateData(){
        addUser();
        addType();
        addScripts();
    }

    public void addUser(){
        String[] user_name = {"chenshijie", "gongyue", "guanhao", "zhouying", "jiangtiankai"};
        String[] password = {"666", "777", "888", "999", "555"};
        for(int i=0; i<user_name.length; i++){
            User user = new User();
            user.setUsername(user_name[i]);
            user.setPassword(password[i]);
            userRepository.save(user);
        }
    }
    public void addType(){
        String[] types = {"python", "javascript", "ruby", "go", "php"};
        for(String name: types){
            Type type = new Type();
            type.setName(name);
            typeRepository.save(type);
        }
    }
    public void addScripts(){
        String[] names = {"ocr", "course_helper", "choose course faster", "the secret of six", "calculator"};
        String[] types = {"python", "javascript", "ruby", "go", "php"};
        String[][] users = {{"chenshijie", "guanhao"}, {"gongyue", "zhouying"}, {"jiangtiankai"}, {"gongyue", "chenshijie"}, {"guanhao", "gongyue"}};
        for(int i=0; i<names.length; i++){
            Script script = new Script();
            script.setName(names[i]);
            Type type = typeRepository.findTypeByName(types[i]);
            script.setType(type);
            String[] script_users = users[i];
            for(String name: script_users){
                User user = userRepository.findUserByUsername(name);
                script.addUser(user);
            }
            scriptRepository.save(script);
        }
    }

}
