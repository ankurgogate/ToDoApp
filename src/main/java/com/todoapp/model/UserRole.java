package com.todoapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7200041082319617517L;
	@Id
	@GeneratedValue
	@Column(name = "r_id")
	private Integer id;
	@Column(name = "role")
	private String role;

	@OneToOne
	private User user;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
