package com.todoapp.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.todoapp.model.UserMapper;

@Component
public class LoggedInChecker {
    public UserMapper getLoggedInUser() {
        UserMapper user = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            // principal can be "anonymousUser" (String)
            if (principal instanceof ToDoUserDetails) {
            	ToDoUserDetails userDetails = (ToDoUserDetails) principal;
                user = userDetails.getUser();
            }
        }

        return user;
    }
}
