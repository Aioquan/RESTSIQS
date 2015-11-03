package com.restsiqs.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restsiqs.DAO.NoticeDAO;
import com.restsiqs.Entities.Notice;

/*
 * Created by devouty on 2015/10/13.
 * 
 * This service deal with resourses' request that it ask for DAOs' operation to finish resourses' actions.
 */
@Transactional
@Service
public class NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;

	public void save(Notice n) {
		noticeDAO.save(n);
	}

	public void update(Notice n) {
		noticeDAO.delete(n);
		noticeDAO.save(n);
	}

	public void delete(String id) {
		noticeDAO.delete(findById(id));
	}

	public Notice findById(String id) {
		return noticeDAO.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<Notice> findAll() {
		return (List<Notice>) noticeDAO.findAll();
	}

}
