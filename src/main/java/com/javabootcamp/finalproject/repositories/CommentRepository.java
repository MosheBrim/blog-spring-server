package com.javabootcamp.finalproject.repositories;

import com.javabootcamp.finalproject.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.postId = :postId")
    List<Comment> findAllByPostId(int postId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Comment c WHERE c.postId = :postId", nativeQuery = false)
    void deleteAllByPostId(int postId);
}
