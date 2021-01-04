package com.pratap.hms.controller;



import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Department;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Message;
import com.pratap.hms.model.Notice;
import com.pratap.hms.model.Patient;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.AppointmentService;
import com.pratap.hms.service.BlogService;
import com.pratap.hms.service.DepartmentService;
import com.pratap.hms.service.DoctorService;
import com.pratap.hms.service.MessageService;
import com.pratap.hms.service.NoticeService;
import com.pratap.hms.service.PatientService;
import com.pratap.hms.service.ScheduleService;
import com.pratap.hms.util.EmailUtil;
import com.pratap.hms.util.ImageUtil;


@Controller
public class doctorController {
	private static final Logger log=Logger.getLogger(doctorController.class);
	
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private MailSender mailSender;
	


	@RequestMapping(value = "/doctor_form", method = RequestMethod.GET)
	public String doctorForm(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("department", departmentService.getAllDepartment());
		return "/admin/addDoctor";
	}
	
	@RequestMapping(value = "add_doctor", method = RequestMethod.POST)
	public String addDoctor(@ModelAttribute Doctor doctor, @RequestParam("image") CommonsMultipartFile file, @RequestParam int departmentId) {
		String imageUrl = ImageUtil.writeImageToFile(file);
		doctor.setImageUrl(imageUrl);
		
		Department department = departmentService.getDepartmentById(departmentId);
		if (department != null) {
			doctor.setDepartment(department);
		}
		doctorService.addDoctor(doctor);
		String e=doctor.getEmail();
		String password= doctor.getPassword();
		String msg = "Your email is: " + e+" and password is: " + password;
		log.debug("debug point for email sending ");
		EmailUtil.sendEmail(mailSender, doctor.getEmail(), "Your email and Password", msg);
			
		
		return "redirect:/doctor_list";
	}
	
	@RequestMapping(value = "/doctor_list", method = RequestMethod.GET)
	public String getAllDoctor(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("doctor", doctorService.getAllDoctor());
		return "/admin/doctorList";
	}
	
	@RequestMapping(value = "/doctor_delete", method = RequestMethod.GET)
	public String deleteNurse(@RequestParam int id) {
		doctorService.deleteDoctor(id);
		return "redirect:/doctor_list";
	}
	@RequestMapping(value = "/edit_doctor", method = RequestMethod.GET)
	public ModelAndView doctorEditForm(@RequestParam int id,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView form = new ModelAndView("/admin/editDoctor");
		form.addObject("department",departmentService.getAllDepartment());
		form.addObject("doctor", doctorService.getDoctorById(id));
		form.addObject("Admin", admin);
		
		return form;
	}
	
	@RequestMapping(value = "/doctor_update", method = RequestMethod.POST)
	public String updateDoctor(@ModelAttribute Doctor doctor, @RequestParam("image") CommonsMultipartFile file, @RequestParam int departmentId) {
		doctorService.getDoctorById(doctor.getId());
		String imageUrl = "";
		if (file.getOriginalFilename().isEmpty()) {
			imageUrl = doctorService.getDoctorById(doctor.getId()).getImageUrl();
		} else {
			imageUrl = ImageUtil.writeImageToFile(file);
		}
		doctor.setImageUrl(imageUrl);
		Department department = departmentService.getDepartmentById(departmentId);
		

		if (department != null) {
			doctor.setDepartment(department);
		}
		
		doctorService.updateDoctor(doctor);
		return "redirect:/doctor_list";
	}
	
	
	//doctor dashboard
	
	@RequestMapping(value = "/doctorlogin", method = RequestMethod.POST)
	public String doctorlogin(@ModelAttribute Doctor doctor,@RequestParam String email, @RequestParam String pass, HttpSession session) {
		boolean isLogin = doctorService.login(email, pass);

		if (isLogin) {
			session.setAttribute("email", email);
			
			log.info("you are successfully logged in...");
			
			return "redirect:/doctor";
		}
		
		else {
			
			return "redirect:/d";
		}
	}
	
