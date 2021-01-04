package com.pratap.hms.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.pratap.hms.model.Message;
import com.pratap.hms.model.Patient;
import com.pratap.hms.model.Prescription;
import com.pratap.hms.service.AppointmentService;
import com.pratap.hms.service.DoctorService;
import com.pratap.hms.service.PatientService;
import com.pratap.hms.service.PrescriptionService;
import com.pratap.hms.util.EmailUtil;
import com.pratap.hms.util.ImageUtil;

@Controller
public class prescriptionController {
	
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private PrescriptionService prescriptionService;
	@Autowired
	private PatientService patientService;

	@RequestMapping(value = "/prescription_form", method = RequestMethod.GET)
	public String prescriptionForm(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		model.addAttribute("doctor", doctor);
		model.addAttribute("appointment", appointmentService.getAcceptedNotPrescripted(doctor));
		
		
		return "/doctor/addPrescription";
	}
	
	@RequestMapping(value = "add_prescription", method = RequestMethod.POST)
	public String addPrescription(@ModelAttribute Prescription prescription, @RequestParam int appointmentId,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		Appointment appointment = appointmentService.getAppointmentById(appointmentId);
		String c= doctor.getFirstName()+" "+doctor.getLastName();
		String d= appointment.getPatient().getFirstName()+" "+appointment.getPatient().getLastName();
		
		if (appointment != null) {
			prescription.setAppointment(appointment);
		}
		prescription.setPrescriptionDate(new Date());
		prescription.setDoctor(c);
		prescription.setPatient(d);
		prescription.setDoctorCharge(doctor.getCharge());
		prescription.setPat(appointment.getPatient());
		prescription.setbAdded("no");
		prescriptionService.addPrescription(prescription);
		appointment.setpAdded("yes");
		appointmentService.updateAppointment(appointment);
		
		
		return "redirect:/prescription_list_d";
	}
	
	@RequestMapping(value = "/prescription_list_d", method = RequestMethod.GET)
	public String getAllPrescription(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		String a= doctor.getFirstName()+" "+doctor.getLastName();
		model.addAttribute("doctor", doctor);
		model.addAttribute("prescription", prescriptionService.getPrescription(a));
		return "/doctor/prescriptionList";
	}
	
	@RequestMapping(value = "/prescription_delete_d", method = RequestMethod.GET)
	public String deletePrescriptionD(@RequestParam int id) {
		prescriptionService.deletePrescription(id);
		return "redirect:/prescription_list_d";
	}
	
	
	
}
