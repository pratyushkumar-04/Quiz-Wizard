package com.quizapp.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.quizapp.model.User;

public class CustomUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5966254174845954923L;
	private final User us;
	
	public CustomUserDetails(User us)
	{
		this.us=us;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    String role = us.getRole();
	    if (!role.startsWith("ROLE_")) {
	        role = "ROLE_" + role;
	    }
	    return List.of(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		return us.getPassword();
	}

	@Override
	public String getUsername() {
		return us.getUsername();
	}
	
	    @Override 
	    public boolean isAccountNonExpired() { return true; }
	    @Override 
	    public boolean isAccountNonLocked() { return true; }
	    @Override
	    public boolean isCredentialsNonExpired() { return true; }
	    @Override 
	    public boolean isEnabled() { return true; }

	    public User getUser() {
	        return us;
	    }
	

}
