package com.techelevator.model;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JDBCLikeDAOTests extends JDBCDAOTests {

	private LikeDAO likeDAO;

	@Override
	public void setup() {
		super.setup();
		likeDAO = new JDBCLikeDAO(dataSource);
	}
    
	@Test
    public void getAllLikesByPostId_Should_Return_All_Likes() {
        List<User> users = likeDAO.getAllLikesByPostId(testPostOneId);
        assertEquals(1, users.size());
    }

	@Test
    public void likePostByUserId_Should_Work()
    {
        List<User> users = likeDAO.getAllLikesByPostId(testPostOneId);
        int numLikes = users.size();
        assertEquals(1, numLikes);

        // Like the post
        likeDAO.likePostByUserId(testPostOneId, testUserOneId); // Poster liking their own post

        // Check that the number increased by one
        users = likeDAO.getAllLikesByPostId(testPostOneId);
        assertEquals(numLikes+1, users.size());
    }

	@Test
	public void LikePostByUserId_Liked_Twice_By_Same_User_Should_Not_Throw_Exception() {
		likeDAO.likePostByUserId(testPostOneId, testUserTwoId); // NewUserTwoId already liked in test-script.sql
        List<User> users = likeDAO.getAllLikesByPostId(testPostOneId);
        assertEquals(1, users.size());
    }

	@Test
	public void unLikePostByUserId_Should_Work() {
        likeDAO.unlikePostByUserId(testPostOneId, testUserTwoId); // NewUserTwoId now unliking post they liked in test-script.sql
        List<User> users = likeDAO.getAllLikesByPostId(testPostOneId);
        assertEquals(0, users.size());
    }
	
	@Test
    public void unLikePostByUserId_Unliked_By_User_Who_Never_Liked_Post_Should_Not_Throw_Exception() {
        likeDAO.unlikePostByUserId(testPostOneId, testUserOneId); // Only NewUserTwoId liked the post in test-script.sql
        List<User> users = likeDAO.getAllLikesByPostId(testPostOneId);
        assertEquals(1, users.size());
    }

}
