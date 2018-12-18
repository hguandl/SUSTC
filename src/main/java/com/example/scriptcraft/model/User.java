package com.example.scriptcraft.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="users")
public class User {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private  String nickname;

    @Column(name="mail")
    private String mail;

    @Column(name="phone")
    private String phone;

    @Column(name="company")
    private String company;
}
