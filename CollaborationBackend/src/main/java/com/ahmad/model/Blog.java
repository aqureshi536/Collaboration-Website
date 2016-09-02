package com.ahmad.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Blog {
	@Id
	private String blogId;
	private String userId;
	private String blogDescription;
	private long noOfComments;
	private char status; // Will include A,P,R as keyword for Approved,Pending
							// and Rejected respectively
	private Timestamp createdAt;
	private Timestamp modifiedAt;

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public long getNoOfComments() {
		return noOfComments;
	}

	public void setNoOfComments(long noOfComments) {
		this.noOfComments = noOfComments;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
