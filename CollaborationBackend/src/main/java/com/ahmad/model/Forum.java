package com.ahmad.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Forum {
	@Id
	private String forumId;
	private String userId;
	private String forumTitle;
	private String forumDescription;
	private long noOfPosts;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private char status;// Will include A,P,R as keyword for Approved,Pending
						// and Rejected respectively

	public String getForumId() {
		return forumId;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}

	public String getForumDescription() {
		return forumDescription;
	}

	public void setForumDescription(String forumDescription) {
		this.forumDescription = forumDescription;
	}

	public long getNoOfPosts() {
		return noOfPosts;
	}

	public void setNoOfPosts(long noOfPosts) {
		this.noOfPosts = noOfPosts;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
