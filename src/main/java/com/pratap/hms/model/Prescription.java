package com.pratap.hms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	private Appointment appointment;
	@Column(name = "prescription")
	private String prescription;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "prescription_date")
	private Date prescriptionDate;
	@Column(name = "doctor")
	private String doctor;
	@Column(name = "patient")
	private String patient;
	@Column(name = "doctor_charge")
	private int doctorCharge;
	@OneToOne
	private Patient pat;
	@Column(name = "b_added")
	private String bAdded;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public Date getPrescriptionDate() {
		return prescriptionDate;
	}
	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public int getDoctorCharge() {
		return doctorCharge;
	}
	public void setDoctorCharge(int doctorCharge) {
		this.doctorCharge = doctorCharge;
	}
	public Patient getPat() {
		return pat;
	}
	public void setPat(Patient pat) {
		this.pat = pat;
	}
	public String getbAdded() {
		return bAdded;
	}
	public void setbAdded(String bAdded) {
		this.bAdded = bAdded;
	}
	
	
	
}
