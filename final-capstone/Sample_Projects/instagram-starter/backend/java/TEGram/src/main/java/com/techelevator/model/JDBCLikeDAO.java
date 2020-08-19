package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.model.JdbcUserDao;
import com.techelevator.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCLikeDAO implements LikeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
    private final String SQL_LIKESBYPOSTID = "SELECT * FROM users where id in (select user_id FROM likes WHERE post_id=?)";
	private final String SQL_INSERTLIKE = 
			"insert into likes (post_id, user_id) (select ? as post_id, ? as user_id " +
			"where not exists (select 1 from likes where post_id=? AND user_id=?));";
    private final String SQL_DELETELIKE = "DELETE FROM likes WHERE post_id=? AND user_id=?;";
    private final String SQL_COUNTLIKES = "SELECT COUNT(*) AS number_of_likes FROM likes WHERE post_id = ?;";
    
    @Autowired
	public JDBCLikeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<User> getAllLikesByPostId(long postId) {
		List<User> users = new ArrayList<>();	    
		SqlRowSet results = jdbcTemplate.queryForRowSet(SQL_LIKESBYPOSTID, postId);
		while (results.next()) {
            User user = JdbcUserDao.mapResultToUser(results);
            users.add(user);
		}
		return users;
	}

	@Override
	public int likePostByUserId(long postId, long userId) {
		jdbcTemplate.update(SQL_INSERTLIKE, postId, userId, postId, userId);
		SqlRowSet results = jdbcTemplate.queryForRowSet(SQL_COUNTLIKES, postId);
		if (results.next()) {
			return results.getInt("number_of_likes");
		}
		return 0;
		
	}

	@Override
	public int unlikePostByUserId(long postId, long userId) {
		jdbcTemplate.update(SQL_DELETELIKE, postId, userId);
		SqlRowSet results = jdbcTemplate.queryForRowSet(SQL_COUNTLIKES, postId);
		if (results.next()) {
			return results.getInt("number_of_likes");
		}
		return 0;
		
	}
	
	public static Like mapRowToLike(SqlRowSet results) {
		Like like = new Like();
		like.setPostId(results.getInt("post_id"));
		like.setUserId(results.getInt("user_id"));
		return like;
	}

}
