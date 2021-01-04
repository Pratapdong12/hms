package com.pratap.hms.service;

import java.util.List;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Enquiry;
import com.pratap.hms.model.Message;
import com.pratap.hms.model.Patient;

public interface MessageService {

	void addMessage(Message message);
	
	void deleteMessage(int id);
	
	Message getMessageById(int id);
	
	List <Message>getAllMessage();
	
	void updateMessage(Message message);
	
	List<Message>getSentMessage(Admin admin);
	
	List<Message>getSentMessageDoctor(Doctor doctor);
	
	
	List<Message>getSentMessagePatient(Patient patient);
	
	List<Message>getInboxMessage(String a);
}
