package com.muchenski.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.muchenski.course.domain.User;
import com.muchenski.course.repositories.UserRepository;
import com.muchenski.course.services.exceptions.DbIntegrityException;
import com.muchenski.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User insert(User user) {
		return repository.save(user);
	}

	public User findById(Long id) {
		// .findById(id) -> Vai até o banco de dados.
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<User> findAll() {
		List<User> users = repository.findAll();
		return users;
	}

	public void delete(Long id) {
		try {
		repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DbIntegrityException(e.getMessage());
		}
	}

	public User update(Long id, User updatedUser) {
		try {
		// .getOne(id); -> retorna entidades mapeadas.
		User entity = repository.getOne(id);
		updateData(entity, updatedUser);
		return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User updatedUser) {
		entity.setName(updatedUser.getName());
		entity.setEmail(updatedUser.getEmail());
		entity.getPhones().addAll(updatedUser.getPhones());
	}
}
