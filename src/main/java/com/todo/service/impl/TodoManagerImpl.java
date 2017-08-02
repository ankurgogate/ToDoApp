package com.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.dao.IUserDAO;
import com.todo.model.ToDoTask;
import com.todo.model.User;

@Service
public class TodoManagerImpl {

	@Autowired
	IUserDAO dao;
	
	
	public List<ToDoTask> getAllTasks(User user) {
		return dao.getAllTasks(user);
	}

	public void createUser(User user) {
		dao.createUser(user);
	}

	public void createToDoTask(ToDoTask task) {
		dao.createToDoTask(task);
	}

	public void updateTask(ToDoTask task) {
		dao.updateTask(task);
	}

	public User getUser(String userName) {
		return dao.getUser(userName);
	}

}
