package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Bill;
import com.pratap.hms.model.Patient;
import com.pratap.hms.repository.BillRepository;

@Service
@Transactional
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;
	
	@Override
	public void addBill(Bill bill) {
		
		billRepository.addBill(bill);
	}

	@Override
	public void updateBill(Bill bill) {
		billRepository.updateBill(bill);
		
	}

	@Override
	public void deleteBill(int id) {
		billRepository.deleteBill(id);
		
	}

	@Override
	public Bill getBillById(int id) {
		// TODO Auto-generated method stub
		return billRepository.getBillById(id);
	}

	@Override
	public List<Bill> getAllBill() {
		// TODO Auto-generated method stub
		return billRepository.getAllBill();
	}

	@Override
	public List<Bill> getPatientBill(Patient pat) {
		// TODO Auto-generated method stub
		return billRepository.getPatientBill(pat);
	}
}
