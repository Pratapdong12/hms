package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Enquiry;
import com.pratap.hms.repository.EnquiryRepository;

@Service
@Transactional
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Override
	public void addEnquiry(Enquiry enquiry) {
		// TODO Auto-generated method stub
		enquiryRepository.addEnquiry(enquiry);
	}

	@Override
	public void updateEnquiry(Enquiry enquiry) {
		// TODO Auto-generated method stub
		enquiryRepository.updateEnquiry(enquiry);
	}

	@Override
	public void deleteEnquiry(int id) {
		// TODO Auto-generated method stub
		enquiryRepository.deleteEnquiry(id);
	}

	@Override
	public Enquiry getEnquiryById(int id) {
		// TODO Auto-generated method stub
		return enquiryRepository.getEnquiryById(id);
	}

	@Override
	public List<Enquiry> getAllEnquiry() {
		// TODO Auto-generated method stub
		return enquiryRepository.getAllEnquiry();
	}
}
