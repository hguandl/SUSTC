package com.ooad.scriptpro.service.auth;

import com.ooad.scriptpro.api.UserRepository;
import com.ooad.scriptpro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class SignupService {
    @Autowired
    UserRepository userRepository;

    private String message;

    public boolean signupUser(User user){
        try{
            User _user = userRepository.findUserByUsername(user.getUsername());
            if(_user == null){
                PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
                String originalPassword = user.getPassword();
                String hashedPassword = passwordAuthentication.hash(originalPassword.toCharArray());
                user.setPassword(hashedPassword);
                userRepository.save(user);
                setMessage("sign up success");
                return true;

            }
            else{
                setMessage("please use another username");
                return false;
            }

        }catch (Exception e){
            setMessage("unknown error");
            return false;
        }
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
