package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Department;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addDepartment(Department department) {
		
		getSession().merge(department);
		
	}

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public void updateDepartment(Department department) {
		getSession().merge(department);
		
	}

	@Override
	public void deleteDepartment(int id) {
		Department department = getDepartmentById(id);
		if (department != null) {
			getSession().delete(department);
		}
	}

	@Override
	public Department getDepartmentById(int id) {
		return (Department) getSession().get(Department.class, id);
	}

	@Override
	public List<Department> getAllDepartment() {
		return getSession().createCriteria(Department.class).list();
	}

	

}
