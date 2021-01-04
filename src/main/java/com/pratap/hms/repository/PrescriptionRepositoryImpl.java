package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Appointment;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Prescription;

@Repository
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addPrescription(Prescription prescription) {
		// TODO Auto-generated method stub
		getSession().merge(prescription);
	}
	
	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public void updatePrescription(Prescription prescription) {
		// TODO Auto-generated method stub
		getSession().merge(prescription);
	}

	@Override
	public void deletePrescription(int id) {
		// TODO Auto-generated method stub
		Prescription prescription = getPrescriptionById(id);
		if (prescription != null) {
			getSession().delete(prescription);
		}
	}

	@Override
	public Prescription getPrescriptionById(int id) {
		// TODO Auto-generated method stub
		return (Prescription) getSession().get(Prescription.class, id);
	}

	@Override
	public List<Prescription> getPrescription(String a) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Prescription.class).add(Restrictions.eq("doctor",a)).list();
}
	@Override
	public List<Prescription> getPrescriptionPatient(String a) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Prescription.class).add(Restrictions.eq("patient",a)).list();
	}

	@Override
	public List<Prescription> prescriptionBill(int patientId) {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Prescription.class).add(Restrictions.eq("pat.id",patientId)).list();
	}

	@Override
	public List<Prescription> getNoBill() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Prescription.class).add(Restrictions.eq("bAdded","no")).list();
	}
	@Override
	public List<Prescription> loadPatientByPrescription(int prescriptionId) {
		// TODO Auto-generated method stub
		
		return getSession().createCriteria(Prescription.class)
				.add(Restrictions.eq("id",prescriptionId))
				.add(Restrictions.eq("bAdded","no"))
				 .list();
	}
	
}
