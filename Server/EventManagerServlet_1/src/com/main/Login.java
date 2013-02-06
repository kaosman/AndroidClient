package com.main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DataAccessObject;
import com.dao.EventEntity;
import com.dao.GroupEntity;
import com.dao.ObjectifyDao;
import com.dao.UserEntity;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.model.Event;
import com.model.Group;
import com.model.User;
import com.support.Helper;

/**
 *  This class validates the credentials and sends back the groups and events of the user.
 * @author Ameya
 *
 */
public class Login extends HttpServlet{

	private Helper helper;
	private ObjectifyDao objectifyDao;
	
	@Override
	public void init() throws ServletException {
		
		this.helper = new Helper();
		this.objectifyDao = new ObjectifyDao();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		InputStream inputStream = req.getInputStream();

		ArrayList<String> credentials = helper.parseCredentials(inputStream);

		boolean credentialsCorrect = this.objectifyDao.checkCredentials(credentials.get(0), credentials.get(1));
		
		/*Objectify objectify = ObjectifyService.begin();
		ObjectifyService.register(UserEntity.class);
		
		UserEntity userEntity = objectify.query(UserEntity.class).filter("email", credentials.get(0)).get();
		if(userEntity.getPassword().equals(credentials.get(1))){
			credentialsCorrect = true;
		}
		
		ObjectifyService.register(EventEntity.class);
		Query<EventEntity> allEvents = objectify.query(EventEntity.class);
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		for(EventEntity eventEntity : allEvents){
			
			ArrayList<GroupEntity> groupEntities = eventEntity.getGroupEntity();
			for(GroupEntity groupEntity : groupEntities){
				
				ArrayList<UserEntity> userEntities = groupEntity.getUserEntities();
				for(UserEntity entity : userEntities){
					if(credentials.get(0).equals(entity.getEmail())){
						eventList.add(eventEntity);
					}
				}
			}
		}*/
		
		ArrayList<EventEntity> eventList = this.objectifyDao.getUserEvents(credentials.get(0));
		
		String sendToClient = this.helper.createHomePageXml(eventList);
		
		DataOutputStream dataOutputStream = new DataOutputStream(resp.getOutputStream());
		
		if(credentialsCorrect){
			dataOutputStream.writeBytes(sendToClient);
			
		}else{
			dataOutputStream.writeBytes("invalid");
		}
		
		dataOutputStream.flush();
		dataOutputStream.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doGet(req, resp);
	}
}
