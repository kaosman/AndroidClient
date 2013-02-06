package com.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Embedded;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Serialized;

public class GroupEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id String groupId;
	String groupName;
	String groupDescription;
	String groupOwnerId;
	
	@Serialized ArrayList<UserEntity> userEntities = new ArrayList<UserEntity>();

	public GroupEntity() {

	}
	
	public GroupEntity(String groupName, String groupDescription,
			String groupOwnerId, ArrayList<UserEntity> userEntities) {
		super();
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.groupOwnerId = groupOwnerId;
		this.userEntities = userEntities;
	}

	public GroupEntity(String groupId, String groupName,
			String groupDescription, String groupOwnerId,
			ArrayList<UserEntity> userEntities) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.groupOwnerId = groupOwnerId;
		this.userEntities = userEntities;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getGroupOwnerId() {
		return groupOwnerId;
	}

	public void setGroupOwnerId(String groupOwnerId) {
		this.groupOwnerId = groupOwnerId;
	}


	public ArrayList<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(ArrayList<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}
	
	
}