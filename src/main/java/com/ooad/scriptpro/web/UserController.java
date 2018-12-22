package com.ooad.scriptpro.web;

import com.ooad.scriptpro.model.User;
import com.ooad.scriptpro.service.WebSecurityConfig;
import com.ooad.scriptpro.service.auth.LoginService;
import javassist.tools.web.Webserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private LoginService loginService;
    /*
    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account){

        return "index";
    }
    */

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, HttpSession httpSession){
        System.out.println("call log in");
        System.out.println(username+"  "+password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        System.out.println("username: "+user.getUsername()+"  password: "+user.getPassword());
        boolean verify = loginService.verifyLogin(user);
        if(verify) {
            System.out.println("success");
            httpSession.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            return "/userHome";
        } else{
            System.out.println("fail");
            return "/index";
        }

    }

    @GetMapping(value = {"/login","/login.html"})
    public String signinControl(){
        return "login";
    }

    @GetMapping(value={"/signup","/signup.html"})
    public String signupControl(){
        return "signup";
    }
    @GetMapping(value = {"/logout","/logout.html"})
    public String logout(HttpSession session){
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }
}
