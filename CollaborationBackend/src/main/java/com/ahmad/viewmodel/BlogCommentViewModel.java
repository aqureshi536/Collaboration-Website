package com.ahmad.viewmodel;

import com.ahmad.model.Blog;
import com.ahmad.model.BlogComment;

public class BlogCommentViewModel {

	private Blog blog;
	private BlogComment blogComment;
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public BlogComment getBlogComment() {
		return blogComment;
	}
	public void setBlogComment(BlogComment blogComment) {
		this.blogComment = blogComment;
	}
	
	
	
	
}
