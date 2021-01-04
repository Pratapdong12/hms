package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Department;
import com.pratap.hms.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public void addDepartment(Department department) {
		
		departmentRepository.addDepartment(department);
	}

	@Override
	public void updateDepartment(Department department) {
		departmentRepository.updateDepartment(department);
		
	}

	@Override
	public void deleteDepartment(int id) {
		departmentRepository.deleteDepartment(id);
		
	}

	@Override
	public Department getDepartmentById(int id) {
		// TODO Auto-generated method stub
		return departmentRepository.getDepartmentById(id);
	}

	@Override
	public List<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return departmentRepository.getAllDepartment();
	}
	
}
