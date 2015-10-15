package com.restsiqs.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restsiqs.DAO.CourseDAO;
import com.restsiqs.Entities.Course;

/*
 * Created by devouty on 2015/10/13.
 * 
 * This service deal with resourses' request that it ask for DAOs' operation to finish resourses' actions.
 */
@Transactional
@Service
public class CourseService {
	@Autowired
	private CourseDAO courseDAO;

	public void save(Course c) {
		courseDAO.save(c);
	}

	public void update(Course c) {
		courseDAO.save(c);
	}

	public void delete(String id) {
		courseDAO.delete(findById(id));
	}

	public Course findById(String id) {
		return courseDAO.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<Course> findAll() {
		return (List<Course>) courseDAO.findAll();
	}

}
