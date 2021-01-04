package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Patient;
import com.pratap.hms.model.Schedule;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		getSession().merge(schedule);
		
	}

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public void updateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		getSession().merge(schedule);
		
	}

	@Override
	public void deleteSchedule(int id) {
		// TODO Auto-generated method stub
		Schedule schedule = getScheduleById(id);
		if (schedule != null) {
			getSession().delete(schedule);
		}
		
	}

	@Override
	public Schedule getScheduleById(int id) {
		// TODO Auto-generated method stub
		return (Schedule) getSession().get(Schedule.class, id);
	}

	@Override
	public List<Schedule> getAllSchedule() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Schedule.class).list();
	}

	@Override
	public List<Schedule> getScheduleDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Schedule.class).add(Restrictions.eq("doctor",doctor)).list();
	}
	@Override
	public List<Schedule> findByDoctor(int doctorId) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Schedule.class).add(Restrictions.eq("doctor.id",doctorId)).list();
	}
}