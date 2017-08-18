package com.todoapp.model;

import java.io.Serializable;
import java.util.Date;

public class ToDoTaskMapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6061615470332085788L;
	
    private Integer id;
	private String toDoTask;
	private String status;
	private Date createdDate;
	
	public ToDoTaskMapper(ToDoTask task) {
		super();
		this.id = task.getId();
		this.toDoTask = task.getToDoTask();
		this.status = task.getStatus();
		this.createdDate = task.getCreatedDate();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToDoTask() {
		return toDoTask;
	}
	public void setToDoTask(String toDoTask) {
		this.toDoTask = toDoTask;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
