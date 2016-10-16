package com.ahmad.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.ahmad.utility.IdGenerator;

@Entity
@Component
public class Friend {

	@Id
	private String id;
	private String user1;
	private String user2;
	private char status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getUser2() {
		return user2;
	}

	public void setUser2(String user2) {
		this.user2 = user2;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	public Friend() {
		this .id = IdGenerator.generateId("FRN");
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", user1=" + user1 + ", user2=" + user2 + ", status=" + status + "]";
	}
	

}
