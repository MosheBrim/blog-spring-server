package com.javabootcamp.finalproject.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String author;

    private String title;

    private String description;

    private String data;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private boolean published;

    private String image;

    public Post() {
        this.createdAt = LocalDateTime.now();
    }

    public Post(String author, String title, String description, String data, boolean published, String image) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.data = data;
        this.createdAt = LocalDateTime.now();
        this.published = published;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getData() {
        return data;
    }

    public String getImage() {
        return image;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
