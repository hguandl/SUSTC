package com.ooad.scriptpro.api;

import com.ooad.scriptpro.model.Script;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScriptRepository extends JpaRepository<Script, Long>{
    Script findScriptById(long id);
    Script findScriptByName(String scriptName);
    void deleteScriptByName(String name);
}
