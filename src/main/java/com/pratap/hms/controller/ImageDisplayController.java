package com.pratap.hms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.BlogService;
import com.pratap.hms.service.DepartmentService;
import com.pratap.hms.service.DoctorService;
import com.pratap.hms.service.PatientService;
import com.pratap.hms.util.ImageUtil;

@Controller
public class ImageDisplayController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private BlogService blogService;

	@RequestMapping("/ImageDisplay")
	public void displayDoctorImage(@RequestParam("doctorId") int id, HttpServletRequest request, HttpServletResponse response) {
		String imageUrl = doctorService.getDoctorById(id).getImageUrl();
		ImageUtil.displayImage(imageUrl, request, response);
	}
	

	@RequestMapping("/ImageDisplayPatient")
	public void displayPatientImage(@RequestParam("patientId") int id, HttpServletRequest request, HttpServletResponse response) {
		String imageUrl = patientService.getPatientById(id).getImageUrl();
		ImageUtil.displayImage(imageUrl, request, response);
	}
	
	@RequestMapping("/ImageDisplayAdmin")
	public void displayAdminImage(@RequestParam("adminId") int id, HttpServletRequest request, HttpServletResponse response) {
		String imageUrl = adminService.getAdminById(id).getImageUrl();
		ImageUtil.displayImage(imageUrl, request, response);
	}
	
	@RequestMapping("/ImageDisplayDepartment")
	public void displayDepartmentImage(@RequestParam("departmentId") int id, HttpServletRequest request, HttpServletResponse response) {
		String imageUrl = departmentService.getDepartmentById(id).getImageUrl();
		ImageUtil.displayImage(imageUrl, request, response);
	}
	@RequestMapping("/ImageDisplayBlog")
	public void displayBlogImage(@RequestParam("blogId") int id, HttpServletRequest request, HttpServletResponse response) {
		String imageUrl = blogService.getBlogById(id).getImageUrl();
		ImageUtil.displayImage(imageUrl, request, response);
	}
}
