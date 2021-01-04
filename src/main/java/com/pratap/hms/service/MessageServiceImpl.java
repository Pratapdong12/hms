package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Enquiry;
import com.pratap.hms.model.Message;
import com.pratap.hms.model.Patient;
import com.pratap.hms.repository.MessageRepository;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void addMessage(Message message) {
		// TODO Auto-generated method stub
		messageRepository.addMessage(message);
	}

	@Override
	public void deleteMessage(int id) {
		// TODO Auto-generated method stub
		messageRepository.deleteMessage(id);
	}

	@Override
	public Message getMessageById(int id) {
		// TODO Auto-generated method stub
		return messageRepository.getMessageById(id);
	}

	@Override
	public List<Message> getAllMessage() {
		// TODO Auto-generated method stub
		return messageRepository.getAllMessage();
	}
	
	@Override
	public void updateMessage(Message message) {
		// TODO Auto-generated method stub
		messageRepository.updateMessage(message);
	}
	
	@Override
	public List<Message> getSentMessage(Admin admin) {
		// TODO Auto-generated method stub
		return messageRepository.getSentMessage(admin);
	}
	
	@Override
	public List<Message> getSentMessageDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return messageRepository.getSentMessageDoctor(doctor);
	}
	
	@Override
	public List<Message> getSentMessagePatient(Patient patient) {
		// TODO Auto-generated method stub
		return messageRepository.getSentMessagePatient(patient);
	}
	

	
	@Override
	public List<Message> getInboxMessage(String a) {
		// TODO Auto-generated method stub
		return messageRepository.getInboxMessage(a);
	}

}
