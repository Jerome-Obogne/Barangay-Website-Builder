package com.entity;

public class Concern {

	private int messageId;
	private String name;
	private String email;
	private String message;
	private String isDelete;
	private String dateTime;
	
	
	/* Attribute use in Concern Table*/
	
	
	public Concern(int messageId, String name, String email, String message, String dateTime) {
		super();
		this.messageId = messageId;
		this.name = name;
		this.email = email;
		this.message = message;
		this.dateTime = dateTime;
	}


	
	public int getMessageId() {
		return messageId;
	}


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public String getMessage() {
		return message;
	}


	public String getIsDelete() {
		return isDelete;
	}


	public String getDateTime() {
		return dateTime;
	}
	
	
	

	
	
	
	
}
