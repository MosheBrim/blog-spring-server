package com.javabootcamp.finalproject.models;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    private String name;

    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
