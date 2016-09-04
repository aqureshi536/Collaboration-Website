package com.ahmad.DAO;

import java.util.List;

import com.ahmad.model.Blog;

public interface BlogDAO {

	void saveOrUpdateBlog(Blog blog);
	
	void deleteBlog(String blogId);
	
	Blog getBlog(String blogId);
	
	List<Blog> listBlogs();
}