	@RequestMapping(value = "/doctor",method = RequestMethod.GET)
	public ModelAndView home(HttpSession session, ModelMap model) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		ModelAndView view = new ModelAndView("/doctor/doctorDash");
		view.addObject("doctor", doctor);
		model.addAttribute("doc",doctorService.getAllDoctor().size());
		model.addAttribute("appointment",appointmentService.getAllAppointment().size());
		model.addAttribute("blog",blogService.getAllBlog().size());
		model.addAttribute("patient",patientService.getAllPatient().size());
		return view;
	}
	
	@RequestMapping(value = "/dlogout")
	public String dlogout(HttpSession session) {
		session.invalidate();
		return"redirect:/d";
	}
	
	 @RequestMapping(value="/dinfo" ,method = RequestMethod.GET)
	   public ModelAndView doctorInfo(HttpSession session) {
		 	

		 	String email = (String) session.getAttribute("email");
			Doctor doctor = doctorService.getDoctorByEmail(email);
			ModelAndView view = new ModelAndView("/doctor/doctorProfile");
			view.addObject("doctor", doctor);
			return view;
	   }
	 
	 @RequestMapping(value="/upload_dphoto" ,method = RequestMethod.POST)
	   public String adminUpload(@ModelAttribute Doctor doctor, @RequestParam("image") CommonsMultipartFile file,
				HttpSession session) {

		 	String email = (String) session.getAttribute("email");
			doctor = doctorService.getDoctorByEmail(email);
			String imageUrl = ImageUtil.writeImageToFile(file);
			doctor.setImageUrl(imageUrl);
			doctorService.updateDoctor(doctor);
			return "redirect:/dinfo";
	   }
	 
	 @RequestMapping(value="/edit_dprofile" ,method = RequestMethod.GET)
	   public ModelAndView doctorEditForm(HttpSession session) {

		 	String email = (String) session.getAttribute("email");
			Doctor doctor = doctorService.getDoctorByEmail(email);
			ModelAndView view = new ModelAndView("/doctor/editDProfile");
			view.addObject("department",departmentService.getAllDepartment());
			view.addObject("doctor", doctor);
			return view;
	   }
	 
	 @RequestMapping(value = "dprofile_update", method = RequestMethod.POST)
		public String updateAdmin(@ModelAttribute Doctor doctor, HttpSession session, @RequestParam String firstName,@RequestParam String lastName,@RequestParam String address,@RequestParam String contactNumber,@RequestParam String password,@RequestParam String sex,@RequestParam String bloodGroup, @RequestParam int departmentId,@RequestParam String status,@RequestParam int charge) {
			String email = (String) session.getAttribute("email");
			doctor = doctorService.getDoctorByEmail(email);
			doctor.setFirstName(firstName);
			doctor.setLastName(lastName);
			doctor.setAddress(address);
			doctor.setContactNumber(contactNumber);
			doctor.setEmail(email);
			doctor.setPassword(password);
			
			doctor.setSex(sex);
			doctor.setBloodGroup(bloodGroup);
			Department department = departmentService.getDepartmentById(departmentId);
			

			if (department != null) {
				doctor.setDepartment(department);
			}
			doctor.setStatus(status);
			doctor.setCharge(charge);
			
			doctorService.updateDoctor(doctor);
			return "redirect:/dinfo";
		}
