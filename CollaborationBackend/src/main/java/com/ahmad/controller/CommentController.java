package com.ahmad.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ahmad.DAO.BlogCommentDAO;
import com.ahmad.DAO.BlogDAO;
import com.ahmad.model.Blog;
import com.ahmad.model.BlogComment;
import com.ahmad.utility.IdGenerator;
import com.ahmad.viewmodel.BlogModel;

@RestController
public class CommentController {

	@Autowired
	BlogComment blogComment;

	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	Blog blog;

/*	@GetMapping("/blogcomments/{blogId)")
	public ResponseEntity<List<BlogComment>> listComments(@PathVariable("blogId") String blogId) {
		List<BlogComment> listOfBlogs = blogCommentDAO.listBlogByCreatedAt(blogId);
		if (listOfBlogs != null) {
			return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<BlogComment>>(HttpStatus.OK);
	}*/

	/*
	 * @PostMapping("/blogcomments/{blogId}") public ResponseEntity<Void>
	 * createBlogComment(@RequestBody BlogComment blogComment,
	 * UriComponentsBuilder ucBuilder) {
	 * blogComment.setBlogCommentId(IdGenerator.generateId("BLGCOM")); Date date
	 * = new Date(); long time = date.getTime(); Timestamp timestamp = new
	 * Timestamp(time); blogComment.setCommentedAt(timestamp);
	 * 
	 * blogCommentDAO.saveOrUpdateBlogComment(blogComment); HttpHeaders
	 * httpHeaders = new HttpHeaders(); httpHeaders.setLocation(
	 * ucBuilder.path("/blogcomments/{blogId}").buildAndExpand(blogComment.
	 * getBlogCommentId()).toUri()); return new
	 * ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
	 * 
	 * }
	 */

	@PostMapping("/blogcomments/")
	public ResponseEntity<BlogComment> createBlogComment(@RequestBody BlogModel blogModel,
			UriComponentsBuilder ucBuilder) {
		blogComment.setBlogCommentId(IdGenerator.generateId("BLGCOM"));
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		blogComment.setCommentedAt(timestamp);
		blogComment.setUserId(blogModel.getUserId());
		blogComment.setBlogCommentContent(blogModel.getBlogComment());
		blogComment.setBlog(blogDAO.getBlog(blogModel.getBlogId()));
		blogCommentDAO.saveOrUpdateBlogComment(blogComment);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders
				.setLocation(ucBuilder.path("/blogcomments/{blogCommentId}").buildAndExpand(blogComment.getBlogCommentId()).toUri());
		return new ResponseEntity<BlogComment>(blogComment,httpHeaders, HttpStatus.CREATED);

	}

}
