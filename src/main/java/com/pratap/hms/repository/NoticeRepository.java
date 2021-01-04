package com.pratap.hms.repository;

import java.util.List;

import com.pratap.hms.model.Notice;

public interface NoticeRepository {
	void addNotice(Notice notice);

	void updateNotice(Notice notice);

	void deleteNotice(int id);

	Notice getNoticeById(int id);

	List<Notice>getAllNotice();
}
