package com.pratap.hms.repository;

import java.util.List;

import com.pratap.hms.model.Admin;


public interface AdminRepository {

	
	void updateAdmin(Admin admin);
	
	Admin getAdminById(int id);
	
	Admin getAdminByEmail(String email);
	
	boolean login(String email, String password);
	
	
}
