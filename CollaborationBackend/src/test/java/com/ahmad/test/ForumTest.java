package com.ahmad.test;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ahmad.DAO.ForumDAO;
import com.ahmad.DAO.ForumPostDAO;
import com.ahmad.model.Forum;
import com.ahmad.model.ForumPost;

public class ForumTest {

	public static void main(String[] args){
		
		AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext();
		context.scan("com.ahmad");
		context.refresh();
		
		ForumDAO forumDAO =(ForumDAO)context.getBean("forumDAO");
		Forum forum = new Forum();
		ForumPostDAO forumPostDAO =(ForumPostDAO)context.getBean("forumPostDAO");
		ForumPost forumPost = new ForumPost();
		
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		
		forum.setForumId("FRM001");
		forum.setForumTitle("New Forum");
		forum.setForumDescription("Forum Created Now");
		forum.setStatus('A');
		
		forum.setCreatedAt(timestamp);
		forum.setModifiedAt(timestamp);
		forum.setUserId("USR001");
		forumDAO.saveOrUpdateForum(forum);
		
		
		forumPost.setForumPostContent("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Suscipit est explicabo molestias perspiciatis, non iusto"
				+ "reiciendis iure perferendis!");
		forumPost.setForum(forum);
		forumPost.setPostedAt(timestamp);
		forumPost.setUserId("USR0001");
		forumPostDAO.saveOrUpdateForumPost(forumPost);
		
		
	}
}
