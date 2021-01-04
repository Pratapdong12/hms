package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Enquiry;
import com.pratap.hms.model.Message;
import com.pratap.hms.model.Patient;
import com.pratap.hms.model.Schedule;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addMessage(Message message) {
		// TODO Auto-generated method stub
		getSession().merge(message);
	}
	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public void deleteMessage(int id) {
		// TODO Auto-generated method stub
		Message message = getMessageById(id);
		if (message != null) {
			getSession().delete(message);
		}
	}

	@Override
	public Message getMessageById(int id) {
		// TODO Auto-generated method stub
		return (Message) getSession().get(Message.class, id);
	}

	@Override
	public List<Message> getAllMessage() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Message.class).list();
	}
	@Override
	public List<Message> getSentMessage(Admin admin) {	
		
		return getSession().createCriteria(Message.class).add(Restrictions.eq("admin",admin)).list();
	}
	
	@Override
	public List<Message> getSentMessageDoctor(Doctor doctor) {	
		
		return getSession().createCriteria(Message.class).add(Restrictions.eq("doctor",doctor)).list();
	}
	
	@Override
	public List<Message> getSentMessagePatient(Patient patient) {	
		
		return getSession().createCriteria(Message.class).add(Restrictions.eq("patient",patient)).list();
	}
	

	
	@Override
	public List<Message> getInboxMessage(String a) {	
		
		return getSession().createCriteria(Message.class).add(Restrictions.eq("receiver",a)).list();
	}
	
	@Override
	public void updateMessage(Message message) {
		// TODO Auto-generated method stub
		getSession().merge(message);
	}
}
