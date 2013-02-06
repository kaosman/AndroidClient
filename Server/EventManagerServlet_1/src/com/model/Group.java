package com.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private String groupName;
	private String groupDescription;
	private String groupOwnerEmail;
	private ArrayList<String> members;
	
	public Group(String groupName, String groupDescription,ArrayList<String> groupMembers){
		super();
		this.groupName = groupName;
		this.members = groupMembers;
		this.groupDescription = groupDescription;
	}
	
	public Group(String groupName, String groupDescription,
			String groupOwnerEmail, ArrayList<String> members) {
		super();
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.groupOwnerEmail = groupOwnerEmail;
		this.members = new ArrayList<String>();
		this.members = members;
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

	public String getGroupOwnerEmail() {
		return groupOwnerEmail;
	}

	public void setGroupOwnerEmail(String groupOwnerEmail) {
		this.groupOwnerEmail = groupOwnerEmail;
	}

	public ArrayList<String> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	
	
}
