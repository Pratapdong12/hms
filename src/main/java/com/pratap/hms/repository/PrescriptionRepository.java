package com.pratap.hms.repository;

import java.util.List;

import com.pratap.hms.model.Prescription;
import com.pratap.hms.model.Appointment;
import com.pratap.hms.model.Doctor;


public interface PrescriptionRepository {

	void addPrescription(Prescription prescription);

	void updatePrescription(Prescription prescription);

	void deletePrescription(int id);

	Prescription getPrescriptionById(int id);

	List<Prescription>getPrescription(String a);
	
	List<Prescription>getPrescriptionPatient(String a);
	//for bill
	List<Prescription>prescriptionBill(int patientId);
	
	List<Prescription>getNoBill();
	
	////////////////load patient by prescription id
	List<Prescription>loadPatientByPrescription(int prescriptionId);
}
