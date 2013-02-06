package com.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Embedded;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Serialized;

public class EventEntity implements Serializable{

	@Id String eventId;
	String eventName;
	String eventDescription;
	String eventDate;
	String eventTime;
	String eventType;
	String eventLocation;
	String eventOwnerId;
	
	@Serialized ArrayList<GroupEntity> groupEntity = new ArrayList<GroupEntity>();

	public EventEntity() {

	}
	
	
	public EventEntity(String eventName, String eventDescription,
			String eventDate, String eventTime, String eventType,
			String eventLocation, String eventOwnerId,
			ArrayList<GroupEntity> groupEntity) {
		super();
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.eventOwnerId = eventOwnerId;
		this.groupEntity = groupEntity;
	}


	public EventEntity(String eventId, String eventName,
			String eventDescription, String eventDate, String eventTime,
			String eventType, String eventLocation, String eventOwnerId,
			ArrayList<GroupEntity> groupEntity) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.eventOwnerId = eventOwnerId;
		this.groupEntity = groupEntity;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventOwnerId() {
		return eventOwnerId;
	}

	public void setEventOwnerId(String eventOwnerId) {
		this.eventOwnerId = eventOwnerId;
	}

	public ArrayList<GroupEntity> getGroupEntity() {
		return groupEntity;
	}

	public void setGroupEntity(ArrayList<GroupEntity> groupEntity) {
		this.groupEntity = groupEntity;
	}

	
}
