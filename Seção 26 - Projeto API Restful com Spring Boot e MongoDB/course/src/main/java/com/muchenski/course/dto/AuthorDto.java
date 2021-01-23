package com.muchenski.course.dto;

import java.io.Serializable;

import com.muchenski.course.domain.User;

public class AuthorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	public AuthorDto() {

	}

	public AuthorDto(User entity) {
		id = entity.getId();
		name = entity.getName();
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

}
