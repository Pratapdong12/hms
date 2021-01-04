package com.pratap.hms.service;


import java.util.List;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Doctor;

public interface DoctorService {
	void addDoctor(Doctor doctor);

	void updateDoctor(Doctor doctor);

	void deleteDoctor(int id);

	Doctor getDoctorById(int id);

	List<Doctor> getAllDoctor();

	boolean login(String email, String password);

	Doctor checkEmail(String email);
	
	Doctor getDoctorByEmail(String email);
	
	List<Doctor> findByDepartment(int departmentId);
}