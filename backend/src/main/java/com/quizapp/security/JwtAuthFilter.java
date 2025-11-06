package com.quizapp.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtservice;
	
	
	@Autowired
	private CustomUserDetailService customuserservice;
	
	@Autowired
	@Lazy

	private AuthenticationManager authmanager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {

	    
	    String path = request.getRequestURI();

	    System.out.println("📍 HTTP method: " + request.getMethod());

	    // ✅ Fix path matching more accurately
	    if (path.startsWith("/auth")) {
	        System.out.println("🚫 Skipping JWT filter for: " + path);
	        filterChain.doFilter(request, response);
	        return;	    
	        }

	    String authHeader = request.getHeader("Authorization");
	    System.out.println("🧪 JWT Header: " + authHeader);


	    if (authHeader != null && authHeader.startsWith("Bearer ")) 
	    	{ String jwt = authHeader.substring(7); // extracts token from authorization header
	    String username = jwtservice.extractUsername(jwt); // Extracts userName from JWT by parser same can be viewed on https://jwt.io/ 
	    System.out.println("🧪 Username: " + username);

	    

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        UserDetails userDetails = customuserservice.loadUserByUsername(username);
	        
	        // validate token,ExtractRole is implemented in JWTSERVICE.java
	        if (jwtservice.validateToken(jwt)) {
	            String role = jwtservice.extractRole(jwt); // extractsRole as it extracted the userName from JWT
	            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role); // converts role to GrantedAuthority
	           
	            System.out.println("🧪 Role extracted: " + role);						//  |
	            UsernamePasswordAuthenticationToken authToken =							//  |
	                new UsernamePasswordAuthenticationToken(							//  V
	                    userDetails, null, List.of(authority)); // builds authentication object of authority 
	            
	            System.out.println("🧪 Current Authorities: " + userDetails.getAuthorities());


	            SecurityContextHolder.getContext().setAuthentication(authToken); // tells spring security that this "authToken is a validated user"
	            																// allow him the access according to role embedded in JWT
	            
	        }
	    }}

	    filterChain.doFilter(request, response); // this says that now authentication is completed continue with other tasks as normal pipepLine
	}


}
