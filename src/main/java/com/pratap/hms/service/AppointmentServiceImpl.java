package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Appointment;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Patient;
import com.pratap.hms.repository.AppointmentRepository;


@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	

	@Override
	public void addAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		appointmentRepository.addAppointment(appointment);
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		appointmentRepository.updateAppointment(appointment);
	}

	@Override
	public void deleteAppointment(int id) {
		// TODO Auto-generated method stub
		appointmentRepository.deleteAppointment(id);
	}

	@Override
	public Appointment getAppointmentById(int id) {
		// TODO Auto-generated method stub
		return appointmentRepository.getAppointmentById(id);
	}

	@Override
	public List<Appointment> getAppointment(Doctor doctor) {
		// TODO Auto-generated method stub
		return appointmentRepository.getAppointment(doctor);
	}
	
	@Override
	public List<Appointment> getAcceptedAppointment(Doctor doctor) {
		// TODO Auto-generated method stub
		return appointmentRepository.getAcceptedAppointment(doctor);
	}
	@Override
	public List<Appointment> getAllAppointment() {
		// TODO Auto-generated method stub
		return appointmentRepository.getAllAppointment();
	}
	
	@Override
	public List<Appointment> getAppointmentPatient(Patient patient) {
		// TODO Auto-generated method stub
		return appointmentRepository.getAppointmentPatient(patient);
	}
	
	@Override
	public List<Appointment> getAcceptedAppointmentPatient() {
		// TODO Auto-generated method stub
		return appointmentRepository.getAcceptedAppointmentPatient();
	}
	
	@Override
	public List<Appointment> getpendingAppointmentPatient() {
		// TODO Auto-generated method stub
		return appointmentRepository.getpendingAppointmentPatient();
	}

	@Override
	public List<Appointment> acceptedAppointmentBill(int patientId) {
		// TODO Auto-generated method stub
		return appointmentRepository.acceptedAppointmentBill(patientId);
	}

	@Override
	public List<Appointment> getAcceptedNotPrescripted(Doctor doctor) {
		// TODO Auto-generated method stub
		return appointmentRepository.getAcceptedNotPrescripted(doctor);
	}
}
