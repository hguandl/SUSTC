package com.ooad.scriptpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Generated;
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
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="company")
    private String company;
    @Column(name="mail")
    private String mail;

    @Column(name="username", unique = true)
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="phone")
    private String phone;

    @JsonBackReference
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
