package com.in28minutes.rest.webservices.project.user;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveOneUser(@PathVariable int id) {
		User user = service.findOneUser(id);

		if (user == null)
			throw new UserNotFoundException("id - " + id);

		// HATEOAS (Hypermedia as the Engine of Application State) is a constraint of
		// the REST application architecture
		// that keeps the RESTful style architecture unique from most other network
		// application architectures.
		// The term “hypermedia” refers to any content that contains links to other
		// forms of media such as images, movies, and text.

		// HATEOAS - Generate All links
		EntityModel<User> model = new EntityModel<User>(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		model.add(link.withRel("all-users"));

		return model;
	}

	@DeleteMapping("/users/{id}")
	public void deleteOneUSer(@PathVariable int id) {
		User user = service.deleteById(id);

		if (user == null)
			throw new UserNotFoundException("no user found for id - " + id);
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
