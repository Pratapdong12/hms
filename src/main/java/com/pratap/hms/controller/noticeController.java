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
import com.pratap.hms.model.Department;
import com.pratap.hms.model.Notice;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.NoticeService;

@Controller
public class noticeController {
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/notice_form", method = RequestMethod.GET)
	public ModelAndView departmentForm(HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView form = new ModelAndView("/admin/addNotice");
		form.addObject("Admin", admin);
		
		return form;
	}
	@RequestMapping(value = "add_notice", method = RequestMethod.POST)
	public String addDepartment(@ModelAttribute Notice notice,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		notice.setAssignedBy(admin.getFirstName()+" "+admin.getLastName());
		notice.setPublishedDate(new Date());
		noticeService.addNotice(notice);
		return "redirect:/notice_list";
	}

	@RequestMapping(value = "/notice_list", method = RequestMethod.GET)
	public String getAllVehicles(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("notice", noticeService.getAllNotice());
		return "/admin/noticeList";
	}
	
	@RequestMapping(value = "/edit_notice", method = RequestMethod.GET)
	public ModelAndView noticeEditForm(@RequestParam int id,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);		
		ModelAndView form = new ModelAndView("/admin/editNotice");
		form.addObject("Admin", admin);
		form.addObject("notice", noticeService.getNoticeById(id));
		return form;
	}
	
	@RequestMapping(value = "/notice_update", method = RequestMethod.POST)
	public String updateNotice(@ModelAttribute Notice notice,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		notice.setAssignedBy(admin.getFirstName()+" "+admin.getLastName());
		notice.setPublishedDate(new Date());
		noticeService.updateNotice(notice);
		return "redirect:/notice_list";
	}
	
	@RequestMapping(value = "/notice_delete", method = RequestMethod.GET)
	public String deleteNotice(@RequestParam int id) {
		noticeService.deleteNotice(id);
		return "redirect:/notice_list";
	}

}
