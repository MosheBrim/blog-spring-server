package com.javabootcamp.finalproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.javabootcamp.finalproject.models.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.published = true")
    List<Post> findAllPublishedPosts();

}
