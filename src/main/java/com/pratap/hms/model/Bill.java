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
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	private Patient patient;
	@OneToOne
	private Prescription prescription;
	@Column(name = "medicine_charge")
	private int medicineCharge;
	@Column(name = "doctor_charge")
	private int doctorCharge;
	@Column(name = "payment_status")
	private String paymentStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "bill_date")
	private Date billDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "payment_date")
	private Date paymentDate;
	@Column(name = "total_charge")
	private int totalCharge;
	@Column(name = "tax_charge")
	private int taxCharge;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	public int getMedicineCharge() {
		return medicineCharge;
	}
	public void setMedicineCharge(int medicineCharge) {
		this.medicineCharge = medicineCharge;
	}
	public int getDoctorCharge() {
		return doctorCharge;
	}
	public void setDoctorCharge(int doctorCharge) {
		this.doctorCharge = doctorCharge;
	}
	
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(int totalCharge) {
		this.totalCharge = totalCharge;
	}
	public int getTaxCharge() {
		return taxCharge;
	}
	public void setTaxCharge(int taxCharge) {
		this.taxCharge = taxCharge;
	}
	
	
}
