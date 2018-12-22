package com.ooad.scriptpro.service;

import com.ooad.scriptpro.api.ScriptRepository;
import com.ooad.scriptpro.model.Script;
import org.springframework.beans.factory.annotation.Autowired;

public interface ScriptService {
    Script findById(long id);
    Script findByScriptName(String scriptName);

    void save(Script script);

    void deleteScript(Script script);
    void deleteById(long id);
    void deleteByScriptName(String scriptName);

}
