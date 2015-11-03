package com.restsiqs.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restsiqs.DAO.TeacherDAO;
import com.restsiqs.Entities.Teacher;

/*
 * Created by devouty on 2015/10/13.
 * 
 * This service deal with resourses' request that it ask for DAOs' operation to finish resourses' actions.
 */
@Transactional
@Service
public class TeacherService {
	@Autowired
	private TeacherDAO teacherDAO;

	public void save(Teacher t) {
		teacherDAO.save(t);
	}

	public void update(Teacher t) {
		teacherDAO.delete(t);
		teacherDAO.save(t);
	}

	public void delete(String id) {
		teacherDAO.delete(findById(id));
	}

	public Teacher findById(String id) {
		return teacherDAO.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<Teacher> findAll() {
		return (List<Teacher>) teacherDAO.findAll();
	}

}