package com.techelevator.model;

public interface FavoriteDAO {

    void favorPostByUserId(long postId, long userId);
    void disfavorPostByUserId(long postId, long userId);

}
