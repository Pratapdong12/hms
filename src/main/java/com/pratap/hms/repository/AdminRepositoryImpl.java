package com.pratap.hms.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Admin;


@Repository
public class AdminRepositoryImpl implements AdminRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void updateAdmin(Admin admin) {
		getSession().merge(admin);
		
	}
	
	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public boolean login(String email, String password) {
		boolean isLogin = false;
		Criteria criteria = getSession().createCriteria(Admin.class);
		Admin admin = (Admin) criteria.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password)).uniqueResult();
		if (admin != null) {
			isLogin = true;
		}
		return isLogin;
	}

	@Override
	public Admin getAdminById(int id) {
		// TODO Auto-generated method stub
		return (Admin) getSession().get(Admin.class, id);
	}
	
	@Override
	public Admin getAdminByEmail(String email) {
		Criteria criteria = getSession().createCriteria(Admin.class);
		Admin admin = (Admin) criteria.add(Restrictions.eq("email", email)).uniqueResult();
		return admin;
	}

}
