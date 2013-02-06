package com.main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ObjectifyDao;
import com.model.Group;
import com.support.Helper;

public class GetGroupDescription extends HttpServlet{

	private Helper helper;
	private ObjectifyDao objectifyDao;
	
	@Override
	public void init() throws ServletException {
		
		System.out.println("init of get group desc");
		this.objectifyDao = new ObjectifyDao();
		this.helper = new Helper();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		InputStream inputStream = req.getInputStream();
		
		String groupName = this.helper.convertToString(inputStream);
		
		Group group = this.objectifyDao.getGroupDescription(groupName);
		
		String sendToClient = this.helper.createGroupXml(group);
		DataOutputStream dataOutputStream = new DataOutputStream(resp.getOutputStream());
		
		dataOutputStream.writeBytes(sendToClient);
			
		
		dataOutputStream.flush();
		dataOutputStream.close();
	}
}
