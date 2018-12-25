package com.ooad.scriptpro.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;

@Getter
@Setter

public class ScriptForm {
    private String name;
    private String type;
    private String description;
    private MultipartFile file;
}
