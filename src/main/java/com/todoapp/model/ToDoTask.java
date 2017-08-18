package com.todoapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="to_do_list")
public class ToDoTask implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="t_id")
    private Integer id;
	
	
	@Column(name="to_do_task")
	private String toDoTask;
	@Column(name="status")
	private String status;
	@Column(name="date_created")
	private Date createdDate;
	
	
	@ManyToOne
	private User user;
	
	public ToDoTask() {}
	 

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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


}