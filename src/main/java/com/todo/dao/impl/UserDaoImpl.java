package com.todo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todo.dao.IUserDAO;
import com.todo.model.ToDoTask;
import com.todo.model.User;

@Repository
@Transactional
public class UserDaoImpl implements IUserDAO {
	@PersistenceContext
	private EntityManager manager;

	public List<ToDoTask> getAllTasks(User user) {
		List<ToDoTask> tasks = manager.createQuery("Select a From ToDoTask a where a.user:user", ToDoTask.class)
				.setParameter("user", user).getResultList();
		return tasks;
	}

	public void createUser(User user) {

		manager.persist(user);
	}

	public void createToDoTask(ToDoTask task) {
		manager.persist(task);
	}

	public void updateTask(ToDoTask task) {
		manager.merge(task);
	}

	public User getUser(String userName) {
		return manager.find(User.class, userName);
	}

}
