package com.quizapp.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.model.User;
import com.quizapp.repository.UserRepository;
import com.quizapp.security.CustomUserDetailService;
import com.quizapp.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {


	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private CustomUserDetailService customuserservice;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtService jwtservice;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        UserDetails userDetails = customuserservice.loadUserByUsername(user.getUsername());
        String token = jwtservice.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Map.of("token", token));
    }
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
	    if (userRepo.findByUsername(user.getUsername()).isPresent()) {
	        return ResponseEntity.badRequest().body(Map.of("message", "Username already exists"));
	    }

	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user.setEmail(user.getEmail());

	    // Allow Admin creation only if role is explicitly set
	    if (user.getRole() == null || user.getRole().isBlank()) {
	        user.setRole("ROLE_USER");
	    }

	    userRepo.save(user);
	    return ResponseEntity.ok(Map.of("message", "User registered successfully"));
	}

}
