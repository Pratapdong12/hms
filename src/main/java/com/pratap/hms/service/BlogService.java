package com.pratap.hms.service;

import java.util.List;

import com.pratap.hms.model.Blog;


public interface BlogService {

	void addBlog(Blog blog);

	void updateBlog(Blog blog);

	void deleteBlog(int id);

	Blog getBlogById(int id);

	List<Blog> getAllBlog();
}
