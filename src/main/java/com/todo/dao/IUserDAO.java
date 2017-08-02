package com.todo.dao;

import java.util.List;

import com.todo.model.ToDoTask;
import com.todo.model.User;

public interface IUserDAO {

	
	public List<ToDoTask> getAllTasks(User user);
	
	public void createUser(User user);
	
	public void createToDoTask(ToDoTask task);
	
	public void updateTask(ToDoTask task);
	
	public User getUser(String userName);
}
