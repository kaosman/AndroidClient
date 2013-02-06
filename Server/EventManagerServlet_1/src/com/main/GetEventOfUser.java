package com.main;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EventEntity;
import com.dao.ObjectifyDao;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.support.Helper;

public class GetEventOfUser extends HttpServlet{

	private Helper helper;
	private ObjectifyDao objectifyDao;
	@Override
	public void init() throws ServletException {
		
		System.out.println("init of get event of user");
		this.helper = new Helper();
		this.objectifyDao = new ObjectifyDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		InputStream inputStream = req.getInputStream();
		
		String groupName = this.helper.convertToString(inputStream);
		
		
		
	}
}
