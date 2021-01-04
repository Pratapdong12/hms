package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Blog;
import com.pratap.hms.repository.BlogRepository;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;
	@Override
	public void addBlog(Blog blog) {
		// TODO Auto-generated method stub
		blogRepository.addBlog(blog);
	}

	@Override
	public void updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		blogRepository.updateBlog(blog);
	}

	@Override
	public void deleteBlog(int id) {
		// TODO Auto-generated method stub
		blogRepository.deleteBlog(id);
	}

	@Override
	public Blog getBlogById(int id) {
		// TODO Auto-generated method stub
		return blogRepository.getBlogById(id);
	}

	@Override
	public List<Blog> getAllBlog() {
		// TODO Auto-generated method stub
		return blogRepository.getAllBlog();
	}
}
