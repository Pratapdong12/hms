package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Department;
import com.pratap.hms.model.Enquiry;

@Repository
public class EnquiryRepositoryImpl implements EnquiryRepository {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addEnquiry(Enquiry enquiry) {
		// TODO Auto-generated method stub
		getSession().merge(enquiry);
	}
	
	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public void updateEnquiry(Enquiry enquiry) {
		// TODO Auto-generated method stub
		getSession().merge(enquiry);
	}

	@Override
	public void deleteEnquiry(int id) {
		// TODO Auto-generated method stub
		Enquiry enquiry = getEnquiryById(id);
		if (enquiry != null) {
			getSession().delete(enquiry);
		}
	}

	@Override
	public Enquiry getEnquiryById(int id) {
		// TODO Auto-generated method stub
		return (Enquiry) getSession().get(Enquiry.class, id);
	}

	@Override
	public List<Enquiry> getAllEnquiry() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Enquiry.class).list();
	}
	
	
}
