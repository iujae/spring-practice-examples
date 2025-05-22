package com.example.demo3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    // 기본 생성자
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter, Setter ...
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}