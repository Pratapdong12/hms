package com.pratap.hms.service;

import java.util.List;

import com.pratap.hms.model.Enquiry;

public interface EnquiryService {
	void addEnquiry(Enquiry enquiry);

	void updateEnquiry(Enquiry enquiry);

	void deleteEnquiry(int id);

	Enquiry getEnquiryById(int id);

	List<Enquiry> getAllEnquiry();
}
