package com.pratap.hms.service;

import java.util.List;

import com.pratap.hms.model.Notice;

public interface NoticeService {
	void addNotice(Notice notice);

	void updateNotice(Notice notice);

	void deleteNotice(int id);

	Notice getNoticeById(int id);

	List<Notice> getAllNotice();
}
