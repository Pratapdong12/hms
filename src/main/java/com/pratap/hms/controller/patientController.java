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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Message;
import com.pratap.hms.model.Patient;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.AppointmentService;
import com.pratap.hms.service.BlogService;
import com.pratap.hms.service.DoctorService;
import com.pratap.hms.service.MessageService;
import com.pratap.hms.service.NoticeService;
import com.pratap.hms.service.PatientService;
import com.pratap.hms.service.PrescriptionService;
import com.pratap.hms.util.EmailUtil;
import com.pratap.hms.util.ImageUtil;

@Controller
public class patientController {
	private static final Logger log=Logger.getLogger(patientController.class);

	
	@Autowired
	private PatientService patientService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private PrescriptionService prescriptionService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private MailSender mailSender;
	

/////////////////////////////////////////////////////////////admin
	@RequestMapping(value = "/patient_form", method = RequestMethod.GET)
	public ModelAndView patientForm(HttpSession session,ModelMap modelMap) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView form = new ModelAndView("/admin/addPatient");
		form.addObject("Admin",admin);
		return form;
	}
	
	@RequestMapping(value = "add_patient", method = RequestMethod.POST)
	public String addPatient(@ModelAttribute Patient patient, @RequestParam("image") CommonsMultipartFile file,HttpSession session,HttpServletRequest request,Model model) {
		String captcha = session.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		if (captcha.equals(verifyCaptcha)) {
			String imageUrl = ImageUtil.writeImageToFile(file);
			patient.setImageUrl(imageUrl);
			patient.setStatus("approved");
			patientService.addPatient(patient);
			
			String e=patient.getEmail();
			String password= patient.getPassword();
			String msg = "Your email is: " + e+" and password is: " + password;
			log.debug("debug point for email sending ");
			EmailUtil.sendEmail(mailSender, patient.getEmail(), "Your email and Password", msg);
			model.addAttribute("success", "Added Successfully");
			return "redirect:/patient_list";
		} else {
			model.addAttribute("error", "Invalid Captcha");
			return "redirect:/patient_form";
			
		}
	}
	
	@RequestMapping(value = "add_patient_v", method = RequestMethod.POST)
	public String addPatientV(@ModelAttribute Patient patient, @RequestParam("image") CommonsMultipartFile file,HttpSession session,HttpServletRequest request,Model model) {
		String captcha = session.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		if (captcha.equals(verifyCaptcha)) {
			String imageUrl = ImageUtil.writeImageToFile(file);
			patient.setImageUrl(imageUrl);
			patient.setStatus("pending");
			patientService.addPatient(patient);
			
			
			model.addAttribute("success", "Added Successfully");
			return "redirect:/r";
		} else {
			model.addAttribute("error", "Invalid Captcha");
			return "redirect:/r";
			
		}
	}
	
	@RequestMapping(value = "/patient_list", method = RequestMethod.GET)
	public String getAllPatient(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin",admin);
		model.addAttribute("patient", patientService.getAllPatient());
		return "/admin/patientList";
	}
	
	@RequestMapping(value = "/patient_list_pending", method = RequestMethod.GET)
	public String getAllPatientPending(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin",admin);
		model.addAttribute("patient", patientService.getAllPatientPending());
		return "/admin/patientListPending";
	}
	
	@RequestMapping(value = "/patient_delete", method = RequestMethod.GET)
	public String patientNurse(@RequestParam int id) {
		patientService.deletePatient(id);
		return "redirect:patient_list";
	}
	@RequestMapping(value = "/edit_patient", method = RequestMethod.GET)
	public ModelAndView patientEditForm(@RequestParam int id,HttpSession session) {
		String email =(String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView form = new ModelAndView("/admin/editPatient");
		form.addObject("patient", patientService.getPatientById(id));
		form.addObject("Admin", admin);
		return form;
	}
	
	@RequestMapping(value = "/patient_update", method = RequestMethod.POST)
	public String updatePatient(@ModelAttribute Patient patient,@RequestParam("image") CommonsMultipartFile file) {
		patientService.getPatientById(patient.getId());
		String imageUrl = "";
		if (file.getOriginalFilename().isEmpty()) {
			imageUrl = patientService.getPatientById(patient.getId()).getImageUrl();
		} else {
			imageUrl = ImageUtil.writeImageToFile(file);
		}
		patient.setImageUrl(imageUrl);
		patientService.updatePatient(patient);
		return "redirect:/patient_list";
	}
	@RequestMapping(value = "/approve_patient_a", method = RequestMethod.GET)
	public String approvePatient(@RequestParam int id) {
		Patient patient=patientService.getPatientById(id);
		
		patient.setStatus("approved");
		patientService.updatePatient(patient);
		String msg="Your account has been approved";
		EmailUtil.sendEmail(mailSender, patient.getEmail(), "Account Approved", msg);
		return "redirect:/patient_list_pending";
	}
	////////////////////////////////////////////////////////patientDash
	@RequestMapping(value = "/patientlogin", method = RequestMethod.POST)
	public String patientlogin(@ModelAttribute Patient patient,@RequestParam String email, @RequestParam String pass, HttpSession session,Model model) {
		boolean isLogin = patientService.login(email, pass);

		if (isLogin) {
			session.setAttribute("email", email);
			
			log.info("you are successfully logged in...");
			
			return "redirect:/patient";
		}
		
		else {
			
			model.addAttribute("error", "Invalid Username and password");
			return "redirect:/p";
		}
	}
	
	@RequestMapping(value = "/patient",method = RequestMethod.GET)
	public ModelAndView home(HttpSession session, ModelMap model) {
		String email = (String) session.getAttribute("email");
		Patient patient=patientService.checkEmail(email);
		ModelAndView view = new ModelAndView("/patient/patientDash");
		view.addObject("patient", patient);
		model.addAttribute("doctor",doctorService.getAllDoctor().size());
		model.addAttribute("appointment",appointmentService.getAllAppointment().size());
		model.addAttribute("blog",blogService.getAllBlog().size());
		model.addAttribute("pat",patientService.getAllPatient().size());
		return view;
	}
	
	@RequestMapping(value = "/plogout")
	public String dlogout(HttpSession session) {
		session.invalidate();
		return"redirect:/p";
	}
	
	 @RequestMapping(value="/pinfo" ,method = RequestMethod.GET)
	   public ModelAndView patientInfo(HttpSession session) {
		 	

		 	String email = (String) session.getAttribute("email");
			Patient patient = patientService.checkEmail(email);
			ModelAndView view = new ModelAndView("/patient/patientProfile");
			view.addObject("patient", patient);
			return view;
	   }
	 
	 @RequestMapping(value="/upload_pphoto" ,method = RequestMethod.POST)
	   public String nurseUpload(@ModelAttribute Patient patient, @RequestParam("image") CommonsMultipartFile file,
				HttpSession session) {

		 	String email = (String) session.getAttribute("email");
			patient = patientService.checkEmail(email);
			String imageUrl = ImageUtil.writeImageToFile(file);
			patient.setImageUrl(imageUrl);
			patientService.updatePatient(patient);
			return "redirect:/pinfo";
	   }
	 
	 @RequestMapping(value="/edit_pprofile" ,method = RequestMethod.GET)
	   public ModelAndView nurseEditForm(HttpSession session) {

		 	String email = (String) session.getAttribute("email");
			Patient patient = patientService.checkEmail(email);
			ModelAndView view = new ModelAndView("/patient/editPProfile");
			view.addObject("patient", patient);
			return view;
	   }
	 
	 @RequestMapping(value = "pprofile_update", method = RequestMethod.POST)
		public String updateAdmin(@ModelAttribute Patient patient, HttpSession session, @RequestParam String firstName,@RequestParam String lastName,@RequestParam String address,@RequestParam String contactNumber,@RequestParam String password,@RequestParam Date dob,@RequestParam String sex,@RequestParam String bloodGroup,@RequestParam String status) {
			String email = (String) session.getAttribute("email");
			patient = patientService.checkEmail(email);
			patient.setFirstName(firstName);
			patient.setLastName(lastName);
			patient.setAddress(address);
			patient.setContactNumber(contactNumber);
			patient.setEmail(email);
			patient.setPassword(password);
			patient.setDob(dob);
			patient.setSex(sex);
			patient.setBloodGroup(bloodGroup);
			patient.setStatus(status);
			patientService.updatePatient(patient);
			return "redirect:/pinfo";
		}
	 
	 //////////////////////////////////////////doctor list
	 @RequestMapping(value = "/doctor_list_p", method = RequestMethod.GET)
		public String getAllDoctorsP(Model model,HttpSession session) {
			String email = (String) session.getAttribute("email");
			Patient patient = patientService.checkEmail(email);
			model.addAttribute("patient", patient);
			model.addAttribute("doctor", doctorService.getAllDoctor());
			return "/patient/doctorList";
		}

	 /////////////////////patient list
	 @RequestMapping(value = "/patient_list_p", method = RequestMethod.GET)
		public String getAllPatientP(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Patient patient = patientService.checkEmail(email);
		model.addAttribute("patient",patient);
		model.addAttribute("pat", patientService.getAllPatient());
		return "/patient/patientList";
		}
	 //////////////////////notice list
	 @RequestMapping(value = "/notice_list_p", method = RequestMethod.GET)
		public String getAllNoticeP(Model model,HttpSession session) {
		 String email = (String) session.getAttribute("email");
		 Patient patient = patientService.checkEmail(email);
		 model.addAttribute("patient", patient);
		 model.addAttribute("notice", noticeService.getAllNotice());
		 return "/patient/noticeList";
		}
	 ////////////////message
	 @RequestMapping(value = "/message_for_doctor_p", method = RequestMethod.GET)
		public String messageFormForD(Model model,HttpSession session) {
		 String email = (String) session.getAttribute("email");
		 Patient patient = patientService.checkEmail(email);
			model.addAttribute("patient", patient);
			model.addAttribute("doctor", doctorService.getAllDoctor());
			
			
			return "/patient/addMessageDoctor";
		}
		

		@RequestMapping(value = "/message_for_patient_p", method = RequestMethod.GET)
		public String messageFormP(Model model,HttpSession session) {
			String email = (String) session.getAttribute("email");
			 Patient patient = patientService.checkEmail(email);
			model.addAttribute("patient", patient);
			model.addAttribute("pat", patientService.getAllPatient());
			
			
			return "/patient/addMessagePatient";
		}
		
		@RequestMapping(value = "add_message_patient", method = RequestMethod.POST)
		public String addMessagePatient(@ModelAttribute Message message,HttpSession session) {
			String email = (String) session.getAttribute("email");
			Patient patient = patientService.checkEmail(email);
			
			message.setSender(patient.getFirstName()+" "+patient.getLastName());
			message.setStatus("Unseen");
			message.setPatient(patient);
			messageService.addMessage(message);
			return "redirect:/sent_message_patient";
		}
		
		@RequestMapping(value = "/sent_message_patient", method = RequestMethod.GET)
		public String getAllDoctorMessage(Model model,HttpSession session) {
			String email = (String) session.getAttribute("email");
			Patient patient = patientService.checkEmail(email);
			model.addAttribute("patient", patient);
			model.addAttribute("message", messageService.getSentMessagePatient(patient));
			return "/patient/sentMessagePatient";
		}
		
		@RequestMapping(value = "/inbox_message_patient", method = RequestMethod.GET)
		public String getAllPatientInbox(Model model,HttpSession session) {
			String email = (String) session.getAttribute("email");
			Patient patient = patientService.checkEmail(email);
			String a=patient.getFirstName()+" "+patient.getLastName();
			model.addAttribute("patient", patient);
			model.addAttribute("message", messageService.getInboxMessage(a));
			return "/patient/inboxMessagePatient";
		}
		@RequestMapping(value = "/message_update_p", method = RequestMethod.GET)
		public String updateMessage(@RequestParam int id) {
			Message message= messageService.getMessageById(id);
			message.setStatus("seen");
			messageService.updateMessage(message);
			return "redirect:/inbox_message_patient";
		}
		
		@RequestMapping(value = "/message_delete_p", method = RequestMethod.GET)
		public String deleteMesageNurse(@RequestParam int id) {
			messageService.deleteMessage(id);
			return "redirect:/sent_message_patient";
		}

		////////////////////////prescription
		@RequestMapping(value = "/prescription_list_p", method = RequestMethod.GET)
		public String getAllPrescription(Model model,HttpSession session) {
			String email = (String) session.getAttribute("email");
			Patient patient = patientService.checkEmail(email);
			String a= patient.getFirstName()+" "+patient.getLastName();
			model.addAttribute("patient", patient);
			model.addAttribute("prescription", prescriptionService.getPrescriptionPatient(a));
			return "/patient/prescriptionList";
		}
		
		 //////////////////forgot password of doctor
		 @RequestMapping(value = "/forgot_password_p", method = RequestMethod.POST)
			public String forgotPasswordP(@RequestParam String email,HttpServletRequest request,HttpSession session,Model model) {
			 	String captcha = session.getAttribute("captcha_security").toString();
				String verifyCaptcha = request.getParameter("captcha");
				log.warn("invalid email is not accepted...");
				
				if (captcha.equals(verifyCaptcha)) {
				Patient patient = patientService.checkEmail(email);
				if (patient.getEmail() != null) {
					String randomPass = new BigInteger(30, new SecureRandom()).toString(32);
					
					String msg = "Your new password is: " + randomPass;
					log.debug("debug point for email sending and password reset");
					EmailUtil.sendEmail(mailSender, patient.getEmail(), "Your new Password", msg);
					patient.setPassword(randomPass);
					patientService.updatePatient(patient);
				}
				return "redirect:/p";
				}
				else {
					model.addAttribute("error", "Invalid Captcha");
					return "redirect:/p";
					
				}
			}
		 ////////////////////////////////////////view
		 @RequestMapping(value = "/view_patient_a", method = RequestMethod.GET)
			public ModelAndView patientViewFormA(@RequestParam int id,HttpSession session) {
				String email = (String) session.getAttribute("email");
				Admin admin = adminService.getAdminByEmail(email);		
				ModelAndView form = new ModelAndView("/admin/viewPatient");
				form.addObject("Admin", admin);
				form.addObject("patient", patientService.getPatientById(id));
				return form;
			}
		 
		 @RequestMapping(value = "/view_patient_d", method = RequestMethod.GET)
			public ModelAndView patientViewFormD(@RequestParam int id,HttpSession session) {
				String email = (String) session.getAttribute("email");
				Doctor doctor = doctorService.checkEmail(email);		
				ModelAndView form = new ModelAndView("/doctor/viewPatient");
				form.addObject("doctor", doctor);
				form.addObject("patient", patientService.getPatientById(id));
				return form;
			}
		 
		 @RequestMapping(value = "/view_patient_p", method = RequestMethod.GET)
			public ModelAndView patientViewFormP(@RequestParam int id,HttpSession session) {
				String email = (String) session.getAttribute("email");
				Patient patient = patientService.checkEmail(email);		
				ModelAndView form = new ModelAndView("/patient/viewPatient");
				form.addObject("patient", patient);
				form.addObject("pat", patientService.getPatientById(id));
				return form;
			}
}
