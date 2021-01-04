package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Patient;
import com.pratap.hms.repository.PatientRepository;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void addPatient(Patient patient) {
		// TODO Auto-generated method stub
		patientRepository.addPatient(patient);
		
	}

	@Override
	public void updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		patientRepository.updatePatient(patient);
	}

	@Override
	public void deletePatient(int id) {
		// TODO Auto-generated method stub
		patientRepository.deletePatient(id);
	}

	@Override
	public Patient getPatientById(int id) {
		// TODO Auto-generated method stub
		return patientRepository.getPatientById(id);
	}

	@Override
	public List<Patient> getAllPatient() {
		// TODO Auto-generated method stub
		return patientRepository.getAllPatient();
	}
	
	@Override
	public List<Patient> getAllPatientPending() {
		// TODO Auto-generated method stub
		return patientRepository.getAllPatientPending();
	}

	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return patientRepository.login(email, password);
	}

	@Override
	public Patient checkEmail(String email) {
		// TODO Auto-generated method stub
		return patientRepository.checkEmail(email);
	}

}
