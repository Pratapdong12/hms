package com.pratap.hms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Admin;
import com.pratap.hms.repository.AdminRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminRepository.updateAdmin(admin);
	}

	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return adminRepository.login(email, password);
	}
	
	@Override
	public Admin getAdminById(int id) {
		// TODO Auto-generated method stub
		return adminRepository.getAdminById(id);
	}
	
	@Override
	public Admin getAdminByEmail(String email) {
		// TODO Auto-generated method stub
		return adminRepository.getAdminByEmail(email);
	}

}
