package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.pratap.hms.model.Appointment;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Message;
import com.pratap.hms.model.Patient;

public interface AppointmentRepository {
	void addAppointment(Appointment appointment);

	void updateAppointment(Appointment appointment);

	void deleteAppointment(int id);

	Appointment getAppointmentById(int id);

	List<Appointment>getAllAppointment();
	
	List<Appointment>getAppointment(Doctor doctor);
	
	List<Appointment>getAcceptedAppointment(Doctor doctor);
	
	List<Appointment>getAcceptedNotPrescripted(Doctor doctor);
	
	List<Appointment>getAppointmentPatient(Patient patient);
	
	List<Appointment>getAcceptedAppointmentPatient();
	
	List<Appointment>getpendingAppointmentPatient();
	
	List<Appointment> acceptedAppointmentBill(int patientId);
}
