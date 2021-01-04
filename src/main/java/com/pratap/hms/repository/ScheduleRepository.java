package com.pratap.hms.repository;

import java.util.List;

import com.pratap.hms.model.Doctor;
import com.pratap.hms.model.Schedule;


public interface ScheduleRepository {
	
	void addSchedule(Schedule schedule);

	void updateSchedule(Schedule schedule);

	void deleteSchedule(int id);

	Schedule getScheduleById(int id);

	List<Schedule>getAllSchedule();
	
	List<Schedule>getScheduleDoctor(Doctor doctor);
	
	List<Schedule> findByDoctor(int doctorId);
	

}