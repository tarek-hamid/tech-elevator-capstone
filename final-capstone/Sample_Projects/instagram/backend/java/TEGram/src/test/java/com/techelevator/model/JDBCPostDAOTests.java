package com.techelevator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JDBCPostDAOTests extends JDBCDAOTests {

	private PostDAO postDao;

	@Override
	public void setup() {
		super.setup();
		postDao = new JDBCPostDAO(dataSource);
	}

	@Test
    public void CreatePost_Should_Create_New_Post()
    {
        // First check the count of posts
        List<Post> posts = postDao.getAllPosts(testUserOneId);
        int count = posts.size();
        assertEquals(count, 3);

        // Add a post
        Post post = new Post();

        post.setUserId(testUserOneId);
        post.setCaption("A new test post");
        post.setImage("http://foo.com");
        post = postDao.createPost(post);

        // Check that the returned post has an id and a timstamp
        assertNotEquals(post.getId(), 0);
        assertTrue(ChronoUnit.MINUTES.between(LocalDateTime.now(),post.getDateTimeStamp()) < 1);
        

        // Count should be increased
        posts = postDao.getAllPosts(testUserOneId);
        assertEquals(count+1, posts.size());
    }

	@Test
    public void getAllPostsBy_Should_Return_All_Posts() {
        List<Post> posts = postDao.getAllPosts(testUserOneId);
        assertEquals(3, posts.size());
        assertEquals(1, posts.get(0).getComments().size());
        assertEquals(1, posts.get(0).getNumberOfLikes());
        assertEquals(1, posts.get(1).getComments().size());
        assertEquals(1, posts.get(1).getNumberOfLikes());
        assertEquals(0, posts.get(2).getComments().size());
        assertEquals(0, posts.get(2).getNumberOfLikes());

        assertEquals(false, posts.get(0).getIsFavored());
        assertEquals(true, posts.get(1).getIsFavored());
        assertEquals(false, posts.get(2).getIsFavored());
        assertEquals(false, posts.get(0).getIsLiked());
        assertEquals(true, posts.get(1).getIsLiked());
        assertEquals(false, posts.get(2).getIsLiked());
    }

	@Test
    public void getAllPostsByUserId_Should_Return_All_Posts_For_UserId() {
        List<Post> posts = postDao.getAllPostsByUserId(testUserOneId);
        assertEquals(2, posts.size());
        assertEquals(1, posts.get(0).getComments().size());
        assertEquals(1, posts.get(0).getNumberOfLikes());
        assertEquals(0, posts.get(1).getComments().size());
        assertEquals(0, posts.get(1).getNumberOfLikes());
    }

	@Test
    public void getPostByPostId_Should_Work() {
        Post post = postDao.getPostById(testPostOneId, testUserOneId);
        assertEquals(testPostOneId, post.getId());
        assertEquals(1, post.getComments().size());
        assertEquals(1, post.getNumberOfLikes());
        assertEquals(false, post.getIsLiked());
        assertEquals(false, post.getIsFavored());

        post = postDao.getPostById(testPostTwoId, testUserOneId);
        assertEquals(testPostTwoId, post.getId());
        assertEquals(1, post.getComments().size());
        assertEquals(1, post.getNumberOfLikes());
        assertEquals(true, post.getIsLiked());
        assertEquals(true, post.getIsFavored());

        post = postDao.getPostById(testPostThreeId, testUserOneId);
        assertEquals(testPostThreeId, post.getId());
        assertEquals(0, post.getComments().size());
        assertEquals(0, post.getNumberOfLikes());
        assertEquals(false, post.getIsLiked());
        assertEquals(false, post.getIsFavored());
    }
	
	@Test
    public void getFavoritesByUserId_Should_Return_Favorites_For_UserId() {
        List<Post> posts = postDao.getFavoritesByUserId(testUserOneId);
        assertEquals(1, posts.size());
        assertEquals(true, posts.get(0).getIsFavored());
        assertEquals(true, posts.get(0).getIsLiked());

        posts = postDao.getFavoritesByUserId(testUserTwoId);
        assertEquals(1, posts.size());
        assertEquals(true, posts.get(0).getIsFavored());
        assertEquals(true, posts.get(0).getIsLiked());
    }


}
