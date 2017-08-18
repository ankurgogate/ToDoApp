package com.todoapp.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.todoapp.model.UserMapper;

public class ToDoUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5910031813164643683L;
	private UserMapper user;
    private List<GrantedAuthority> authorities;

    public ToDoUserDetails(UserMapper user, List<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public UserMapper getUser() {
        return user;
    }

    public void setUser(UserMapper user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
