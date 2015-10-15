package com.restsiqs.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restsiqs.DAO.StudentDAO;
import com.restsiqs.Entities.Student;

/*
 * Created by devouty on 2015/10/13.
 * 
 * This service deal with resourses' request that it ask for DAOs' operation to finish resourses' actions.
 */
@Transactional
@Service
public class StudentService {
	@Autowired
	private StudentDAO studentDAO;

	public void save(Student s) {
		studentDAO.save(s);
	}

	public void update(Student s) {
		studentDAO.save(s);
	}

	public void delete(String id) {
		studentDAO.delete(findById(id));
	}

	public Student findById(String id) {
		return studentDAO.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<Student> findAll() {
		return (List<Student>) studentDAO.findAll();
	}

}
