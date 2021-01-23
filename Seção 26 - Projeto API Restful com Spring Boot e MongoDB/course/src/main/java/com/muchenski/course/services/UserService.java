package com.muchenski.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muchenski.course.dto.UserDto;
import com.muchenski.course.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<UserDto> findAll() {
		return repository.findAll().stream().map(user -> new UserDto(user)).collect(Collectors.toList());
	}
}
