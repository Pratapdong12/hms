package com.pratap.hms.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Enquiry;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.EnquiryService;

@Controller
public class enquiryController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private EnquiryService enquiryService;
	
	@RequestMapping(value = "add_enquiry", method = RequestMethod.POST)
	public String addEnquiry(@ModelAttribute Enquiry enquiry) {
		
		enquiry.setEnquiryDate(new Date());
		enquiry.setStatus("unseen");
		enquiryService.addEnquiry(enquiry);
		
		return "contact";
	}

	@RequestMapping(value = "/enquiry_list", method = RequestMethod.GET)
	public String getAllEnquiry(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("enquiry", enquiryService.getAllEnquiry());
		return "/admin/enquiryList";
	}
	
	@RequestMapping(value = "/enquiry_delete", method = RequestMethod.GET)
	public String deleteEnquiry(@RequestParam int id) {
		enquiryService.deleteEnquiry(id);
		return "redirect:/enquiry_list";
	}
	
	
	
	
	@RequestMapping(value = "/enquiry_update", method = RequestMethod.GET)
	public String updateEnquiry(@RequestParam int id) {
		Enquiry enquiry= enquiryService.getEnquiryById(id);
		enquiry.setStatus("seen");
		enquiryService.updateEnquiry(enquiry);
		return "redirect:/enquiry_list";
	}

}
