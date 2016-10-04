package com.ahmad.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.ahmad.DAO.BlogCommentDAO;
import com.ahmad.DAO.BlogDAO;
import com.ahmad.model.Blog;
import com.ahmad.model.BlogComment;
import com.ahmad.utility.IdGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

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
		List<Blog> listOfBlogs = blogDAO.listBlogsByCreatedAt();
		if (listOfBlogs.isEmpty()) {
			return new ResponseEntity <List<Blog>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity < List < Blog >> (listOfBlogs, HttpStatus.OK);
	}
	
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId") String blogId){
		this.blog = blogDAO.getBlog(blogId);
		if(this.blog == null){
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(this.blog,HttpStatus.OK);
	}
	
	@PostMapping("/blogs/")
	public ResponseEntity<Blog> createBlog(@RequestParam(value="blog") String blogJson,@RequestParam(value="blogImage")MultipartFile file,HttpServletRequest request,UriComponentsBuilder ucBuilder){
		System.out.println(blogJson.toString());
		 JSONObject jsonObj = new JSONObject(blogJson);
		// JsonNode jsonNode = convertJsonFormat(jsonObj);		 
		 ObjectMapper mapper = new ObjectMapper().registerModule(new JsonOrgModule());
		 blog=mapper.convertValue(jsonObj, Blog.class);
		 
		String directory = "E:\\beone\\blog\\";
		
	
		
		blog.setBlogId(IdGenerator.generateId("BLG"));
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		blog.setCreatedAt(timestamp);
		blog.setModifiedAt(timestamp);
		blog.setStatus('P');
		
		blog.setUserId("USR001");  //  to be changed when developed user module		
		
		blogDAO.saveOrUpdateBlog(blog);
		
		if(file!=null){
			if(!Files.exists(Paths.get(directory))){
				try{
					Files.createDirectories(Paths.get(directory));
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
			try{
				file.transferTo(new File(directory+blog.getBlogId()+".png"));
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ucBuilder.path("/blog/{id}").buildAndExpand(blog.getBlogId()).toUri());
		return new ResponseEntity<Blog>(blog,httpHeaders,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<Blog> updateBlog(@PathVariable("blogId")String blogId,@RequestBody Blog blog){
		this.blog = blogDAO.getBlog(blogId);
		if(this.blog==null){
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		this.blog.setBlogName(blog.getBlogName());
		this.blog.setBlogDescription(blog.getBlogDescription());
		Date date = new Date();
	long time= 	date.getTime();
	Timestamp timestamp = new Timestamp(time);
		this.blog.setModifiedAt(timestamp);
		blogDAO.saveOrUpdateBlog(this.blog);
		return new ResponseEntity<Blog>(this.blog,HttpStatus.OK);
	}
	
	@DeleteMapping("/blogs/{blogId}")
	public ResponseEntity<Blog> deleteBlog(@PathVariable("blogId")String blogId){
	this.blog = blogDAO.getBlog(blogId);
	if(this.blog==null){
		System.out.println("Blog not exist to delete");
		return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		
	}
	
		blogDAO.deleteBlog(blogId);
		return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
	
	}

}
