package com.ahmad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
