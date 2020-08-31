package com.techelevator.entity;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class User {

	public static final String BREWER = "BREWER";
	public static final String BEER_LOVER = "BEER_LOVER";
    public static final String ADMINISTRATOR = "ADMINISTRATOR";

	private Long id;

	private String role;

    @NotBlank(message = "Email is required")
	@Email(message = "Must be a valid email address")
	private String userName;

	@Size(min = 10, message = "Password too short, must be at least 10")
	@Pattern.List({
			@Pattern(regexp = ".*[a-z].*", message = "Must have a lower case"),
			@Pattern(regexp = ".*[A-Z].*", message = "Must have a capital")
	})
	private String password;

	private String confirmPassword;

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    public User(){
    	this.role = CUSTOMER_ROLE;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the role
     */
    public String getRole() {

    	return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {

    	this.role = role;
    }

    public String getUserName() {

    	return userName;
    }

    public void setUserName(String userName) {

    	this.userName = userName;
    }

    public String getPassword() {

    	return password;
    }

    public void setPassword(String password) {

    	this.password = password;
    }

    public String getConfirmPassword() {

    	return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {

    	this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
