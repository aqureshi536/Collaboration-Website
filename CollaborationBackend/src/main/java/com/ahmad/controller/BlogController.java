package com.ahmad.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ahmad.DAO.BlogCommentDAO;
import com.ahmad.DAO.BlogDAO;
import com.ahmad.model.Blog;
import com.ahmad.model.BlogComment;

@RestController
public class BlogController {

	@Autowired
	Blog blog;

	@Autowired
	BlogComment blogComment;

	@Autowired
	BlogDAO blogDAO;

	@Autowired
	BlogCommentDAO blogCommentDAO;

	@GetMapping("/blogs/")
	public ResponseEntity<List<Blog>> listAllBlogs() {
		List<Blog> listOfBlogs = blogDAO.listBlogs();
		if (listOfBlogs.isEmpty()) {
			return new ResponseEntity <List<Blog>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity < List < Blog >> (listOfBlogs, HttpStatus.OK);
	}
	
	@PostMapping("/blogs/")
	public ResponseEntity<Void> createBlog(@RequestBody Blog blog,UriComponentsBuilder ucBuilder){
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		blog.setCreatedAt(timestamp);
		blog.setModifiedAt(timestamp);
		blog.setStatus('P');
		
		blog.setUserId("USR001");  //  to be changed when developed user module		
		
		blogDAO.saveOrUpdateBlog(blog);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ucBuilder.path("/blog/{id}").buildAndExpand(blog.getBlogId()).toUri());
		return new ResponseEntity<Void>(httpHeaders,HttpStatus.CREATED);
		
		
	}

}
