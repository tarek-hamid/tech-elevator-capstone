package com.techelevator.model;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotBlank;

public class Comment {
	
	private long id;
    private long postId;
    private long userId;
    @NotBlank(message="Message is required")
    private String message;
    private LocalDateTime dateTimeStamp;
    private String userName;
    private String userImage;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getDateTimeStamp() {
		return dateTimeStamp;
	}
	public void setDateTimeStamp(LocalDateTime dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

}
