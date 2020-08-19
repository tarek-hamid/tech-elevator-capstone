package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {

	private long id;
	private long userId;
	private String image;
	private String caption;
	private LocalDateTime dateTimeStamp;
	private int numberOfLikes;
	private boolean isLiked;
	private boolean isFavored;
    private String userName;
    private String userImage;
    private List<Comment> comments = new ArrayList<Comment>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public LocalDateTime getDateTimeStamp() {
		return dateTimeStamp;
	}

	public void setDateTimeStamp(LocalDateTime dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public boolean getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public boolean getIsFavored() {
		return isFavored;
	}

	public void setIsFavored(boolean isFavored) {
		this.isFavored = isFavored;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
