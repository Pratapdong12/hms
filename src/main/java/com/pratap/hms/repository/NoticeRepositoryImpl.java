package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Notice;

@Repository
public class NoticeRepositoryImpl implements NoticeRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		getSession().merge(notice);
	}
	
	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}


	@Override
	public void updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		getSession().merge(notice);
	}

	

	@Override
	public Notice getNoticeById(int id) {
		// TODO Auto-generated method stub
		return (Notice) getSession().get(Notice.class, id);
	}
	
	@Override
	public void deleteNotice(int id) {
		// TODO Auto-generated method stub
		Notice notice = getNoticeById(id);
		if (notice != null) {
			getSession().delete(notice);
		}
	}

	@Override
	public List<Notice> getAllNotice() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Notice.class).list();
	}
}
