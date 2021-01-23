package com.muchenski.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muchenski.course.domain.User;
import com.muchenski.course.dto.UserDto;
import com.muchenski.course.repositories.UserRepository;
import com.muchenski.course.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public UserDto findById(String id) {
		Optional<User> user = repository.findById(id);
		return new UserDto(user.orElseThrow(() -> new ObjectNotFoundException("Object not found -> id: " + id)));
	}

	public List<UserDto> findAll() {
		return repository.findAll().stream().map(user -> new UserDto(user)).collect(Collectors.toList());
	}

	public void deleteById(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public UserDto insert(UserDto dto) {
		User user = new User(null, dto.getName(), dto.getEmail());
		user = repository.insert(user);
		// Agora nosso DTO possui id.
		return new UserDto(user);
	}
}
