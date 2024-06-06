package com.javabootcamp.finalproject.controllers;

import com.javabootcamp.finalproject.models.Post;
import com.javabootcamp.finalproject.models.User;
import com.javabootcamp.finalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("users/single/{user}")
    public ResponseEntity<Object> getUser(@PathVariable String user, @RequestParam String password) {
        try {
            if (userRepo.findById(user).isPresent()) {
                User singleUser = userRepo.findById(user).get();
                System.out.println(singleUser.getPassword());
                if (singleUser.getPassword().equals(password)) {
                    return ResponseEntity.status(HttpStatus.OK).body(userRepo.findById(user).get());
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("admin/users/{user}")
    public ResponseEntity<Object> getAllUsers(@PathVariable String user) {
        try {
            if (userRepo.findById(user).isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(userRepo.findAll());
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("admin/users/{user}")
    public ResponseEntity<Object> createUser(@PathVariable String user, @RequestBody User newUser){
        if (newUser.getName() == null || newUser.getPassword() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username and password required");
        }
        try {
            if (userRepo.findById(user).isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(userRepo.save(newUser));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("admin/users/{user}/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String user, @PathVariable String id){
        try {
            if (userRepo.findById(user).isPresent()) {
                userRepo.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("User" + id + "was successfully deleted");            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the permissions to perform this action");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
