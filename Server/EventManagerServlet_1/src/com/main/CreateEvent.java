package com.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DataAccessObject;
import com.dao.EventEntity;
import com.dao.ObjectifyDao;
import com.model.Event;
import com.support.Helper;
import com.support.Helper;

public class CreateEvent extends HttpServlet{

	private SendEmail sendEmail;
	private Event event;
	private Helper helper;
	private EventEntity eventEntity;
	private ObjectifyDao objectifyDao;
	
	@Override
	public void init() throws ServletException {
		
		System.out.println("in init of createEvent");
		this.helper = new Helper();
		this.objectifyDao = new ObjectifyDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		InputStream inputStream = req.getInputStream();
		this.event = this.helper.parseEvent(inputStream);
		this.objectifyDao.createEvent(event);
		ArrayList<String> receivers = this.objectifyDao.getGroupMembers(event.getGroupList());
		this.sendEmail = new SendEmail(1);
		this.sendEmail.sendEmail(receivers);
		
	}

	
}
