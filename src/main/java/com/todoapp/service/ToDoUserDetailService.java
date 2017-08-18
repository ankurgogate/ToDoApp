package com.todoapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todoapp.model.User;
import com.todoapp.model.UserMapper;

@Service
public class ToDoUserDetailService implements UserDetailsService{

	private final UserService userService;

    @Autowired
    ToDoUserDetailService(UserService userService) {
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User usr = userService.getUserByUsername(username);
    	UserMapper user = null;
        if (usr == null) {
            throw new UsernameNotFoundException(username);
        }else {

        	user = new UserMapper(usr);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<String> permissions = userService.getPermissions(user.getUsername());
        for (String permission : permissions) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission));
        }

        return new ToDoUserDetails(user, grantedAuthorities);
    }

}
