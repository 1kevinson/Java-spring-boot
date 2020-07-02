package com.in28minutes.learning.jpa.jpain10steps.service;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;

@Repository
public class UserDaoService {

	private EntityManager entityManager;

	public long insert(User user) {
		entityManager.persist(user);
		return user.getId();
	}
}
