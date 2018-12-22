package com.ooad.scriptpro.service;
import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User getUserByName(String username);
    User getUserById(long id);

    void save(User user);

    void deleteUserByID(long id);
    void deleteUserByUsername(String username);

    Set<Script> getUserScripts(User user);
}
