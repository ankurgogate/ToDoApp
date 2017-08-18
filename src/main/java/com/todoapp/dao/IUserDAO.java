package com.todoapp.dao;

import java.util.List;

import com.todoapp.model.ToDoTask;
import com.todoapp.model.ToDoTaskMapper;
import com.todoapp.model.User;


public interface IUserDAO {

	public List<ToDoTaskMapper> getAllTasks(User user);

	public User createUser(User user);

	public ToDoTask createToDoTask(ToDoTask task);

	public ToDoTask updateTask(ToDoTask task);

	public User getUser(String userName);

	public void deleteTask(ToDoTask task);

	public List<String> getUserPermissions(String username);
}
