package com.in28minutes.rest.webservices.project.post;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.project.user.UserNotFoundException;

@RestController
public class PostController {

	@Autowired
	private PostDaoService service;

	@GetMapping("/posts")
	public List<Post> findAllPosts() {
		return service.findAll();
	}

	@GetMapping("/users/{id}/posts")
	public List<Post> findAllPostsForUser(@PathVariable int id) {
		List<Post> posts = service.findUserPosts(id);

		if (posts == null)
			throw new UserNotFoundException("Posts not found for this User" + id);

		return posts;
	}

	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createUser(@PathVariable int id, @RequestBody Post post) {
		Post savedPost = service.save(id, post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/users/{user_id}/posts/{post_id}")
	public Post retrieveOneUser(@PathVariable int user_id, @PathVariable int post_id) {
		Post post = service.findOnePost(user_id, post_id);

		if (post == null)
			throw new PostNotFoundException("Post not found for this id - " + post_id);

		return post;
	}
}
