package com.pratap.hms.controller;

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
import com.pratap.hms.model.Department;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Schedule;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.DoctorService;
import com.pratap.hms.service.ScheduleService;
import com.pratap.hms.util.ImageUtil;

@Controller
public class scheduleController {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/schedule_form", method = RequestMethod.GET)
	public String doctorForm(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("doctor", doctorService.getAllDoctor());
		
		return "/admin/addSchedule";
	}
	
	@RequestMapping(value = "add_schedule", method = RequestMethod.POST)
	public String addSchedule(@ModelAttribute Schedule schedule,@RequestParam int doctorId) {
		
		Doctor doctor=doctorService.getDoctorById(doctorId);
		if(doctor !=null){
			schedule.setDoctor(doctor);
		}
		scheduleService.addSchedule(schedule);
		return "redirect:/schedule_list";
	}
	
	@RequestMapping(value="/schedule_list",method= RequestMethod.GET)
	public String getAllSchedule(Model model,HttpSession session){
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("schedule",scheduleService.getAllSchedule());
		return "/admin/scheduleList";
	}
	
	@RequestMapping(value="/schedule_delete",method= RequestMethod.GET)
	public String deleteSchedule(@RequestParam int id) {
		scheduleService.deleteSchedule(id);
		return "redirect:/schedule_list";
	}
	
	@RequestMapping(value = "/edit_schedule", method = RequestMethod.GET)
	public ModelAndView scheduleEditForm(@RequestParam int id,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView form = new ModelAndView("/admin/editSchedule");
		form.addObject("doctor",doctorService.getAllDoctor());
		form.addObject("schedule", scheduleService.getScheduleById(id));
		form.addObject("Admin", admin);
		
		return form;
	}
	
	@RequestMapping(value = "/schedule_update", method = RequestMethod.POST)
	public String updateStudent(@ModelAttribute Schedule schedule, @RequestParam int doctorId) {
		scheduleService.getScheduleById(schedule.getId());
		Doctor doctor = doctorService.getDoctorById(doctorId);
		if(doctor !=null) {
			schedule.setDoctor(doctor);
		}

		
		scheduleService.updateSchedule(schedule);
		return "redirect:/schedule_list";
	}
}
