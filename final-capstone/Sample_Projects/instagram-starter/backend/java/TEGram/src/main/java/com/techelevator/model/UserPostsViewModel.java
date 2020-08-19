package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class UserPostsViewModel {

	private String username;
	private String image;
	private List<Post> userPosts = new ArrayList<>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<Post> getUserPosts() {
		return userPosts;
	}
	public void setUserPosts(List<Post> userPosts) {
		this.userPosts = userPosts;
	}
	
}
