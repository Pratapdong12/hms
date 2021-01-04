package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Blog;
import com.pratap.hms.model.Enquiry;

@Repository
public class BlogRepositoryImpl implements BlogRepository {

	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public void addBlog(Blog blog) {
		// TODO Auto-generated method stub
		getSession().merge(blog);
	}
	
	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public void updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		getSession().merge(blog);
	}

	@Override
	public void deleteBlog(int id) {
		// TODO Auto-generated method stub
		Blog blog = getBlogById(id);
		if (blog != null) {
			getSession().delete(blog);
		}
	}

	@Override
	public Blog getBlogById(int id) {
		// TODO Auto-generated method stub
		return (Blog) getSession().get(Blog.class, id);
	}

	@Override
	public List<Blog> getAllBlog() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Blog.class).list();
	}


	

}
