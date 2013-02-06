package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	
	private String userEmail;
	private String userName;
	private String dob;
	private String password;
	private String contactNumber;
	
	public User(String userEmail, String userName, String dob, String password,
			String contactNumber) {
		super();
		this.userEmail = userEmail;
		this.userName = userName;
		this.dob = dob;
		this.password = password;
		this.contactNumber = contactNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
}
