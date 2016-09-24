package com.ahmad.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ahmad.DAO.BlogCommentDAO;
import com.ahmad.model.Blog;
import com.ahmad.model.BlogComment;
import com.ahmad.utility.IdGenerator;

@RestController
public class CommentController {

	@Autowired
	BlogComment blogComment;

	@Autowired
	BlogCommentDAO blogCommentDAO;

	@GetMapping("/blogcomments/{blogId)")
	public ResponseEntity<List<BlogComment>> listComments(@PathVariable("blogId") String blogId) {
		List<BlogComment> listOfBlogs = blogCommentDAO.listBlogByCreatedAt(blogId);
		if (listOfBlogs != null) {
			return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<BlogComment>>(HttpStatus.OK);
	}

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
	public ResponseEntity<Void> createBlogComment(@RequestBody Blog blog, @RequestBody String comment,
			UriComponentsBuilder ucBuilder) {
		blogComment.setBlogCommentId(IdGenerator.generateId("BLGCOM"));
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		blogComment.setCommentedAt(timestamp);
		blogComment.setUserId("USR001");
		blogComment.setBlogCommentContent(comment);
		blogComment.setBlog(blog);
		blogCommentDAO.saveOrUpdateBlogComment(blogComment);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders
				.setLocation(ucBuilder.path("/blogcomments/").buildAndExpand(blogComment.getBlogCommentId()).toUri());
		return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);

	}

}
