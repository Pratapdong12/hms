package com.pratap.hms.service;

import java.util.List;

import com.pratap.hms.model.Prescription;
import com.pratap.hms.model.Appointment;
import com.pratap.hms.model.Doctor;

public interface PrescriptionService {

	void addPrescription(Prescription prescription);

	void updatePrescription(Prescription prescription);

	void deletePrescription(int id);

	Prescription getPrescriptionById(int id);

	List<Prescription> getPrescription(String a);
	
	List<Prescription> getPrescriptionPatient(String a);
	
	List<Prescription> prescriptionBill(int patientId);
	//////for bill generation
	List<Prescription> getNoBill();
	////////////////
	List<Prescription> loadPatientByPrescription(int prescriptionId);
}
