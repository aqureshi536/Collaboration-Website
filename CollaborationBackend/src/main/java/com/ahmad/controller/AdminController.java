package com.ahmad.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.ahmad.DAO.BlogDAO;
import com.ahmad.DAO.ForumDAO;
import com.ahmad.DAO.UserDetailDAO;
import com.ahmad.model.Blog;
import com.ahmad.model.Forum;
import com.ahmad.model.UserDetail;
import com.ahmad.viewmodel.BlogAdminModel;
import com.ahmad.viewmodel.ForumAdminModel;

@Controller
public class AdminController {

	@Autowired
	Blog blog;
	@Autowired
	BlogDAO blogDAO;

	@Autowired
	Forum forum;
	@Autowired
	ForumDAO forumDAO;

	@Autowired
	UserDetail userDetail;

	@Autowired
	UserDetailDAO userDetailDAO;

	@GetMapping("/admin/blogs/")
	public ResponseEntity<List<BlogAdminModel>> getUnApprovedBlogs() {
		BlogAdminModel blogAdminModel = new BlogAdminModel();
		List<BlogAdminModel> listOfPendingBlogs = new ArrayList<>();
		List<Blog> listOfBlogs = blogDAO.listBlogsByCreatedAt('P');
		if (listOfBlogs != null && !listOfBlogs.isEmpty()) {
			listOfBlogs.forEach(blog -> {
				blogAdminModel.setBlog(blog);
				userDetail = userDetailDAO.getUserDetail(blog.getUserId());
				blogAdminModel.setEmail(userDetail.getEmail());
				blogAdminModel.setName(userDetail.getName());
				String role = userDetail.getUserAuthorities().getAuthority();
				switch (role) {
				case "ROLE_STUDENT":
					role = "STUDENT";
					break;
				case "ROLE_EMPLOYEE":
					role = "EMPLOYEE";
					break;
				case "ROLE_ALUMNI":
					role = "ALUMNI";
					break;

				}
				blogAdminModel.setRole(role);
				listOfPendingBlogs.add(blogAdminModel);
			});
			return new ResponseEntity<List<BlogAdminModel>>(listOfPendingBlogs, HttpStatus.OK);
		}
		return new ResponseEntity<List<BlogAdminModel>>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/admin/forums/")
	public ResponseEntity<List<ForumAdminModel>> getUnApprovredForums() {
		ForumAdminModel forumAdminModel = new ForumAdminModel();
		List<ForumAdminModel> listOfPendingForums = new ArrayList<>();
		List<Forum> listOfForums = forumDAO.listForumByCreatedAt('P');
		if (listOfForums != null && !listOfForums.isEmpty()) {
			listOfForums.forEach(forum -> {
				forumAdminModel.setForum(forum);
				userDetail = userDetailDAO.getUserDetail(forum.getUserId());
				forumAdminModel.setEmail(userDetail.getEmail());
				forumAdminModel.setName(userDetail.getName());
				String role = userDetail.getUserAuthorities().getAuthority();
				switch (role) {
				case "ROLE_STUDENT":
					role = "STUDENT";
					break;
				case "ROLE_EMPLOYEE":
					role = "EMPLOYEE";
					break;
				case "ROLE_ALUMNI":
					role = "ALUMNI";
					break;

				}

				forumAdminModel.setRole(role);
				listOfPendingForums.add(forumAdminModel);
			});
			return new ResponseEntity<List<ForumAdminModel>>(listOfPendingForums, HttpStatus.OK);
		}
		return new ResponseEntity<List<ForumAdminModel>>(HttpStatus.NO_CONTENT);

	}

	@PutMapping("/mange/blog/approve/{blogId}")
	public ResponseEntity<Void> approveBlog(@PathVariable("blogId") String blogId) {
		blog = blogDAO.getBlog(blogId);
		if (blog == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		blog.setStatus('A');
		blogDAO.saveOrUpdateBlog(blog);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/manage/blog/disapprove/{blogId}")
	public ResponseEntity<Void> disapproveBlog(@PathVariable("blogId") String blogId) {
		blog = blogDAO.getBlog(blogId);
		if (blog == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		blog.setStatus('R');
		blogDAO.saveOrUpdateBlog(blog);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@PutMapping("/manage/forum/approve/{forumId}")
	public ResponseEntity<Void> approveForum(@PathVariable("forumId") String forumId) {
		forum = forumDAO.getForum(forumId);
		if (forum == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		forum.setStatus('A');
		forumDAO.saveOrUpdateForum(forum);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/manage/forum/disapprove/{forumId}")
	public ResponseEntity<Void> disApproveForum(@PathVariable("forumId") String forumId) {
		forum = forumDAO.getForum(forumId);
		if (forum == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		forum.setStatus('R');
		forumDAO.saveOrUpdateForum(forum);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
