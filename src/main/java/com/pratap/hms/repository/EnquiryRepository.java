package com.pratap.hms.repository;

import java.util.List;

import com.pratap.hms.model.Enquiry;

public interface EnquiryRepository {
	void addEnquiry(Enquiry enquiry);

	void updateEnquiry(Enquiry enquiry);

	void deleteEnquiry(int id);

	Enquiry getEnquiryById(int id);

	List<Enquiry>getAllEnquiry();
}
