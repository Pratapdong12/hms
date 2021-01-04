package com.pratap.hms.repository;

import java.util.List;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Message;
import com.pratap.hms.model.Patient;

public interface MessageRepository {

	void addMessage(Message message);

	void deleteMessage(int id);

	Message getMessageById(int id);

	List<Message>getAllMessage();
	
	List<Message>getSentMessage(Admin admin);
	
	List<Message>getSentMessageDoctor(Doctor doctor);
	
	List<Message>getSentMessagePatient(Patient patient);
	
	
	List<Message>getInboxMessage(String a);
	
	void updateMessage(Message message);
}
