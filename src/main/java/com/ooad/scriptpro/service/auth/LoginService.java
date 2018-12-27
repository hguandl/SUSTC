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
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
        User db_user = userRepository.findUserByUsername(user.getUsername());
        String token = db_user.getPassword();
        String password = user.getPassword();
        return passwordAuthentication.authenticate(password.toCharArray(), token);
    }

}
