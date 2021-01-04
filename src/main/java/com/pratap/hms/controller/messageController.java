package com.pratap.hms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Enquiry;
import com.pratap.hms.model.Message;
import com.pratap.hms.model.Patient;
import com.pratap.hms.model.Schedule;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.DoctorService;
import com.pratap.hms.service.MessageService;
import com.pratap.hms.service.PatientService;

@Controller
public class messageController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private MessageService messageService;
	
	
//////////////////////////////////////////admin	
	@RequestMapping(value = "/message_for_doctor", method = RequestMethod.GET)
	public String messageForm(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("doctor", doctorService.getAllDoctor());
		
		
		return "/admin/addMessageDoctor";
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String messageFormA(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("doctor", doctorService.getAllDoctor());
		model.addAttribute("patient", patientService.getAllPatient());
		
		return "/admin/addMessage";
	}
	

	
	@RequestMapping(value = "/message_for_patient", method = RequestMethod.GET)
	public String messagePatientForm(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("patient", patientService.getAllPatient());
		
		
		return "/admin/addMessagePatient";
	}
	
		
	@RequestMapping(value = "add_message_admin", method = RequestMethod.POST)
	public String addMessageDoctor(@ModelAttribute Message message,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);

		message.setSender(admin.getFirstName()+" "+admin.getLastName());
		message.setStatus("Unseen");
		message.setAdmin(admin);
		
		messageService.addMessage(message);
		return "redirect:/sent_message_admin";
	}
	
	@RequestMapping(value = "/sent_message_admin", method = RequestMethod.GET)
	public String getAllAdminMessage(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("message", messageService.getSentMessage(admin));
		return "/admin/sentMessageAdmin";
	}
	
	@RequestMapping(value = "/inbox_message_admin", method = RequestMethod.GET)
	public String getAllAdminInbox(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		String a=admin.getFirstName()+" "+admin.getLastName();
		model.addAttribute("Admin", admin);
		model.addAttribute("message", messageService.getInboxMessage(a));
		return "/admin/inboxMessageAdmin";
	}
	
	@RequestMapping(value = "/message_update", method = RequestMethod.GET)
	public String updateEnquiry(@RequestParam int id) {
		Message message= messageService.getMessageById(id);
		message.setStatus("seen");
		messageService.updateMessage(message);
		return "redirect:/sent_message_admin";
	}
	
	@RequestMapping(value = "/message_delete", method = RequestMethod.GET)
	public String deleteMesage(@RequestParam int id) {
		messageService.deleteMessage(id);
		return "redirect:/sent_message_admin";
	}
	
//////////////////////////////////////////////////////////////////////////

	
	
	
	
}
