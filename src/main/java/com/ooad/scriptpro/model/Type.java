package com.ooad.scriptpro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="types")
public class Type {
    @Id
    @Column(name="type_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long type_id;

    @Column(name="name")
    private String name;

    @JsonBackReference
    @OneToMany(
            mappedBy = "type",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Script> script_list = new ArrayList<>();

    public void addScript(Script script){
        script_list.add(script);
        script.setType(this);
    }
    public void removeScript(Script script){
        script_list.remove(script);
        script.setType(null);
    }
}
