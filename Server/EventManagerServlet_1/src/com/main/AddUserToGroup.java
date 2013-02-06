package com.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DataAccessObject;
import com.dao.ObjectifyDao;
import com.support.Helper;

public class AddUserToGroup extends HttpServlet{

	private Helper helper;
	private ObjectifyDao objectifyDao;
	private SendEmail sendEmail;
	
	@Override
	public void init() throws ServletException {
		
		System.out.println("init of add user to grp");
		this.helper = new Helper();
		this.objectifyDao = new ObjectifyDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		InputStream inputStream = req.getInputStream();
		final String userIdGroupId = this.helper.convertToString(inputStream);
		
		String[] strings = new String[2];
		strings = userIdGroupId.split(":");
		final String userId = strings[0];
		final String groupName = strings[1];
		this.objectifyDao.addUserToGroup(userId, groupName);
		ArrayList<String> groupList = new ArrayList<String>();
		groupList.add(groupName);
		ArrayList<String> groupMembers = this.objectifyDao.getGroupMembers(groupList);
		groupMembers.add(userId);
		this.sendEmail = new SendEmail(3);
		this.sendEmail.sendEmail(groupMembers);
	}
}
