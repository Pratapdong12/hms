package com.pratap.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratap.hms.model.Notice;
import com.pratap.hms.repository.NoticeRepository;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeRepository noticeRepository;

	@Override
	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeRepository.addNotice(notice);
	}

	@Override
	public void updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeRepository.updateNotice(notice);
	}

	@Override
	public void deleteNotice(int id) {
		// TODO Auto-generated method stub
		noticeRepository.deleteNotice(id);
	}

	@Override
	public Notice getNoticeById(int id) {
		// TODO Auto-generated method stub
		return noticeRepository.getNoticeById(id);
	}

	@Override
	public List<Notice> getAllNotice() {
		// TODO Auto-generated method stub
		return noticeRepository.getAllNotice();
	}
}
