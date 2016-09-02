package com.ahmad.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class BlogComment {
	@Id
	private String blogCommentId;
	private String blogId;
	private String userId;
	private String blogCommentContent;
	private Timestamp commentedAt;

	public String getBlogCommentId() {
		return blogCommentId;
	}

	public void setBlogCommentId(String blogCommentId) {
		this.blogCommentId = blogCommentId;
	}

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

	public String getBlogCommentContent() {
		return blogCommentContent;
	}

	public void setBlogCommentContent(String blogCommentContent) {
		this.blogCommentContent = blogCommentContent;
	}

	public Timestamp getCommentedAt() {
		return commentedAt;
	}

	public void setCommentedAt(Timestamp commentedAt) {
		this.commentedAt = commentedAt;
	}

}
