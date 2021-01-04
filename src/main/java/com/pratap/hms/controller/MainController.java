package com.pratap.hms.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.hms.model.Admin;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.AppointmentService;
import com.pratap.hms.service.BlogService;
import com.pratap.hms.service.DoctorService;
import com.pratap.hms.service.PatientService;
import com.pratap.hms.util.EmailUtil;
import com.pratap.hms.util.ImageUtil;

@Controller
public class MainController {
	private static final Logger log=Logger.getLogger(MainController.class);
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private MailSender mailSender;
	///////////////////////////////////////////////////
	
	
	
	
	
	
	///////////////////////////////////////////////
	@RequestMapping(value = "/a")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value = "/d")
	public String dlogin() {
		return "doctorLogin";
	}
	
	@RequestMapping(value = "/n")
	public String nlogin() {
		return "nurseLogin";
	}
	@RequestMapping(value = "/p")
	public String plogin() {
		return "patientLogin";
	}
	@RequestMapping(value = "/r")
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Admin admin,@RequestParam String email, @RequestParam String pass, HttpSession session,ModelMap model) {
		boolean isLogin = adminService.login(email, pass);

		if (isLogin) {
			session.setAttribute("email", email);
			
			log.info("you are successfully logged in...");
			
			return "redirect:/admin";
		}
		
		else {
			model.addAttribute("error", "Invalid username and password");
			return "redirect:/a";
		}
	}
	
	
	
	
	
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public ModelAndView home(HttpSession session,ModelMap model) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView view = new ModelAndView("/admin/adminDash");
		view.addObject("Admin", admin);
		model.addAttribute("doctor",doctorService.getAllDoctor().size());
		model.addAttribute("appointment",appointmentService.getAllAppointment().size());
		model.addAttribute("blog",blogService.getAllBlog().size());
		model.addAttribute("patient",patientService.getAllPatient().size());
		return view;
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return"redirect:/a";
	}
	
	 @RequestMapping(value="/info" ,method = RequestMethod.GET)
	   public ModelAndView adminInfo(HttpSession session) {

		 	String email = (String) session.getAttribute("email");
			Admin admin = adminService.getAdminByEmail(email);
			ModelAndView view = new ModelAndView("/admin/adminProfile");
			view.addObject("Admin", admin);
			return view;
	   }
	 
	 @RequestMapping(value="/edit_admin" ,method = RequestMethod.GET)
	   public ModelAndView adminEditForm(HttpSession session) {

		 	String email = (String) session.getAttribute("email");
			Admin admin = adminService.getAdminByEmail(email);
			ModelAndView view = new ModelAndView("/admin/editAdmin");
			view.addObject("Admin", admin);
			return view;
	   }
	 
	 @RequestMapping(value = "admin_update", method = RequestMethod.POST)
		public String updateAdmin(@ModelAttribute Admin admin, HttpSession session, @RequestParam String firstName,@RequestParam String lastName,@RequestParam String address,@RequestParam String contactNumber,@RequestParam String password,@RequestParam Date dob,@RequestParam String sex,@RequestParam String bloodGroup) {
			String email = (String) session.getAttribute("email");
			admin = adminService.getAdminByEmail(email);
			admin.setFirstName(firstName);
			admin.setLastName(lastName);
			admin.setAddress(address);
			admin.setContactNumber(contactNumber);
			admin.setEmail(email);
			admin.setPassword(password);
			admin.setDob(dob);
			admin.setSex(sex);
			admin.setBloodGroup(bloodGroup);
			adminService.updateAdmin(admin);
			return "redirect:/info";
		}
	 
	 @RequestMapping(value="/upload_aphoto" ,method = RequestMethod.POST)
	   public String adminUpload(@ModelAttribute Admin admin, @RequestParam("image") CommonsMultipartFile file,
				HttpSession session) {

		 	String email = (String) session.getAttribute("email");
			admin = adminService.getAdminByEmail(email);
			String imageUrl = ImageUtil.writeImageToFile(file);
			admin.setImageUrl(imageUrl);
			adminService.updateAdmin(admin);
			return "redirect:/info";
	   }
	 //////////////////forgot password of admin
	 @RequestMapping(value = "/forgot_password_a", method = RequestMethod.POST)
		public String forgotPassword(@RequestParam String email,HttpServletRequest request,HttpSession session,Model model) {
		 	String captcha = session.getAttribute("captcha_security").toString();
			String verifyCaptcha = request.getParameter("captcha");
			log.warn("invalid email is not accepted...");
			
			if (captcha.equals(verifyCaptcha)) {
			Admin admin = adminService.getAdminByEmail(email);
			if (admin.getEmail() != null) {
				String randomPass = new BigInteger(30, new SecureRandom()).toString(32);
				
				String msg = "Your new password is: " + randomPass;
				log.debug("debug point for email sending and password reset");
				EmailUtil.sendEmail(mailSender, admin.getEmail(), "Your new Password", msg);
				admin.setPassword(randomPass);
				adminService.updateAdmin(admin);
			}
			return "redirect:/a";
			}
			else {
				model.addAttribute("error", "Invalid Captcha");
				return "redirect:/a";
				
			}
		}
	 
	 
}
