package com.pratap.hms.service;

import java.util.List;

import com.pratap.hms.model.Patient;

public interface PatientService {
	void addPatient(Patient patient);

	void updatePatient(Patient patient);

	void deletePatient(int id);

	Patient getPatientById(int id);

	List<Patient> getAllPatient();
	
	List<Patient> getAllPatientPending();

	boolean login(String email, String password);

	Patient checkEmail(String email);
	
}
