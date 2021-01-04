package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Doctor;

@Repository
public class DoctorRepositoryImpl implements DoctorRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addDoctor(Doctor doctor) {
		
		getSession().merge(doctor);
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		getSession().merge(doctor);
	}

	@Override
	public void deleteDoctor(int id) {
		Doctor doctor = getDoctorById(id);
		if (doctor != null) {
			getSession().delete(doctor);
		}
	}

	@Override
	public Doctor getDoctorById(int id) {
		return (Doctor) getSession().get(Doctor.class, id);
	}

	@Override
	public List<Doctor> getAllDoctor() {
		return getSession().createCriteria(Doctor.class).list();
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
		// TODO Auto-generated method stub
		boolean Login = false;
		Criteria criteria = getSession().createCriteria(Doctor.class);
		Doctor doctor = (Doctor) criteria.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password)).uniqueResult();
		if (doctor != null) {
			Login = true;
		}
		return Login;
	}

	@Override
	public Doctor checkEmail(String email) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(Doctor.class);
		Doctor doctor = (Doctor) criteria.add(Restrictions.eq("email", email)).uniqueResult();
		return doctor;
	}
	
	@Override
	public Doctor getDoctorByEmail(String email) {
		Criteria criteria = getSession().createCriteria(Doctor.class);
		Doctor doctor = (Doctor) criteria.add(Restrictions.eq("email", email)).uniqueResult();
		return doctor;
	}
	
	@Override
	public List<Doctor> findByDepartment(int departmentId) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Doctor.class).add(Restrictions.eq("department.id",departmentId)).list();
	}


}
