package com.techelevator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JDBCCommentDAOTests extends JDBCDAOTests {

	private CommentDAO commentDao;
	private PostDAO postDao;

	@Override
	public void setup() {
		super.setup();
		commentDao = new JDBCCommentDAO(dataSource);
		postDao = new JDBCPostDAO(dataSource);
	}
	
	@Test
	public void createComment_Should_CreateComment() {
        Post post = postDao.getPostById(testPostOneId, testPostTwoId);
        int commentCount = post.getComments().size();

        Comment comment = new Comment();
        comment.setPostId(testPostOneId);
        comment.setUserId(testUserTwoId);
        comment.setMessage("This is a test comment");
        comment = commentDao.createComment(comment);

        // Assert that we got a new ID
        long commentId = comment.getId();
        assertNotEquals(0, commentId);
        
        // Assert that the post has one more comment than it did.
        post = postDao.getPostById(testPostOneId, testPostTwoId);
        assertEquals(post.getComments().size(), ++commentCount);

        // Add a second comment by the same user - should be allowed and should be a new Id
        comment = new Comment();
        comment.setPostId(testPostOneId);
        comment.setUserId(testUserTwoId);
        comment.setMessage("This is a SECOND test comment");
        comment = commentDao.createComment(comment);

        // Assert that we got a new ID, different from the previous one
        assertNotEquals(0, comment.getId());
        assertNotEquals(commentId, comment.getId());

        // Assert that the post has one more comment than it did.
        post = postDao.getPostById(testPostOneId, testPostTwoId);
        assertEquals(post.getComments().size(), ++commentCount);
	}

	@Test
    public void deleteComment_Should_Work() {
        Post post = postDao.getPostById(testPostOneId, testUserTwoId);
        int commentCount = post.getComments().size();

        commentDao.deleteComment(testCommentOneId);

        post = postDao.getPostById(testPostOneId, testUserTwoId);
        assertEquals(post.getComments().size() + 1, commentCount);

        // Try to delete the comment again. Should not fail, even though
        // there is no change in the data.
        commentDao.deleteComment(testCommentOneId);

        post = postDao.getPostById(testPostOneId, testPostTwoId);
        assertEquals(post.getComments().size() + 1, commentCount);
    }

}
