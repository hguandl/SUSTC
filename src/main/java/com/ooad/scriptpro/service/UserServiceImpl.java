package com.ooad.scriptpro.service;

import com.ooad.scriptpro.api.UserRepository;
import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserByID(long id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public void deleteUserByUsername(String username) {
        userRepository.deleteUserByUsername(username);
    }



    @Override
    public Set<Script> getUserScripts(User user) {
        return user.getScripts();
    }
}
