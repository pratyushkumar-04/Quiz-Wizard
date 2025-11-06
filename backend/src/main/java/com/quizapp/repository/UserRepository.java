package com.quizapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username (used in authentication and profile)
    Optional<User> findByUsername(String username);

    // Check if username already exists (useful for registration)
    boolean existsByUsername(String username);

    // Optional: find by email (useful if you add password reset)
    Optional<User> findByEmail(String email);
}
