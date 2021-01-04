package com.pratap.hms.service;

import java.util.List;

import com.pratap.hms.model.Department;

public interface DepartmentService {
	void addDepartment(Department department);

	void updateDepartment(Department department);

	void deleteDepartment(int id);

	Department getDepartmentById(int id);

	List<Department> getAllDepartment();

	
		
		
	
}
