package com.main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EventEntity;
import com.dao.GroupEntity;
import com.dao.PMF;
import com.dao.UserEntity;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.model.EventType;
import com.support.Helper;

@SuppressWarnings("serial")
public class EventManagerServlet_1 extends HttpServlet {
	
	private Helper helper;
	
	@Override
	public void init() throws ServletException {

		System.out.println("init of eventmanagerservlet");
		this.helper = new Helper();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		
		this.insertData();
		
		/*resp.setContentType("text/plain");

		InputStream inputStream = req.getInputStream();

		ArrayList<String> credentials = helper.parseCredentials(inputStream);
		
		System.out.println("username "+credentials.get(0));
			Objectify objectify = ObjectifyService.begin();
			ObjectifyService.register(EventEntity.class);
			EventEntity retreivedEventEntity = objectify.query(EventEntity.class).filter("eventName", "projectdemo").get();
			ArrayList<GroupEntity> groupEntities = retreivedEventEntity.getGroupEntity();

			ArrayList<GroupEntity> groupsOfUser = new ArrayList<GroupEntity>();

			for (GroupEntity entity : groupEntities) {

				ArrayList<UserEntity> userEntities = entity.getUserEntities();

				for (UserEntity userentity : userEntities) {

					if(credentials.get(1).equals(userentity.getPassword())){
							groupsOfUser.add(entity);
					}
					
				}
			}

		String sendToClient = this.helper.createGroupXmlTest(groupsOfUser);
		DataOutputStream dataOutputStream = new DataOutputStream(resp.getOutputStream());
		dataOutputStream.writeBytes(sendToClient);
		dataOutputStream.flush();
		dataOutputStream.close();
		
		Objectify objectify = ObjectifyService.begin();
		
		ObjectifyService.register(EventEntity.class);
		ObjectifyService.register(GroupEntity.class);
		ObjectifyService.register(UserEntity.class);
		
		UserEntity userEntity = new UserEntity("1", "ameyac.fun@gmail.com", "xxx", "10.09.1987", "Ameya", "123");
		UserEntity userEntity2 = new UserEntity("2", "kaosman@gmail.com", "yyy", "24.09.1986", "Kaushik", "456");
		
		ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
		userList.add(userEntity);
		userList.add(userEntity2);
		
		GroupEntity groupEntity = new GroupEntity("g1", "CBD", "ece750t11", "ameyac.fun@gmail.com", userList);
		
		ArrayList<GroupEntity> groupList = new ArrayList<GroupEntity>();
		groupList.add(groupEntity);
		
		EventEntity eventEntity = new EventEntity("e1", "projectdemo", "project demo", "23.11.2011", "09:00", "Meeting", "e5", "ameyac.fun@gmail.com", groupList);
		
		objectify.put(eventEntity);
		
		EventEntity retreived = objectify.query(EventEntity.class).filter("eventName", "projectdemo").get();
		System.out.println("retreived grp"+retreived.getGroupEntity().get(0).getGroupName());
		System.out.println("retreived user"+retreived.getGroupEntity().get(0).getUserEntities().get(0).getName());*/
		
	}
	
	private void insertData() {

		
		Objectify objectify = ObjectifyService.begin();
		ObjectifyService.register(UserEntity.class);
		ObjectifyService.register(EventEntity.class);
		ObjectifyService.register(GroupEntity.class);
		
		/*Query<EventEntity> allEvents = objectify.query(EventEntity.class);
		for(EventEntity entity : allEvents){
			System.out.println(" events "+entity.getEventName());
		}*/
		UserEntity userEntity = new UserEntity("u1", "ameyac.fun@gmail.com", "xxx", "10.09.1987", "Ameya", "123");
		UserEntity userEntity2 = new UserEntity("u2", "kaosman@gmail.com", "yyy", "26.09.1986", "Kaushik", "456");
		//UserEntity userEntity3 = new UserEntity("u3", "ameyac.fun@gmail.com", "zzz", "10.09.1987", "Ameya", "123");
		
		ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
		userList.add(userEntity);
		userList.add(userEntity2);
		/*Query<UserEntity> allUsers = objectify.query(UserEntity.class);
		for(UserEntity entity : allUsers){
			userList.add(entity);
		}*/
		
		GroupEntity groupEntity = new GroupEntity("g1", "CBD", "ece750t11", "ameyac.fun@gmail.com", userList);
		GroupEntity groupEntity2 = new GroupEntity("g2", "FSE", "ece651", "ameyac.fun@gmail.com", userList);
		//Query<GroupEntity> groupentities = objectify.query(GroupEntity.class);
		ArrayList<GroupEntity> groupList = new ArrayList<GroupEntity>();
		groupList.add(groupEntity);
		/*for(GroupEntity entity : groupentities){
			System.out.println(" groups "+entity.getGroupName());
			groupList.add(entity);
		}*/
		
		EventEntity eventEntity = new EventEntity("e1","projectdemo", "demo for CBD", "15.12.2011", "10:00", EventType.Meeting.name(), "E5", "ameyac.fun@gmail.com", groupList);
		EventEntity eventEntity2 = new EventEntity("e2","party", "birthday party", "06.12.2011", "10:00", EventType.Party.name(), "DC", "ameyac.fun@gmail.com", groupList); 
		objectify.put(userEntity);
		objectify.put(userEntity2);
		objectify.put(groupEntity);
		objectify.put(groupEntity2);
		objectify.put(eventEntity);
		objectify.put(eventEntity2);
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doGet(req,resp);
	}
}
