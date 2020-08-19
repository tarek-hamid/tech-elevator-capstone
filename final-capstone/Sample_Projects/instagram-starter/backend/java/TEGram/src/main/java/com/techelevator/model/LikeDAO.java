package com.techelevator.model;

import java.util.List;

import com.techelevator.model.User;

public interface LikeDAO {

    List<User> getAllLikesByPostId(long postId);
    int likePostByUserId(long postId, long userId);		// returns updated number of likes
    int unlikePostByUserId(long postId, long userId);		// returns updated number of likes

}
