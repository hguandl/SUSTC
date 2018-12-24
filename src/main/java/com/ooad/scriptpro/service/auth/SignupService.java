package com.ooad.scriptpro.service.auth;

import com.ooad.scriptpro.api.UserRepository;
import com.ooad.scriptpro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    UserRepository userRepository;

    public boolean signupUser(User user){
        try{
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
