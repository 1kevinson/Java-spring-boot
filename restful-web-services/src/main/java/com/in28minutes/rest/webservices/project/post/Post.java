package com.in28minutes.rest.webservices.project.post;

import java.util.Date;

import com.in28minutes.rest.webservices.project.user.User;

public class Post {

	private Integer id;
	private User user;
	private String title;
	private Date postDate;
	private String details;

	public Post(Integer id, User user, String title, Date postDate, String details) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.postDate = postDate;
		this.details = details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public Date getPostDate() {
		return postDate;
	}

	public String getDetails() {
		return details;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + user.toString() + ", title=" + title + ", postDate=" + postDate
				+ ", details=" + details + "]";
	}

}
