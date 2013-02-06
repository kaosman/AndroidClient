package com.dao;

import java.util.ArrayList;

import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.model.Event;
import com.model.EventType;
import com.model.Group;
import com.model.User;

public class ObjectifyDao {

	
	public boolean checkCredentials(String username,String password){
		
		Objectify objectify = ObjectifyService.begin();
		ObjectifyService.register(UserEntity.class);
		UserEntity userEntity = objectify.query(UserEntity.class).filter("email", username).get();
		
		if(password.equals(userEntity.password)){
			return true;
		}
		return false;
		
	}
	
	public ArrayList<EventEntity> getUserEvents(String userid){
		
		Objectify objectify = ObjectifyService.begin();
		ObjectifyService.register(EventEntity.class);
		Query<EventEntity> allEvents = objectify.query(EventEntity.class);
		ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();
		
		QueryResultIterator<EventEntity> iterator = allEvents.iterator();
		while(iterator.hasNext()){
			EventEntity eventEntity = iterator.next();
			ArrayList<GroupEntity> groupEntities = eventEntity.getGroupEntity();
			for(GroupEntity groupEntity : groupEntities){
				
				ArrayList<UserEntity> userEntities = groupEntity.getUserEntities();
				for(UserEntity entity : userEntities){
					if(userid.equals(entity.getEmail())){
						eventList.add(eventEntity);
					}
				}
			}
		}
		return eventList;
	}
	
	public UserEntity getUserEntity(String username){
		
		Objectify objectify = ObjectifyService.begin();
		UserEntity userEntity = objectify.query(UserEntity.class).filter("email", username).get();
		return userEntity;
	}
	
	public ArrayList<GroupEntity> getGroupsOfUser(String username){
		
		Objectify objectify = ObjectifyService.begin();
		ArrayList<GroupEntity> groupList = new ArrayList<GroupEntity>();
		
		UserEntity userEntity = objectify.query(UserEntity.class).filter("email", username).get();
		
		return null;
	}
	
	public void createEvent(Event event){
		
		Objectify objectify = ObjectifyService.begin();
		ObjectifyService.register(EventEntity.class);
		ObjectifyService.register(GroupEntity.class);
		
		ArrayList<String> groupList = event.getGroupList();
		ArrayList<GroupEntity> groupEntities = new ArrayList<GroupEntity>();
		for(String groupName : groupList){
			groupEntities.add(objectify.query(GroupEntity.class).filter("groupname",groupName).get());
		}
		
		
		EventEntity eventEntity = new EventEntity(event.getEventName(), event.getEventDescription(), event.getEventDate(), event.getEventTime(), event.getEventType().name(), event.getEventLocation(), event.getEventOwnerId(), groupEntities);
		objectify.put(eventEntity);
	}
	
	public ArrayList<String> getGroupMembers(ArrayList<String> groupList){
		
		Objectify objectify = ObjectifyService.begin();
		ObjectifyService.register(GroupEntity.class);

		ArrayList<String> groupMembers = new ArrayList<String>();
		for (String groupName : groupList) {
			
			GroupEntity groupEntity = objectify.query(GroupEntity.class).filter("groupname", groupName).get();
			ArrayList<UserEntity> userEntities = groupEntity.getUserEntities();
			
			for (UserEntity userEntity : userEntities) {
				groupMembers.add(userEntity.name);
			}
		}
		return groupMembers;
		
	}

	public void createGroup(Group group) {
		
		Objectify objectify = ObjectifyService.begin();
		ObjectifyService.register(EventEntity.class);
		ObjectifyService.register(GroupEntity.class);
		
		ArrayList<UserEntity> userEntities = new ArrayList<UserEntity>();
		ArrayList<String> userIdList = group.getMembers();
		for(String userid : userIdList){
			userEntities.add(objectify.query(UserEntity.class).filter("email",userid).get());
		}
		
		GroupEntity groupEntity = new GroupEntity(group.getGroupName(), group.getGroupDescription(), group.getGroupOwnerEmail(), userEntities);
		objectify.put(groupEntity);
	}
	
	public void addUserToGroup(String userid,String groupName){
		
		Objectify objectify = ObjectifyService.begin();
		ObjectifyService.register(EventEntity.class);
		ObjectifyService.register(GroupEntity.class);
		
		UserEntity userEntity = objectify.query(UserEntity.class).filter("email",userid).get();
		GroupEntity groupEntity = objectify.query(GroupEntity.class).filter("groupname", groupName).get();
		ArrayList<UserEntity> userEntities = groupEntity.getUserEntities();
		userEntities.add(userEntity);
		objectify.put(groupEntity);
	}
	
	public Group getGroupDescription(String groupName){
		
		Objectify objectify = ObjectifyService.begin();
		ObjectifyService.register(GroupEntity.class);
		
		GroupEntity groupEntity = objectify.query(GroupEntity.class).filter("groupname", groupName).get();
		
		ArrayList<UserEntity> userEntities = groupEntity.getUserEntities();
		ArrayList<String> members = new ArrayList<String>();
		
		for(UserEntity entity : userEntities){
			
			members.add(entity.getName());
		}
		Group group = new Group(groupEntity.getGroupName(), groupEntity.getGroupDescription(), groupEntity.getGroupOwnerId(), members);
		return group;
	}
	
	public Event getEventDescription(String eventName){
		
		Objectify objectify = ObjectifyService.begin();
		ObjectifyService.register(EventEntity.class);
		ObjectifyService.register(GroupEntity.class);
		
		EventEntity eventEntity = objectify.query(EventEntity.class).filter("eventname", eventName).get();
		
		ArrayList<GroupEntity> groupEntities = eventEntity.getGroupEntity();
		ArrayList<String> groupList = new ArrayList<String>();
		for(GroupEntity entity : groupEntities){
			groupList.add(entity.getGroupName());
		}
		Event event = new Event(eventEntity.getEventName(), eventEntity.getEventDescription(), eventEntity.getEventDate(), eventEntity.getEventTime(), EventType.valueOf(eventEntity.getEventType()), eventEntity.getEventLocation(), eventEntity.getEventLocation(), groupList);
		return event;
	}
	
}
