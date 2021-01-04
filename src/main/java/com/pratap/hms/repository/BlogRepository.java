package com.pratap.hms.repository;

import java.util.List;

import com.pratap.hms.model.Blog;


public interface BlogRepository {

	void addBlog(Blog blog);

	void updateBlog(Blog blog);

	void deleteBlog(int id);

	Blog getBlogById(int id);

	List<Blog>getAllBlog();
	

}
