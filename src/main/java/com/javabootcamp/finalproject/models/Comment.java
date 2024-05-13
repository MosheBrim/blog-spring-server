package com.javabootcamp.finalproject.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "post_id")
    private int postId;

    private String name;

    private String data;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Comment() {
        this.createdAt = LocalDateTime.now();

    }

    public Comment( int postId, String name,String data) {
        this.name = name;
        this.data = data;
        this.postId = postId;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

