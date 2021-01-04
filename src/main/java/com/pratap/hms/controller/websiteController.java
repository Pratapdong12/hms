package com.pratap.hms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.pratap.hms.model.Admin;

import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.BlogService;
import com.pratap.hms.service.DepartmentService;
import com.pratap.hms.service.DoctorService;


@Controller
public class websiteController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private BlogService blogService;
	

	@RequestMapping(value = "/front", method = RequestMethod.GET)
	public String front(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("doctor", doctorService.getAllDoctor());
		model.addAttribute("department", departmentService.getAllDepartment());
		return "/temp";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("doctor", doctorService.getAllDoctor());
		return "/about";
	}
	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public String department(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("department", departmentService.getAllDepartment());
		return "/department";
	}
	@RequestMapping(value = "/contact")
	public String contact() {
		return "contact";
	}
	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String blog(Model model,HttpSession session) {
		
		
		model.addAttribute("blog", blogService.getAllBlog());
		return "/blog";
	}
	
	
	
}
