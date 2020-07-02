package com.in28minutes.rest.webservices.project.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJpaResource {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		return user;
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteOneUSer(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.saveAndFlush(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllPostForUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("User doesn't exists");

		return user.get().getPosts();
	}
}
