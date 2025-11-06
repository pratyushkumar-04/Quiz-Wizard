package com.quizapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quizapp.model.User;
import com.quizapp.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    User us = userRepo.findByUsername(username)
	            .orElseThrow(() -> {
	                return new UsernameNotFoundException("Username Not Found");
	            });
		return new CustomUserDetails(us);
	}
	

}
