package com.pratap.hms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Appointment;
import com.pratap.hms.model.Department;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Enquiry;
import com.pratap.hms.model.Patient;
import com.pratap.hms.model.Schedule;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.AppointmentService;
import com.pratap.hms.service.DepartmentService;
import com.pratap.hms.service.DoctorService;
import com.pratap.hms.service.PatientService;
import com.pratap.hms.service.ScheduleService;
import com.pratap.hms.util.EmailUtil;
import com.pratap.hms.util.ImageUtil;

@Controller
public class appointmentController {
	
	private static final Logger log=Logger.getLogger(appointmentController.class);
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private MailSender mailSender;
	
////////////////////////////for the appointmnent form
	@RequestMapping(value = "/appointment_form", method = RequestMethod.GET)
	public String appointmentForm(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Patient patient = patientService.checkEmail(email);
		model.addAttribute("patient", patient);
		model.addAttribute("department", departmentService.getAllDepartment());
	
		return "/patient/addAppointment";
	}
	///////////////////// find soctor detail with the help of departmentId
	@RequestMapping(value = "/loadDoctor/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<List<Doctor>>  loadDoctor(@PathVariable("departmentId") int departmenId) {
		List<Doctor> findByDepartment = doctorService.findByDepartment(departmenId);
		findByDepartment.forEach(ac->{
			System.out.println(ac.getAddress());
		});
		
		return new ResponseEntity<>(findByDepartment,HttpStatus.OK);
		
	}
	/////////////////////////////////////find schedule with the help of doctor id
	@RequestMapping(value = "/loadSchedule/{doctorId}", method = RequestMethod.GET)
	public ResponseEntity<List<Schedule>>  loadSchedule(@PathVariable("doctorId") int doctorId) {
		List<Schedule> findByDoctor = scheduleService.findByDoctor(doctorId);
		findByDoctor.forEach(ac->{
			
		});
		
		return new ResponseEntity<>(findByDoctor,HttpStatus.OK);
		
	}
	//////////////////action for adding appointment
	@RequestMapping(value = "add_appointment", method = RequestMethod.POST)
	public String addAppointment(@ModelAttribute Appointment appointment, @RequestParam int departmentId,@RequestParam int doctorId,@RequestParam int scheduleId,HttpSession session,HttpServletRequest request,Model model) {
		String captcha = session.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		if (captcha.equals(verifyCaptcha)) {////////////verifying captcha 
		String email = (String) session.getAttribute("email");
		Patient patient = patientService.checkEmail(email);
		Department department = departmentService.getDepartmentById(departmentId);
		if (department != null) {
			appointment.setDepartment(department);
		}
		Doctor doctor = doctorService.getDoctorById(doctorId);
		if (doctor != null) {
			appointment.setDoctor(doctor);
		}
		Schedule schedule = scheduleService.getScheduleById(scheduleId);
		if (schedule != null) {
			appointment.setSchedule(schedule);
		}
		
		appointment.setPatient(patient);
		appointment.setStatus("pending");
		appointment.setpAdded("no");
		schedule.setCapacity(schedule.getCapacity()-1);
		scheduleService.updateSchedule(schedule);
		appointmentService.addAppointment(appointment);
		
		
		return "redirect:/appointment_list_p";}
		else {
			model.addAttribute("error","Tnvalid captcha");
			return "redirect:/appointment_form";
		}
	}
	
	///////////////////////////appoint list for patient
	@RequestMapping(value = "/appointment_list_p", method = RequestMethod.GET)
	public String getAppointmentPatient(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Patient patient = patientService.checkEmail(email);
		model.addAttribute("patient", patient);
		model.addAttribute("appointment", appointmentService.getAppointmentPatient(patient));
		return "/patient/appointmentList";
	}
///////////////////////////appoint list for doctor
	@RequestMapping(value = "/appointment_list_d", method = RequestMethod.GET)
	public String getAppointment(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		model.addAttribute("doctor", doctor);
		model.addAttribute("appointment", appointmentService.getAcceptedAppointment(doctor));
		return "/doctor/appointmentList";
	}
	
	///////////////////////////////////accepts appointment 
	@RequestMapping(value = "/appointment_accept", method = RequestMethod.GET)
	public String appointmentAccept(@RequestParam int id) {
		Appointment appointment= appointmentService.getAppointmentById(id);
		appointment.setStatus("accepted ");
		appointmentService.updateAppointment(appointment);
		String msg = "Your appointment is accepted be on time ";
		String msg1 = "You hav been assigned with patient in"+appointment.getSchedule().getScheduleDate()+" ";
		log.debug("debug point for email sending ");
		EmailUtil.sendEmail(mailSender, appointment.getPatient().getEmail(), "Appointment", msg);
		EmailUtil.sendEmail(mailSender, appointment.getDoctor().getEmail(), "Appointment", msg1);
		return "redirect:/appointment_list_a";
	}
	
	//////////////delete appointment by patient
	@RequestMapping(value = "/appointment_delete_p", method = RequestMethod.GET)
	public String deleteAppointmentP(@RequestParam int id) {
		appointmentService.deleteAppointment(id);
		return "redirect:/appointment_list_p";
	}
	
	/////////////////all appointment  list for admin
	@RequestMapping(value = "/allappointment_list_a", method = RequestMethod.GET)
	public String getAllAppointment(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("appointment", appointmentService.getAllAppointment());
		return "/admin/allAppointmentList";
	}
	 
	 ///////////appointment list which shows only pending appointment
	 @RequestMapping(value = "/appointment_list_a", method = RequestMethod.GET)
		public String getPendingAppointment(Model model,HttpSession session) {
			String email = (String) session.getAttribute("email");
			Admin admin = adminService.getAdminByEmail(email);
			model.addAttribute("Admin", admin);
			model.addAttribute("appointment", appointmentService.getpendingAppointmentPatient());
			return "/admin/appointmentList";
		}
}
