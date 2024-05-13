package com.javabootcamp.finalproject.controllers;

import com.javabootcamp.finalproject.models.Comment;
import com.javabootcamp.finalproject.repositories.CommentRepository;
import com.javabootcamp.finalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    CommentRepository commentRepo;
    @Autowired
    UserRepository userRepo;

    @GetMapping("admin/{user}/comments")
    public ResponseEntity<Object> getAllComments(@PathVariable String user) {
        try {
            if (userRepo.findById(user).isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(commentRepo.findAll());
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("posts/{id}/comments")
    public ResponseEntity<Object> getAllCommentsByPostId(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(commentRepo.findAllByPostId(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("comments/{id}")
    public ResponseEntity<Object> getComment(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(commentRepo.findById(id).get());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("comments")
    public ResponseEntity<Object> createComment(@RequestBody Comment newComment){
        System.out.println("enter");
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(commentRepo.save(newComment));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("admin/comments/{user}/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable String user, @PathVariable int id, @RequestBody Comment updatedComment){
        try {
            if (userRepo.findById(user).isPresent()) {
                Comment originalComment = commentRepo.findById(id).get();
                originalComment.setName(updatedComment.getName());
                originalComment.setData(updatedComment.getData());
                return ResponseEntity.status(HttpStatus.OK).body(commentRepo.save(originalComment));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("admin/comments/{user}/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable String user, @PathVariable int id){
        try {
            if (userRepo.findById(user).isPresent()) {
                commentRepo.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Comment " + id + " was successfully deleted");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
