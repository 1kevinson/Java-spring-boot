package com.in28minutes.rest.webservices.project.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrtieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveOneUser(@PathVariable int id) {
		return service.findOneUser(id);
	}

	@PostMapping("/users")
	public List<User> createUser(@RequestBody User user) {
		List<User> savedUser = service.save(user);
		return savedUser;
	}
}
