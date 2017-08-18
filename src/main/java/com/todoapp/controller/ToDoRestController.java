package com.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.model.ToDoTask;
import com.todoapp.model.ToDoTaskMapper;
import com.todoapp.model.User;
import com.todoapp.model.UserMapper;
import com.todoapp.service.TodoManager;

@RestController
public class ToDoRestController {

	@Autowired
	TodoManager manager;

	@SuppressWarnings("rawtypes")
	@PostMapping("/getToDoList")
	public ResponseEntity<?> getToDoTasks(@RequestBody User user) {
		return new ResponseEntity<List>(manager.getAllTasks(user), HttpStatus.OK);
	}

	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		
		return new ResponseEntity<UserMapper>(manager.createUser(user), HttpStatus.OK);
	}

	@PostMapping("/addTask")
	public ResponseEntity<?> addTask(@RequestBody ToDoTask task) {
		return new ResponseEntity<ToDoTaskMapper>(manager.createToDoTask(task), HttpStatus.OK);
	}
	@PostMapping("/updateTask")
	public ResponseEntity<?> updateTask(@RequestBody ToDoTask task) {
		return new ResponseEntity<ToDoTaskMapper>(manager.updateTask(task), HttpStatus.OK);
	}

	@PostMapping("/deleteTask")
	public ResponseEntity<?> deleteTask(@RequestBody ToDoTask task) {

		manager.deleteTask(task);

		return new ResponseEntity<ToDoTask>(task, HttpStatus.OK);
	}
}
