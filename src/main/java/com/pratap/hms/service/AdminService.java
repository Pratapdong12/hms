package com.pratap.hms.service;

import com.pratap.hms.model.Admin;

public interface AdminService {
	
	void updateAdmin(Admin admin);
	
	boolean login(String email, String password);

	Admin getAdminById(int id);
	
	Admin getAdminByEmail(String email);
}
