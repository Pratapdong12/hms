package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Schedule;
import com.pratap.hms.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public void addSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		scheduleRepository.addSchedule(schedule);
		
	}

	@Override
	public void updateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		scheduleRepository.updateSchedule(schedule);
	}

	@Override
	public void deleteSchedule(int id) {
		// TODO Auto-generated method stub
		scheduleRepository.deleteSchedule(id);
	}

	@Override
	public Schedule getScheduleById(int id) {
		// TODO Auto-generated method stub
		return scheduleRepository.getScheduleById(id);
	}

	@Override
	public List<Schedule> getAllSchedule() {
		// TODO Auto-generated method stub
		return scheduleRepository.getAllSchedule();
	}

	@Override
	public List<Schedule> getScheduleDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return scheduleRepository.getScheduleDoctor(doctor);
	}
	@Override
	public List<Schedule> findByDoctor(int doctorId) {
		// TODO Auto-generated method stub
		return scheduleRepository.findByDoctor(doctorId);
	}
}