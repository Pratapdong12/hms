package com.pratap.hms.service;

import java.util.List;

import com.pratap.hms.model.Bill;
import com.pratap.hms.model.Patient;

public interface BillService {

	void addBill(Bill bill);

	void updateBill(Bill bill);

	void deleteBill(int id);

	Bill getBillById(int id);

	List<Bill> getAllBill();
	
	List<Bill> getPatientBill(Patient pat);
}
