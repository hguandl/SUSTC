package com.ooad.scriptpro.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="script")
public class Script {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category", nullable = false)
    private Type type;

    @Column(name="author")
    private String author;

    @Column(name="description")
    private String description;

    @Column(name="popular_points", nullable = false, columnDefinition = "INT default 0")
    private int popular_points;

    @Column(name="path")
    private int path;

    @ManyToMany(mappedBy = "scripts")
    private Set<User> users = new HashSet<>();

    public void addUser(User user){
        users.add(user);
        user.getScripts().add(this);
    }

    public void remove(User user){
        users.remove(user);
        user.getScripts().remove(this);
    }
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Script))
            return false;
        return id!=null && id.equals((Script)o);
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<User> getUsers() {
        return users;
    }
}
