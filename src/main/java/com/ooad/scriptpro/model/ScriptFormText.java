package com.ooad.scriptpro.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ScriptFormText {
    private String name;
    private String type;
    private String description;
    private String file;
}
