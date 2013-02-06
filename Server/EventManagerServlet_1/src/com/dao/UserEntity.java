package com.dao;

import java.io.Serializable;

import javax.persistence.Id;


public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id String userId;
	String email;
	String password;
	String dob;
	String name;
	String contactNumber;
	
	public UserEntity(){
		
	}
	
	public UserEntity(String userid, String email, String password, String dob,
			String name, String contactNumber) {
		super();
		this.userId = userid;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.name = name;
		this.contactNumber = contactNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	
}
