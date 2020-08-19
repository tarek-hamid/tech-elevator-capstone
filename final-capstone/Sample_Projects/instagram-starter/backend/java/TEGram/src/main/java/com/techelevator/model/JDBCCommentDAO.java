package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCCommentDAO implements CommentDAO {
	
	private JdbcTemplate jdbcTemplate;

    private final String DELETE_COMMENT = "DELETE FROM comments WHERE id=?"; // commentID
    private final String INSERT_COMMENT = "INSERT INTO comments(post_id, user_id, message) VALUES (?, ?, ?) RETURNING *;"; //postID, userID, message
    private final String SELECT_USERNAME = 
    		"SELECT c.*, u.username, u.image userimage from comments c " +
    		"join users u on c.user_id = u.id " + 
    		"where c.id = ?;"; //Newly inserted Comments record Comments.id
    		
    @Autowired
	public JDBCCommentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Comment createComment(Comment comment) {
		SqlRowSet results = jdbcTemplate.queryForRowSet(INSERT_COMMENT, comment.getPostId(), comment.getUserId(), comment.getMessage());
		// Insert the new comment in order to get the id of it
		if (results.next()) {
			comment.setId(results.getLong("id"));
		}
		// Then get the full comment including username and userimage in order to perform the full mapping
		results = jdbcTemplate.queryForRowSet(SELECT_USERNAME, comment.getId());
		if (results.next()) {
			comment = mapRowToComment(results, ""); //Empty prefix
		}
		return comment;
	}

	@Override
	public void deleteComment(long commentId) {
		jdbcTemplate.update(DELETE_COMMENT, commentId);
		
	}
	
	public static Comment mapRowToComment(SqlRowSet results, String prefix) {
		Comment comment = new Comment();
		comment.setId(results.getLong(prefix + "id"));
		comment.setPostId(results.getLong(prefix + "post_id"));
		comment.setUserId(results.getLong(prefix + "user_id"));
		comment.setUserName(results.getString(prefix + "username"));
		comment.setUserImage(results.getString(prefix + "userimage"));
		comment.setMessage(results.getString(prefix + "message"));
		comment.setDateTimeStamp(results.getTimestamp(prefix + "datetime_stamp").toLocalDateTime());
		return comment;
	}

}
