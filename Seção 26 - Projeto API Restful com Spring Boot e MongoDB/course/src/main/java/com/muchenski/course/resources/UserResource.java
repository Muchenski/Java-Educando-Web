package com.muchenski.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.muchenski.course.domain.Post;
import com.muchenski.course.dto.UserDto;
import com.muchenski.course.resources.utils.URL;
import com.muchenski.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDto>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<UserDto> insert(@RequestBody UserDto dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody UserDto dto) {
		return ResponseEntity.ok().body(service.update(id, dto));
	}

	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPostByUserId(@PathVariable String id) {
		return ResponseEntity.ok().body(service.findPostByUserId(id));
	}

	@GetMapping(value = "/namesearch")
	public ResponseEntity<List<UserDto>> findByNameLike(@RequestParam(value = "name", defaultValue = "") String name) {
		name = URL.decode(name);
		return ResponseEntity.ok().body(service.findByNameLike(name));
	}
}
