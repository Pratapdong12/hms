package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Admin;
import com.pratap.hms.model.Doctor;
import com.pratap.hms.repository.DoctorRepository;


@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public void addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		doctorRepository.addDoctor(doctor);
		
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		doctorRepository.updateDoctor(doctor);
	}

	@Override
	public void deleteDoctor(int id) {
		// TODO Auto-generated method stub
		doctorRepository.deleteDoctor(id);
	}

	@Override
	public Doctor getDoctorById(int id) {
		// TODO Auto-generated method stub
		return doctorRepository.getDoctorById(id);
	}

	@Override
	public List<Doctor> getAllDoctor() {
		// TODO Auto-generated method stub
		return doctorRepository.getAllDoctor();
	}

	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return doctorRepository.login(email, password);
	}

	@Override
	public Doctor checkEmail(String email) {
		// TODO Auto-generated method stub
		return doctorRepository.checkEmail(email);
	}
	
	@Override
	public Doctor getDoctorByEmail(String email) {
		// TODO Auto-generated method stub
		return doctorRepository.getDoctorByEmail(email);
	}
	
	@Override
	public List<Doctor> findByDepartment(int departmentId) {
		// TODO Auto-generated method stub
		return doctorRepository.findByDepartment(departmentId);
	}

}
