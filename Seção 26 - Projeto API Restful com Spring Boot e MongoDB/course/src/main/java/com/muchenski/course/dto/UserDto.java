package com.muchenski.course.dto;

import java.io.Serializable;

import com.muchenski.course.domain.User;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String email;

	public UserDto() {
	}
	
	public UserDto(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
