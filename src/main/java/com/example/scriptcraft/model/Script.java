package com.example.scriptcraft.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name="script")
public class Script {
    @Id
    @Column(name="id")
    private long id;
    @Column(name="name")
    private String name;

}
