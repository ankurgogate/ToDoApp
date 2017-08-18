package com.todoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.todoapp.dao.IUserDAO;
import com.todoapp.model.ToDoTask;
import com.todoapp.model.ToDoTaskMapper;
import com.todoapp.model.User;
import com.todoapp.model.UserMapper;

@Service
public class TodoManager {

	@Autowired
	IUserDAO dao;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<ToDoTaskMapper> getAllTasks(User user) {
		return dao.getAllTasks(user);
	}

	public UserMapper createUser(User user) {
		return new UserMapper(dao.createUser(user));
	}

	public ToDoTaskMapper createToDoTask(ToDoTask task) {
		return new ToDoTaskMapper(dao.createToDoTask(task));
	}
	
	public ToDoTaskMapper updateToDoTask(ToDoTask task) {
		return new ToDoTaskMapper(dao.updateTask(task));
	}

	public ToDoTaskMapper updateTask(ToDoTask task) {
		return new ToDoTaskMapper(dao.updateTask(task));
	}

	public User getUser(String userName) {
		return dao.getUser(userName);
	}

	public void deleteTask(ToDoTask task) {
		dao.deleteTask(task);
		
	}

	

	
}