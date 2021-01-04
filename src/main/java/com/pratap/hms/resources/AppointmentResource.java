package com.pratap.hms.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.service.DoctorService;

@RestController
public class AppointmentResource {

	@Autowired
	private DoctorService doctorService;
	@GetMapping(value="/test")
	public String test(@RequestParam("value") String value) {
		System.out.println(value);
		return value;
	}
	
	
}
