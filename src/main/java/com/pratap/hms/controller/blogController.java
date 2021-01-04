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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Blog;
import com.pratap.hms.model.Department;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Patient;
import com.pratap.hms.service.AdminService;
import com.pratap.hms.service.BlogService;
import com.pratap.hms.service.DoctorService;
import com.pratap.hms.util.EmailUtil;
import com.pratap.hms.util.ImageUtil;

@Controller
public class blogController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private BlogService blogService;
	
/////////////////////////////////////////admin
	//////////////////////////////////
	/////////////////////////////////
	@RequestMapping(value="/blog_form",method=RequestMethod.GET)
	public String photoForm(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
	
		return "/admin/addBlog";
	}
	@RequestMapping(value = "add_blog", method = RequestMethod.POST)
	public String addBlogA(@ModelAttribute Blog blog, @RequestParam("image") CommonsMultipartFile file,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		String a= admin.getFirstName()+" "+admin.getLastName();
		String imageUrl = ImageUtil.writeImageToFile(file);
		blog.setImageUrl(imageUrl);
		blog.setPostedBy(a);
		blog.setPublishedDate(new Date());
		blog.setUser("Admin");
		blogService.addBlog(blog);
		
		
		return "redirect:/blog_list_a";
	}
	
	@RequestMapping(value = "/blog_list_a", method = RequestMethod.GET)
	public String getBlog(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		model.addAttribute("Admin", admin);
		model.addAttribute("blog", blogService.getAllBlog());
		return "/admin/blogList";
	}
	
	@RequestMapping(value = "/blog_delete", method = RequestMethod.GET)
	public String deleteBlog(@RequestParam int id) {
		blogService.deleteBlog(id);
		return "redirect:/blog_list_a";
	}
	
	@RequestMapping(value = "/edit_blog_a", method = RequestMethod.GET)
	public ModelAndView blogEditForm(@RequestParam int id,HttpSession session) {
		String email =(String) session.getAttribute("email");
		Admin admin = adminService.getAdminByEmail(email);
		ModelAndView form = new ModelAndView("/admin/editBlog");
		form.addObject("blog", blogService.getBlogById(id));
		form.addObject("Admin", admin);
		return form;
	}
	
	@RequestMapping(value = "/blog_update", method = RequestMethod.POST)
	public String updateBlog(@ModelAttribute Blog blog,@RequestParam("image") CommonsMultipartFile file, HttpSession session) {
		
		blogService.getBlogById(blog.getId());
		String imageUrl = "";
		if (file.getOriginalFilename().isEmpty()) {
			imageUrl = blogService.getBlogById(blog.getId()).getImageUrl();
		} else {
			imageUrl = ImageUtil.writeImageToFile(file);
		}
		blog.setImageUrl(imageUrl);
		blog.setUser("Admin");
		blogService.updateBlog(blog);
		return "redirect:/blog_list_a";
	}
	
	@RequestMapping(value = "/view_blog", method = RequestMethod.GET)
	public ModelAndView blogView(@RequestParam int id,HttpSession session) {
		
		ModelAndView form = new ModelAndView("/viewBlog");
		form.addObject("blog", blogService.getBlogById(id));
		
		return form;
	}
	//////////////////////////////////////doctor
	//////////////////////////////////////////
	////////////////////////////////////////
	/////////////////////////////////////////
	@RequestMapping(value="/blog_form_d",method=RequestMethod.GET)
	public String blogFormD(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		model.addAttribute("doctor", doctor);
	
		return "/doctor/addBlog";
	}
	@RequestMapping(value = "add_blog_d", method = RequestMethod.POST)
	public String addBlogD(@ModelAttribute Blog blog, @RequestParam("image") CommonsMultipartFile file,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.checkEmail(email);
		String a= doctor.getFirstName()+" "+doctor.getLastName();
		String imageUrl = ImageUtil.writeImageToFile(file);
		blog.setImageUrl(imageUrl);
		blog.setPostedBy(a);
		blog.setPublishedDate(new Date());
		blog.setUser("Doctor");
		blogService.addBlog(blog);
		
		
		return "redirect:/blog_list_d";
	}
	
	@RequestMapping(value = "/blog_list_d", method = RequestMethod.GET)
	public String getBlogB(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		model.addAttribute("doctor", doctor);
		model.addAttribute("blog", blogService.getAllBlog());
		return "/doctor/blogList";
	}
	
	@RequestMapping(value = "/blog_delete_d", method = RequestMethod.GET)
	public String deleteBlogD(@RequestParam int id) {
		blogService.deleteBlog(id);
		return "redirect:/blog_list_d";
	}
	
	@RequestMapping(value = "/edit_blog_d", method = RequestMethod.GET)
	public ModelAndView blogEditFormD(@RequestParam int id,HttpSession session) {
		String email =(String) session.getAttribute("email");
		Doctor doctor = doctorService.getDoctorByEmail(email);
		ModelAndView form = new ModelAndView("/doctor/editBlog");
		form.addObject("blog", blogService.getBlogById(id));
		form.addObject("doctor", doctor);
		return form;
	}
	@RequestMapping(value = "/blog_update_d", method = RequestMethod.POST)
	public String updateBlogD(@ModelAttribute Blog blog,@RequestParam("image") CommonsMultipartFile file, HttpSession session) {
		blogService.getBlogById(blog.getId());
		String imageUrl = "";
		if (file.getOriginalFilename().isEmpty()) {
			imageUrl = blogService.getBlogById(blog.getId()).getImageUrl();
		} else {
			imageUrl = ImageUtil.writeImageToFile(file);
		}
		blog.setImageUrl(imageUrl);
		blog.setUser("Doctor");
		blogService.updateBlog(blog);
		return "redirect:/blog_list_d";
	}
}
