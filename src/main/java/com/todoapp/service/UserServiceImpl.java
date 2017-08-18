package com.todoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todoapp.dao.IUserDAO;
import com.todoapp.model.User;
import com.todoapp.model.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	IUserDAO dao;

	private final LoggedInChecker loggedInChecker;

	@Autowired
	UserServiceImpl(LoggedInChecker loggedInChecker) {
		this.loggedInChecker = loggedInChecker;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = dao.getUser(username);
		if (user != null) {

			user.setPassword(new ShaPasswordEncoder().encodePassword(user.getPassword(), null));

			return user;
		} else {
			return null;
		}
	}

	@Override
	public List<String> getPermissions(String username) {

		return dao.getUserPermissions(username);
	}
    @Override
    public UserMapper getCurrentUser() {
        return loggedInChecker.getLoggedInUser();
    }

    @Override
    public Boolean isCurrentUserLoggedIn() {
        return loggedInChecker.getLoggedInUser() != null;
    }

}
