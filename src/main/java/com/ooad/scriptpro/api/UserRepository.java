package com.ooad.scriptpro.api;

import com.ooad.scriptpro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
