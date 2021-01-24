package com.muchenski.course.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.muchenski.course.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// QueryMethods Spring Data
	public List<Post> findByTitleContainingIgnoreCase(String title);
}
