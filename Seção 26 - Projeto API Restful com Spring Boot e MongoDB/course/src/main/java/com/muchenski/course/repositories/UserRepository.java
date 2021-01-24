package com.muchenski.course.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.muchenski.course.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Query("{ 'name': { $regex: ?0, $options: 'i' } }")
	public List<User> findByNameLike(String name);

	// ?0 -> É o parâmetro índice 0 da nossa função.
	// $options: 'i' -> ignoreCase
}
