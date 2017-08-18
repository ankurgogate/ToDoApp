package com.todoapp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todoapp.dao.IUserDAO;
import com.todoapp.model.ToDoTask;
import com.todoapp.model.ToDoTaskMapper;
import com.todoapp.model.User;
import com.todoapp.model.UserRole;

@Repository
@Transactional
public class UserDaoImpl implements IUserDAO {
	@PersistenceContext
	private EntityManager manager;

	public List<ToDoTaskMapper> getAllTasks(User user) {
		@SuppressWarnings("unchecked")
		List<ToDoTask> tasks = manager.createQuery("SELECT a From ToDoTask a where a.user=:user")
				.setParameter("user", user).getResultList();
		List<ToDoTaskMapper> taskmapper = new ArrayList<>();
		for(ToDoTask task : tasks) {
			ToDoTaskMapper maper = new ToDoTaskMapper(task);
			taskmapper.add(maper);
		}
		return taskmapper;
	}

	public User createUser(User user) {
		
		user.setCreatedDate(new Date());
		UserRole us = new UserRole();
		us.setUser(user);
		us.setRole("USER");
		user.setUserRole(us);
		return manager.merge(user);
	}

	public ToDoTask createToDoTask(ToDoTask task) {
		task.setCreatedDate(new Date());
		return manager.merge(task);
	}

	public ToDoTask updateTask(ToDoTask task) {
		return manager.merge(task);
	}

	public User getUser(String userName) {
		return (User) manager.createQuery("SELECT a From User a where a.username=:userName")
				.setParameter("userName", userName).getResultList().get(0);
	}

	public void deleteTask(ToDoTask task) {
		ToDoTask managed = manager.merge(task);
		manager.remove(managed);
		
	}

	@Override
	public List<String> getUserPermissions(String username) {
		User u = (User) manager.createQuery("SELECT a From User a where a.username=:userName")
		.setParameter("userName", username).getResultList().get(0);
		ArrayList<String> rt = new ArrayList<>();
		if(u!=null) {
			UserRole ur = (UserRole) manager.createQuery("SELECT a From UserRole a where a.user=:user")
					.setParameter("user", u).getResultList().get(0);
			 rt.add(ur.getRole());
		}
		return rt;
	}

}
