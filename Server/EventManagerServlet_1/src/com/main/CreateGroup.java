package com.main;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DataAccessObject;
import com.dao.ObjectifyDao;
import com.googlecode.objectify.Objectify;
import com.model.Group;
import com.support.Helper;

public class CreateGroup extends HttpServlet{

	private Helper helper;
	private Group group;
	private ObjectifyDao objectifyDao;
	private SendEmail sendEmail;
	
	@Override
	public void init() throws ServletException {
		
		System.out.println("init of create group");
		this.helper = new Helper();
		this.objectifyDao = new ObjectifyDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		InputStream inputStream = req.getInputStream();
		
		this.group = this.helper.parseGroup(inputStream);
		this.objectifyDao.createGroup(this.group);
		
		this.sendEmail = new SendEmail(3);
		this.sendEmail.sendEmail(this.group.getMembers());
	}
}
