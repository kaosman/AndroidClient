package com.model;

public enum EventType {

	Birthday(1),Meeting(2),Anniversary(3),Party(4),Other(5);
	
	int value;
	
	private EventType(int value) {
		this.value=value;
	}
}
