package com.ahmad.model;

import java.util.Date;

public class OutputMessage extends Message{

	
	private String email;
	private Date time;
	
	public OutputMessage (Message message,Date time,String email){
		super(message.getChatMessage());
		this.email= email;
		this.time = time;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
