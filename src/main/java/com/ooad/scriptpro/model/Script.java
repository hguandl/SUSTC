package com.ooad.scriptpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mysql.jdbc.Clob;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.BlobType;
import org.hibernate.type.ClobType;

import javax.persistence.*;
import java.util.Date;
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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category", nullable = false)
    private Type type;

    @Column(name="author")
    private String author;

    @Column(name="description")
    private String description;

    @Column(name="popular_points", nullable = false, columnDefinition = "INT default 0")
    private int popularPoints;

    @Column(name="path")
    private int path;

    @Column(name="content")
    private java.sql.Clob content;

    @CreationTimestamp
    @Column(name="create_time", updatable = false)
    private Date createTime;

    @UpdateTimestamp
    @Column(name="update_time")
    private Date updateTime;

    @JsonBackReference
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
