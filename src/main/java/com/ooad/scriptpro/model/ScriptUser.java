package com.ooad.scriptpro.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(ScriptUserPK.class)
@Table(name="script_user")
public class ScriptUser {
    @Id
    @Column(name="user_id")
    private int user_id;

    @Id
    @Column(name="script_id")
    private int script_id;

}
