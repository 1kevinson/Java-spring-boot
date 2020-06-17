package com.in28minutes.rest.webservices.project.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.rest.webservices.project.user.UserDaoService;
import com.in28minutes.rest.webservices.project.user.UserNotFoundException;

@Component
public class PostDaoService {

	private static List<Post> posts = new ArrayList<>();
	private static int postCount = 8;

	@Autowired
	private static UserDaoService service = new UserDaoService();
	static Random rn = new Random();

	static {
		posts.add(new Post(1, service.findOneUser(rn.nextInt(service.findAll().size()) + 1), "comment", new Date(),
				"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old"));
		posts.add(new Post(2, service.findOneUser(rn.nextInt(service.findAll().size()) + 1), "comment", new Date(),
				"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old"));
		posts.add(new Post(3, service.findOneUser(rn.nextInt(service.findAll().size()) + 1), "comment", new Date(),
				"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old"));
		posts.add(new Post(4, service.findOneUser(rn.nextInt(service.findAll().size()) + 1), "comment", new Date(),
				"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old"));
		posts.add(new Post(5, service.findOneUser(rn.nextInt(service.findAll().size()) + 1), "comment", new Date(),
				"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old"));
		posts.add(new Post(6, service.findOneUser(rn.nextInt(service.findAll().size()) + 1), "comment", new Date(),
				"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old"));
		posts.add(new Post(7, service.findOneUser(rn.nextInt(service.findAll().size()) + 1), "comment", new Date(),
				"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old"));
		posts.add(new Post(8, service.findOneUser(rn.nextInt(service.findAll().size()) + 1), "comment", new Date(),
				"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old"));
	}

	public List<Post> findAll() {
		return posts;
	}

	public List<Post> findUserPosts(int userId) {
		List<Post> newPosts = new ArrayList<Post>(posts);

		if (service.findOneUser(userId) == null)
			throw new UserNotFoundException("User not found for this Id : " + userId);

		// Check the post(s) of one user
		newPosts.removeIf(p -> (p.getUser().getId() != userId));

		return newPosts;
	}

	// business logic
	public Post save(int userId, Post post) {
		if (post.getId() == null) {
			post.setId(postCount++);
		}

		if (post.getUser() == null) {
			throw new UserNotFoundException("Cannot save post for Id null");
		}

		post.setUser(service.findOneUser(rn.nextInt(service.findAll().size()) + 1));
		post.getUser().setId(userId);
		posts.add(post);
		return post;
	}

	public Post findOnePost(int postId, int userId) {
		for (Post post : posts) {
			if ((post.getId() == postId) && (post.getUser().getId() == userId)) {
				return post;
			}
		}

		return null;
	}
}
