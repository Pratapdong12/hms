package com.pratap.hms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Department;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.DepartmentService;
import com.pratap.hms.util.ImageUtil;

@Controller
public class departmentController {
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/department_form", method = RequestMethod.GET)
	public ModelAndView departmentForm(HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView form = new ModelAndView("/admin/addDepartment");
		form.addObject("Admin", admin);
		return form;
	}
	
	@RequestMapping(value = "add_department", method = RequestMethod.POST)
	public String addDepartment(@ModelAttribute Department department,@RequestParam("image") CommonsMultipartFile file) {
		String imageUrl = ImageUtil.writeImageToFile(file);
		department.setImageUrl(imageUrl);
		departmentService.addDepartment(department);
		return "redirect:/department_list";
	}

	@RequestMapping(value = "/department_list", method = RequestMethod.GET)
	public String getAllEnquiry(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("department", departmentService.getAllDepartment());
		return "/admin/departmentList";
	}

	@RequestMapping(value = "/department_delete", method = RequestMethod.GET)
	public String deleteDepartment(@RequestParam int id) {
		departmentService.deleteDepartment(id);
		return "redirect:/department_list";
	}
	


	
	@RequestMapping(value = "/edit_department", method = RequestMethod.GET)
	public ModelAndView departmentEditForm(@RequestParam int id,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);		
		ModelAndView form = new ModelAndView("/admin/editDepartment");
		form.addObject("Admin", admin);
		form.addObject("department", departmentService.getDepartmentById(id));
		return form;
	}
	
	
	@RequestMapping(value = "/department_update", method = RequestMethod.POST)
	public String updateDepartment(@ModelAttribute Department department,@RequestParam("image") CommonsMultipartFile file) {
		departmentService.getDepartmentById(department.getId());
		String imageUrl = "";
		if (file.getOriginalFilename().isEmpty()) {
			imageUrl = departmentService.getDepartmentById(department.getId()).getImageUrl();
		} else {
			imageUrl = ImageUtil.writeImageToFile(file);
		}
		
		department.setImageUrl(imageUrl);
		departmentService.updateDepartment(department);
		return "redirect:/department_list";
	}

}
