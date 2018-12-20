package com.ooad.scriptpro.service.auth;

import com.ooad.scriptpro.api.UserRepository;
import com.ooad.scriptpro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public boolean verifyLogin(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null;
    }

}
