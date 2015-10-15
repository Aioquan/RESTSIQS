package com.restsiqs.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restsiqs.DAO.TechnologicalexamDAO;
import com.restsiqs.Entities.Technologicalexam;

/*
 * Created by devouty on 2015/10/13.
 * 
 * This service deal with resourses' request that it ask for DAOs' operation to finish resourses' actions.
 */
@Transactional
@Service
public class TechnologicalexamService {
	@Autowired
	private TechnologicalexamDAO technologicalexamDAO;

	public void save(Technologicalexam t) {
		technologicalexamDAO.save(t);
	}

	public void update(Technologicalexam t) {
		technologicalexamDAO.save(t);
	}

	public void delete(String id) {
		technologicalexamDAO.delete(findById(id));
	}

	public Technologicalexam findById(String id) {
		return technologicalexamDAO.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<Technologicalexam> findAll() {
		return (List<Technologicalexam>) technologicalexamDAO.findAll();
	}

}
