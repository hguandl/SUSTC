package com.ooad.scriptpro.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="company")
    private String company;
    @Column(name="mail")
    private String mail;
    @Column(name="nickname")
    private String nickname;
    @Column(name="phone")
    private String phone;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="script_user",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="script_id")
    )
    private Set<Script> scripts = new HashSet<>();

    public void addScript(Script script){
        scripts.add(script);
        script.getUsers().add(this);
    }

    public void removeScript(Script script){
        scripts.remove(script);
        script.getUsers().remove(this);
    }

}
