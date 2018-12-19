package com.ooad.scriptpro.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ScriptUserPK implements Serializable{
    private int user_id;
    private int script_id;
}
