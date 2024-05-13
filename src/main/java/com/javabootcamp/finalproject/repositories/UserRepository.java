package com.javabootcamp.finalproject.repositories;

import com.javabootcamp.finalproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
