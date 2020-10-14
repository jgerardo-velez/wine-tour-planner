package edu.cetys.springlabs.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String name;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails() {
		
	}
	
	public MyUserDetails(User user) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.password = user.getPassword();
		this.active = user.isActive();
		this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}

}