////////////////////////////////////////////////////////////for Patient
		
	@RequestMapping(value = "/patient_form_d", method = RequestMethod.GET)
	public ModelAndView patientFormD(HttpSession session) {
	String email = (String) session.getAttribute("email");
	Doctor doctor = doctorService.getDoctorByEmail(email);
	ModelAndView form = new ModelAndView("/doctor/addPatient");
	form.addObject("doctor",doctor);
	return form;
	}
	
	@RequestMapping(value = "add_patient_d", method = RequestMethod.POST)
	public String addPatientD(@ModelAttribute Patient patient, @RequestParam("image") CommonsMultipartFile file) {
	String imageUrl = ImageUtil.writeImageToFile(file);
	patient.setImageUrl(imageUrl);
	patient.setStatus("approved");
	patientService.addPatient(patient);
	
	String e=patient.getEmail();
	String password= patient.getPassword();
	String msg = "Your email is: " + e+" and password is: " + password;
	log.debug("debug point for email sending ");
	EmailUtil.sendEmail(mailSender, patient.getEmail(), "Your email and Password", msg);
	return "redirect:/patient_list_d";
	}
	
	@RequestMapping(value = "/patient_list_d", method = RequestMethod.GET)
	public String getAllPatientD(Model model,HttpSession session) {
	String email = (String) session.getAttribute("email");
	Doctor doctor = doctorService.getDoctorByEmail(email);
	model.addAttribute("doctor",doctor);
	model.addAttribute("patient", patientService.getAllPatient());
	return "/doctor/patientList";
	}
	
	@RequestMapping(value = "/patient_delete_d", method = RequestMethod.GET)
	public String patientNurseD(@RequestParam int id) {
	patientService.deletePatient(id);
	return "redirect:/patient_list_d";
	}
	@RequestMapping(value = "/edit_patient_d", method = RequestMethod.GET)
	public ModelAndView patientEditFormD(@RequestParam int id,HttpSession session) {
	String email = (String) session.getAttribute("email");
	Doctor doctor = doctorService.getDoctorByEmail(email);
	ModelAndView form = new ModelAndView("/doctor/editPatient");
	form.addObject("patient", patientService.getPatientById(id));
	form.addObject("doctor", doctor);
	return form;
	}
	
	@RequestMapping(value = "/patient_update_d", method = RequestMethod.POST)
	public String updatePatientD(@ModelAttribute Patient patient,@RequestParam("image") CommonsMultipartFile file) {
	patientService.getPatientById(patient.getId());
	String imageUrl = "";
	if (file.getOriginalFilename().isEmpty()) {
	imageUrl = patientService.getPatientById(patient.getId()).getImageUrl();
	} else {
	imageUrl = ImageUtil.writeImageToFile(file);
	}
	patient.setImageUrl(imageUrl);
	patientService.updatePatient(patient);
	return "redirect:/patient_list_d";
	
	}

	///////////////////////////////////////////////////for schedule
	@RequestMapping(value = "/schedule_list_d", method = RequestMethod.GET)
	public String getAllScheduleD(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		model.addAttribute("doctor", doctor);
		model.addAttribute("schedule", scheduleService.getScheduleDoctor(doctor));
		return "/doctor/scheduleList";
	}
	///////////////////////////////////////////////////////message
	@RequestMapping(value = "/message_for_doctor_d", method = RequestMethod.GET)
	public String messageFormD(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		model.addAttribute("doctor", doctor);
		model.addAttribute("doc", doctorService.getAllDoctor());
		
		
		return "/doctor/addMessageDoctor";
	}
	

	@RequestMapping(value = "/message_for_patient_d", method = RequestMethod.GET)
	public String messageFormP(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		model.addAttribute("doctor", doctor);
		model.addAttribute("patient", patientService.getAllPatient());
		
		
		return "/doctor/addMessagePatient";
	}
	
	@RequestMapping(value = "add_message_doctor", method = RequestMethod.POST)
	public String addMessageDoctor(@ModelAttribute Message message,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
	
		message.setSender(doctor.getFirstName()+" "+doctor.getLastName());
		message.setStatus("Unseen");
		message.setDoctor(doctor);
		
		messageService.addMessage(message);
		return "redirect:/message_for_doctor_d";
	}
	
	@RequestMapping(value = "/sent_message_doctor", method = RequestMethod.GET)
	public String getAllDoctorMessage(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		model.addAttribute("doctor", doctor);
		model.addAttribute("message", messageService.getSentMessageDoctor(doctor));
		return "/doctor/sentMessageDoctor";
	}
	
	@RequestMapping(value = "/inbox_message_doctor", method = RequestMethod.GET)
	public String getAllAdminInbox(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		String a=doctor.getFirstName()+" "+doctor.getLastName();
		model.addAttribute("doctor", doctor);
		model.addAttribute("message", messageService.getInboxMessage(a));
		return "/doctor/inboxMessageDoctor";
	}
	@RequestMapping(value = "/message_update_d", method = RequestMethod.GET)
	public String updateMessage(@RequestParam int id) {
		Message message= messageService.getMessageById(id);
		message.setStatus("seen");
		messageService.updateMessage(message);
		return "redirect:/inbox_message_doctor";
	}
	
	@RequestMapping(value = "/message_delete_d", method = RequestMethod.GET)
	public String deleteMesageDoctor(@RequestParam int id) {
		messageService.deleteMessage(id);
		return "redirect:/sent_message_doctor";
	}
	
	@RequestMapping(value = "/doctor_list_d", method = RequestMethod.GET)
	public String getAll(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		model.addAttribute("doctor", doctor);
		model.addAttribute("doc", doctorService.getAllDoctor());
		return "/doctor/doctorList";
	}
	////////////////////////////////////////////////////////notice
	
	@RequestMapping(value = "/notice_form_d", method = RequestMethod.GET)
	public ModelAndView noticeFormD(HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		ModelAndView form = new ModelAndView("/doctor/addNotice");
		form.addObject("doctor", doctor);
		
		return form;
	}
	@RequestMapping(value = "add_notice_d", method = RequestMethod.POST)
	public String addNoticed(@ModelAttribute Notice notice,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		notice.setAssignedBy(doctor.getFirstName()+" "+doctor.getLastName());
		notice.setPublishedDate(new Date());
		noticeService.addNotice(notice);
		return "redirect:/notice_list_d";
	}

	@RequestMapping(value = "/notice_list_d", method = RequestMethod.GET)
	public String getAllVehicles(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		model.addAttribute("doctor", doctor);
		model.addAttribute("notice", noticeService.getAllNotice());
		return "/doctor/noticeList";
	}
	
	@RequestMapping(value = "/edit_notice_d", method = RequestMethod.GET)
	public ModelAndView noticeEditFormD(@RequestParam int id,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);	
		ModelAndView form = new ModelAndView("/doctor/editNotice");
		form.addObject("doctor", doctor);
		form.addObject("notice", noticeService.getNoticeById(id));
		return form;
	}
	
	@RequestMapping(value = "/notice_update_d", method = RequestMethod.POST)
	public String updateNoticeD(@ModelAttribute Notice notice,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		notice.setAssignedBy(doctor.getFirstName()+" "+doctor.getLastName());
		notice.setPublishedDate(new Date());
		noticeService.updateNotice(notice);
		return "redirect:/notice_list_d";
	}
	
	@RequestMapping(value = "/notice_delete_d", method = RequestMethod.GET)
	public String deleteNoticeD(@RequestParam int id) {
		noticeService.deleteNotice(id);
		return "redirect:/notice_list_d";
	}
	 //////////////////forgot password of doctor
	 @RequestMapping(value = "/forgot_password_d", method = RequestMethod.POST)
		public String forgotPasswordD(@RequestParam String email,HttpServletRequest request,HttpSession session,Model model) {
		 	String captcha = session.getAttribute("captcha_security").toString();
			String verifyCaptcha = request.getParameter("captcha");
			log.warn("invalid email is not accepted...");
			
			if (captcha.equals(verifyCaptcha)) {
			Doctor doctor = doctorService.getDoctorByEmail(email);
			if (doctor.getEmail() != null) {
				String randomPass = new BigInteger(30, new SecureRandom()).toString(32);
				
				String msg = "Your new password is: " + randomPass;
				log.debug("debug point for email sending and password reset");
				EmailUtil.sendEmail(mailSender, doctor.getEmail(), "Your new Password", msg);
				doctor.setPassword(randomPass);
				doctorService.updateDoctor(doctor);
			}
			return "redirect:/d";
			}
			else {
				model.addAttribute("error", "Invalid Captcha");
				return "redirect:/d";
				
			}
		}
