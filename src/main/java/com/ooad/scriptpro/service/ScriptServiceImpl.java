package com.ooad.scriptpro.service;

import com.ooad.scriptpro.api.ScriptRepository;
import com.ooad.scriptpro.model.Script;
import org.springframework.beans.factory.annotation.Autowired;

public class ScriptServiceImpl implements ScriptService {
    @Autowired
    ScriptRepository scriptRepository;

    @Override
    public Script findById(long id) {
        return scriptRepository.findScriptById(id);
    }

    @Override
    public Script findByScriptName(String scriptName) {
        return scriptRepository.findScriptByName(scriptName);
    }

    @Override
    public void save(Script script) {
        scriptRepository.save(script);
    }

    @Override
    public void deleteScript(Script script) {
        scriptRepository.delete(script);
    }

    @Override
    public void deleteById(long id) {
        scriptRepository.deleteById(id);
    }

    @Override
    public void deleteByScriptName(String scriptName) {
        scriptRepository.deleteScriptByName(scriptName);
    }

}
