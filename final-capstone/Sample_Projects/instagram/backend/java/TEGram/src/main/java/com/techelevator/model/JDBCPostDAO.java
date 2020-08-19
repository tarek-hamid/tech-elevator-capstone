package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCPostDAO implements PostDAO {
	
	private JdbcTemplate jdbcTemplate;
    
    private final String GET_POSTS = 
    	"select p.*, pu.username, pu.image userimage, c.id comment_id, c.post_id comment_post_id, " + 
    	"c.user_id comment_user_id, cu.username comment_username, cu.image comment_userimage, " + 
    	"c.message comment_message, c.datetime_stamp comment_datetime_stamp, " +  
    	"COALESCE(l.likes_count, 0) as likes_count, " + 
    	"(select case when ld.user_id is null then false else true end) as liked, " + 
    	"(select case when f.user_id is null then false else true end) as favored " +
    	"from posts p " +
		"join users pu on p.user_id = pu.id " +
    	"left join comments c on p.id=c.post_id " +
		"left join users cu on c.user_id = cu.id " +
    	"left join (select post_id, count(post_id) as likes_count from likes group by post_id) as l on p.id=l.post_id " + 
    	"left join likes as ld on ld.user_id=? and p.id=ld.post_id " +  //user_id
    	"left join favorites as f on f.user_id=? and p.id=f.post_id ";  //user_id

    private final String GET_POSTS_WHERE = "where (p.user_id=? or ? = 0) and (? = 0 or p.id=?);"; //user_id, currentUserOnly(0 or 1), post_id, post_id
    private final String GET_POSTS_WHEREFAVORITE = "where p.id in (select post_id from favorites where user_id = ?);"; //user_id
    private final String INSERT_POST = "INSERT INTO posts(user_id, image, caption) VALUES (?, ?, ?) RETURNING *;"; //user_id, image, caption

    @Autowired
	public JDBCPostDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Post> getAllPosts(long userId) {
		SqlRowSet results = getCommandAllPosts(userId, false);
		return getPosts(results);
	}

	@Override
	public List<Post> getAllPostsByUserId(long userId) {
		SqlRowSet results = getCommandAllPosts(userId, true);
		return getPosts(results);
	}

	@Override
	public Post getPostById(long id, long currentUserId) {
		SqlRowSet results = getCommandPostId(currentUserId, id);
		List<Post> posts = getPosts(results);
		if (posts.size() > 0) {
			return posts.get(0);
		}
		else {
			return null;
		}
	}

	@Override
	public List<Post> getFavoritesByUserId(long userId) {
		SqlRowSet results = getCommandFavorites(userId);
		return getPosts(results);
	}

	@Override
	public Post createPost(Post post) {
		SqlRowSet results = jdbcTemplate.queryForRowSet(INSERT_POST, post.getUserId(), post.getImage(), post.getCaption());
		if (results.next()) {
			post = getPostById(results.getInt("id"), results.getInt("user_id"));
		}
		return post;
	}

	// Get command object for Get All Posts and Get Posts by User
    private SqlRowSet getCommandAllPosts(long currentUserId, boolean userPostsOnly) {
        String sql = GET_POSTS + " " + GET_POSTS_WHERE;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, currentUserId, currentUserId, currentUserId, userPostsOnly ? 1 : 0, 0, 0);
        return results;
    }

    // Get command object for Get Posts by Id
    private SqlRowSet getCommandPostId(long currentUserId, long postId)
    {
        String sql = GET_POSTS + " " + GET_POSTS_WHERE;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, currentUserId, currentUserId, currentUserId, 0, postId, postId);
        return results;
    }

    // Get command object for Get user Favorites posts
    private SqlRowSet getCommandFavorites(long userId)
    {
        String sql = GET_POSTS + " " + GET_POSTS_WHEREFAVORITE;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId, userId);
        return results;
    }
	
	private List<Post> getPosts(SqlRowSet results) {
		List<Post> posts = new ArrayList<>();
		int lastPostId = 0;
		Post post = null;
		while (results.next()) {
			int nextPostId = results.getInt("id");
			if (nextPostId != lastPostId) {
				lastPostId = nextPostId;
				post = mapRowToPost(results);
				posts.add(post);
			}
			int comment_id = results.getInt("comment_id");
			if (! results.wasNull()) {
				Comment comment = JDBCCommentDAO.mapRowToComment(results, "comment_");
				post.getComments().add(comment);
			}
		}
		return posts;
	}
	
	public static Post mapRowToPost(SqlRowSet results) {
		Post post = new Post();
        post.setId(results.getLong("id"));
        post.setUserId(results.getLong("user_id"));
        post.setUserName(results.getString("username"));
        post.setUserImage(results.getString("userimage"));
        post.setImage(results.getString("image"));
        post.setCaption(results.getString("caption"));
        post.setDateTimeStamp(results.getTimestamp("datetime_stamp").toLocalDateTime());
        post.setIsFavored(results.getBoolean("favored"));
        post.setNumberOfLikes(results.getInt("likes_count"));
        post.setIsLiked(results.getBoolean("liked"));
		return post;
	}
}
