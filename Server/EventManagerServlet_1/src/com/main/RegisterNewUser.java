package com.main;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DataAccessObject;
import com.model.User;
import com.support.Helper;
import com.support.Helper;

public class RegisterNewUser extends HttpServlet{

	private DataAccessObject dataAccessObject;
	private User user;
	private Helper helper;
	
	@Override
	public void init() throws ServletException {
		
		System.out.println("init of register new user");
		this.dataAccessObject = new DataAccessObject();
		this.helper = new Helper();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		InputStream inputStream = req.getInputStream();
		
		this.user = this.helper.parseUser(inputStream);
		this.dataAccessObject.createUser(this.user);
	}
}
