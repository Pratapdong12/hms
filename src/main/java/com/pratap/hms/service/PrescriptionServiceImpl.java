package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Appointment;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Prescription;
import com.pratap.hms.repository.PrescriptionRepository;

@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {

	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@Override
	public void addPrescription(Prescription prescription) {
		// TODO Auto-generated method stub
		prescriptionRepository.addPrescription(prescription);
	}

	@Override
	public void updatePrescription(Prescription prescription) {
		// TODO Auto-generated method stub
		prescriptionRepository.updatePrescription(prescription);
	}

	@Override
	public void deletePrescription(int id) {
		// TODO Auto-generated method stub
		prescriptionRepository.deletePrescription(id);
	}

	@Override
	public Prescription getPrescriptionById(int id) {
		// TODO Auto-generated method stub
		return prescriptionRepository.getPrescriptionById(id);
	}

	@Override
	public List<Prescription> getPrescription(String a) {
		// TODO Auto-generated method stub
		return prescriptionRepository.getPrescription(a);
	}
	
	@Override
	public List<Prescription> getPrescriptionPatient(String a) {
		// TODO Auto-generated method stub
		return prescriptionRepository.getPrescriptionPatient(a);
	}

	@Override
	public List<Prescription> prescriptionBill(int patientId) {
		// TODO Auto-generated method stub
		return prescriptionRepository.prescriptionBill(patientId);
	}
	
	@Override
	public List<Prescription> getNoBill() {
		// TODO Auto-generated method stub
		return prescriptionRepository.getNoBill();
	}

	@Override
	public List<Prescription> loadPatientByPrescription(int prescriptionId) {
		// TODO Auto-generated method stub
		return prescriptionRepository.loadPatientByPrescription(prescriptionId);
	}
	
}
