package com.techelevator.model;

public interface CommentDAO {
	
    Comment createComment(Comment comment);
    void deleteComment(long commentId);

}
