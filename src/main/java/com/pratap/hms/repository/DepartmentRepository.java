package com.pratap.hms.repository;

import com.pratap.hms.model.Department;

import java.util.List;

public interface DepartmentRepository {
	void addDepartment(Department department);

	void updateDepartment(Department department);

	void deleteDepartment(int id);

	Department getDepartmentById(int id);

	List<Department>getAllDepartment();
}
