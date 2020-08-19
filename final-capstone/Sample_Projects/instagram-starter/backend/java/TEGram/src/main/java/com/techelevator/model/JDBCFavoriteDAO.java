package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCFavoriteDAO implements FavoriteDAO {
	
	private JdbcTemplate jdbcTemplate;
	private final String SQL_INSERTFAVORITE = 
			"insert into favorites (post_id, user_id) (select ? as post_id, ? as user_id " +
			"where not exists (select 1 from favorites where post_id=? AND user_id=?));";
	
    private final String SQL_DELETEFAVORITE = "DELETE FROM favorites WHERE post_id=? AND user_id=?;";
    
    @Autowired
	public JDBCFavoriteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void favorPostByUserId(long postId, long userId) {
		jdbcTemplate.update(SQL_INSERTFAVORITE, postId, userId, postId, userId);
	}

	@Override
	public void disfavorPostByUserId(long postId, long userId) {
		jdbcTemplate.update(SQL_DELETEFAVORITE, postId, userId);
	}
	
	public static Favorite mapRowToFavorite(SqlRowSet results) {
		Favorite favorite = new Favorite();
		favorite.setPostId(results.getLong("post_id"));
		favorite.setUserId(results.getLong("user_id"));
		return favorite;
	}

}
