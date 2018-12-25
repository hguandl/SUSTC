package com.ooad.scriptpro.api;

import com.ooad.scriptpro.model.Script;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script, Long>{
    Script findScriptById(long id);
    Script findScriptByName(String scriptName);
    void deleteScriptByName(String name);
    List<Script> findAllByOrderByPopularPointsDesc(Pageable pageable);
    List<Script> findAllByOrderByUpdateTimeDesc(Pageable pageable);
}
