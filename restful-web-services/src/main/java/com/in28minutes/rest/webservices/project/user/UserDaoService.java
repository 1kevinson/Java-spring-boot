package com.in28minutes.rest.webservices.project.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 5;

	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Brandon", new Date()));
		users.add(new User(3, "Blake", new Date()));
		users.add(new User(4, "Tyler", new Date()));
		users.add(new User(5, "Marie", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(userCount++);
		}
		users.add(user);
		return user;
	}
}
