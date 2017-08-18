package com.todoapp.service;

import java.util.List;

import com.todoapp.model.User;
import com.todoapp.model.UserMapper;

public interface UserService {
	User getUserByUsername(String username);

	List<String> getPermissions(String username);

	UserMapper getCurrentUser();

	Boolean isCurrentUserLoggedIn();

}
