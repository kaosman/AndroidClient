package com.model;

import java.util.ArrayList;
import java.util.Date;


public class Event {
	
	private String eventName;
	private String eventDescription;
	private String eventDate;
	private String eventTime;
	private EventType eventType;
	private String eventLocation;
	private String eventOwnerId;
	private ArrayList<String> groupList;
	
	public Event(String eventName, String eventDescription, String eventDate,
			String eventTime, EventType eventType, String eventLocation,
			String eventOwnerId, ArrayList<String> groupList) {
		super();
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.eventOwnerId = eventOwnerId;
		this.groupList = new ArrayList<String>();
		this.groupList = groupList;
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

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
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

	public ArrayList<String> getGroupList() {
		return groupList;
	}

	public void setGroupList(ArrayList<String> groupList) {
		this.groupList = groupList;
	}
	
}
