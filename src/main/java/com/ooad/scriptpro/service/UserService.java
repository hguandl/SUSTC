package com.ooad.scriptpro.service;
import com.ooad.scriptpro.model.User;

public interface UserService {
    User getUserByName(String username);
    User getUserById(Long id);

    void addUser(User user);

    void deleteUserByID(Long id);
    void deleteUserByUsername(String username);

    void updateUser(User user);

}
