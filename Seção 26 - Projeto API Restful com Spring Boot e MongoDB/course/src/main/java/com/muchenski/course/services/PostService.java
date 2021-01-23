package com.muchenski.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muchenski.course.domain.Post;
import com.muchenski.course.repositories.PostRepository;
import com.muchenski.course.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not found -> id: " + id));
	}

	public List<Post> findAll() {
		return repository.findAll();
	}
}
