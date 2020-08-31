package com.techelevator.dao;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.entity.User;
import com.techelevator.security.PasswordHasher;

/**
 33    * This class does a pile of cool stuff!
 37    *
 38    * <p>The methods of this class all throw a <tt>NullPointerException</tt>
 39    * if the collections or class objects provided to them are null.
 40    *
 41    * <p>The documentation for the polymorphic algorithms contained in this class
 42    * generally includes a brief description of the <i>implementation</i>.  Such
 43    * descriptions should be regarded as <i>implementation notes</i>, rather than
 44    * parts of the <i>specification</i>.  Implementors should feel free to
 45    * substitute other algorithms, so long as the specification itself is adhered
 46    * to.  (For example, the algorithm used by <tt>sort</tt> does not have to be
 47    * a mergesort, but it does have to be <i>stable</i>.)
 61    *
 62    * @author  Capstone Development Team
 63    * @author  Tech Start Columbus 2020
 63    * @version  1.0
 69    */

@Component
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher hashMaster;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher hashMaster) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.hashMaster = hashMaster;
	}

    /**
      * @param user <i>The new user to be inserted into the database.</i>
      * @throws ClassCastException if the list contains elements that are not
      *         <i>mutually comparable</i> (for example, strings and integers).
      */
	@Override
	public void saveUser(User user) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(user.getPassword(), salt);
		String saltString = new String(Base64.encode(salt));
		
		jdbcTemplate.update("INSERT INTO app_user(user_name, password, first_name, last_name, role, salt) " +
						"VALUES (?, ?, ?, ?, ?, ?)",
				user.getUserName(), hashedPassword, user.getFirstName(),
				user.getLastName(), user.getRole(), saltString);
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM app_user "+
							      "WHERE UPPER(user_name) = ? ";
		
		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(user.next()) {
			String dbSalt = user.getString("salt");
			String dbHashedPassword = user.getString("password");
			String givenPassword = hashMaster.computeHash(password, Base64.decode(dbSalt));
			return dbHashedPassword.equals(givenPassword);
		} else {
			return false;
		}
	}

	@Override
	public void updatePassword(String userName, String password) {
		/* TODO:
		 * The password needs to be hashed before being inserted in the database!
		 */
		jdbcTemplate.update("UPDATE app_user SET password = ? WHERE user_name = ?", password, userName);
	}

	@Override
	public User getUserByUserName(String userName) {
		String sqlSearchForUsername ="SELECT * "+
		"FROM app_user "+
		"WHERE UPPER(user_name) = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName.toUpperCase()); 
		User thisUser = null;
		if(user.next()) {
			thisUser = new User();
			thisUser.setId(user.getLong("id"));
			thisUser.setUserName(user.getString("user_name"));
			thisUser.setPassword(user.getString("password"));
			thisUser.setFirstName(user.getString("first_name"));
			thisUser.setLastName(user.getString("last_name"));
			thisUser.setRole(user.getString("role"));
		}

		return thisUser;
	}


}
