package com.javabootcamp.finalproject.controllers;

import com.javabootcamp.finalproject.models.Post;
import com.javabootcamp.finalproject.repositories.CommentRepository;
import com.javabootcamp.finalproject.repositories.PostRepository;
import com.javabootcamp.finalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    PostRepository postRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    CommentRepository commentRepo;


    @GetMapping("posts")
    public ResponseEntity<Object> getAllPublishedPosts() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postRepo.findAllPublishedPosts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("admin/posts/{user}")
    public ResponseEntity<Object> getAllPosts(@PathVariable String user) {
        try {
            if (userRepo.findById(user).isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(postRepo.findAll());
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<Object> getPost(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postRepo.findById(id).get());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("admin/posts/{user}")
    public ResponseEntity<Object> createPost(@PathVariable String user, @RequestBody Post newPost){
        try {
            if (userRepo.findById(user).isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(postRepo.save(newPost));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("admin/posts/{user}/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable String user, @PathVariable int id, @RequestBody Post updatedPost){
        try {
            if (userRepo.findById(user).isPresent()) {
                Post originalPost = postRepo.findById(id).get();
                originalPost.setTitle(updatedPost.getTitle());
                originalPost.setDescription(updatedPost.getDescription());
                originalPost.setData(updatedPost.getData());
                originalPost.setPublished(updatedPost.isPublished());
                return ResponseEntity.status(HttpStatus.OK).body(postRepo.save(originalPost));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("admin/posts/publish/{user}/{id}")
    public ResponseEntity<Object> postStatusUpdate(@PathVariable String user, @PathVariable int id){
        try {
            System.out.println("enter");
            if (userRepo.findById(user).isPresent()) {
                Post post = postRepo.findById(id).get();
                post.setPublished(!post.isPublished());
                return ResponseEntity.status(HttpStatus.OK).body(postRepo.save(post));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("admin/posts/{user}/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable String user, @PathVariable int id){
        try {
            if (userRepo.findById(user).isPresent()) {
                postRepo.deleteById(id);
                commentRepo.deleteAllByPostId(id);
                return ResponseEntity.status(HttpStatus.OK).body("Post " + id + " was successfully deleted");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping("/**")
    public ResponseEntity<Object> nonExistentMapping(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested path does not exist");
    }


}
