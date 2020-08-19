package com.techelevator.model;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCDAOTests {

    
    /* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	protected static SingleConnectionDataSource dataSource;
	
	protected long testUserOneId;
	protected long testUserTwoId; 
	protected long testPostOneId; 
	protected long testPostTwoId;
	protected long testPostThreeId;
	protected long testCommentOneId;
	protected long testCommentTwoId;

    private final String SQL_SETUP_TEST = 
    		"SELECT new_user_one_id, new_user_two_id, new_post_one_id, new_post_two_id, new_post_three_id, new_comment_one_id, new_comment_two_id " +
    		"FROM test_setup() AS " + 
    		"(new_user_one_id INT, new_user_two_id INT, new_post_one_id INT, new_post_two_id INT, new_post_three_id INT, new_comment_one_id INT, new_comment_two_id INT);";
	
    /* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/tegram");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections 
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}
	
	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		SqlRowSet results = jdbcTemplate.queryForRowSet(SQL_SETUP_TEST);
		if (results.next()) {
			testUserOneId = results.getLong("new_user_one_id");
			testUserTwoId = results.getLong("new_user_two_id"); 
			testPostOneId = results.getLong("new_post_one_id"); 
			testPostTwoId = results.getLong("new_post_two_id");
			testPostThreeId = results.getLong("new_post_three_id");
			testCommentOneId = results.getLong("new_comment_one_id");
			testCommentTwoId = results.getLong("new_comment_two_id");
		}
	}
	
	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

}
