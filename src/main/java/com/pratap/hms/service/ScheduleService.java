package com.pratap.hms.service;

import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Schedule;

import java.util.List;

public interface ScheduleService {
	void addSchedule(Schedule schedule);
	
	void updateSchedule(Schedule schedule);
	
	void deleteSchedule(int id);
	
	Schedule getScheduleById(int id);
	
	List <Schedule>getAllSchedule();
	
	List<Schedule> getScheduleDoctor(Doctor doctor);
	
	List<Schedule> findByDoctor(int doctorId);
}