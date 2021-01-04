package com.pratap.hms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Appointment;

import com.pratap.hms.model.Bill;
import com.pratap.hms.model.Patient;
import com.pratap.hms.model.Prescription;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.AppointmentService;
import com.pratap.hms.service.BillService;
import com.pratap.hms.service.PatientService;
import com.pratap.hms.service.PrescriptionService;
import com.pratap.hms.util.ImageUtil;

@Controller
public class billController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private PrescriptionService prescriptionService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private BillService billService;
	//////////////////////////////////// helps to show the bill form
	@RequestMapping(value = "/bill_form", method = RequestMethod.GET)
	public String BillForm(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("prescription", prescriptionService.getNoBill());
	
		return "/admin/addBill";
	}
	///////////////////////////////////////used for ajax to get appoinment by patient Id
	@RequestMapping(value = "/loadAppointment/{patientId}", method = RequestMethod.GET)
	public ResponseEntity<List<Appointment>>  loadAppointment(@PathVariable("patientId") int patientId) {
		List<Appointment> acceptedAppointmentBill = appointmentService.acceptedAppointmentBill(patientId);
		acceptedAppointmentBill.forEach(ac->{
			System.out.println(ac.getStatus());
		});
		
		return new ResponseEntity<>(acceptedAppointmentBill,HttpStatus.OK);
		
	}
	

	
	
	///////////////////////////////////////used for ajax to get prescription by prescription Id
	@RequestMapping(value = "/loadPatientByPrescription/{prescriptionId}", method = RequestMethod.GET)
	public ResponseEntity<List<Prescription>>  loadPatientByPrescription(@PathVariable("prescriptionId") int prescritionId) {
		List<Prescription> patientb = prescriptionService.loadPatientByPrescription(prescritionId);
		patientb.forEach(ac->{
			System.out.println(ac.getDoctorCharge());
		});
		
		return new ResponseEntity<>(patientb,HttpStatus.OK);
		
	}
	//////////////////////////////adds bill
	@RequestMapping(value = "add_bill", method = RequestMethod.POST)
	public String addBill(@ModelAttribute Bill bill,@RequestParam int prescriptionId,@RequestParam int doctorCharge,@RequestParam int medicineCharge) {
		
		
		Prescription prescription=prescriptionService.getPrescriptionById(prescriptionId);
		if(prescription !=null) {
			bill.setPrescription(prescription);
		}
		bill.setBillDate(new Date());
		int a=((doctorCharge+medicineCharge)/100)*13;
		int t=doctorCharge+medicineCharge+a;
		bill.setTaxCharge(a);
		bill.setTotalCharge(t);
		
		billService.addBill(bill);
		
		prescription.setbAdded("yes");
		prescriptionService.updatePrescription(prescription);
		
		return "redirect:/bill_list_a";
	}
	/////////////////////bill list for admin
	@RequestMapping(value = "/bill_list_a", method = RequestMethod.GET)
	public String getAllBill(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin",admin);
		model.addAttribute("bill", billService.getAllBill());
		return "/admin/billList";
	}
//////////////////////////////helps to view the bill
	@RequestMapping(value = "/view_bill", method = RequestMethod.GET)
	public ModelAndView billView(@RequestParam int id,HttpSession session) {
		String email =(String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView form = new ModelAndView("/admin/viewBill");
		form.addObject("bill", billService.getBillById(id));
		form.addObject("Admin", admin);
		return form;
	}
	@RequestMapping(value = "/view_bill_p", method = RequestMethod.GET)
	public ModelAndView billViewP(@RequestParam int id,HttpSession session) {
		String email =(String) session.getAttribute("email");
		Patient patient = patientService.checkEmail(email);
		ModelAndView form = new ModelAndView("/patient/viewBill");
		form.addObject("bill", billService.getBillById(id));
		form.addObject("patient", patient);
		return form;
	}
	////////////////printings
	@RequestMapping(value = "/invoice-print", method = RequestMethod.GET)
	public ModelAndView billPrint(@RequestParam int id,HttpSession session) {
		String email =(String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView form = new ModelAndView("/admin/invoice-print");
		form.addObject("bill", billService.getBillById(id));
		form.addObject("Admin", admin);
		return form;
	}
	////////////////////////////////edit bill
	@RequestMapping(value = "/edit_bill", method = RequestMethod.GET)
	public ModelAndView billEditForm(@RequestParam int id,HttpSession session) {
		String email =(String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView form = new ModelAndView("/admin/editBill");
		form.addObject("bill", billService.getBillById(id));
		form.addObject("Admin", admin);
		return form;
	}
	//////////////update bill
	@RequestMapping(value = "/update_bill", method = RequestMethod.POST)
	public String updateBill(@ModelAttribute Bill bill,@RequestParam int patientId,@RequestParam int prescriptionId,@RequestParam int doctorCharge,@RequestParam int medicineCharge) {
		billService.getBillById(bill.getId());
		Patient patient=patientService.getPatientById(patientId);
		if(patient !=null) {
			bill.setPatient(patient);
		}
		Prescription prescription=prescriptionService.getPrescriptionById(prescriptionId);
		if(prescription !=null) {
			bill.setPrescription(prescription);
		}
		int a=((doctorCharge+medicineCharge)/100)*13;
		int t=doctorCharge+medicineCharge+a;
		bill.setTaxCharge(a);
		bill.setTotalCharge(t);
		
		billService.updateBill(bill);
		return "redirect:/bill_list_a";
	}
	/////////////////////////////////////////////////
	@RequestMapping(value = "/bill_list_patient", method = RequestMethod.GET)
	public String getPatientBill(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Patient patient = patientService.checkEmail(email);
		int patientId= patient.getId();
		model.addAttribute("patient",patient);
		model.addAttribute("bill", billService.getPatientBill(patient));
		return "/patient/billList";
	}
	
	@RequestMapping(value = "/bill_delete_a", method = RequestMethod.GET)
	public String deleteBillA(@RequestParam int id) {
		billService.deleteBill(id);
		return "redirect:/bill_list_a";
	}
}
