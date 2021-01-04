package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Patient;

@Repository
public class PatientRepositoryImpl implements PatientRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addPatient(Patient patient) {
		
		getSession().merge(patient);
	}

	@Override
	public void updatePatient(Patient patient) {
		getSession().merge(patient);
	}

	@Override
	public void deletePatient(int id) {
		Patient patient = getPatientById(id);
		if (patient != null) {
			getSession().delete(patient);
		}
	}

	@Override
	public Patient getPatientById(int id) {
		return (Patient) getSession().get(Patient.class, id);
	}

	@Override
	public List<Patient> getAllPatient() {
		return getSession().createCriteria(Patient.class).list();
	}
	
	@Override
	public List<Patient> getAllPatientPending() {	
		
		return getSession().createCriteria(Patient.class).add(Restrictions.eq("status","pending")).list();
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
		Criteria criteria = getSession().createCriteria(Patient.class);
		Patient patient = (Patient) criteria.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password))
				.add(Restrictions.eq("status", "approved")).uniqueResult();
		if (patient != null) {
			Login = true;
		}
		return Login;
	}

	@Override
	public Patient checkEmail(String email) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(Patient.class);
		Patient patient = (Patient) criteria.add(Restrictions.eq("email", email)).uniqueResult();
		return patient;
	}

}