////////////////////////////////////////////////////////view doctor
	 @RequestMapping(value = "/view_doctor_a", method = RequestMethod.GET)
		public ModelAndView doctorViewFormA(@RequestParam int id,HttpSession session) {
			String email = (String) session.getAttribute("email");
			Admin admin = adminService.getAdminByEmail(email);		
			ModelAndView form = new ModelAndView("/admin/viewDoctor");
			form.addObject("Admin", admin);
			form.addObject("doctor", doctorService.getDoctorById(id));
			return form;
		}
	 
	 @RequestMapping(value = "/view_doctor_p", method = RequestMethod.GET)
		public ModelAndView doctorViewFormP(@RequestParam int id,HttpSession session) {
			String email = (String) session.getAttribute("email");
			Patient patient = patientService.checkEmail(email);		
			ModelAndView form = new ModelAndView("/patient/viewDoctor");
			form.addObject("patient", patient);
			form.addObject("doctor", doctorService.getDoctorById(id));
			return form;
		}
	 
	 @RequestMapping(value = "/view_doctor_d", method = RequestMethod.GET)
		public ModelAndView doctorViewFormD(@RequestParam int id,HttpSession session) {
			String email = (String) session.getAttribute("email");
			Doctor doctor = doctorService.checkEmail(email);		
			ModelAndView form = new ModelAndView("/doctor/viewDoctor");
			form.addObject("doctor", doctor);
			form.addObject("doc", doctorService.getDoctorById(id));
			return form;
		}

}
