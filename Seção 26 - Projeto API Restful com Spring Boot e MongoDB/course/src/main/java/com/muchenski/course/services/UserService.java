package com.muchenski.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muchenski.course.domain.Post;
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

	public UserDto update(String id, UserDto dto) {
		Optional<User> bdUser = repository.findById(id);

		User user = bdUser.orElseThrow(() -> new ObjectNotFoundException("Object not found -> id: " + id));

		user.setName(dto.getName());
		user.setEmail(dto.getEmail());

		return new UserDto(repository.save(user));
	}

	public List<Post> findPostByUserId(String id) {
		findById(id);
		return repository.findById(id).get().getPosts();
	}

	public List<UserDto> findByNameLike(String name) {
		return repository.findByNameLike(name).stream().map(user -> new UserDto(user)).collect(Collectors.toList());
	}
}
