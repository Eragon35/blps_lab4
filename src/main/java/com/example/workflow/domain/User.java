package com.example.workflow.domain;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="token_seq")
    @SequenceGenerator(name="token_seq", sequenceName="token_seq", allocationSize=1)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;


    public User() { }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
