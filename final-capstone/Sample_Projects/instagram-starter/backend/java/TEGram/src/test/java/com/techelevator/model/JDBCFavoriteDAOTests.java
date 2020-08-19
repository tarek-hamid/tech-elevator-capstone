package com.techelevator.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.techelevator.model.FavoriteDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JDBCFavoriteDAOTests extends JDBCDAOTests {

	private FavoriteDAO favoriteDao;
	private PostDAO postDao;

	@Override
	public void setup() {
		super.setup();
		favoriteDao = new JDBCFavoriteDAO(dataSource);
		postDao = new JDBCPostDAO(dataSource);
	}

	@Test
    public void favoredPostByUserId_Should_Work()
    {
        favoriteDao.favorPostByUserId(testPostOneId, testUserOneId); // Post favored their own post
        List<Post> favorites = postDao.getFavoritesByUserId(testUserOneId);
        assertEquals(2, favorites.size());
    }

	@Test
    public void FavoredPostByUserId_Favored_Twice_By_Same_User_Should_Not_Throw_Exception()
    {
        favoriteDao.favorPostByUserId(testPostOneId, testUserTwoId); 
        List<Post> favorites = postDao.getFavoritesByUserId(testUserTwoId);
        assertEquals(1, favorites.size());
    }

	@Test
    public void unfavorPostByUserId_Should_Work() {
		favoriteDao.disfavorPostByUserId(testPostOneId, testUserTwoId); // NewUserTwoId now unliking post they liked in test-script.sql
        List<Post> favorites = postDao.getFavoritesByUserId(testUserTwoId);
        assertEquals(0, favorites.size());
    }

	@Test
    public void UnfavorPostByUserId_Unfavored_By_User_Who_Never_Liked_Post_Should_Not_Throw_Exception() {
		favoriteDao.disfavorPostByUserId(testPostOneId, testUserOneId); // Only NewUserTwoId liked the post in test-script.sql
        List<Post> favorites = postDao.getFavoritesByUserId(testUserTwoId);  // Confirm that NewUserTwoId stills favor the post
        assertEquals(1, favorites.size());
    }


}
