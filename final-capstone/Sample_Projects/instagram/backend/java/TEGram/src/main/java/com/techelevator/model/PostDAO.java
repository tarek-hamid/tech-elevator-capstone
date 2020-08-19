package com.techelevator.model;

import java.util.List;

public interface PostDAO {

	List<Post> getAllPosts(long currentUserId);
    List<Post> getAllPostsByUserId(long userId);
    Post getPostById(long id, long currentUserId);
    List<Post> getFavoritesByUserId(long userId);
    Post createPost(Post post);
	
}
