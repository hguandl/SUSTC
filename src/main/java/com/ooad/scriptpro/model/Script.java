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
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category")
    private Type type;

    @Column(name="author")
    private String author;

    @Column(name="description")
    private String description;

    @Column(name="popular_points")
    private int popular_points;

    @Column(name="path")
    private int path;

    @ManyToMany(mappedBy = "scripts")
    private Set<User> users = new HashSet<>();

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
