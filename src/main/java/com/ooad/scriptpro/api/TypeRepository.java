package com.ooad.scriptpro.api;

import com.ooad.scriptpro.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findTypeByName(String name);
}
