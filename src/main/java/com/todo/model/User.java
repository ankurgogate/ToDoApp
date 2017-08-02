package com.todo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="u_id")
	private Integer id;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastname;
	@Column(name="date_created")
	private Date createdDate;
	
	public User(){
		
	}

	
	
	public User(Integer id, String username, String password, String firstName, String lastname, Date createdDate,
			List<ToDoTask> toDoTasks) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastname = lastname;
		this.createdDate = createdDate;
		this.toDoTasks = toDoTasks;
	}



	@OneToMany(mappedBy="user",cascade=CascadeType.PERSIST)
    private List<ToDoTask> toDoTasks = new ArrayList<ToDoTask>();
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + username + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<ToDoTask> getToDoTasks() {
		return toDoTasks;
	}

	public void setToDoTasks(List<ToDoTask> toDoTasks) {
		this.toDoTasks = toDoTasks;
	}

}
