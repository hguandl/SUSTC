package com.ooad.scriptpro.web.utils;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.service.docker.config.ScriptLang;

public class TypeAdapter {
    public ScriptLang toScriptLang(String typeName){

        switch (typeName){
            case "javascript":
                return ScriptLang.JAVASCRIPT;
            case "python":
                return ScriptLang.PYTHON;
            case "ruby":
                return ScriptLang.RUBY;
            case "go":
                return ScriptLang.GO;
            case "php":
                return ScriptLang.PHP;
        }
        return ScriptLang.PHP;
    }
}
