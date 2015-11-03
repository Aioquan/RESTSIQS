package com.restsiqs.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restsiqs.DAO.AcademyDAO;
import com.restsiqs.Entities.Academy;

/*
 * Created by devouty on 2015/10/13.
 * 
 * This service deal with resourses' request that it ask for DAOs' operation to finish resourses' actions.
 */
@Transactional
@Service
public class AcademyService {
	@Autowired
	private AcademyDAO academyDAO;

	public void save(Academy a) {
		academyDAO.save(a);
	}

	public void update(Academy a) {
		academyDAO.delete(a);
		academyDAO.save(a);
	}

	public void delete(String id) {
		academyDAO.delete(findById(id));
	}

	public Academy findById(String id) {
		return academyDAO.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<Academy> findAll() {
		return (List<Academy>) academyDAO.findAll();
	}

}
