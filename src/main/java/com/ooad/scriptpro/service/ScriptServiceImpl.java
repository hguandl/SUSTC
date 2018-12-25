package com.ooad.scriptpro.service;

import com.ooad.scriptpro.api.ScriptRepository;
import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
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

    @Override
    public List<User> getAuthors(Script script) {
        return new ArrayList<>(script.getUsers());
    }

    @Override
    public List<Script> getTopFivePopular() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Script> scripts = scriptRepository.findAllByOrderByPopularPointsDesc(pageable);
        return scripts;

    }

    @Override
    public List<Script> getTopFiveLatest() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Script> scripts = scriptRepository.findAllByOrderByUpdateTimeDesc(pageable);
        return scripts;
    }

    @Override
    public String getScriptContentById(int id) throws IOException, SQLException {
        Script script = findById(id);
        BufferedReader br = new BufferedReader(script.getContent().getCharacterStream());
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        br.close();
        return sb.toString();
    }
}
