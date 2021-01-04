package com.pratap.hms.service;

import java.util.List;

import com.pratap.hms.model.Appointment;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Patient;

public interface AppointmentService {
	void addAppointment(Appointment appointment);

	void updateAppointment(Appointment appointment);

	void deleteAppointment(int id);

	Appointment getAppointmentById(int id);

	List<Appointment> getAppointment(Doctor doctor);
	
	List<Appointment> getAllAppointment();
	
	List<Appointment> getAcceptedAppointment(Doctor doctor);
	
	List<Appointment> getAcceptedNotPrescripted(Doctor doctor);
	
	List<Appointment> getAppointmentPatient(Patient patient);
	
	List<Appointment> getAcceptedAppointmentPatient();
	
	List<Appointment> getpendingAppointmentPatient();
	
	List<Appointment> acceptedAppointmentBill(int patientId);
}
