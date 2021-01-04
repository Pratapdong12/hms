package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Appointment;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Message;
import com.pratap.hms.model.Patient;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		getSession().merge(appointment);
	}
	
	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		getSession().merge(appointment);
	}

	@Override
	public void deleteAppointment(int id) {
		// TODO Auto-generated method stub
		Appointment appointment = getAppointmentById(id);
		if (appointment != null) {
			getSession().delete(appointment);
		}
	}

	@Override
	public Appointment getAppointmentById(int id) {
		// TODO Auto-generated method stub
		return (Appointment) getSession().get(Appointment.class, id);
	}
	
	@Override
	public List<Appointment> getAllAppointment() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Appointment.class).list();
	}

	@Override
	public List<Appointment> getAppointment(Doctor doctor) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Appointment.class).add(Restrictions.eq("doctor",doctor)).list();
	}
	
	@Override
	public List<Appointment> getAcceptedAppointment(Doctor doctor) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Appointment.class)
				 .add(Restrictions.eq("doctor",doctor))
				 .add(Restrictions.eq("status","accepted"))
				 .list();
	}
	

	
	@Override
	public List<Appointment> getAppointmentPatient(Patient patient) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Appointment.class).add(Restrictions.eq("patient",patient)).list();
	}
	
	@Override
	public List<Appointment> getAcceptedAppointmentPatient() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Appointment.class).add(Restrictions.eq("status","accepted")).list();
	}
	
	@Override
	public List<Appointment> getpendingAppointmentPatient() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Appointment.class).add(Restrictions.eq("status","pending")).list();
	}
	
	@Override
	public List<Appointment> acceptedAppointmentBill(int patientId) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Appointment.class).add(Restrictions.eq("patient.id",patientId)).list();
	}

	@Override
	public List<Appointment> getAcceptedNotPrescripted(Doctor doctor) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Appointment.class)
				 .add(Restrictions.eq("doctor",doctor))
				 .add(Restrictions.eq("status","accepted"))
				 .add(Restrictions.eq("pAdded","no"))
				 .list();
	}
	
}
