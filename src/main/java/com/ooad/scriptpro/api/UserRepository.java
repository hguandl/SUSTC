package com.ooad.scriptpro.api;

import com.ooad.scriptpro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsernameAndPassword(String username, String password);
        User findUserByUsername(String username);
        User findUserById(long id);
        void deleteUserById(long id);
        void deleteUserByUsername(String username);

}
